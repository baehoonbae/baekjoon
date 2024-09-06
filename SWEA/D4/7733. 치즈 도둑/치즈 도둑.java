import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 7733. 치즈 도둑 / D4 / 9:48 ~ 10:15
// 100초까지
// 시간이 1초씩 지날때마다 시간만큼의 칸은 모두 제거(-1로)
// 덩어리 몇개인지 확인
// 갱신
public class Solution {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int n, maxCount;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            maxCount = 0;
            arr = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int time = -1;
            while (time++ <= 100) {
                visited = new boolean[n][n];
                erase(time);
                int count = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (arr[i][j] != -1 && !visited[i][j]) {
                            search(i, j);
                            count++;
                        }
                    }
                }
                maxCount = Math.max(maxCount, count);
            }

            sb.append(maxCount).append("\n");
        }
        System.out.print(sb);
    }

    public static void erase(int time) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == time) {
                    arr[i][j] = -1;
                }
            }
        }
    }

    public static void search(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int y = pos[0];
            int x = pos[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx] || arr[ny][nx] == -1) {
                    continue;
                }
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
            }
        }
    }
}