import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 3584. 가장 가까운 공통 조상 / 골드4 / 12:54 ~
public class Main {
    public static class Node {
        Integer idx;
        Integer parent;
        Integer level;
        List<Node> children = new ArrayList<>();

        public Node(Integer idx, Integer parent, Integer level) {
            this.idx = idx;
            this.parent = parent;
            this.level = level;
        }
    }

    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            nodes = new Node[n + 1];
            for (int i = 1; i <= n; i++) {
                nodes[i] = new Node(i, null, null);
            }
            for (int i = 1; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                nodes[idx].children.add(nodes[child]);
                nodes[child].parent = idx;
            }
            int root = 0;
            for (int i = 1; i <= n; i++) {
                if (nodes[i].parent == null) {
                    root = i;
                    break;
                }
            }
            setLevel(root, 1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int commonParent = lca(nodes[a], nodes[b]);
            sb.append(commonParent).append("\n");
        }
        System.out.print(sb);
    }

    private static void setLevel(int idx, int level) {
        nodes[idx].level = level;
        List<Node> children = nodes[idx].children;
        for (int i = 0; i < children.size(); i++) {
            setLevel(children.get(i).idx, level + 1);
        }
    }

    private static int lca(Node a, Node b) {
        if (a.level < b.level) {
            Node temp = a;
            a = b;
            b = temp;
        }
        while (a.level > b.level) {
            a = nodes[a.parent];
        }
        while (!a.equals(b)) {
            a = nodes[a.parent];
            b = nodes[b.parent];
        }
        return a.idx;
    }
}