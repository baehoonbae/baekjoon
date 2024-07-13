import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    //    static BufferedWriter bw;
    static int start;
    static int end;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        bfs(start);
    }

    public static void bfs(int x) {
        boolean[] visited = new boolean[222211];
        visited[x] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, 0});
        while (!q.isEmpty()) {
            int here = q.peek()[0];
            int time = q.poll()[1];
            if (here == end) {
                System.out.println(time);
                break;
            }
            if (here * 2 >= 0&& here * 2 <= 100000 && !visited[here * 2]) {
                visited[here * 2] = true;
                q.add(new int[]{here * 2, time});
            }
            if (here - 1 >= 0&& here - 1 <= 100000 && !visited[here - 1]) {
                visited[here - 1] = true;
                q.add(new int[]{here - 1, time + 1});
            }
            if (here + 1 >= 0&& here + 1 <= 100000 && !visited[here + 1]) {
                visited[here + 1] = true;
                q.add(new int[]{here + 1, time + 1});
            }
        }
    }
}
