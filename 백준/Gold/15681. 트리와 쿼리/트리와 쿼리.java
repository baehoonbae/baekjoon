import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] adj;
    static int[] count;
    static int n, r, q;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        adj = new List[n + 1];
        count = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        calculate(r, 0);
        for (int i = 0; i < q; i++) {
            int root = Integer.parseInt(br.readLine());
            sb.append(count[root]).append("\n");
        }
        System.out.print(sb);
    }

    public static int calculate(int node, int parent) {
        count[node] = 1;
        for (int child : adj[node]) {
            if (child != parent) {
                count[node] += calculate(child, node);
            }
        }
        return count[node];
    }
}
