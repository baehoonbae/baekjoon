import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static int n;
    static int m;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (flag == 0) {
                union(a, b);
            } else {
                String s = find(a) == find(b) ? "YES" : "NO";
                bw.write(s + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int u) {
        if (parents[u] == u) {
            return u;
        }
        return find(parents[u]);
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return;
        }
        parents[v] = u;
    }
}
