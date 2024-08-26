import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1234567891;
    static SegTree t;
    static int[] arr;
    static int n, m;

    public static class SegTree {
        int[] minTree;
        int[] maxTree;
        int size;

        SegTree() {
            int h = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
            int size = (int) Math.pow(2, h + 1);
            minTree = new int[size];
            maxTree = new int[size];
            this.size = size;
            minInit(1, 1, n);
            maxInit(1, 1, n);
        }

        int minInit(int i, int s, int e) {
            if (s == e) {
                return minTree[i] = arr[s];
            }
            int mid = (s + e) / 2;
            return minTree[i] = Math.min(
                    minInit(i * 2, s, mid),
                    minInit(i * 2 + 1, mid + 1, e)
            );
        }

        int maxInit(int i, int s, int e) {
            if (s == e) {
                return maxTree[i] = arr[s];
            }
            int mid = (s + e) / 2;
            return maxTree[i] = Math.max(
                    maxInit(i * 2, s, mid),
                    maxInit(i * 2 + 1, mid + 1, e)
            );
        }

        int minSearch(int i, int s, int e, int l, int r) {
            if (r < s || e < l) {
                return INF;
            }
            if (l <= s && e <= r) {
                return minTree[i];
            } else {
                int mid = (s + e) / 2;
                return Math.min(
                        minSearch(i * 2, s, mid, l, r),
                        minSearch(i * 2 + 1, mid + 1, e, l, r)
                );
            }
        }

        public int maxSearch(int i, int s, int e, int l, int r) {
            if (r < s || e < l) {
                return 0;
            }
            if (l <= s && e <= r) {
                return maxTree[i];
            } else {
                int mid = (s + e) / 2;
                return Math.max(
                        maxSearch(i * 2, s, mid, l, r),
                        maxSearch(i * 2 + 1, mid + 1, e, l, r)
                );
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        t = new SegTree();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            sb.append(t.minSearch(1, 1, n, a, b)).append(" ");
            sb.append(t.maxSearch(1, 1, n, a, b)).append("\n");
        }
        System.out.print(sb);
    }
}
