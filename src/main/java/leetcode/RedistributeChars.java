package leetcode;

public class RedistributeChars {
    public static boolean makeEqual(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String s = words[0]; // abc
            char[] chars = s.toCharArray();
            
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abc","aabc","bc"};
        boolean val = RedistributeChars.makeEqual(words);
        if (val) {
            System.out.println("passed test");;
        } else {
            System.err.println("failed test");
        }
    }
}
