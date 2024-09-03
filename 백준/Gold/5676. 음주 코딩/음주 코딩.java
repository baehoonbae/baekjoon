import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class SegTree {
        int[] tree;
        int size;

        SegTree(int[] arr) {
            size = arr.length * 4;
            tree = new int[size];
            init(1, 1, arr.length - 1);
        }

        int init(int node, int start, int end) {
            if (start == end) {
                if (arr[start] == 0) {
                    return tree[node] = 0;
                } else if (arr[start] < 0) {
                    return tree[node] = -1;
                } else {
                    return tree[node] = 1;
                }
            }
            int mid = (start + end) / 2;
            return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end);
        }

        void update(int node, int start, int end, int idx, int num) {
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
            tree[node] = tree[node * 2] * tree[node * 2 + 1];
        }

        int query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return 1;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return query(node * 2, start, mid, left, right) * query(node * 2 + 1, mid + 1, end, left, right);
        }
    }

    static int[] arr;
    static int n, k;
    static SegTree t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = "";
        while ((str = br.readLine()) != null) {
            String[] s = str.split(" ");
            n = Integer.parseInt(s[0]);
            k = Integer.parseInt(s[1]);
            StringBuilder sb = new StringBuilder();

            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            t = new SegTree(arr);

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                if (c == 'C') {
                    int idx = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 0) {
                        num = 0;
                    } else {
                        num = num < 0 ? -1 : 1;
                    }
                    t.update(1, 1, n, idx, num);
                } else {
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());
                    int num = t.query(1, 1, n, left, right);
                    if (num == 0) {
                        sb.append(0);
                    } else if (num < 0) {
                        sb.append("-");
                    } else {
                        sb.append("+");
                    }
                }
            }
            System.out.println(sb);
        }
    }
}