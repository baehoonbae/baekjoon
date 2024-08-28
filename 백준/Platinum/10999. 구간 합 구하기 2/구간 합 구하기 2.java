import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 10999. 구간 합 구하기 2 / 플래4 / 12:52 ~ 3:30
// 구간 합 세그트리 구현
// lazy propagation 필요.
// 특정 구간 값을 업데이트 하는 쿼리가 들어오면?
// 원래는 바로바로 반영해야 하지만 배열 크기가 클수록 바로 반영하면 그 시간 복잡도가 그대로 계속 들어오게 된다
// 그래서 해당 구간 업데이트 정보를 저장해놨다가 해당 구간에 대한 쿼리가 들어오면 갱신한다
// => 업데이트를 미뤄뒀다가 필요할 때 한다
// 구체적으로 동작하는 방식은, 원래는 update가 일어나면 갱신 구간에 '완전히 포함되는 구간까지만' 갱신을 한다
// 즉 만약 4~10이 갱신 구간이고, 4~5 가 완전히 포함되는 노드 중 하나라고 한다면, 4~5 노드의 리프 노드로는 재귀를 하지 않는다
// 이는 나중에 반드시 기억을 했다가 해당 구간 합 정보를 구하려고 한다면 반영해야하기 떄문에, 이를 미뤄뒀다가 나중에 리프노드에다 반영하는 것
// => 4, 5 리프 노드에 lazy 값을 더해놓는다
public class Main {
    static SegTree t;
    static long[] arr;
    static int n, m, k;

    public static class SegTree {
        long[] tree;
        long[] lazy;
        int size;

        SegTree(long[] arr) {
            size = arr.length * 4;
            tree = new long[size];
            lazy = new long[size];
            init(arr, 1, 1, n);
        }

        long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }
            int mid = (start + end) / 2;
            return tree[node]
                    = init(arr, node * 2, start, mid)
                    + init(arr, node * 2 + 1, mid + 1, end);
        }

        void propagate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] += (end - start + 1) * lazy[node];
                if (start != end) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                lazy[node] = 0;
            }
        }

        void update(int node, int start, int end, int left, int right, long diff) {
            propagate(node, start, end);

            if (right < start || end < left) {
                return;
            }
            if (left <= start && end <= right) {
                // 특정 노드가 아닌 구간 합의 갱신은 구간의 노드 개수만큼 diff 를 더해줘야한다.
                tree[node] += (end - start + 1) * diff;
                if (start != end) {
                    lazy[node * 2] += diff;
                    lazy[node * 2 + 1] += diff;
                }
                return;
            }
            int mid = (start + end) / 2;
            update(node * 2, start, mid, left, right, diff);
            update(node * 2 + 1, mid + 1, end, left, right, diff);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        long get(int node, int start, int end, int left, int right) {
            propagate(node, start, end);
            if (right < start || end < left) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            } else {
                int mid = (start + end) / 2;
                return get(node * 2, start, mid, left, right) + get(node * 2 + 1, mid + 1, end, left, right);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n + 1];

        // 배열 초기화
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        t = new SegTree(arr);

        // 수의 변경(a==1) 및 구간의 합(a==2)이 일어남
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                long d = Long.parseLong(st.nextToken());
                t.update(1, 1, n, (int) b, (int) c, d);
            } else {
                sb.append(t.get(1, 1, n, (int) b, (int) c)).append("\n");
            }
        }
        System.out.print(sb);
    }
}
