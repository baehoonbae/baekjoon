import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[][] arr;
    static int d, w, k, minCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            minCount = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (k == 1 || check()) {
                sb.append(0).append("\n");
            } else {
                input(0, 0);
                sb.append(minCount).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void input(int idx, int membrane) {
        // 종료 조건
        if (membrane >= minCount) {
            return;
        }
        if (idx == d) {
            if (check()) {
                minCount = Math.min(minCount, membrane);
            }
            return;
        }

        input(idx + 1, membrane);

        int[][] origin = new int[d][w];
        for (int i = 0; i < d; i++) {
            origin[i] = Arrays.copyOf(arr[i], w);
        }
        fill(idx, 0);
        input(idx + 1, membrane + 1);
        fill(idx, 1);
        input(idx + 1, membrane + 1);
        revert(origin);
    }

    // 모든 열이 K개의 연속하는 동일 특성 셀이 있는지 확인
    // 슬라이딩 윈도우로 하면 될듯하다(열 기준 범위 옮겨가면서 합이 0 또는 K면 그 열은 합격)
    public static boolean check() {
        for (int i = 0; i < w; i++) {
            boolean found = false;
            int total = 0;
            for (int j = 0; j < k; j++) {
                total += arr[j][i];
            }
            if (total == 0 || total == k) {
                continue;
            }
            for (int j = 0; j < d - k; j++) {
                total -= arr[j][i];
                total += arr[j + k][i];
                if (total == 0 || total == k) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    // y 행의 모든 원소를 val 값으로 채움
    public static void fill(int y, int val) {
        for (int i = 0; i < w; i++) {
            arr[y][i] = val;
        }
    }

    // 이전 상태로 되돌리기
    public static void revert(int[][] origin) {
        for (int i = 0; i < d; i++) {
            arr[i] = Arrays.copyOf(origin[i], w);
        }
    }
}
