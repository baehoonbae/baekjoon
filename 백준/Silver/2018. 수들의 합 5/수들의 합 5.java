import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 1;
        int sum = 0;
        int left = 1, right = 1;
        while (right <= n) {
            if (sum == n) count++;
            sum += right++;
            while (sum > n) {
                sum -= left++;
            }
        }
        System.out.println(count);
    }
}
