import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] isPalindrome;
    static int[] dp;
    static int n;

    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        n = s.length();
        isPalindrome = new boolean[n][n];
        dp = new int[n];
        // 홀수 길이일 때
        for (int i = 0; i < n; i++) {
            int left = i, right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                isPalindrome[left][right] = true;
                left--;
                right++;
            }
            left = i; right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                isPalindrome[left][right] = true;
                left--;
                right++;
            }
        }
        for (int i = 0; i < n; i++) {
            dp[i] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            if (isPalindrome[0][i]) {
                dp[i] = 1;
            } else {
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i]) {
                        // j 이전에 분할결과 저장한거 + 1
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        System.out.println(dp[n - 1]);
    }
}