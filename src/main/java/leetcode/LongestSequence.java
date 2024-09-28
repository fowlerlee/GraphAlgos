package leetcode;

import java.util.HashSet;

public class LongestSequence {

    public static int resultsConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int results = 1;

        for (int i : set) {
            if (!set.contains(i - 1)) {
                int count = 1;
                int next = i;

                while (set.contains(next + 1)) {
                    next++;
                    count++;
                }
                results = Math.max(results, count);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        int r = LongestSequence.resultsConsecutive(new int[] { 5, 100, 4, 200, 1, 3, 2 });
        System.out.println(r);
    }
}
