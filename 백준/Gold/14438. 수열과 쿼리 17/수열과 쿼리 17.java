import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class SegmentTree {
        long[] tree;
        int size;

        public SegmentTree(int arrSize) {
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
            int size = (int) Math.pow(2, h + 1);
            tree = new long[size];
            this.size = size;
        }

        public long init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }
            int mid = (start + end) / 2;
            return tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
        }

        public long get(int node, int start, int end, int left, int right) {
            if (right < start || end < left) return INF;
            if (left <= start && end <= right) return tree[node];
            int mid = (start + end) / 2;
            return Math.min(get(node * 2, start, mid, left, right), get(node * 2 + 1, mid + 1, end, left, right));
        }

        public void update(int node, int start, int end, int idx, long num) {
            if (idx < start || end < idx) return;
            if (start == end) {
                tree[node] = num;
            } else {
                int mid = (start + end) / 2;
                update(node * 2, start, mid, idx, num);
                update(node * 2 + 1, mid + 1, end, idx, num);
                tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
            }
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static long[] arr;
    static SegmentTree t;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        t = new SegmentTree(n);
        t.init(1, 1, n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                t.update(1, 1, n, b, c);
                arr[b] = c;
            } else {
                if (b > c) {
                    int temp = b;
                    b = (int) c;
                    c = temp;
                }
                sb.append(t.get(1, 1, n, b, (int) c)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
