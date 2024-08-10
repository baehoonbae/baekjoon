import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {0, 0, 0, 1, -1};
    static int[] dx = {0, 1, -1, 0, 0};
    static int[][] arr = new int[1111][1111];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int maxH = 0, maxW = 0;
        int maxHIdx = -1, maxWIdx = -1;
        int[] dir = new int[6];
        int[] dist = new int[6];
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dir[i] = Integer.parseInt(st.nextToken());
            dist[i] = Integer.parseInt(st.nextToken());
            if (dir[i] == 1 || dir[i] == 2) {
                if (maxH < dist[i]) {
                    maxH = dist[i];
                    maxHIdx = i;
                }
            } else {
                if (maxW < dist[i]) {
                    maxW = dist[i];
                    maxWIdx = i;
                }
            }
        }
        int totalArea = maxW * maxH;
        totalArea -= dist[(maxWIdx + 3) % 6] * dist[(maxHIdx + 3) % 6];
        System.out.println(totalArea * n);
    }
}