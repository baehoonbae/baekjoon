import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static final int U = 0, D = 1, L = 2, R = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static char[][] arr;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        parent = new int[n * m + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dir = 0;
                if (arr[i][j] == 'U') {
                    dir = U;
                } else if (arr[i][j] == 'D') {
                    dir = D;
                } else if (arr[i][j] == 'L') {
                    dir = L;
                } else if (arr[i][j] == 'R') {
                    dir = R;
                }
                int y = i + dy[dir];
                int x = j + dx[dir];
                int a = i * m + j;
                int b = y * m + x;
                if (find(a) != find(b)) {
                    union(a, b);
                }
            }
        }
        for (int i = 0; i < n * m; i++) {
            parent[i] = find(parent[i]);
        }
        Set<Integer> count = new HashSet<>();
        for (int i = 0; i < n * m; i++) {
            count.add(parent[i]);
        }
        System.out.println(count.size());
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