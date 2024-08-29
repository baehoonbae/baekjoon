import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 2749. 피보나치 수 3 / 골드2 / 10:12 ~10:44
public class Main {
    static final int MOD = 1_000_000;
    static long n;
    static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextLong();
        dp.put(0L, 0L);
        dp.put(1L, 1L);
        dp.put(2L, 1L);
        System.out.println(f(n));
    }

    public static long f(long n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        long result = 0;
        if (n % 2 == 0) {
            result = (f(n / 2 + 1) % MOD * f(n / 2) % MOD)
                    + (f(n / 2) % MOD * f(n / 2 - 1) % MOD);
        } else {
            result = (long) (Math.pow(f((n + 1) / 2) % MOD, 2) % MOD
                    + Math.pow(f((n - 1) / 2) % MOD, 2)) % MOD;
        }
        dp.put(n, result % MOD);
        return dp.get(n);
    }
}
