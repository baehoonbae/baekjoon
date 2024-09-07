import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class SegTree {
        long[] tree;
        int size;

        SegTree(long[] arr) {
            size = arr.length * 4;
            tree = new long[size];
            init(arr, 1, 1, arr.length - 1);
        }

        long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }
            int mid = (start + end) / 2;
            return tree[node] = init(arr, node * 2, start, mid) + init(arr, node * 2 + 1, mid + 1, end);
        }

        void update(int node, int start, int end, int idx, long num) {
            if (idx < start || end < idx) {
                return;
            }
            if (start == end) {
                tree[node] = num;
                return;
            }
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, num);
            update(node * 2 + 1, mid + 1, end, idx, num);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        long query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] arr = new long[n + 1];
        SegTree t = new SegTree(arr);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 0) {
                if (b > c) {
                    int temp = b;
                    b = c;
                    c = temp;
                }
                sb.append(t.query(1, 1, n, b, c)).append("\n");
            } else {
                t.update(1, 1, n, b, c);
                arr[b] = c;
            }
        }
        System.out.print(sb);
    }
}