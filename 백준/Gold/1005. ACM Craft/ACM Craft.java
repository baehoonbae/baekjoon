import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1005. ACM Craft / 골드3 / 1:19 ~ 3:55
// 각 노드마다 다음 노드를 연결리스트로 관리
// 다음 노드방문때 건설시간 최대로 갱신ㄱㄱ
// 모든 연결된 노드를 돌아보면 시간초과 나온다.
// 위상정렬 사용
// 진입 차수가 0인 노드들만 관리

public class Main {
    static int[] arr;
    static int[] degree;
    static int[] dp;
    static List<Integer>[] adj;
    static int n, k, w;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new int[n + 1];
            dp = new int[n + 1];
            degree = new int[n + 1];
            adj = new List[n + 1];

            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a].add(b);
                degree[b]++;
            }
            w = Integer.parseInt(br.readLine());
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (degree[i] == 0) {
                    q.add(i);
                    dp[i] = arr[i];
                }
            }
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    dp[next] = Math.max(dp[next], dp[cur] + arr[next]);
                    degree[next]--;
                    if (degree[next] == 0) {
                        q.add(next);
                    }
                }
            }
            sb.append(dp[w]).append("\n");
        }

        System.out.print(sb);
    }
}