import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node> {
        int start;
        int dest;
        int cost;

        public Node(int start, int dest, int cost) {
            this.start = start;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] parent;
    static int n, m, minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        minCost = 0;

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(a, b, cost));
        }
        int edgeCount = 0;
        while (!pq.isEmpty() && edgeCount < n - 1) {
            Node node = pq.poll();
            if (find(node.start) != find(node.dest)) {
                minCost += node.cost;
                union(node.start, node.dest);
                edgeCount++;
            }
        }
        System.out.println(minCost);
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        parent[b] = a;
    }
}
