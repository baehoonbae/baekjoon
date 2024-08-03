import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int n;
    static int maxVal = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(arr, 0);
        System.out.println(maxVal);
    }

    public static void dfs(int[][] arr, int depth) {
        if (depth == 5) {
            findMax(arr);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] newArr = play(i, arr);
            dfs(newArr, depth + 1);
        }
    }

    public static int[][] play(int dir, int[][] arr) {
        int[][] newArr = new int[n][n];
        boolean[][] merged = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, n);
        }

        if (dir == 0) { // 위
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (newArr[j][i] != 0) {
                        int ny = j;
                        while (ny > 0 && newArr[ny - 1][i] == 0) {
                            newArr[ny - 1][i] = newArr[ny][i];
                            newArr[ny][i] = 0;
                            ny--;
                        }
                        if (ny > 0 && newArr[ny - 1][i] == newArr[ny][i] && !merged[ny - 1][i]) {
                            newArr[ny - 1][i] *= 2;
                            newArr[ny][i] = 0;
                            merged[ny - 1][i] = true;
                        }
                    }
                }
            }
        } else if (dir == 1) { // 아래
            for (int i = 0; i < n; i++) {
                for (int j = n - 2; j >= 0; j--) {
                    if (newArr[j][i] != 0) {
                        int ny = j;
                        while (ny < n - 1 && newArr[ny + 1][i] == 0) {
                            newArr[ny + 1][i] = newArr[ny][i];
                            newArr[ny][i] = 0;
                            ny++;
                        }
                        if (ny < n - 1 && newArr[ny + 1][i] == newArr[ny][i] && !merged[ny + 1][i]) {
                            newArr[ny + 1][i] *= 2;
                            newArr[ny][i] = 0;
                            merged[ny + 1][i] = true;
                        }
                    }
                }
            }
        } else if (dir == 2) { // 왼쪽
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (newArr[i][j] != 0) {
                        int nx = j;
                        while (nx > 0 && newArr[i][nx - 1] == 0) {
                            newArr[i][nx - 1] = newArr[i][nx];
                            newArr[i][nx] = 0;
                            nx--;
                        }
                        if (nx > 0 && newArr[i][nx - 1] == newArr[i][nx] && !merged[i][nx - 1]) {
                            newArr[i][nx - 1] *= 2;
                            newArr[i][nx] = 0;
                            merged[i][nx - 1] = true;
                        }
                    }
                }
            }
        } else { // 오른쪽
            for (int i = 0; i < n; i++) {
                for (int j = n - 2; j >= 0; j--) {
                    if (newArr[i][j] != 0) {
                        int nx = j;
                        while (nx < n - 1 && newArr[i][nx + 1] == 0) {
                            newArr[i][nx + 1] = newArr[i][nx];
                            newArr[i][nx] = 0;
                            nx++;
                        }
                        if (nx < n - 1 && newArr[i][nx + 1] == newArr[i][nx] && !merged[i][nx + 1]) {
                            newArr[i][nx + 1] *= 2;
                            newArr[i][nx] = 0;
                            merged[i][nx + 1] = true;
                        }
                    }
                }
            }
        }
        return newArr;
    }

    public static void findMax(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                maxVal = Math.max(maxVal, arr[i][j]);
            }
        }
    }
}
