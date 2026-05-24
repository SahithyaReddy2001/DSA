import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

public class LoadTest {

    // ================= CONFIG =================

    private static final int TOTAL_USERS = 15;
    private static final int RAMP_UP_SECONDS = 20;
    private static final int RETRIES = 2;

    private static final String FILE_PATH = "/root/IN_GCP_LI_Upload_1_500_new.xlsx";

    private static final String CREATE_URL = "https://icat-pp.unilever.com/ccf/createContract";

    private static final String UPLOAD_URL = "https://icat-pp.unilever.com/ccf/upload/";

    private static final String FIND_URL = "https://icat-pp.unilever.com/ccf/findById";

    private static final String VALIDATE_URL = "https://icat-pp.unilever.com/validition/validate";

    private static final Random random = new Random();

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiIxMTY5NiIsInN1YiI6IjIzYmMzMTUyMDY0MzQ1ODE5ZGM5NTE3NWZhNzMwMTFjIiwibmFtZSI6IlVtYW5nIEFnZ2F3YWwiLCJlbWFpbCI6IlVtYW5nLkFnZ2Fyd2FsQHVuaWxldmVyLmNvbSIsImV4cCI6MTc3OTUyMDk0MX0.9jvAYPPY_cmnk4E3qpmBCGd4cMAMTz0yv-aT6ZNFHaUjLS0KCelGf8LNFVROsu7nXBXQ7HzJyt2OrI0WQzpS3Q";

    private static final List<String> Tokens = Arrays.asList("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiIxMTY4NiIsInN1YiI6IjgyNTgyN2FkOWJjMTQzZGU4MjdiYzhkYTExNDI4ZDNjIiwibmFtZSI6IlNhaGl0aHlhIFZhZGl5YWxhIiwiZW1haWwiOiJTYWhpdGh5YS5WYWRpeWFsYUB1bmlsZXZlci5jb20iLCJleHAiOjE3ODA0NzkyMzB9.VRxsF8f8iaYT_WG_UiKz_yLXZuLFnwEIJQJ8QXLfMKgAgC-zNObo1VDg62240OVlG1yv9LLOqMPpHuQ5qeuD_Q",
            "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiIxMTY5NiIsInN1YiI6IjIzYmMzMTUyMDY0MzQ1ODE5ZGM5NTE3NWZhNzMwMTFjIiwibmFtZSI6IlVtYW5nIEFnZ2F3YWwiLCJlbWFpbCI6IlVtYW5nLkFnZ2Fyd2FsQHVuaWxldmVyLmNvbSIsImV4cCI6MTc3OTUyMDk0MX0.9jvAYPPY_cmnk4E3qpmBCGd4cMAMTz0yv-aT6ZNFHaUjLS0KCelGf8LNFVROsu7nXBXQ7HzJyt2OrI0WQzpS3Q");

    private static final String JSESSIONID = "C6922D2498A6D55BE2E3EB4BE8D4FF42";
    private static final List<Result> results = Collections.synchronizedList(new ArrayList<>());

    // ================= RESULTS =================
    private static byte[] FILE_BYTES;

    // ================= MAIN =================

