import java.io.*;
import java.util.StringTokenizer;

// 9466. 텀 프로젝트 / 골드3 / 1:40 ~ 2:13
// union-find?
// dfs로 사이클이 생기는지 확인하고 기록만해주면 되는 문제
public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int ans;
    static int[] parent;
    static boolean[] visited;
    static boolean[] finished;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            ans = 0;
            parent = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
            bw.write((n - ans) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        int next = parent[cur];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            for (int i = next; i != cur; i = parent[i]) {
                ans++;
            }
            ans++;
        }

        finished[cur] = true;
    }
}