import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1647. 도시 분할 계획 / 골드4 / 8:55 ~ 9:07
// 최소 스패닝 트리로 도시들을 한번에 연결시키는 문제
// 그러나 두 개의 도시를 최소의 간선으로 연결시켜야 하기 때문에
// 하나의 최소 스패닝 트리에서 가장 비싼 간선 하나를 제거하면 두 개의 최소 스패닝 트리가 나올 것.
// 간선의 수가 정점의 수에 비해 많이 주어진다면 프림 알고리즘이 더 나을 수 있다.
// 시간이 남으면 경로 압축 하자 ->
public class Main {
    public static class Node implements Comparable<Node> {
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int[] parent;
    static int[] rank;
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();
    static int n, m, maxCost, minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        rank = new int[n + 1];
        maxCost = Integer.MIN_VALUE;
        minCost = 0;

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
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
            if (find(node.start) != find(node.end)) {
                maxCost = Math.max(maxCost, node.cost);
                minCost += node.cost;
                union(node.start, node.end);
                edgeCount++;
            }
        }
        System.out.println(minCost - maxCost);
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
        if (a == b) return;
        if (rank[a] < rank[b]) {
            parent[a] = b;
        } else if (rank[a] > rank[b]) {
            parent[b] = a;
        } else {
            parent[b] = a;
            rank[a]++;
        }
    }
}