    public static void main(String[] args) throws Exception {
        FILE_BYTES = Files.readAllBytes(Paths.get(FILE_PATH));
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(TOTAL_USERS);
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < TOTAL_USERS; i++) {
            int userId = i;
            futures.add(executor.submit(() -> userFlow(userId)));
            Thread.sleep((RAMP_UP_SECONDS * 1000L) / TOTAL_USERS);
        }
        for (Future<?> future : futures) {
            future.get();
        }
        executor.shutdown();
        long totalTime = System.currentTimeMillis() - start;
        printReport(totalTime);
        log("Script end");
    }

    // ================= USER FLOW =================

    private static void userFlow(int uid) {
        long start = System.currentTimeMillis();
        try {
            String token = Tokens.get(random.nextInt(Tokens.size()));
            // ---------- CREATE ----------
            String contractId = retryCreate(token);
            if (contractId == null) {
                results.add(new Result("FAIL_CREATE", 0, null));
                return;
            }
            Thread.sleep(1500);

            // ---------- UPLOAD ----------
            boolean uploadSuccess = retryUpload(contractId, token);
            if (!uploadSuccess) {
                results.add(new Result("FAIL_UPLOAD", 0, contractId));
                return;
            }
            // ---------- FIND ----------
            String ccf = retryFind(contractId, token);
            if (ccf == null) {
                results.add(new Result("FAIL_FIND", 0, contractId));
                return;
            }

            // ---------- VALIDATE ----------
            boolean validateSuccess = retryValidate(ccf, token);
            if (!validateSuccess) {
                results.add(new Result("FAIL_VALIDATE", 0, contractId));
                return;
            }
            long total = System.currentTimeMillis() - start;
            results.add(new Result("SUCCESS", total, contractId));
            log("[SUCCESS] " + contractId);
        } catch (Exception e) {
            log("[USER ERROR] " + uid + " -> " + e.getMessage());
            results.add(new Result("ERROR", 0, null));
        }
    }

    // ================= CREATE =================

    private static String retryCreate(String token) {
        for (int i = 0; i <= RETRIES; i++) {
            String contractId = createContract(token);
            if (contractId != null) {
                return contractId;
            }
            sleep(i + 1);
        }
        return null;
    }

    private static String createContract(String token) {
        try {
            HttpURLConnection conn = createConnection(CREATE_URL, "application/json", token);
            String payload = buildCreatePayload();
            writeRequest(conn, payload);
            int code = conn.getResponseCode();
            String response = readResponse(conn);
            log("[CREATE STATUS] " + code);
            if (code != 200) {
                log("[CREATE FAILED] " + response);
                return null;
            }
            String contractId = extractValue(response, "contractId");
            log("[CREATE SUCCESS] " + contractId);
            return contractId;
        } catch (Exception e) {
            log("[CREATE ERROR] " + e.getMessage());
            return null;
        }
    }

    // ================= UPLOAD =================

    private static boolean retryUpload(String contractId, String token) {
        for (int i = 0; i <= RETRIES; i++) {
            boolean success = uploadFile(contractId, token);
            if (success) {
                return true;
            }
            sleep(i + 1);
        }
        return false;
    }

    private static boolean uploadFile(String contractId, String token) {
        String boundary = "----Boundary" + System.currentTimeMillis();
        try {
            log("Uploading file for contractId: " + contractId);
            URL url = new URL(UPLOAD_URL + contractId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setConnectTimeout(60_000);     // connection establish timeout
            conn.setReadTimeout(1_800_000);
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            conn.setChunkedStreamingMode(8192);
            addHeaders(conn, token);
            OutputStream output = conn.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
            writer.append("--").append(boundary).append("\r\n");
            writer.append("Content-Disposition: " + "form-data; " + "name=\"file\"; " + "filename=\"IN_GCP_LI_Upload_1_500_new.xlsx\"\r\n");
            writer.append("Content-Type: " + "application/vnd.openxmlformats-" + "officedocument.spreadsheetml.sheet\r\n\r\n");
            writer.flush();
            output.write(FILE_BYTES);
            output.flush();
            writer.append("\r\n");
            writer.append("--").append(boundary).append("--").append("\r\n");
            writer.flush();
            output.close();
            int code = conn.getResponseCode();
            String response = readResponse(conn);
            log("[UPLOAD STATUS] " + contractId + " -> " + code);
            log("[UPLOAD RESPONSE] " + response);
            return response.contains("\"status\":true");
        } catch (Exception e) {
            log("[UPLOAD ERROR] " + contractId + " -> " + e.getMessage());
            return false;
        }
    }

    // ================= FIND =================

    private static String retryFind(String contractId, String token) {
        for (int i = 0; i <= RETRIES; i++) {
            String ccf = findContract(contractId, token);
            if (ccf != null) {
                return ccf;
            }
            sleep(i + 1);
        }
        return null;
    }

    private static String findContract(String contractId, String token) {
        try {
            HttpURLConnection conn = createConnection(FIND_URL, "application/json", token);
            String payload = "{\"id\":\"" + contractId + "\"}";
            writeRequest(conn, payload);
            int code = conn.getResponseCode();
            String response = readResponse(conn);
            log("[FIND STATUS] " + contractId + " -> " + code);
            if (code != 200) {
                return null;
            }
            return response;
        } catch (Exception e) {
            log("[FIND ERROR] " + contractId + " -> " + e.getMessage());
            return null;
        }
    }

    // ================= VALIDATE =================

    private static boolean retryValidate(String ccf, String token) {
        for (int i = 0; i <= RETRIES; i++) {
            boolean success = validateContract(ccf, token);
            if (success) {
                return true;
            }
            sleep(i + 1);
        }
        return false;
    }

    private static boolean validateContract(String ccf, String token) {
        try {
            HttpURLConnection conn = createConnection(VALIDATE_URL, "application/json", token);
            String payload = "{" + "\"validationArray\":[\"FORMAT\",\"MANDATORY\"]," + "\"request\":" + ccf + "}";
            writeRequest(conn, payload);
            int code = conn.getResponseCode();
            String response = readResponse(conn);
            log("[VALIDATE STATUS] " + code);
            log("[VALIDATE RESPONSE] " + response);
            return code == 200 && !response.contains("\"errorResponse\":true");
        } catch (Exception e) {
            log("[VALIDATE ERROR] " + e.getMessage());
            return false;
        }
    }

    // ================= CONNECTION =================

    private static HttpURLConnection createConnection(String url, String contentType, String token) throws Exception {
        URL endpoint = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", contentType);
        conn.setRequestProperty("Accept", "application/json");
        addHeaders(conn, token);
        return conn;
    }

    private static void addHeaders(HttpURLConnection conn, String token) {
        conn.setRequestProperty("token", token);
        conn.setRequestProperty("timezoneId", "Asia/Calcutta");
        conn.setRequestProperty("zone", "-330");
        //conn.setRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID);
    }

    // ================= UTIL =================

    private static void writeRequest(HttpURLConnection conn, String payload) throws Exception {
        OutputStream os = conn.getOutputStream();
        os.write(payload.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
    }

    private static String readResponse(HttpURLConnection conn) throws Exception {
        InputStream stream;
        if (conn.getResponseCode() >= 400) {
            stream = conn.getErrorStream();
        } else {
            stream = conn.getInputStream();
        }
        if (stream == null) {
            return "";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    private static String extractValue(String json, String key) {
        try {
            String pattern = "\"" + key + "\":";
            int start = json.indexOf(pattern);
            if (start == -1) {
                return null;
            }
            start = json.indexOf("\"", start + pattern.length()) + 1;
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        } catch (Exception e) {
            return null;
        }
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (Exception ignored) {
        }
    }

    private static synchronized void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }

    // ================= PAYLOAD =================

    private static String buildCreatePayload() {
        return "{\n" +
                "  \"contractTypeIdentifier\": \"CTC\",\n" +
                "  \"maId\": null,\n" +
                "  \"contractIdentifierName\": \"500 line item 20 user\",\n" +
                "  \"contractGroup\": null,\n" +
                "  \"iCatReferenceNumber\": null,\n" +
                "  \"cloningReferenceNumber\": null,\n" +
                "  \"ccfReferenceNumber\": null,\n" +
                "  \"anaplanHeaderReferenceNumber\": null,\n" +
                "  \"contractOwnerEmail\": \"shalini.singh3@unilever.com\",\n" +
                "  \"contractRequester\": \"Shalini Singh\",\n" +
                "  \"contractRequesterEmail\": \"shalini.singh3@unilever.com\",\n" +
                "  \"contractRequesterId\": 251,\n" +
                "  \"l1Requester\": \"\",\n" +
                "  \"l2Actioner\": \"\",\n" +
                "  \"ritmNumber\": null,\n" +
                "  \"id\": null,\n" +
                "  \"contractOwnerId\": 251,\n" +
                "  \"requestDate\": \"\",\n" +
                "  \"masterAggrementRequestType\": \"New Contract\",\n" +
                "  \"contractName\": \"MERCK TBK PT_2026/Mar\",\n" +
                "  \"contractOwner\": \"Shalini Singh\",\n" +
                "  \"contractCoOwner\": \"\",\n" +
                "  \"coownersList\": [],\n" +
                "  \"cluster\": \"Global\",\n" +
                "  \"country\": \"Multiple Countries\",\n" +
                "  \"alertThresholdLimitPercentage\": 80,\n" +
                "  \"renewalReminderDate\": 1797273000000,\n" +
                "  \"renewalReminderDateObj\": null,\n" +
                "  \"provisionalContract\": false,\n" +
                "  \"sapClmContractReference\": null,\n" +
                "  \"upaNumber\": null,\n" +
                "  \"ctcType\": \"2\",\n" +
                "  \"supplierCode\": \"31554\",\n" +
                "  \"supplierName\": \"MERCK TBK PT\",\n" +
                "  \"parent\": null,\n" +
                "  \"amendmentFlag\": 0,\n" +
                "  \"lineItemCounter\": 0,\n" +
                "  \"contractSupplierAddress\": {\n" +
                "    \"id\": null,\n" +
                "    \"street\": \"JL TB SIMATUPANG NO.8\",\n" +
                "    \"city\": \"JAKARTA\",\n" +
                "    \"zipcode\": \"13760\",\n" +
                "    \"state\": null,\n" +
                "    \"country\": \"ID\",\n" +
                "    \"email\": \"umang@dummy.com\"\n" +
                "  },\n" +
                "  \"contactPerson\": \"umang\",\n" +
                "  \"email\": \"umang@dummy.com\",\n" +
                "  \"phone\": \"+12-13312\",\n" +
                "  \"network\": \"Chemicals\",\n" +
                "  \"portfolio\": \"Fine Chemicals 1( Sian Chan)\",\n" +
                "  \"teamName\": \"Fragrances & Flavours\",\n" +
                "  \"legalEntitySigningContract\": \"Global: 5487 - Unilever North America Supply Chain Company, LLC\",\n" +
                "  \"memberOfLegalSigningEntity\": true,\n" +
                "  \"contractLegalTemplate\": \"CTC 1.0 Product and Price Details with Upstream buy\",\n" +
                "  \"language\": \"English\",\n" +
                "  \"contractingMethod\": \"1\",\n" +
                "  \"contractingCurrency\": null,\n" +
                "  \"contractDescription\": \"Test\",\n" +
                "  \"collaborators\": [],\n" +
                "  \"collaboratorsList\": [\n" +
                "    {\n" +
                "      \"ownerFlag\": true,\n" +
                "      \"type\": \"Individual\",\n" +
                "      \"email\": \"shalini.singh3@unilever.com\",\n" +
                "      \"name\": \"Shalini Singh\",\n" +
                "      \"role\": \"Collaborator\",\n" +
                "      \"clmAccess\": \"read/write\",\n" +
                "      \"silentCollaborator\": false,\n" +
                "      \"requesterFlag\": true\n" +
                "    }\n" +
                "  ],\n" +
                "  \"mechanistic\": false,\n" +
                "  \"contractBoardApproved\": false,\n" +
                "  \"eSourcing\": false,\n" +
                "  \"contractualLiabilitiesExists\": false,\n" +
                "  \"liabilityType\": [],\n" +
                "  \"riskOfExposure\": null,\n" +
                "  \"liabilityDescription\": null,\n" +
                "  \"contractedLiabilityAmount\": null,\n" +
                "  \"mitigationActionsInPlace\": null,\n" +
                "  \"mitigationValue\": null,\n" +
                "  \"rightToBuyFeedstock\": false,\n" +
                "  \"supplierUsqsCompliant\": true,\n" +
                "  \"hybridMaterial\": false,\n" +
                "  \"contractSpendAmount\": 0,\n" +
                "  \"contractsRequireSignature\": true,\n" +
                "  \"reasonForNoSignature\": null,\n" +
                "  \"globalCTCSigned\": null,\n" +
                "  \"costModelCheck\": 3,\n" +
                "  \"contractSupplierTierStatus\": \"1\",\n" +
                "  \"estimatedContractValue\": null,\n" +
                "  \"mostCommonPaymentTerms\": null,\n" +
                "  \"additionalInformation\": null,\n" +
                "  \"additionalInstructionsForCpmo\": null,\n" +
                "  \"netMaterialImpact\": \"0\",\n" +
                "  \"applicableIncoTerms\": null,\n" +
                "  \"effectiveDate\": 1773599400000,\n" +
                "  \"effectiveDateObj\": null,\n" +
                "  \"expirationDate\": 1805049000000,\n" +
                "  \"expirationDateObj\": null,\n" +
                "  \"supplierSystemId\": \"R1PCLNT100\",\n" +
                "  \"headerQuestionull\": {\n" +
                "    \"rootAnswer\": false,\n" +
                "    \"nestedAnswer\": null,\n" +
                "    \"otherDetails\": \"\"\n" +
                "  },\n" +
                "  \"headerQuestionTwo\": {\n" +
                "    \"rootAnswer\": false\n" +
                "  },\n" +
                "  \"headerQuestionThree\": {\n" +
                "    \"rootAnswer\": null,\n" +
                "    \"nestedAnswer\": null\n" +
                "  },\n" +
                "  \"headerQuestionFour\": {\n" +
                "    \"rootAnswer\": null\n" +
                "  },\n" +
                "  \"headerQuestionFive\": {\n" +
                "    \"rootAnswer\": null,\n" +
                "    \"nestedAnswer\": null\n" +
                "  },\n" +
                "  \"leastLiabilityAmount\": \"\",\n" +
                "  \"startDateOfLiability\": \"\",\n" +
                "  \"startDateOfLiabilityObj\": null,\n" +
                "  \"endDateOfLiability\": \"\",\n" +
                "  \"endDateOfLiabilityObj\": null,\n" +
                "  \"items\": [],\n" +
                "  \"deleteFlag\": false,\n" +
                "  \"legalTriggers\": {\n" +
                "    \"renewal\": \"2\",\n" +
                "    \"agreementRelation\": \"1\",\n" +
                "    \"partBQuestion1Options\": \"\",\n" +
                "    \"partBQuestion1checkList\": {},\n" +
                "    \"contactPerson\": null,\n" +
                "    \"email\": null,\n" +
                "    \"phone\": null,\n" +
                "    \"network\": null,\n" +
                "    \"portfolio\": null,\n" +
                "    \"teamName\": null,\n" +
                "    \"legalEntitySigningContract\": null,\n" +
                "    \"memberOfLegalSigningEntity\": false,\n" +
                "    \"contractLegalTemplate\": null,\n" +
                "    \"language\": \"\",\n" +
                "    \"contractingMethod\": \"2\",\n" +
                "    \"contractingCurrency\": null,\n" +
                "    \"contractDescription\": null,\n" +
                "    \"collaborators\": [],\n" +
                "    \"collaboratorsList\": [],\n" +
                "    \"mechanistic\": false,\n" +
                "    \"contractBoardApproved\": false,\n" +
                "    \"eSourcing\": false,\n" +
                "    \"contractualLiabilitiesExists\": null,\n" +
                "    \"liabilityType\": null,\n" +
                "    \"riskOfExposure\": null,\n" +
                "    \"liabilityDescription\": null,\n" +
                "    \"contractedLiabilityAmount\": \"\",\n" +
                "    \"mitigationActionsInPlace\": null,\n" +
                "    \"mitigationValue\": \"\",\n" +
                "    \"rightToBuyFeedstock\": false,\n" +
                "    \"supplierUsqsCompliant\": true,\n" +
                "    \"hybridMaterial\": false,\n" +
                "    \"costModelCheck\": 3,\n" +
                "    \"contractSupplierTierStatus\": \"1\",\n" +
                "    \"estimatedContractValue\": null,\n" +
                "    \"mostCommonPaymentTerms\": null,\n" +
                "    \"additionalInformation\": null,\n" +
                "    \"additionalInstructionsForCpmo\": null,\n" +
                "    \"netMaterialImpact\": null,\n" +
                "    \"applicableIncoTerms\": [],\n" +
                "    \"effectiveDate\": \"\",\n" +
                "    \"effectiveDateObj\": null,\n" +
                "    \"expirationDate\": \"\",\n" +
                "    \"expirationDateObj\": null,\n" +
                "    \"supplierSystemId\": null,\n" +
                "    \"headerQuestionull\": null,\n" +
                "    \"headerQuestionTwo\": null,\n" +
                "    \"headerQuestionThree\": null,\n" +
                "    \"headerQuestionFour\": null,\n" +
                "    \"headerQuestionFive\": null,\n" +
                "    \"leastLiabilityAmount\": \"\",\n" +
                "    \"startDateOfLiability\": \"\",\n" +
                "    \"startDateOfLiabilityObj\": null,\n" +
                "    \"endDateOfLiability\": \"\",\n" +
                "    \"endDateOfLiabilityObj\": null,\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"id\": null,\n" +
                "        \"lineItemId\": null,\n" +
                "        \"lineItemValue\": null,\n" +
                "        \"localLineItemValue\": null,\n" +
                "        \"incotermId\": null,\n" +
                "        \"unitPriceBeginDate\": \"2026-03-16T09:02:54.507Z\",\n" +
                "        \"unitPriceBeginDateObj\": 1543622400000,\n" +
                "        \"unitPriceExpirationDate\": \"2026-03-16T09:02:54.507Z\",\n" +
                "        \"unitPriceExpirationDateObj\": null,\n" +
                "        \"deliveryLeadTime\": null,\n" +
                "        \"moq\": null,\n" +
                "        \"paymentTermsCode\": null,\n" +
                "        \"paymentTermsDescription\": null,\n" +
                "        \"materialId\": null,\n" +
                "        \"materialGroupId\": null,\n" +
                "        \"plantId\": null,\n" +
                "        \"supplierCode\": null,\n" +
                "        \"quantity\": null,\n" +
                "        \"uom\": null,\n" +
                "        \"unitPrice\": null,\n" +
                "        \"unitPriceCurrency\": null,\n" +
                "        \"priceUom\": null,\n" +
                "        \"materialDescription\": null,\n" +
                "        \"tradeName\": null,\n" +
                "        \"materialGroupDescription\": null,\n" +
                "        \"itemType\": null,\n" +
                "        \"comments\": null,\n" +
                "        \"quantityUnit\": null,\n" +
                "        \"quantityUnitDescription\": null,\n" +
                "        \"unitPriceScaleRange\": null,\n" +
                "        \"supplierName\": null,\n" +
                "        \"sapUpcType\": null,\n" +
                "        \"taxCode\": null,\n" +
                "        \"personResponsibleSapId\": null,\n" +
                "        \"companyCode\": null,\n" +
                "        \"companyName\": null,\n" +
                "        \"country\": null,\n" +
                "        \"purchasingOrganisationId\": null,\n" +
                "        \"lineItemComments\": null,\n" +
                "        \"purchasingGroupId\": null,\n" +
                "        \"plantName\": null,\n" +
                "        \"sapSystem\": null,\n" +
                "        \"systemId\": null,\n" +
                "        \"itemValue\": null,\n" +
                "        \"pricingCondition\": [],\n" +
                "        \"priceScales\": [],\n" +
                "        \"priceValidity\": [],\n" +
                "        \"creationDate\": null,\n" +
                "        \"incotermName\": null,\n" +
                "        \"incotermLocation\": null,\n" +
                "        \"originCountryCity\": null,\n" +
                "        \"supplierSiteId\": null,\n" +
                "        \"supplierType\": null,\n" +
                "        \"priceMasked\": null,\n" +
                "        \"maskedPrice\": null,\n" +
                "        \"maskedMechanism\": null,\n" +
                "        \"cmSupplier\": null,\n" +
                "        \"materialSupplier\": null,\n" +
                "        \"inboundLogisticPrice\": null,\n" +
                "        \"materialPackType\": null,\n" +
                "        \"localDutyCost\": null,\n" +
                "        \"accessorialCost\": null,\n" +
                "        \"goodsType\": null\n" +
                "      }\n" +
                "    ],\n" +
                "    \"upstreamLineItems\": [\n" +
                "      {\n" +
                "        \"id\": null,\n" +
                "        \"lineItemId\": null,\n" +
                "        \"ccfReferenceNumber\": null,\n" +
                "        \"materialId\": null,\n" +
                "        \"materialDescription\": null,\n" +
                "        \"plantId\": null,\n" +
                "        \"plantName\": null,\n" +
                "        \"country\": null,\n" +
                "        \"network\": null,\n" +
                "        \"paymentTermsCode\": null,\n" +
                "        \"paymentTermsDescription\": null,\n" +
                "        \"quantity\": null,\n" +
                "        \"commercialUom\": null,\n" +
                "        \"availableQuantity\": null,\n" +
                "        \"quantityUnit\": null,\n" +
                "        \"quantityUnitDescription\": null,\n" +
                "        \"unitPrice\": null,\n" +
                "        \"unitPriceCurrency\": null,\n" +
                "        \"priceUom\": null,\n" +
                "        \"incotermId\": null,\n" +
                "        \"incotermLocation\": null,\n" +
                "        \"incotermName\": null,\n" +
                "        \"tradeName\": null,\n" +
                "        \"moq\": null,\n" +
                "        \"sapSystem\": null,\n" +
                "        \"systemId\": null,\n" +
                "        \"deliveryLeadTime\": null,\n" +
                "        \"downstreamContractCount\": null,\n" +
                "        \"creationDate\": null,\n" +
                "        \"modificationDate\": null,\n" +
                "        \"temporaryId\": null,\n" +
                "        \"isDeleted\": false,\n" +
                "        \"consumed\": false,\n" +
                "        \"specId\": null,\n" +
                "        \"specDescription\": null,\n" +
                "        \"lineItemValue\": null,\n" +
                "        \"localLineItemValue\": null\n" +
                "      }\n" +
                "    ],\n" +
                "    \"deleteFlag\": false,\n" +
                "    \"legalTriggers\": {\n" +
                "      \"renewal\": \"\",\n" +
                "      \"agreementRelation\": \"\",\n" +
                "      \"partBQuestion1Options\": \"\",\n" +
                "      \"partBQuestion1checkList\": {},\n" +
                "      \"partBQuestion2Options\": \"\",\n" +
                "      \"partBQuestion2checkList\": {},\n" +
                "      \"partBQuestion3Options\": \"\",\n" +
                "      \"partBQuestion3checkList\": {},\n" +
                "      \"partBQuestion4Options\": \"\",\n" +
                "      \"partBQuestion5Options\": \"\",\n" +
                "      \"legalReviewRequired\": false\n" +
                "    },\n" +
                "    \"partBQuestion3Options\": \"\",\n" +
                "    \"partBQuestion3checkList\": {},\n" +
                "    \"partBQuestion4Options\": \"\",\n" +
                "    \"partBQuestion5Options\": \"\",\n" +
                "    \"legalReviewRequired\": false,\n" +
                "    \"partBQuestion2Options\": null\n" +
                "  },\n" +
                "  \"additionalDetails\": {\n" +
                "    \"upstream\": false,\n" +
                "    \"upstreamDocument\": null,\n" +
                "    \"quotaAllocation\": false,\n" +
                "    \"quotaAllocationDocument\": null,\n" +
                "    \"manualContractDocument\": false,\n" +
                "    \"manualContractAttachment\": null,\n" +
                "    \"markAsEmergency\": false,\n" +
                "    \"otm\": false,\n" +
                "    \"contactName\": null,\n" +
                "    \"emailContact\": null,\n" +
                "    \"phone\": null,\n" +
                "    \"customExecutiveSummary\": null,\n" +
                "    \"additionalDocumentsFlag\": null\n" +
                "  },\n" +
                "  \"contractStatus\": null,\n" +
                "  \"creationDate\": null,\n" +
                "  \"tempCcfReferenceNumber\": null,\n" +
                "  \"isValidationActive\": false,\n" +
                "  \"reasonForDelay\": \"Late Negotiation/Awaiting cost from supplier\",\n" +
                "  \"v1SubmissionTime\": null,\n" +
                "  \"parentLinkedContractList\": [],\n" +
                "  \"amountReservedFromNiContract\": null,\n" +
                "  \"niContractRemainingValue\": null,\n" +
                "  \"contractScope\": \"Global\",\n" +
                "  \"buyingCategory\": \"Chemicals Fine Chemicals 1( Sian Chan)\",\n" +
                "  \"sourceListDate\": 1773654827835,\n" +
                "  \"signingViaDigitalSignature\": true,\n" +
                "  \"vatCoeff\": null,\n" +
                "  \"frameworkAgreementNumber\": null,\n" +
                "  \"tenderName\": null,\n" +
                "  \"rubAdditionalDetailComment\": null,\n" +
                "  \"cmClassification\": null,\n" +
                "  \"cmSiteClassification\": null,\n" +
                "  \"divisionMapping\": null,\n" +
                "  \"hybridCM\": null,\n" +
                "  \"upaDescription\": null,\n" +
                "  \"upaEffectiveDate\": null,\n" +
                "  \"upaExpirationDate\": null,\n" +
                "  \"priceDetermination\": \"1\",\n" +
                "  \"priceAdjustmentPeriod\": \"Half-yearly\",\n" +
                "  \"usefulLifeOfAsset\": null,\n" +
                "  \"companyAcquiringContract\": null,\n" +
                "  \"autoRenewalClause\": null,\n" +
                "  \"purchaseLeaseItems\": null,\n" +
                "  \"specificTerminationClause\": null,\n" +
                "  \"leasePaymentRate\": null,\n" +
                "  \"fixedLiabilityRate\": null,\n" +
                "  \"paymentPeriod\": null,\n" +
                "  \"commencementDate\": null,\n" +
                "  \"valueOfCommitment\": null,\n" +
                "  \"ifrsDescription\": null,\n" +
                "  \"contractAccessControl\": \"General Usage\",\n" +
                "  \"upstreamLineItems\": [],\n" +
                "  \"l1RequesterEmail\": \"\",\n" +
                "  \"l1RequesterId\": \"\",\n" +
                "  \"l2ActionerEmail\": \"\",\n" +
                "  \"l2ActionerId\": \"\",\n" +
                "  \"updatedItemsList\": [],\n" +
                "  \"updatedUpstreamItemsList\": []\n" +
                "}";
    }

    // ================= REPORT =================

    private static void printReport(long totalTime) {
        long success = results.stream().filter(r -> "SUCCESS".equals(r.status)).count();
        System.out.println("\n========== LOAD TEST REPORT ==========");
        System.out.println("Users: " + TOTAL_USERS);
        System.out.println("Success: " + success);
        System.out.println("Failures: " + (results.size() - success));
        System.out.println("Total Time: " + totalTime + "ms");
        System.out.println("\n========== SUCCESS CONTRACT IDs ==========");
        for (Result r : results) {
            if ("SUCCESS".equals(r.status)) {
                System.out.println(r.contractId + " -> " + r.time + "ms");
            }
        }
    }

    // ================= RESULT =================

    static class Result {
        String status;
        long time;
        String contractId;
        Result(String status, long time, String contractId) {
            this.status = status;
            this.time = time;
            this.contractId = contractId;
        }
    }
}