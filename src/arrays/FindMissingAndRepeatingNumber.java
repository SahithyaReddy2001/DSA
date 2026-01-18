package arrays;

public class FindMissingAndRepeatingNumber {
    //Q: You will be given array containing 1 to n elements in those find the numbers which are missing and repeating

    /*
    * Brute:
    * Iterate over array end check for each element and store its count
    * If count = 2. It is repeating
    * else its missing
    * TC: O(N^2)
    * SC: O(1)
    * */
    public static void findMissingAndRepeatingNumberBrute(int[] arr){
        int missing = -1;
        int repeating = -1;
        for(int i=1; i<=arr.length; i++){
            int count = 0;
            for(int j=0; j<arr.length; j++){
                if(arr[j] == i) count++;
            }
            if(count==2) repeating = i;
            if(count==0) missing = i;
        }
        System.out.println("Repeating : "+ repeating + " Missing: " + missing);
    }




    /*
    * Better:
    * we will maintain a hash array
    * for every element increase count at index
    * Iterate over the array again to check missing and repeated element
    * TC: O(2N)
    * SC: O(N+1)
    * */
    public static void findMissingAndRepeatingNumberBetter(int[] arr){
        int[] ansArr = new int[arr.length+1];
        int missing = -1;
        int repeating = -1;
        for(int i=0; i<arr.length; i++){
            int index = arr[i];
            ansArr[index] = ansArr[index]+1;
        }
        for(int i=1; i<ansArr.length; i++){
            if(ansArr[i] == 0) missing=i;
            if(ansArr[i] == 2) repeating= i;
            if(missing != -1 && repeating != -1) break;
        }

        System.out.println("Repeating : "+ repeating + " Missing: " + missing);
    }



    /*
    * Optimal 1:
    * Step 1 : sum of all elements in arr - sum of 1st n natural numbers(n)(n+1)/2  -> equation 1 = x-y= ans
    * Step 2 : sum of squares of all elements in arr - sum of squares of 1st n natural numbers (n) (n+1) (2n+1)/6  -> x^2-y^2= ans2
    * x^2-y^2 = (x+y)(x-y)
    * we know the value of x-y
    * equation 2 = x+y = ans2/ans;
    * solve equation 1 and equation 2
    * we will get x and y values x = repeating number and y = missing number
    * TC: O(N)
    * SC: O(1)
    * */
    public static void findMissingAndRepeatingNumberOptimal1(int[] arr){
        int s = ((arr.length) * (arr.length+1))/2;
        int s2 = ((arr.length) * (arr.length+1)* (2 * arr.length+1))/6;
        int missing = -1;
        int repeating = -1;
        int sn = 0;
        int s2n = 0;
        for(int i=0; i<arr.length; i++){
            sn += arr[i];
            s2n += arr[i] * arr[i];
        }
        int temp = (s2n - s2)/(sn-s);
        repeating = (temp + (sn-s))/2;
        missing = temp - repeating;
        System.out.println("Repeating : "+ repeating + " Missing: " + missing);
    }


    //TODO : Optimal 2 using XOR


}
