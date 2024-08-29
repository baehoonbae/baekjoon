import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 4386. 별자리 만들기 / 골드3 / 9:40 ~ 10:06
// 최소 스패닝 트리 구현 문제
// 별자리마다 간선의 비용(거리)이 주어지지 않음
// 각 별자리마다의 길이를 모두 계산하고 저장해야한다.
// 그렇게 만들어진 노드와 간선으로 최소 스패닝 트리를 구현하기!
public class Main {
    public static class Node implements Comparable<Node> {
        int start;
        int end;
        double cost;

        public Node(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static double[][] arr;
    static int[] parent;
    static int[] rank;
    static int n;
    static double minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new double[n][2];
        parent = new int[n];
        rank = new int[n];
        minCost = 0.0;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            arr[i][0] = y;
            arr[i][1] = x;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                double cost = calDist(arr[i][0], arr[i][1], arr[j][0], arr[j][1]);
                pq.add(new Node(i, j, cost));
            }
        }
        int edgeCount = 0;
        while (!pq.isEmpty() && edgeCount < n - 1) {
            Node node = pq.poll();
            if (find(node.start) != find(node.end)) {
                minCost += node.cost;
                union(node.start, node.end);
                edgeCount++;
            }
        }
        System.out.printf("%.2f", minCost);
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

    public static double calDist(double y, double x, double ty, double tx) {
        return Math.sqrt(Math.pow(ty - y, 2) + Math.pow(tx - x, 2));
    }
}
