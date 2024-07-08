import java.io.*;
import java.util.*;

class Main {
    static int[] dy = { 1, 1, 0, -1 };
    static int[] dx = { 0, 1, 1, 1 };
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[19][19];
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (arr[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int y = i;
                    int x = j;
                    int cnt = 1;
                    while (true) {
                        int ny = y + dy[k];
                        int nx = x + dx[k];
                        if (ny < 0 || nx < 0 || ny >= 19 || nx >= 19 || arr[ny][nx] != arr[y][x] || arr[ny][nx] == -1) {
                            break;
                        }
                        cnt++;
                        y = ny;
                        x = nx;
                    }
                    if (cnt == 5) {
                        int oppositeY = i - dy[k];
                        int oppositeX = j - dx[k];
                        if (oppositeY >= 0 && oppositeX >= 0 && oppositeY < 19 && oppositeX < 19 && arr[oppositeY][oppositeX] == arr[i][j]) {
                            // Found the same stone in the opposite direction, not a valid line
                            continue;
                        }
                        System.out.println(arr[i][j]);
                        System.out.println((i + 1) + " " + (j + 1));
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}