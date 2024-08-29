import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 17471. 게리맨더링 / 골드3 / 12:53 ~ 2:35
public class Main {
    static final int INF = 1234567891;
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] arr;
    static int n, minDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        minDiff = INF;
        arr = new int[n];
        adj = new ArrayList[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                adj[i].add(a);
                adj[a].add(i);
            }
        }
        for (int i = 1; i <= n - 1; i++) {
            visited = new boolean[n];
            div(i, 0, 0);
        }
        System.out.println(minDiff == INF ? -1 : minDiff);
    }

    // 선거구는 딱 두개로 나눠져야함
    // 두개로 나누는 경우의 수를 모두 탐색하기
    // 두개로 나눴을 때 두 구간의 리스트를 내부적으로 연결된지 확인하고 갱신하는 gerry 메서드로 넘긴다.
    public static void div(int limit, int idx, int count) {
        if (count == limit) {
            List<Integer> a = new ArrayList<>();
            List<Integer> b = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    a.add(i);
                } else {
                    b.add(i);
                }
            }
            gerry(a, b);
            return;
        }
        for (int i = idx; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                div(limit, i, count + 1);
                visited[i] = false;
            }
        }
    }

    // 두 구간으로 나누었을 때 각 구간이 내부적으로 연결되어 있는지 확인한다.
    // 만약 둘 다 내부적으로 연결되어 있다면 최소 차이를 갱신한다.
    // 인덱스로 저장되어있기 때문에 해당 인덱스의 값을 더하자!
    public static void gerry(List<Integer> a, List<Integer> b) {
        if (isConnected(a) && isConnected(b)) {
            int sumA = 0;
            int sumB = 0;
            for (int i : a) {
                sumA += arr[i];
            }
            for (int i : b) {
                sumB += arr[i];
            }
            minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
        }
    }

    // 내부적으로 연결되어 있는지를 BFS로 확인한다.
    // 연결된 노드들이 인자로 받아온 group에 포함되어 있는지를 확인하고 count한다.
    // count와 group의 사이즈가 동일하면 true를 리턴한다.
    public static boolean isConnected(List<Integer> group) {
        boolean[] check = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(group.get(0));
        check[group.get(0)] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : adj[cur]) {
                if (!check[next] && group.contains(next)) {
                    check[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }
        return count == group.size();
    }
}
