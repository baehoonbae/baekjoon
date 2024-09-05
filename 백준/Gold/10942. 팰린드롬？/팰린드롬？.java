import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 10942. 팰린드롬? / 골드4 / 10:40~ 10~50
// 그냥 브루트포스로도 풀려서 dp로 한번..
public class Main {
    static int n, m;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1][n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        // 홀수 길이일 때
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
            int left = i - 1;
            int right = i + 1;
            while (left >= 1 && right <= n && arr[left] == arr[right]) {
                dp[left--][right++] = 1;
            }
        }
        // 짝수 길이일 때
        for (int i = 1; i <= n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = 1;
                int left = i - 1;
                int right = i + 2;
                while (left >= 1 && right <= n && arr[left] == arr[right]) {
                    dp[left--][right++] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(dp[left][right]).append("\n");
        }
        System.out.print(sb);
    }
}