package leetcode;

public class LeastLength {

    /*
     * s = "ABFCACDB", "AB", "CD"
     */

    char[] ab = { 'A', 'B' };
    char[] cd = { 'C', 'D' };

    public int minLength(String s) {
        StringBuilder builder = new StringBuilder(s);
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if ((ab[0] == array[i] && ab[1] == array[i + 1]) || (cd[0] == array[i] && cd[1] == array[i + 1])) {
                array[i] = ' ';
                array[i + 1] = ' ';
            }

        }
        return s.length(); // 2
    }
}
