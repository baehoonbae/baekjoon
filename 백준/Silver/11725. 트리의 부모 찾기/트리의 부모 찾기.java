import java.io.*;
import java.util.*;

class Main {
    static List<Integer>[] list;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        parent = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        findParents(1);
        for (int i = 2; i <= n; i++) {
            bw.write(parent[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void findParents(int node) {
        visited[node] = true;
        for (int child : list[node]) {
            if (!visited[child]) {
                parent[child] = node;
                findParents(child);
            }
        }
    }
}
