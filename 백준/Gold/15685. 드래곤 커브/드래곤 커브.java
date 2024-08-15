import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] arr = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            List<Integer> dirs = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dirs.add(dir);
            int num = 0;
            while (num < g) {
                int size = dirs.size();
                for (int j = size - 1; j >= 0; j--) {
                    dirs.add((dirs.get(j) + 1) % 4);
                }
                num++;
            }
            arr[startY][startX] = 1;
            for (Integer d : dirs) {
                startY += dy[d];
                startX += dx[d];
                arr[startY][startX] = 1;
            }
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr[i][j] == 1 && arr[i + 1][j] == 1 && arr[i][j + 1] == 1 && arr[i + 1][j + 1] == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
