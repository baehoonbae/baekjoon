import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Fenwick {
        long[][] tree;
        int size;

        Fenwick() {
            size = n;
            tree = new long[n + 1][n + 1];
        }

        void update(int y, int x, long diff) {
            for (int i = y; i <= n; i += (i & -i)) {
                for (int j = x; j <= n; j += (j & -j)) {
                    tree[i][j] += diff;
                }
            }
        }

        long sum(int y, int x) {
            long result = 0;
            for (int i = y; i > 0; i -= (i & -i)) {
                for (int j = x; j > 0; j -= (j & -j)) {
                    result += tree[i][j];
                }
            }
            return result;
        }

        long rangeSum(int y1, int x1, int y2, int x2) {
            return sum(y2, x2) - sum(y1 - 1, x2) - sum(y2, x1 - 1) + sum(y1 - 1, x1 - 1);
        }
    }

    static int n, m;
    static long[][] arr;
    static Fenwick t;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[n + 1][n + 1];
        t = new Fenwick();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                t.update(i, j, arr[i][j]);
            }
        }
        // 0: update
        // 1: query(합)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if (a == 0) {
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                long num = Long.parseLong(st.nextToken());
                long diff = num - arr[y][x];
                t.update(y, x, diff);
                arr[y][x] = num;
            } else {
                int y1 = Integer.parseInt(st.nextToken());
                int x1 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                long sum = t.rangeSum(y1, x1, y2, x2);
                bw.write(sum + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}