import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] arr;
    static int n, count;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            count = 0;
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            visited = new boolean[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            generatePerm(0, new int[n]);
            sb.append("#").append(t).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }

    // 중복 조합을 방지하기 위해 순열 생성하고 백트래킹하기
    public static void generatePerm(int idx, int[] perm) {
        if (idx == n) {
            bbbb(0, 0, 0, perm);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // 왼쪽에 올릴 경우
                visited[i] = true;
                perm[idx] = arr[i];
                generatePerm(idx + 1, perm);
                perm[idx] = 0;
                visited[i] = false;
            }
        }
    }

    private static void bbbb(int idx, int left, int right, int[] perm) {
        if (left < right) {
            return;
        }
        if (idx == n) {
            count++;
            return;
        }
        bbbb(idx + 1, left + (perm[idx]), right, perm);
        bbbb(idx + 1, left, right + perm[idx], perm);
    }
}
