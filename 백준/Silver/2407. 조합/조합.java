import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// 2407. 조합 / / 7분
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		BigInteger num = BigInteger.valueOf(1);
		BigInteger div = BigInteger.valueOf(1);
		for (int i = n; i > n - m; i--) {
			num = num.multiply(BigInteger.valueOf(i));
		}
		for (int i = m; i >= 1; i--) {
			div = div.multiply(BigInteger.valueOf(i));
		}
		System.out.println(num.divide(div));
	}
}
