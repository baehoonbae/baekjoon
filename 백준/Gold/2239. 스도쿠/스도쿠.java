import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] arr;
    static List<int[]> zeroList;
    static List<String> ans = new ArrayList<>();
    static boolean solved = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[10][10];
        zeroList = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            String s = br.readLine();
            for (int j = 1; j <= 9; j++) {
                arr[i][j] = s.charAt(j - 1) - '0';
                if (arr[i][j] == 0) {
                    zeroList.add(new int[]{i, j});
                }
            }
        }
        solve(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void solve(int idx) {
        if (idx == zeroList.size()) {
            solved = true;
            return;
        }
        int[] pos = zeroList.get(idx);
        int i = pos[0], j = pos[1];
        for (int k = 1; k <= 9; k++) {
            if (isAvailable(i, j, k)) {
                arr[i][j] = k;
                solve(idx + 1);
                if (solved) return;
                arr[i][j] = 0;
            }
        }
    }

    public static boolean isAvailable(int y, int x, int val) {
        for (int i = 1; i <= 9; i++) {
            if (arr[y][i] == val || arr[i][x] == val) {
                return false;
            }
        }
        y = (y - 1) / 3 * 3 + 1;
        x = (x - 1) / 3 * 3 + 1;
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                if (arr[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }
}