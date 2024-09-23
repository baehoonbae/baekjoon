import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] dp = new String[1001];
        dp[0] = "CY";
        dp[1] = "SK";
        dp[2] = "CY";
        dp[3] = "SK";
        int n = new Scanner(System.in).nextInt();
        for (int i = 4; i <= n; i++) {
            if (dp[i - 1] == "CY" || dp[i - 3] == "CY" || dp[i - 4] == "CY") {
                dp[i] = "SK";
            } else {
                dp[i] = "CY";
            }
        }
        System.out.print(dp[n]);
    }
}