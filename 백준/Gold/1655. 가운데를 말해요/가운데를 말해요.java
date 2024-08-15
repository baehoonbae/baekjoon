import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> dpq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (pq.size() == dpq.size()) {
                pq.add(x);
            } else {
                dpq.add(x);
            }
            if (dpq.isEmpty()) {
                sb.append(pq.peek()).append("\n");
                continue;
            }
            if (pq.peek() > dpq.peek()) {
                pq.add(dpq.poll());
                dpq.add(pq.poll());
            }
            sb.append(pq.peek()).append("\n");
        }
        System.out.println(sb);
    }
}