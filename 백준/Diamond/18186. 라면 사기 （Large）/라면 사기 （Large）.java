import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 18186. 라면 사기 (Large) / 다이아4 / 8:13 ~ 9: 42
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long one = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long two = one + b;
        long three = one + (2L * b);

        long cost = 0;
        long[] arr = new long[n+3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        if (one >= b) {
            for (int i = 0; i < n; i++) {
                if (arr[i + 1] > arr[i + 2]) {
                    long count = Math.min(arr[i], arr[i + 1] - arr[i + 2]);
                    cost += (two) * count;
                    arr[i] -= count;
                    arr[i + 1] -= count;

                    count = Math.min(arr[i], Math.min(arr[i + 1], arr[i + 2]));
                    cost += (three) * count;
                    arr[i] -= count;
                    arr[i + 1] -= count;
                    arr[i + 2] -= count;
                } else {
                    long count = Math.min(arr[i], Math.min(arr[i + 1], arr[i + 2]));
                    cost += three * count;
                    arr[i] -= count;
                    arr[i + 1] -= count;
                    arr[i + 2] -= count;
                }
                cost += one * arr[i];
                arr[i] = 0;
            }
        } else {
            for (int i = 0; i < n; i++) {
                cost += arr[i] * one;
            }
        }
        System.out.println(cost);
    }
}
