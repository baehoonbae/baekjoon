import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1562. 계단 수 / 골드1 / 2:28 ~ 4:09
// 비트마스크에 대한 개념이 아직 부족하다
// 각 수의 길이가 1씩 추가됨에 따라 어떤 수가 추가되었는지를 모두 비트필드로 기록해야 한다
// 이 문제의 경우 10칸의 비트필드(0~9)를 비트마스크하는 기법이 사용됨
public class Main {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][][] dp = new long[n + 1][10][1 << 10];

        // 길이 1일 때 0은 제외
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        // 길이 2 이상인 계단수 확인
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    if (j == 0) {
                        dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
                        dp[i][j][k | (1 << j)] %= MOD;
                    } else if (j == 9) {
                        dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
                        dp[i][j][k | (1 << j)] %= MOD;
                    } else {
                        dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
                        dp[i][j][k | (1 << j)] %= MOD;
                        dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
                        dp[i][j][k | (1 << j)] %= MOD;
                    }
                }
            }
        }
        long sum = 0;
        int full = (1 << 10) - 1;
        for (int i = 0; i <= 9; i++) {
            sum += dp[n][i][full];
            sum %= MOD;
        }
        System.out.println(sum);
    }
}