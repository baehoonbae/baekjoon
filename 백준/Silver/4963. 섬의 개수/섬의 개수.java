import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 4963. 섬의 개수 / 실버2 / 5:02 ~ 5:13
// 8방 탐색으로 bfs
// 탐색 끝나는 지점에서 섬 카운트 +1
public class Main {
    static final int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    static final int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[][] arr;
    static boolean[][] visited;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            arr = new int[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && arr[i][j] == 1) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            y = pos[0];
            x = pos[1];
            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx] || arr[ny][nx] == 0) {
                    continue;
                }
                q.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }
    }
}
