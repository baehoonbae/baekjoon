import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println("0 1");
        } else {
            int a = 0;
            int b = 1;
            int c = a + b;
            for (int i = 2; i <= n; i++) {
                a = b;
                b = c;
                c = a + b;
            }
            System.out.println(a + " " + b);
        }
    }
}
