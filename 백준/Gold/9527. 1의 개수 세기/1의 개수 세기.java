import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long a, b;
    static long[] d;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        d = new long[65];
        d[0] = 1;
        for (int i = 1; i < 64; i++) {
            d[i] = 2 * d[i - 1] + (1L << i);
        }
        System.out.println(play(b) - play(a - 1));
    }

    private static long play(long x) {
        long answer = x & 1;
        int i = 63;
        for (; i > 0; i--) {
            if ((x & (1L << i)) > 0) {
                answer += d[i - 1] + (x - (1L << i) + 1);
                x -= 1L << i;
            }
        }
        return answer;
    }
}