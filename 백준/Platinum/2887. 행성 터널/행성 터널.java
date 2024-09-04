import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 2887. 행성 터널 / 플래5 / 11:43 ~ 1:45
// 각 정점의 시작점,끝점,비용을 다 구하고 최소스패닝트리로 연결하자
// 신경써야할 점은 3차원 좌표 위라는 것과 행성이 100_000개 까지 주어진다
// => 브루트포스로 다 구해버리면(크루스칼이든 프림이든) 무조건 시간초과임
// 우선 수많은 간선을 생성하는 것이 아니라, 각 시작지점과 끝지점에 대한 최적의 간선을 찾아야 함
// min(|xA-xB|, |yA-yB|, |zA-zB|)이 간선의 비용이 된다
// x기준으로 정렬, y기준으로 정렬, z기준으로 정렬하면 각 노드 다음에 있는 노드로 연결하는 간선이 가장 최적의 비용이 됨
// 따라서 그거에 따라 간선들을 모두 추가한 후 크루스칼하면 풀릴 듯 하다
public class Main {
    public static class Node implements Comparable<Node> {
        int start, end, cost;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        parent = new int[n];
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][3] = parent[i] = i;
        }
        int[][] xSorted = new int[n][2];
        int[][] ySorted = new int[n][2];
        int[][] zSorted = new int[n][2];
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            xSorted[i][0] = arr[i][0];
            xSorted[i][1] = arr[i][3];
        }
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            ySorted[i][0] = arr[i][1];
            ySorted[i][1] = arr[i][3];
        }
        Arrays.sort(arr, (a, b) -> a[2] - b[2]);
        for (int i = 0; i < n; i++) {
            zSorted[i][0] = arr[i][2];
            zSorted[i][1] = arr[i][3];
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            int xStart = xSorted[i][1];
            int xEnd = xSorted[i + 1][1];
            int xCost = Math.abs(xSorted[i + 1][0] - xSorted[i][0]);

            int yStart = ySorted[i][1];
            int yEnd = ySorted[i + 1][1];
            int yCost = Math.abs(ySorted[i + 1][0] - ySorted[i][0]);

            int zStart = zSorted[i][1];
            int zEnd = zSorted[i + 1][1];
            int zCost = Math.abs(zSorted[i + 1][0] - zSorted[i][0]);

            pq.add(new Node(xStart, xEnd, xCost));
            pq.add(new Node(yStart, yEnd, yCost));
            pq.add(new Node(zStart, zEnd, zCost));
        }
        int minDist = 0;
        int edgeCount = 0;
        while (!pq.isEmpty() && edgeCount < n - 1) {
            Node node = pq.poll();
            if (find(node.start) != find(node.end)) {
                union(node.start, node.end);
                minDist += node.cost;
                edgeCount++;
            }
        }
        System.out.println(minDist);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        parent[b] = a;
    }

}