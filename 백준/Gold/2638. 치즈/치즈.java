import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2638. 치즈 / 골드3 / 10:27 ~ 10:45
// 필요 로직
// 1. 외부 2변 이상 닿은 변 판단
// 2. 2변 이상 닿은 것 기준으로 외부/내부 분리
// 3. 시간이 지남(1초)에 따라 2변이상 닿은 변을 없애기
public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int n, m;
    static int[][] arr;
    static List<int[]> eraseList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        eraseList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        while (!check()) {
            floodFill();
            erase();
            time++;
        }
        System.out.println(time);
    }

    private static boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void floodFill() {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        arr[0][0] = 2;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int y = pos[0];
            int x = pos[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx] || arr[ny][nx] == 1) {
                    continue;
                }
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
                arr[ny][nx] = 2;
            }
        }
    }

    private static void erase() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (arr[ny][nx] == 2) {
                            count++;
                        }
                    }
                    if (count >= 2) {
                        eraseList.add(new int[]{i, j});
                    }
                }
            }
        }
        for (int[] pos : eraseList) {
            arr[pos[0]][pos[1]] = 0;
        }
    }
}