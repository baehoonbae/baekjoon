import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static final int INF = Integer.MAX_VALUE;
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static char[][] arr;
    static int[][] dist;
    static int n, m;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new char[n][m];
            dist = new int[n][m];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    if (s.charAt(j) == 'W') {
                        q.add(new int[]{i, j});
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = INF;
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] pos = q.poll();
                int y = pos[0];
                int x = pos[1];
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                        continue;
                    }
                    if (dist[ny][nx] > dist[y][x] + 1) {
                        dist[ny][nx] = dist[y][x] + 1;
                        q.add(new int[]{ny, nx});
                    }
                }
            }
            int minCost = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    minCost += dist[i][j];
                }
            }
            sb.append(minCost).append("\n");
        }
        System.out.print(sb);
    }
}
