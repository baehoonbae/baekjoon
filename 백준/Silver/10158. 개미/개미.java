import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(br.readLine());
        x = (x + t) % (2 * m);
        y = (y + t) % (2 * n);
        if (x > m) {
            x = 2 * m - x;
        }
        if (y > n) {
            y = 2 * n - y;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(x).append(" ").append(y);
        System.out.println(sb);
    }
}