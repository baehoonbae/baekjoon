import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] p;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        p = new int[n + 1];
        dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Integer.parseInt(st.nextToken());
            p[i + 1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[
                    i], 1234567891);
            dp[i][i] = 0;
        }
//        dp[1][2] = dp[1][1] + dp[2][2] + p[0] * p[1] * p[2]

//        dp[1][3] = dp[1][1] + dp[2][3] + p[0] * p[1] * p[3],
//                   dp[1][2] + dp[3][3] + p[0] * p[2] * p[3]

//        dp[2][3] = dp[2][2] + dp[3][3] + p[1] * p[2] * p[3]

//        dp[1][4] = dp[1][1] + dp[2][4] + p[0] * p[1] * p[4],
//                   dp[1][2] + dp[3][4] + p[0] * p[2] * p[4],
//                   dp[1][3] + dp[4][4] + p[0] * p[3] * p[4]

//        dp[2][4] = dp[2][2] + dp[3][4] + p[1] * p[2] * p[4],
//                   dp[2][3] + dp[4][4] + p[1] * p[3] * p[4]

//        dp[3][4] = dp[3][3] + dp[4][4] + p[2] * p[3] * p[4]
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
                }
            }
        }
        System.out.println(dp[1][n]);
    }
}