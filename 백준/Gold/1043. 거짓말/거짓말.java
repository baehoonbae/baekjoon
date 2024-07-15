import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int count;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] knows;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        knows = new boolean[n + 1];
        parent = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        int count1 = Integer.parseInt(st.nextToken());
        for (int i = 0; i < count1; i++) {
            int num = Integer.parseInt(st.nextToken());
            knows[num] = true;
        }
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new ArrayList<>());
            int count2 = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count2; j++) {
                int node = Integer.parseInt(st.nextToken());
                list.get(i).add(node);
            }

        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < list.get(i).size() - 1; j++) {
                union(list.get(i).get(j), list.get(i).get(j + 1));
            }
        }

        for (int i = 1; i <= n; i++) {
            if (knows[i]) {
                knows[find(i)] = true;
            }
        }

        for (int i = 0; i < m; i++) {
            boolean check = true;
            for (int j = 0; j < list.get(i).size(); j++) {
                int node = list.get(i).get(j);
                int parent = find(node);
                if (knows[parent]) {
                    check = false;
                    break;
                }
            }
            if (check) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static int find(int u) {
        if (parent[u] == u) {
            return u;
        }
        return parent[u] = find(parent[u]);
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u > v) {
            int temp = u;
            u = v;
            v = temp;
        }
        if (u == v) {
            return;
        } else {
            parent[v] = u;
        }
    }
}
