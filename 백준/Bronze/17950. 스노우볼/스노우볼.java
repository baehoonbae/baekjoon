import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mod = 1_000_000_007;
        long sum = 0;
        long pow = 1;
        int h = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= h; i++) {
            int snowBall = Integer.parseInt(br.readLine());
            pow = (pow * x) % mod;
            sum += (pow * snowBall) % mod;
            sum %= mod;
        }
        System.out.println(sum);
    }
}