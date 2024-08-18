import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 17837. 새로운 게임 2 / 골드2 / 166분
public class Main {
    static final int WHITE = 0, RED = 1, BLUE = 2;
    static final int[] dy = {0, 0, 0, -1, 1};
    static final int[] dx = {0, 1, -1, 0, 0};
    static Tile[][] arr;
    static Piece[] pieces;
    static int n, k;

    public static class Piece {
        int y, x, dir;

        public Piece(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    public static class Tile {
        int color;
        List<Piece> pList;

        public Tile(int color) {
            this.color = color;
            this.pList = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new Tile[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                arr[i][j] = new Tile(0);
            }
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int color = Integer.parseInt(st.nextToken());
                arr[i][j] = new Tile(color);
            }
        }

        pieces = new Piece[k + 1];
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            pieces[i] = new Piece(y, x, dir);
            arr[y][x].pList.add(pieces[i]);
        }
        play();
    }

    // 시뮬레이션 시작
    public static void play() {
        int turn = 0;
        while (true) {
            turn++;
            if (turn > 1000) {
                System.out.println(-1);
                return;
            }
            for (int i = 1; i <= k; i++) {
                if (move(i)) {
                    System.out.println(turn);
                    return;
                }
            }
        }
    }

    private static boolean move(int i) {
        Piece p = pieces[i];
        List<Piece> list = arr[p.y][p.x].pList;
        int idx = list.indexOf(p);

        // 움직여야할 말 위에 쌓여있는 말들 추가함
        List<Piece> newList = new ArrayList<>(list.subList(idx, list.size()));
        list.subList(idx, list.size()).clear();

        int ny = p.y + dy[p.dir];
        int nx = p.x + dx[p.dir];

        // 다음 이동해야할 좌표가 범위를 벗어나거나 파란색이면
        if (ny < 1 || nx < 1 || ny > n || nx > n || arr[ny][nx].color == BLUE) {
            if (p.dir == 1 || p.dir == 3) {
                p.dir++;
            } else if (p.dir == 2 || p.dir == 4) {
                p.dir--;
            }
            ny = p.y + dy[p.dir];
            nx = p.x + dx[p.dir];

            // 방향바꾼 다음 좌표도 파란색이면
            // 제거했던 말들 다시 추가한다
            if (ny < 1 || nx < 1 || ny > n || nx > n || arr[ny][nx].color == BLUE) {
                arr[p.y][p.x].pList.addAll(newList);
                return false;
            }
        }
        // 리스트의 상태를 변경시키는건 빨간색밖에 없으니
        // 빨간색이면 리버스해서 추가
        if (arr[ny][nx].color == RED) {
            Collections.reverse(newList);
        }

        // 다음 좌표에 말들 추가
        arr[ny][nx].pList.addAll(newList);

        // 추가한 말들 좌표도 갱신해야 한다
        update(ny, nx);

        // 말 옮길 때마다 종료조건 체크
        return arr[ny][nx].pList.size() >= 4;
    }

    public static void update(int y, int x) {
        List<Piece> list = arr[y][x].pList;
        for (Piece piece : list) {
            piece.y = y;
            piece.x = x;
        }
    }
}
