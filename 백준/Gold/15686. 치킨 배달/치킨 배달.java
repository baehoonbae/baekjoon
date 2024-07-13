import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    //    static BufferedWriter bw;
    static int n;
    static int m;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] arr;
    static List<int[]> chickenPos = new ArrayList<>();
    static boolean[] backVisited;
    static int cityChicken = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    chickenPos.add(new int[]{i, j});
                }
            }
        }
        backVisited = new boolean[chickenPos.size()];
        close(0, 0);
        System.out.println(cityChicken);
    }

    public static void close(int here, int idx) {
        int remaining = chickenPos.size() - idx;
        if (here + remaining < m) {
            return;
        }
        if (here == m) {
            int sumChicken = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 1) {
                        sumChicken += bfs(i, j);
                    }
                }
            }
            cityChicken = Math.min(cityChicken, sumChicken);
            return;
        }
        for (int i = idx; i < chickenPos.size(); i++) {
            if (!backVisited[i]) {
                backVisited[i] = true;
                int y = chickenPos.get(i)[0];
                int x = chickenPos.get(i)[1];
                arr[y][x] = 3;
                close(here + 1, i + 1);
                arr[y][x] = 2;
                backVisited[i] = false;
            }
        }
    }

    public static int bfs(int y, int x) {
        boolean[][] visited = new boolean[n][n];
        visited[y][x] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});

        while (!q.isEmpty()) {
            int hy = q.peek()[0];
            int hx = q.poll()[1];
            for (int i = 0; i < 4; i++) {
                int ny = hy + dy[i];
                int nx = hx + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx]) {
                    continue;
                }
                if (arr[ny][nx] == 3) {
                    return Math.abs(y - ny) + Math.abs(x - nx);
                }
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
            }
        }

        return 0;
    }
}
