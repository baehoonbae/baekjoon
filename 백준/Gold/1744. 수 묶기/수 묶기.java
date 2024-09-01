import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 1744. 수 묶기 / 골드4 / 5:53~6:54
// 우선순위 큐 사용
public class Main {
    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        PriorityQueue<Integer> minusQ = new PriorityQueue<>();
        PriorityQueue<Integer> plusQ = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] <= 0) {
                minusQ.offer(arr[i]);
            } else if (arr[i] > 1) {
                plusQ.offer(arr[i]);
            } else {
                sum += arr[i];
            }
        }
        if (arr.length == 1) {
            System.out.println(arr[0]);
            return;
        }
        while (minusQ.size() >= 2) {
            sum += minusQ.poll() * minusQ.poll();
        }
        while (plusQ.size() >= 2) {
            sum += plusQ.poll() * plusQ.poll();
        }
        while (!minusQ.isEmpty()) {
            sum += minusQ.poll();
        }
        while (!plusQ.isEmpty()) {
            sum += plusQ.poll();
        }
        System.out.println(sum);
    }
}
