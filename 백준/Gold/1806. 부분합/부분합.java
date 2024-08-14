import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1806. 부분합 / 골드4 /
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0, left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;

        while (right < n) {
            sum += arr[right++];
            while (sum >= s) {
                minLen = Math.min(minLen, right - left);
                sum -= arr[left++];
            }
        }
        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }
}
