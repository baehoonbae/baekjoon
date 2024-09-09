import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 섬당 모든 간선 추가
//
// 17472. 다리 만들기 2 / 골드1 / 1:15 ~
public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static List<Integer>[] adj = new ArrayList[7];
    static List<int[]>[] poses;
    static int[][] arr;
    static int[] parent = new int[7];
    static boolean[][] visited;
    static int n, m, fill;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int edgeCount = 0;
        int minCount = 0;

        poses = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            poses[i] = new ArrayList<>();
        }

        arr = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        fill = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    fill++;
                }
            }
        }
        for (int i = 1; i < fill; i++) {
            parent[i] = i;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        for (int i = 1; i < fill; i++) {
            for (int j = 1; j < fill; j++) {
                if (i == j) continue;
                int minCost = Integer.MAX_VALUE;
                for (int k = 0; k < poses[i].size(); k++) {
                    for (int l = 0; l < poses[j].size(); l++) {
                        minCost = Math.min(minCost, check(poses[i].get(k), poses[j].get(l)));
                    }
                }
                if (minCost != Integer.MAX_VALUE) {
                    pq.add(new int[]{i, j, minCost});
                }
            }
        }
        while (!pq.isEmpty() && edgeCount < (fill - 2)) {
            int[] info = pq.poll();
            if (find(info[0]) != find(info[1])) {
                minCount += info[2];
                union(info[0], info[1]);
                edgeCount++;
            }
        }
        if (edgeCount < fill - 2) {
            System.out.println(-1);
        } else {
            System.out.println(minCount);
        }
    }

    public static int check(int[] pos1, int[] pos2) {
        if (pos1[0] == pos2[0]) {
            int dist = Math.abs(pos1[1] - pos2[1]) - 1;
            if (dist >= 2 && isValid(pos1, pos2)) {
                return dist;
            }
        }
        if (pos1[1] == pos2[1]) {
            int dist = Math.abs(pos1[0] - pos2[0]) - 1;
            if (dist >= 2 && isValid(pos1, pos2)) {
                return dist;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static boolean isValid(int[] pos1, int[] pos2) {
        int y = pos1[0], x = pos1[1];

        if (pos1[0] == pos2[0]) {
            for (int i = Math.min(pos1[1], pos2[1]) + 1; i < Math.max(pos1[1], pos2[1]); i++) {
                if (arr[y][i] != 0) {
                    return false;
                }
            }
        } else if (pos1[1] == pos2[1]) {
            for (int i = Math.min(pos1[0], pos2[0]) + 1; i < Math.max(pos1[0], pos2[0]); i++) {
                if (arr[i][x] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        parent[y] = x;
    }

    public static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;
        poses[fill].add(new int[]{y, x});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int hy = pos[0];
            int hx = pos[1];
            for (int i = 0; i < 4; i++) {
                int ny = hy + dy[i];
                int nx = hx + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m || arr[ny][nx] == 0 || visited[ny][nx]) {
                    continue;
                }
                visited[ny][nx] = true;
                poses[fill].add(new int[]{ny, nx});
                q.add(new int[]{ny, nx});
            }
        }
    }
}