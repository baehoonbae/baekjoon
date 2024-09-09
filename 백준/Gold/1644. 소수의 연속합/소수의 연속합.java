import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 1644. 소수의 연속합 / 골드3 / 9:59~ 10:21
// 에라토스테네스 체 구현
// 소수 연속합은 슬라이딩 윈도우나 투포인터로 ㄱㄱ
public class Main {
    static final int MAX = 4_000_000;
    static boolean[] era = new boolean[MAX + 1];
    static List<Integer> primes;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        makeEra();
        primes = new ArrayList<>();
        int count = 0;
        int total = 0;
        int left = 0;
        for (int i = 2; i < era.length; i++) {
            if(!era[i]) primes.add(i);
        }
        for (int right = 0; right < primes.size(); right++) {
            total += primes.get(right);
            while (total >= n && left <= right) {
                if (total == n) {
                    count++;
                }
                total -= primes.get(left);
                left++;
            }
        }
        System.out.println(count);
    }

    private static void makeEra() {
        for (int i = 2; i < Math.sqrt(MAX); i++) {
            if (era[i]) continue;
            for (int j = i * 2; j < era.length; j += i) {
                era[j] = true;
            }
        }
    }
}