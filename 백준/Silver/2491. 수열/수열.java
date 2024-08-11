import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int answer = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if (n == 1) {
            System.out.println(1);
            return;
        }
        boolean isInc = arr[0] < arr[1];
        int left = 0;
        for (int i = 2; i < n; i++) {
            if (isInc && arr[i] < arr[i - 1]) {
                isInc = false;
                answer = Math.max(answer, i - left);
                while (arr[i - 1] > arr[left]) {
                    left++;
                }
            } else if (!isInc && arr[i] > arr[i - 1]) {
                isInc = true;
                answer = Math.max(answer, i - left);
                while (arr[i - 1] < arr[left]) {
                    left++;
                }
            }
        }
        answer = Math.max(answer, n - left);
        sb.append(answer);
        System.out.println(sb);
    }
}