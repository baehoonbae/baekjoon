import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1865. 웜홀
// 음수의 가중치(웜홀인 경우)가 주어지니 벨만-포드 알고리즘 사용!
// 구조는 알겠지만 사실 이해는 잘 안된다.. 개념을 더 공부해야 할 듯
public class Main {
    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static final int INF = (int) 1e10;
    static int n, m, worm;
    static ArrayList<ArrayList<Node>> list;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            worm = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            dist = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            for (int i = 0; i < m + worm; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                if (i < m) {
                    list.get(a).add(new Node(b, cost));
                    list.get(b).add(new Node(a, cost));
                } else {
                    list.get(a).add(new Node(b, -cost));
                }
            }
            StringBuilder sb = new StringBuilder();
            boolean check = false;
            for (int i = 1; i <= n; i++) {
                if (isMinusCycle(i)) {
                    check = true;
                    sb.append("YES");
                    break;
                }
            }
            if (!check) {
                sb.append("NO");
            }
            System.out.println(sb);
        }
    }

    public static boolean isMinusCycle(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean isUpdated = false;

        // (정점 개수 - 1)번 최단거리 업데이트 진행.
        // 기본적으로 최단거리 알고리즘은 (정점 개수 - 1)번만 최단거리를 갱신한다.
        // 그래서 만약 (정점 개수)번도 최단거리가 업데이트되고 있다면 음수 사이클이 있다는 것이다
        for (int i = 1; i < n; i++) {
            isUpdated = false;
            for (int j = 1; j <= n; j++) {
                for (Node node : list.get(j)) {
                    if (dist[j] != INF && dist[node.end] > dist[j] + node.cost) {
                        dist[node.end] = dist[j] + node.cost;
                        isUpdated = true;
                    }
                }
            }
            // 최단거리가 업데이트되고 있지 않다면 이미 최상의 경로인 것(동떨어져 있거나)
            if (!isUpdated) break;
        }
        if (isUpdated) {
            for (int i = 1; i <= n; i++) {
                for (Node node : list.get(i)) {
                    if (dist[i] != INF && dist[node.end] > dist[i] + node.cost) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
