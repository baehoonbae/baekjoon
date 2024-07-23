import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static final int MOD = 1_000_000_007;
	static Map<Long, Long> dp = new HashMap<>();

	public static long fib(long n) {
		if (dp.containsKey(n)) {
			return dp.get(n);
		}
		long result = 0;
		if (n % 2 == 0) {
			result = (fib(n / 2) * (fib(n / 2 + 1) + fib(n / 2 - 1))) % MOD;
		} else {
			result = ((fib((n + 1) / 2) * fib((n + 1) / 2)) % MOD)
					+ ((fib((n - 1) / 2) * fib((n - 1) / 2)) % MOD) % MOD;
		}
		dp.put(n, result % MOD);
		return dp.get(n);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dp.put(0l, 0l);
		dp.put(1l, 1l);
		dp.put(2l, 1l);
		long n = Long.parseLong(br.readLine());
		System.out.println(fib(n));
	}
}