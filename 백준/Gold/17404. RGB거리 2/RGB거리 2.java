import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1234567891;
    static int[][] arr;
    static int[][] cache;
    static int n, minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        minCost = Integer.MAX_VALUE;
        arr = new int[n][3];
        cache = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    cache[j][k] = INF;
                }
            }
            solve(1, arr[0][i], i, i);
        }
        System.out.println(minCost);
    }

    public static void solve(int idx, int cost, int prevColor, int firstColor) {
        if (cost >= minCost) {
            return;
        }
        if (idx == n) {
            if (firstColor != prevColor) {
                minCost = cost;
            }
            return;
        }
        if (cache[idx][prevColor] <= cost) {
            return;
        }
        cache[idx][prevColor] = cost;
        for (int i = 0; i < 3; i++) {
            if (prevColor != i) {
                solve(idx + 1, cost + arr[idx][i], i, firstColor);
            }
        }
    }
}