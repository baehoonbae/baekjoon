import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int idx;
        int val;
        List<Node> children;

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
            children = new ArrayList<>();
        }
    }

    static int n, m, k;
    static Node[] adj;
    static boolean[] checked;
    static int[] arr;
    static List<Integer> candies;
    static List<Integer> sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        adj = new Node[n + 1];
        arr = new int[n + 1];
        checked = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            adj[i] = new Node(arr[i], i);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].children.add(adj[b]);
            adj[b].children.add(adj[a]);
        }
        List<Integer> candies = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!checked[i]) {
                int[] res = bfs(i);
                candies.add(res[0]);
                sizes.add(res[1]);
            }
        }
        int[] dp = new int[k + 1];
        for (int i = 0; i < candies.size(); i++) {
            int candy = candies.get(i);
            int size = sizes.get(i);
            for (int j = k; j >= size; j--) {
                dp[j] = Math.max(dp[j], dp[j - size] + candy);
            }
        }
        System.out.println(dp[k - 1]);
    }

    private static int[] bfs(int idx) {
        int total = 0;
        int size = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(adj[idx]);
        checked[idx] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            total += cur.val;
            size++;
            for (Node next : adj[cur.idx].children) {
                if (!checked[next.idx]) {
                    checked[next.idx] = true;
                    q.add(next);
                }
            }
        }
        return new int[]{total, size};
    }
}