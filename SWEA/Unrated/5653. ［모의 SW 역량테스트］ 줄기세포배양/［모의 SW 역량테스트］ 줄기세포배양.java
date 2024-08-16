import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 5653. 줄기세포 배양 / A형 / 9:54 ~
public class Solution {
    static final int DEAD = 0, ACTIVE = 1, INACTIVE = 2;
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    public static class Cell {
        int life;
        int time;
        int state;

        public Cell(int life) {
            this.life = life;
            this.time = life;
            this.state = INACTIVE;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            Cell[][] arr = new Cell[n + 2 * k + 1][m + 2 * k + 1];

            int y = arr.length / 2;
            int x = arr[0].length / 2;

            Queue<int[]> q = new LinkedList<>();
            for (int i = y; i < y + n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = x; j < x + m; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life != 0) {
                        arr[i][j] = new Cell(life);
                        q.add(new int[]{i, j});
                    }
                }
            }

            for (int K = 0; K < k; K++) {
                // 덮어씌울 배열
                Cell[][] temp = new Cell[arr.length][arr[0].length];
                Queue<int[]> tempQ = new LinkedList<>();

                int size = q.size();
                while (size-- > 0) {
                    int[] pos = q.poll();
                    int i = pos[0];
                    int j = pos[1];
                    // 죽은 상태의 세포라면?
                    if (arr[i][j] == null || arr[i][j].state == DEAD) continue;

                    // ACTIVE 라면 확산시킨다.
                    if (arr[i][j].state == ACTIVE && arr[i][j].time == arr[i][j].life) {
                        for (int d = 0; d < 4; d++) {
                            int ny = i + dy[d];
                            int nx = j + dx[d];
                            if (arr[ny][nx] == null && (temp[ny][nx] == null || temp[ny][nx].life < arr[i][j].life)) {
                                temp[ny][nx] = new Cell(arr[i][j].life);
                                tempQ.add(new int[]{ny, nx});
                            }
                        }
                    }

                    arr[i][j].time--;
                    if (arr[i][j].time == 0) {
                        arr[i][j].time = arr[i][j].life;
                        arr[i][j].state--;
                    }
                    if (arr[i][j].state != DEAD) {
                        q.add(new int[]{i, j});
                    }
                }

                while (!tempQ.isEmpty()) {
                    int[] pos = tempQ.poll();
                    int i = pos[0];
                    int j = pos[1];
                    arr[i][j] = temp[i][j];
                    q.add(new int[]{i, j});
                }
            }
            sb.append(q.size()).append("\n");
        }
        System.out.print(sb);
    }
}
