package leetcode;

public class RedistributeChars {
    public static boolean makeEqual(String[] words) {
        int charArray[] = new int[26];
        int n = words.length;
        for (int i = 0; i < n; i++) {
            int length = words[i].length();
            for (int j = 0; j < length; j++) {
                char ch = words[i].charAt(j);
                charArray[ch - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (charArray[i] % n != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = new String[] { "abc", "aabc", "bc" };
        boolean val = RedistributeChars.makeEqual(words);
        if (val) {
            System.out.println("passed test");
        } else {
            System.err.println("failed test");
        }
    }
}
