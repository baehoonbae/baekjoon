import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static final int[] hy = {2, 2, 1, 1, -1, -1, -2, -2};
    static final int[] hx = {-1, 1, -2, 2, -2, 2, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int k, n, m, min;
    static int[][] arr;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        arr = new int[n][m];
        visited = new boolean[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {

                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(0, 0);
        System.out.println(min == INF ? -1 : min);
    }

    // 1. y
    // 2. x
    // 3. 말 사용 횟수
    // 4. 이동 횟수
    private static void bfs(int y, int x) {
        int[][][] minMove = new int[n][m][k + 1];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(minMove[i][j], INF);
            }
        }
        q.add(new int[]{y, x, 0, 0});
        minMove[y][x][0] = 0;
        visited[y][x][0] = true;
        while (!q.isEmpty()) {
            int[] info = q.poll();
            y = info[0];
            x = info[1];
            int jump = info[2];
            int time = info[3];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m || arr[ny][nx] == 1) {
                    continue;
                }
                if (minMove[ny][nx][jump] > time + 1) {
                    minMove[ny][nx][jump] = time + 1;
                    q.add(new int[]{ny, nx, jump, time + 1});
                }
            }
            for (int i = 0; i < 8; i++) {
                int ny = y + hy[i];
                int nx = x + hx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m || jump == k || arr[ny][nx] == 1) {
                    continue;
                }

                if (minMove[ny][nx][jump + 1] > minMove[y][x][jump] + 1) {
                    minMove[ny][nx][jump + 1] = minMove[y][x][jump] + 1;
                    q.add(new int[]{ny, nx, jump + 1, time + 1});
                }
            }
        }
        for (int i = 0; i <= k; i++) {
            min = Math.min(min, minMove[n - 1][m - 1][i]);
        }
    }
}