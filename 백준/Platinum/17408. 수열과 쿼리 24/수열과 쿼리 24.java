import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17408. 수열과 쿼리 24 / 플래4 / 8:32~10:01
// 최댓값과 최댓값의 인덱스를 구하고
// 최댓값의 인덱스를 둘로 나누는 쿼리를 실행해서 둘을 더하면 된다.
public class Main {
    public static class Node {
        long val;
        int idx;

        public Node(long val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public static class SegTree {
        Node[] tree;
        int size;

        public SegTree(long[] arr) {
            int h = (int) Math.ceil(Math.log(arr.length - 1) / Math.log(2));
            int size = (int) Math.pow(2, h + 1);
            tree = new Node[size];
            this.size = size;
            init(arr, 1, 1, arr.length - 1);
        }

        public Node init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = new Node(arr[start], start);
            }
            int mid = (start + end) / 2;
            Node node1 = init(arr, node * 2, start, mid);
            Node node2 = init(arr, node * 2 + 1, mid + 1, end);
            return tree[node] = node1.val >= node2.val ? node1 : node2;
        }

        public void update(int node, int start, int end, int idx, long num) {
            if (idx < start || end < idx) {
                return;
            }
            if (start == end) {
                tree[node] = new Node(num, start);
            } else {
                int mid = (start + end) / 2;
                update(node * 2, start, mid, idx, num);
                update(node * 2 + 1, mid + 1, end, idx, num);
                tree[node] = tree[node * 2].val >= tree[node * 2 + 1].val ? tree[node * 2] : tree[node * 2 + 1];
            }
        }

        public Node get(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return new Node(-1, -1);
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            Node node1 = get(node * 2, start, mid, left, right);
            Node node2 = get(node * 2 + 1, mid + 1, end, left, right);
            return node1.val >= node2.val ? node1 : node2;
        }
    }

    static SegTree t;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        t = new SegTree(arr);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                t.update(1, 1, n, b, c);
                arr[b] = c;
            } else {
                Node max = t.get(1, 1, n, b, c);
                Node node1 = t.get(1, 1, n, b, max.idx - 1);
                Node node2 = t.get(1, 1, n, max.idx + 1, c);
                long answer = max.val + (Math.max(node1.val, node2.val));
                sb.append(answer).append("\n");
            }
        }
        System.out.print(sb);
    }
}
