import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int n, sy, sx, ty, tx;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            visited = new boolean[n][n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            sy = Integer.parseInt(st.nextToken());
            sx = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ty = Integer.parseInt(st.nextToken());
            tx = Integer.parseInt(st.nextToken());
            if (sy == ty && sx == tx) {
                System.out.println(0);
                continue;
            }
            dfs(sy, sx);
        }
    }

    public static void dfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x, 0});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int hy = q.peek()[0];
            int hx = q.peek()[1];
            int depth = q.poll()[2];
            for (int i = 0; i < 8; i++) {
                int ny = hy + dy[i];
                int nx = hx + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx]) {
                    continue;
                }
                if (ny == ty && nx == tx) {
                    System.out.println(depth + 1);
                    return;
                }
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx, depth + 1});
            }
        }
    }
}
