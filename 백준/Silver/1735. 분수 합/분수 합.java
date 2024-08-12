import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long c = Integer.parseInt(st.nextToken());
        long d = Integer.parseInt(st.nextToken());

        long num1 = a * d + b * c;
        long num2 = b * d;
        for (long i = Math.min(num1, num2) / 2; i >= 2; i--) {
            if (num1 % i == 0 && num2 % i == 0) {
                num1 /= i;
                num2 /= i;
            }
        }
        sb.append(num1).append(" ").append(num2);
        System.out.println(sb);
    }
}