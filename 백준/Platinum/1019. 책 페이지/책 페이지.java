import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] count = new int[10];

    public static void main(String[] args) throws IOException {
        int a = 1;
        int b = Integer.parseInt(br.readLine());
        int factor = 1;
        while (true) {
            while (a % 10 != 0 && a <= b) {
                cal(a, factor);
                a++;
            }
            if (a > b) {
                break;
            }
            while (b % 10 != 9 && a <= b) {
                cal(b, factor);
                b--;
            }
            int cnt = ((b / 10) - (a / 10) + 1);
            for (int i = 0; i <= 9; i++) {
                count[i] += cnt * factor;
            }
            a /= 10;
            b /= 10;
            factor *= 10;
        }
        for (int i = 0; i <= 9; i++) {
            System.out.print(count[i] + " ");
        }
    }

    private static void cal(int n, int factor) {
        while (n > 0) {
            count[n % 10] += factor;
            n /= 10;
        }
    }
}