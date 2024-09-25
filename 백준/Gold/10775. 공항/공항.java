import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int p;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int count = 0;
        for (int i = 0; i < p; i++) {
            int num = Integer.parseInt(br.readLine());
            int root = find(num);
            if (root == 0) {
                break;
            }
            union(root - 1, root);
            count++;
        }
        System.out.println(count);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        parent[b] = a;
    }
}