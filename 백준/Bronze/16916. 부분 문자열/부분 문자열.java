import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String comp = sc.nextLine();

        System.out.println(kmp(s, comp) ? 1 : 0);
    }

    private static boolean kmp(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] pi = getPi(pattern);
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == m - 1) {
                    return true;
                } else {
                    j++;
                }
            }
        }
        return false;
    }

    public static int[] getPi(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
