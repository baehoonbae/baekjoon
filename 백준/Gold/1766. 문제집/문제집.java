import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] inDegree;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        inDegree = new int[n + 1];
        adj = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            inDegree[b]++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                pq.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");
            for (int next : adj[cur]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
        System.out.print(sb);
    }
}