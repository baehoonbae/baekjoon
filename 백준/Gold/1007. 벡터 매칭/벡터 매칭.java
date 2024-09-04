import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1007. 벡터 매칭 / 골드2 / 10:25 ~ 10:38
// swea의 사랑의 카운슬러와 거의 동일한 문제이다
// visited 배열에서 true 한 곳은 시작점, false 한 곳은 끝점이라고 판단
// 그 두 부분을 나뉘게 subset 을 돈 다음 쌍이 n/2 개 생기면 벡터 합을 구한다
// 이때 시작점(true)은 +, 끝점(false)은 -를 해준다
// 이는 벡터의 특성(방향을 나타냄)상 그런 것임(시작점 -> 끝점은 +, 끝점 -> 시작점은 -)
// 그리고 이 문제는 실제 유클리드 거리를 구해야하기 때문에 결과물로 벡터의 제곱근을 반환해줘야 한다.
public class Main {
    static List<int[]> list;
    static boolean[] visited;
    static int n;
    static double minVal;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            minVal = Double.MAX_VALUE;
            list = new ArrayList<>();
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                list.add(new int[]{y, x});
            }
            solve(0, 0);
            sb.append(minVal).append("\n");
        }
        System.out.print(sb);
    }

    public static void solve(int idx, int count) {
        if (count == n / 2) {
            int y = 0, x = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    y += list.get(i)[0];
                    x += list.get(i)[1];
                } else {
                    y -= list.get(i)[0];
                    x -= list.get(i)[1];
                }
            }
            minVal = Math.min(minVal, Math.sqrt((double) y * y + (double) x * x));
            return;
        }
        if (idx == n) {
            return;
        }
        visited[idx] = true;
        solve(idx + 1, count + 1);
        visited[idx] = false;
        solve(idx + 1, count);
    }
}