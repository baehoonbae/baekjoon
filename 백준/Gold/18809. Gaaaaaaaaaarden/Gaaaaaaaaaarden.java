import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 0: 호수
// 1: 배양액 못 뿌리는 땅
// 2: 배양액 뿌릴수있는 땅

// 어떤 칸에 배양액 뿌리면 1초마다 도달한적 없는 칸으로 퍼져감
// 만약 도달한적 없는 칸에 2개의 배양액이 만나면?
// => 꽃이 피어남

// 초록, 빨강 조합 하고
// bfs ㄱㄱ
public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static List<Pos> fluidPos = new ArrayList<>();
    static Deque<Pos> newList = new ArrayDeque<>();
    static int[][] arr;
    static int n, m, g, r, maxFlower = 0;
    static int field;

    public static class Pos {
        int y, x, color, time;

        public Pos(int y, int x, int color, int time) {
            this.y = y;
            this.x = x;
            this.color = color;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) fluidPos.add(new Pos(i, j, 0, 0));
                if (arr[i][j] != 0) field++;
            }
        }
        dfs(0, 0, 0, 0);
        System.out.println(maxFlower);
    }

    public static void dfs(int cnt, int idx, int green, int red) {
        if (cnt == g + r) {
            maxFlower = Math.max(maxFlower, bfs());
            return;
        }
        if (idx == fluidPos.size()) {
            return;
        }

        // 초록색 선택하는 경우
        if (green < g) {
            newList.addLast(fluidPos.get(idx));
            fluidPos.get(idx).color = 3;
            dfs(cnt + 1, idx + 1, green + 1, red);
            fluidPos.get(idx).color = 0;
            newList.pollLast();
        }

        // 빨간색 선택하는 경우
        if (red < r) {
            newList.addLast(fluidPos.get(idx));
            fluidPos.get(idx).color = 4;
            dfs(cnt + 1, idx + 1, green, red + 1);
            fluidPos.get(idx).color = 0;
            newList.pollLast();
        }

        // 아무것도 선택하지 않는 경우
        dfs(cnt, idx + 1, green, red);
    }

    private static int bfs() {
        int[][] time = new int[n][m];
        int[][] state = new int[n][m];
        int flower = 0;

        Queue<Pos> q = new LinkedList<>();
        for (Pos pos : newList) {
            state[pos.y][pos.x] = pos.color;
            q.add(pos);
        }

        while (!q.isEmpty()) {
            Pos p = q.poll();
            if (state[p.y][p.x] == 5) continue;
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m || arr[ny][nx] == 0) continue;

                if (state[ny][nx] == 0) {
                    state[ny][nx] = p.color;
                    time[ny][nx] = p.time + 1;
                    q.add(new Pos(ny, nx, p.color, p.time + 1));
                } else if (state[ny][nx] != 5 && state[ny][nx] + p.color == 7 && time[ny][nx] == p.time + 1) {
                    state[ny][nx] = 5;
                    flower++;
                }
            }
        }
        return flower;
    }
}