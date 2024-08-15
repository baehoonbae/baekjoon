import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 13172. Σ / 골드4 / 7:37~
// 문제 이해만 하면 정말 쉬운 문젠데.. 내용이 너무 어렵다
//
// 결국 문제에서 말하고자 하는 건 다음과 같다
// 1. 기약분수들끼리 덧셈은 그 수가 많아질수록 통분을 너무 많이 하게된다.
// 2. 통분을 할수록 분모와 분자가 굉장히 커지기 때문에 이를 저장해서 연산하려 하면 금세 오버플로우가 일어날 것이다.
// 3. 그래서 분모를 분모 자체로 저장하는 것이 아니라, 어떤 수 X로 나눈 분모의 나머지를 분모의 역원으로서 특정 정수로 저장하려고 한다. 이때 그 분모의 역원과 분자를 곱한 결과를 X로 나눈 나머지가 1이 되어야 하는 것이 모듈러 곱셈의 역원이다.
// 4. 그리고 그게 어떤 공식으로 정리되어 있는게 바로 페르마의 소정리이다 (소수 한정으로만 가능)
// 5. 페르마의 소정리는 어떤 기약분수 a/b 를 모듈러 곱셈의 역원으로 표현하면 a * (b^(-1) % X) = 1 이고, 여기서 b^(-1) % X = b^(X-2) 이라는 것
// 이 문제에서는 모든 주사위 기댓값(Si/Ni)들을 모듈러 곱셈 역원으로 표현한 뒤 그 값들을 모두 더해서 출력하면 된다
public class Main {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            // 두 수를 최대 공약수로 나눠준다(기약분수로 나타내기 위함)
            int gcd = gcd(Math.max(b, a), Math.min(b, a));
            b /= gcd;
            a /= gcd;

            // a * b^(-1) % X 를 저장하기
            // b^(-1) % X = b^(X-2) 이다(페르마의 소정리, prime number 한정).
            arr[i] = a * inv(b, MOD - 2) % MOD;
            arr[i] %= MOD;
        }
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sum %= MOD;
        }
        System.out.println(sum);
    }

    // 분할 정복으로 빠른 거듭제곱 구하기!
    public static long inv(long b, long power) {
        long res = 1;
        b = b % MOD;
        while (power > 0) {
            if (power % 2 == 1) {
                res *= b;
                res %= MOD;
            }
            b *= b;
            b %= MOD;
            power /= 2;

        }
        return res;
    }

    public static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        int mod = a % b;
        return gcd(b, mod);
    }
}
