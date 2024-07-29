import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// 10826. 피보나치 수 / / 10분
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if (n == 0) {
			System.out.println(0);
			return;
		}
		if (n == 1) {
			System.out.println(1);
			return;
		}
		BigInteger[] dp = new BigInteger[n + 1];
		dp[0] = BigInteger.valueOf(0L);
		dp[1] = BigInteger.valueOf(1L);
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 2].add(dp[i - 1]);
		}
		System.out.println(dp[n]);
	}
}
