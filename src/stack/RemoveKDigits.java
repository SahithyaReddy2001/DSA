package stack;

public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        int n = num.length();
        if(k==n){
            return "0";
        }

        if(num.charAt(0) == '0'){
            int index = 0;
            for(int i=0; i<n; i++){
                if(num.charAt(i) == '0'){
                    index++;
                }
            }
            num = num.substring(index);
            n = num.length();
        }

        java.util.Stack<Character> stack = new java.util.Stack<Character>();
        for(int i=0; i<n; i++){
            while(!stack.isEmpty() && stack.peek() > num.charAt(i)  && k>0){
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));

        }
        if(k>0){
            while(k>0){
                stack.pop();
                k--;
            }
        }

        String reverseAns = "";
        while(!stack.isEmpty()){
            reverseAns += String.valueOf(stack.pop());
        }

        num = "";
        for(int i=reverseAns.length()-1; i>=0; i--){
            num += String.valueOf(reverseAns.charAt(i));
        }

        if(num.charAt(0) == '0' && num.length()>1){
            int index = 0;
            for(int i=0; i<num.length(); i++){
                if(num.charAt(i) == '0'){
                    index++;
                }else{
                    break;
                }
            }
            num = num.substring(index);
        }
        return num;
    }
}
