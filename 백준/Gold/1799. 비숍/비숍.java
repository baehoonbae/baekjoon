import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] diag1_black, diag2_black;
    static boolean[] diag1_white, diag2_white;
    static int[][] arr;
    static int n, maxBlack, maxWhite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        diag1_black = new boolean[2 * n - 1];
        diag2_black = new boolean[2 * n - 1];
        diag1_white = new boolean[2 * n - 1];
        diag2_white = new boolean[2 * n - 1];
        maxBlack = maxWhite = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 검은칸은 0,0부터 시작
        // 검은칸과 흰칸에 각각 비숍 놓기
        solve(0, 0, true, 0);
        solve(0, 1, false, 0);
        System.out.println(maxBlack + maxWhite);
    }

    public static void solve(int y, int x, boolean isBlack, int count) {
        if (x >= n) {
            y++;
            if (isBlack) {
                x = y % 2 == 0 ? 0 : 1;
            } else {
                x = y % 2 == 0 ? 1 : 0;
            }
        }
        if (y >= n) {
            if (isBlack) {
                maxBlack = Math.max(maxBlack, count);
            } else {
                maxWhite = Math.max(maxWhite, count);
            }
            return;
        }
        // 검은칸만 보고 있는 건지 흰칸만 보고 있는 건지 분기하기
        if (arr[y][x] == 1) {
            if (isBlack) {
                if (!diag1_black[y + x] && !diag2_black[y - x + n - 1]) {
                    diag1_black[y + x] = diag2_black[y - x + n - 1] = true;
                    solve(y, x + 2, isBlack, count + 1);
                    diag1_black[y + x] = diag2_black[y - x + n - 1] = false;
                }
            } else {
                if (!diag1_white[y + x] && !diag2_white[y - x + n - 1]) {
                    diag1_white[y + x] = diag2_white[y - x + n - 1] = true;
                    solve(y, x + 2, isBlack, count + 1);
                    diag1_white[y + x] = diag2_white[y - x + n - 1] = false;
                }
            }
        }
        solve(y, x + 2, isBlack, count);
    }
}