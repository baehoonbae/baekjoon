import java.util.*;

public class Main {
    static int[] prev = new int[100011];
    static Stack<Integer> stack = new Stack<>();
    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        bfs(n);
        System.out.print(n + " ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void bfs(int x) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[100011];
        q.add(new int[]{x, 0});

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int cur = info[0];
            int time = info[1];
            if (cur == k) {
                System.out.println(time);
                while (cur != n) {
                    stack.add(cur);
                    cur = prev[cur];
                }
                return;
            }
            if (cur - 1 >= 0 && !visited[cur - 1]) {
                q.add(new int[]{cur - 1, time + 1});
                prev[cur - 1] = cur;
                visited[cur - 1] = true;
            }
            if (cur + 1 <= 100000 && !visited[cur + 1]) {
                q.add(new int[]{cur + 1, time + 1});
                prev[cur + 1] = cur;
                visited[cur + 1] = true;
            }
            if (cur * 2 <= 100000 && !visited[cur * 2]) {
                q.add(new int[]{cur * 2, time + 1});
                prev[cur * 2] = cur;
                visited[cur * 2] = true;
            }
        }
    }
}
