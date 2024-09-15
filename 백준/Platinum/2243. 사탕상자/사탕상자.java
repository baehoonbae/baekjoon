import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class SegTree {
        int[] count;
        int size;

        SegTree(int n) {
            this.size = n;
            count = new int[size * 4];
        }

        //2
        void update(int node, int start, int end, int idx, int num) {
            if (idx < start || end < idx) {
                return;
            }
            count[node] += num;
            if (start != end) {
                int mid = (start + end) / 2;
                update(node * 2, start, mid, idx, num);
                update(node * 2 + 1, mid + 1, end, idx, num);
            }
        }

        //1
        int query(int node, int start, int end, int rank) {
            if (start == end) {
                return start;
            }
            int mid = (start + end) / 2;
            if (rank <= count[node * 2]) {
                return query(node * 2, start, mid, rank);
            } else {
                return query(node * 2 + 1, mid + 1, end, rank - count[node * 2]);
            }
        }
    }

    static final int MAX = 1_000_000;
    static SegTree t = new SegTree(MAX);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                int num = t.query(1, 1, MAX, b);
                bw.write(num + "\n");
                t.update(1, 1, MAX, num, -1);
            } else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                t.update(1, 1, MAX, b, c);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}