import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    // 왼쪽 -1
    // 오른쪽 1
    // 맨위(0번)가 S극(1)이어야 점수 얻음
    public static class Gear {
        int[] teeth = new int[8];

        public Gear(int[] teeth) {
            for (int i = 0; i < 8; i++) {
                this.teeth[i] = teeth[i];
            }
        }

        public void leftRotate() {
            int temp = teeth[0];
            for (int i = 1; i <= 7; i++) {
                teeth[i - 1] = teeth[i];
            }
            teeth[7] = temp;
        }

        public void rightRotate() {
            int temp = teeth[7];
            for (int i = 7; i >= 1; i--) {
                teeth[i] = teeth[i - 1];
            }
            teeth[0] = temp;
        }
    }

    public static Gear[] gears = new Gear[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int k = Integer.parseInt(br.readLine());
            for (int i = 1; i <= 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int[] arr = new int[8];
                for (int j = 0; j < 8; j++) {
                    arr[j] = Integer.parseInt(st.nextToken());
                }
                gears[i] = new Gear(arr);
            }

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{idx, dir});

                int temp = dir;
                for (int j = idx - 1; j >= 1; j--) {
                    temp = -temp;
                    if (gears[j].teeth[2] != gears[j + 1].teeth[6]) {
                        q.add(new int[]{j, temp});
                    } else {
                        break;
                    }
                }
                for (int j = idx + 1; j <= 4; j++) {
                    dir = -dir;
                    if (gears[j].teeth[6] != gears[j - 1].teeth[2]) {
                        q.add(new int[]{j, dir});
                    } else {
                        break;
                    }
                }
                while (!q.isEmpty()) {
                    int[] info = q.poll();
                    if (info[1] == -1) {
                        gears[info[0]].leftRotate();
                    } else {
                        gears[info[0]].rightRotate();
                    }
                }
            }

            int score = 0;
            for (int i = 1; i <= 4; i++) {
                score += (int) Math.pow(2, i - 1) * gears[i].teeth[0];
            }
            sb.append("#").append(t).append(" ").append(score).append("\n");
        }
        System.out.println(sb);
    }
}
