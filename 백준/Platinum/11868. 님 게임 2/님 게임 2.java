import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int mask = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            mask ^= Integer.parseInt(st.nextToken());
        }
        if (mask > 0) {
            System.out.println("koosaga");
        } else {
            System.out.println("cubelover");
        }
    }
}