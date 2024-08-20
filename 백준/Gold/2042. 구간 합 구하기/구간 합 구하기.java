import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2042. 구간 합 구하기 / 골드1 / 76분
// 세그먼트 트리
public class Main {
    public static class SegmentTree {
        long[] tree;
        int size;

        public SegmentTree(int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2));
            this.size = (int) Math.pow(2, h + 1);
            tree = new long[this.size];
        }

        // 루트부터!
        public long init(long[] arr, int node, int start, int end) {
            // 배열의 시작과 끝이 같으면 리프노드이다.
            // 원소 배열 값 그대로 담으면 됨
            if (start == end) {
                return tree[node] = arr[start];
            }

            // 리프노드가 아니라면?
            // 트리를 분할하고 병합한다.
            // 현재 노드 = 좌측 노드 + 우측 노드
            return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                    + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        // 값이 업데이트 되었을 때
        public void update(int node, int start, int end, int idx, long diff) {
            // 탐색 범위를 벗어났다면 return
            if (idx < start || end < idx) return;

            // 해당 노드에 차이만큼을 더해서 갱신한다
            tree[node] += diff;
            if (start != end) {
                // 좌측 노드로
                update(node * 2, start, (start + end) / 2, idx, diff);

                // 우측 노드로
                update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right) {
            // 탐색 범위를 벗어났다면 return
            if (left > end || right < start) return 0;

            // 범위를 완전히 포함하고 있다면
            // 해당 노드의 값을 return
            if (left <= start && end <= right) return tree[node];

            // 좌측 노드 구간합 + 우측 노드 구간합
            return sum(node * 2, start, (start + end) / 2, left, right) +
                    sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    public static SegmentTree t;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        long[] tree = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        
        t = new SegmentTree(n);
        t.init(tree, 1, 1, n);
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                t.update(1, 1, n, b, c - tree[b]);
                tree[b] = c;
            } else {
                sb.append(t.sum(1, 1, n, b, (int) c)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
