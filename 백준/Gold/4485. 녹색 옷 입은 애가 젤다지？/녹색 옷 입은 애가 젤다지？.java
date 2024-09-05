import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE - 1111111;
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int[][] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = "";
        int idx = 0;
        while (!(str = br.readLine()).equals("0")) {
            idx++;
            n = Integer.parseInt(str);
            arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem " + idx + ": " + solve()).append("\n");
        }
        System.out.print(sb);
    }

    private static int solve() {
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, arr[0][0]});
        dist[0][0] = arr[0][0];

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int y = info[0];
            int x = info[1];
            int curDist = info[2];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                    continue;
                }
                if (dist[ny][nx] > arr[ny][nx] + curDist) {
                    dist[ny][nx] = arr[ny][nx] + curDist;
                    q.add(new int[]{ny, nx, dist[ny][nx]});
                }
            }
        }
        return dist[n-1][n-1];
    }
}