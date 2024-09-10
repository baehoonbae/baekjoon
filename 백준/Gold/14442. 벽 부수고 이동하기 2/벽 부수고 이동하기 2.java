import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE - 5;
    static final int[] dy = {1, -1, 0, 0};
    static final int[] dx = {0, 0, 1, -1};
    static int n, m, k, answer;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        answer = INF;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        play(0, 0);
        System.out.println(answer == (INF + 1) ? -1 : answer);
    }

    //1. y
    //2. x
    //3. broken(벽 부순 횟수)
    //4. dist(현재 거리)
    public static void play(int y, int x) {
        int[][][] minDist = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(minDist[i][j], INF);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x, 0, 0});
        minDist[y][x][0] = 0;
        while (!q.isEmpty()) {
            int[] info = q.poll();
            y = info[0];
            x = info[1];
            int broken = info[2];
            int dist = info[3];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }
                if (broken == k && arr[ny][nx] == 1) {
                    continue;
                }
                if (arr[ny][nx] == 1) {
                    if (minDist[ny][nx][broken] > dist + 1) {
                        minDist[ny][nx][broken] = dist + 1;
                        q.add(new int[]{ny, nx, broken + 1, dist + 1});
                    }
                } else {
                    if (minDist[ny][nx][broken] > dist + 1) {
                        minDist[ny][nx][broken] = dist + 1;
                        q.add(new int[]{ny, nx, broken, dist + 1});
                    }
                }
            }
        }
        for (int i = 0; i <= k; i++) {
            answer = Math.min(answer, minDist[n - 1][m - 1][i]);
        }
        answer++;
    }
}