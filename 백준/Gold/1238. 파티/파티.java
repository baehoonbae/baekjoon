import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        // 인자 순서 주의하기!
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    final static int INF = (int) 1e9;
    static int n, m, x, maxDist;
    static int[] dist;
    static ArrayList<ArrayList<Node>> edges = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.get(a).add(new Node(b, cost));
        }
        for (int i = 1; i <= n; i++) {
            maxDist = Math.max(maxDist, dijkstra(i, x) + dijkstra(x, i));
        }
        System.out.println(maxDist);
    }

    public static int dijkstra(int start, int end) {
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            int curVertex = pq.poll().index;
            if (visited[curVertex]) {
                continue;
            }
            visited[curVertex] = true;
            for (Node next : edges.get(curVertex)) {
                if (dist[next.index] > dist[curVertex] + next.cost) {
                    dist[next.index] = dist[curVertex] + next.cost;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }
        return dist[end];
    }
}
