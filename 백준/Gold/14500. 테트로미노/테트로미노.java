import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int[][] arr;
    static boolean[][] visited;
    static int n, m, maxVal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
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
                visited[i][j] = true;
                ㅋㅋㅋㅋㅋ(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(maxVal);
    }

    public static void ㅋㅋㅋㅋㅋ(int y, int x, int depth, int value) {
        if (depth == 4) {
            maxVal = Math.max(maxVal, value);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx]) {
                continue;
            }
            visited[ny][nx] = true;
            ㅋㅋㅋㅋㅋ(ny, nx, depth + 1, value + arr[ny][nx]);
            visited[ny][nx] = false;
        }
        // ㅗ
        if (y - 1 >= 0 && x - 1 >= 0 && x + 1 < m) {
            int num = arr[y][x] + arr[y - 1][x] + arr[y][x - 1] + arr[y][x + 1];
            maxVal = Math.max(maxVal, num);
        }
        // ㅏ
        if (y - 1 >= 0 && x + 1 < m && y + 1 < n) {
            int num = arr[y][x] + arr[y - 1][x] + arr[y + 1][x] + arr[y][x + 1];
            maxVal = Math.max(maxVal, num);
        }
        // ㅜ
        if (y + 1 < n && x - 1 >= 0 && x + 1 < m) {
            int num = arr[y][x - 1] + arr[y][x] + arr[y][x + 1] + arr[y + 1][x];
            maxVal = Math.max(maxVal, num);
        }
        // ㅓ
        if (y - 1 >= 0 && x - 1 >= 0 && y + 1 < n) {
            int num = arr[y][x - 1] + arr[y - 1][x] + arr[y][x] + arr[y + 1][x];
            maxVal = Math.max(maxVal, num);
        }
    }
}
