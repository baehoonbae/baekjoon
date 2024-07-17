import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[][] arr;
    static boolean[] visited;
    static int n, m, maxValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        visited = new boolean[26];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[i] = s.toCharArray();
        }
        if (n == 1 && m == 1) {
            System.out.println(1);
        } else {
            dfs(0, 0, 0);
            System.out.println(maxValue);
        }
    }

    public static void dfs(int y, int x, int khan) {
        if (visited[arr[y][x] - 'A']) {
            maxValue = Math.max(maxValue, khan);
        } else {
            visited[arr[y][x] - 'A'] = true;
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }

                dfs(ny, nx, khan + 1);
            }
            visited[arr[y][x] - 'A'] = false;
        }
    }
}
