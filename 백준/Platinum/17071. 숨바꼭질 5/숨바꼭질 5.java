import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 17071. 숨바꼭질 5 / 플래5 / 10:00 ~ 11:15
// BFS 문제이지만 경로 압축이나 최적화를 잘 해야할 듯하다
// 특정 시간에 해당 배열을 방문한 적이 있으면 탐색 중단?
// 시간은 계속 늘어나니까 시간에 대한 조건은 => 홀수 시간대에 방문했는지, 짝수 시간대에 방문했는지로 나누기
// 홀수/짝수로 나눈 이유는 다른 시간대의 같은 위치 방문을 기록하기 위함
// 방문처리 한 다음에 다음 가야할 위치가 동생 위치라면 그때의 시간을 반환하는 부분도 있어야 한다
public class Main {
    static boolean[][] visited = new boolean[500001][2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(solve(n, k));
    }

    private static int solve(int n, int k) {
        if (n == k) return 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{n, 0, k});
        visited[n][0] = true;

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int x = info[0];
            int time = info[1];
            if (x == info[2]) {
                return time;
            }
            int nt = time + 1;
            int nk = info[2] + nt;
            if (nk > 500000) {
                break;
            }
            int[] next = {x - 1, x + 1, x * 2};
            for (int nPos : next) {
                if (nPos >= 0 && nPos <= 500000 && !visited[nPos][nt % 2]) {
                    visited[nPos][nt % 2] = true;
                    q.add(new int[]{nPos, nt, nk});
                }
            }
            // 동생의 위치에 도착했다면
            if (visited[nk][nt % 2]) {
                return nt;
            }
        }
        return -1;
    }
}
