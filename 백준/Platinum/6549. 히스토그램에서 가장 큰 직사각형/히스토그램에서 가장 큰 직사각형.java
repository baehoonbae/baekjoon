import java.io.*;
import java.util.StringTokenizer;

// 6549. 히스토그램에서 가장 큰 직사각형 / 플래5 / 5:25 ~ 11:15
// 구간 내 최솟값을 가진 인덱스를 가진 세그 트리 구현
// 원본 배열에서 먼저 나온 최솟값을 지닌 인덱스를 기준으로 왼쪽, 오른쪽으로 분할시키는 쿼리들을 실행
// 넓이 반환
public class Main {
    public static class SegTree {
        int[] tree;
        int size;

        SegTree(int[] arr) {
            size = arr.length * 4;
            tree = new int[size];
            init(1, 1, n);
        }

        int init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = start;
            }
            int mid = (start + end) / 2;
            int idx1 = init(node * 2, start, mid);
            int idx2 = init(node * 2 + 1, mid + 1, end);
            if (arr[idx1] < arr[idx2]) {
                return tree[node] = tree[node * 2];
            } else {
                return tree[node] = tree[node * 2 + 1];
            }
        }

        int query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return -1;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int l = query(node * 2, start, mid, left, right);
            int r = query(node * 2 + 1, mid + 1, end, left, right);
            if (l == -1) {
                return r;
            } else if (r == -1) {
                return l;
            } else {
                if (arr[l] <= arr[r]) {
                    return l;
                } else {
                    return r;
                }
            }
        }

        // 핵심로직
        // 일단 세그트리에서 최솟값을 지닌 인덱스를 추출한다
        // 구간에 해당하는 넓이는 (추출한 최솟값 * 구간)
        // 그 넓이를 최댓값으로 계속 갱신
        // 구간을 분할 정복한다
        long search(int left, int right) {
            int m = query(1, 1, n, left, right);
            long res = (long) (right - left + 1) * arr[m];
            if (left <= m - 1) {
                long search = search(left, m - 1);
                res = Math.max(search, res);
            }
            if (right >= m + 1) {
                long search = search(m + 1, right);
                res = Math.max(search, res);
            }
            return res;
        }
    }

    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while (!(str = br.readLine()).equals("0")) {
            StringTokenizer st = new StringTokenizer(str);
            n = Integer.parseInt(st.nextToken());
            arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            SegTree t = new SegTree(arr);
            bw.write(t.search(1, n) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
