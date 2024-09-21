import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, d, maxCount;
    static List<int[]> coord;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        coord = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            coord.add(new int[]{a, b});
        }
        d = Integer.parseInt(br.readLine());
        Collections.sort(coord, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] c : coord) {
            int start = c[0];
            int end = c[1];
            int len = end - d;
            if (start < len) {
                continue;
            }
            while (!pq.isEmpty() && pq.peek() < len) {
                pq.poll();
            }
            pq.add(start);
            maxCount = Math.max(maxCount, pq.size());
        }
        System.out.println(maxCount);
    }
}