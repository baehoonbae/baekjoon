import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    // (1,1), (10,10) 에서 시작
    static List<BC>[][] arr = new ArrayList[11][11];
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static int m;

    static public class BC {
        int y, x, range, power;

        public BC(int y, int x, int range, int power) {
            this.y = y;
            this.x = x;
            this.range = range;
            this.power = power;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int[][] moves = new int[2][m];
            arr = new ArrayList[11][11];
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    arr[i][j] = new ArrayList<>();
                    arr[i][j].add(new BC(i, j, 0, 0));
                }
            }
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    moves[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            List<BC> bcs = new ArrayList<>();
            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int range = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                BC bc = new BC(y, x, range, power);
                bcs.add(bc);
                fill(bc);
            }
            int total = simulate(moves);
            sb.append("#").append(t).append(" ").append(total).append("\n");
        }
        System.out.print(sb);
    }

    private static int simulate(int[][] moves) {
        int y1 = 1, x1 = 1, y2 = 10, x2 = 10;
        int total = 0;
        for (int i = 0; i <= m; i++) {
            total += getMax(y1, x1, y2, x2);
            if (i < m) {
                y1 += dy[moves[0][i]];
                x1 += dx[moves[0][i]];
                y2 += dy[moves[1][i]];
                x2 += dx[moves[1][i]];
            }
        }
        return total;
    }

    private static int getMax(int y1, int x1, int y2, int x2) {
        int max = 0;
        for (BC bc1 : arr[y1][x1]) {
            for (BC bc2 : arr[y2][x2]) {
                int power = 0;
                if (bc1 == bc2) {
                    power = bc1.power;
                } else {
                    power = bc1.power + bc2.power;
                }
                max = Math.max(max, power);
            }
        }
        return max;
    }

    public static void fill(BC bc) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (Math.abs(bc.x - j) + Math.abs(bc.y - i) <= bc.range) {
                    arr[i][j].add(bc);
                }
            }
        }
    }
}