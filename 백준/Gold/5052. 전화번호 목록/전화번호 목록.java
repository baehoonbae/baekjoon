import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder ans = new StringBuilder();
    static int n;
    static String[] arr;
    static Set<String> isPre;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            arr = new String[n];
            isPre = new HashSet<>();
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
                isPre.add(arr[i]);
            }
            out:
            for (String s : arr) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < s.length() - 1; j++) {
                    sb.append(s.charAt(j));
                    if (isPre.contains(sb.toString())) {
                        flag = false;
                        break out;
                    }
                }
            }
            ans.append(flag ? "YES" : "NO").append("\n");
        }
        System.out.print(ans);
    }
}