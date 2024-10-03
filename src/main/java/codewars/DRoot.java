package codewars;

public class DRoot {
    public static int digital_root(int n) {
        // ...
        String s = String.valueOf(n);
        String[] stringsNums = s.split("");
        int sum = 0;
        int[] nums = new int[stringsNums.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(stringsNums[i]);
            sum += nums[i];
        }

        if (String.valueOf(sum).split("").length >= 2) {
            sum = digital_root(sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        int s = 546;
        int result = DRoot.digital_root(s);
        System.out.println(result);
    }
}
