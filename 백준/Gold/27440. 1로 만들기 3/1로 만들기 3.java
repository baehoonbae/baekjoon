import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 27440. 1로 만들기 3 / 골드4 / 8:33~8:48
public class Main {
    static final int INF = 1234567891;
    static long n, minCount = INF;
    static Map<Long, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        bt(n, 0);
        System.out.println(minCount);
    }

    public static void bt(long num, int count) {
        if (count >= minCount) {
            return;
        }
        if (num == 1) {
            minCount = Math.min(minCount, count);
            return;
        }
        if (cache.containsKey(num) && cache.get(num) <= count) {
            return;
        }
        cache.put(num, count);
        if (num % 3 == 0) {
            bt(num / 3, count + 1);
        }
        if (num % 2 == 0) {
            bt(num / 2, count + 1);
        }
        bt(num - 1, count + 1);
    }
}
