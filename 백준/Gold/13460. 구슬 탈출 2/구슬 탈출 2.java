import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[][] arr;
    static int n, m;
    static int ry, rx, by, bx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'R') {
                    ry = i;
                    rx = j;
                } else if (arr[i][j] == 'B') {
                    by = i;
                    bx = j;
                }
            }
        }
        System.out.println(move());
    }

    public static int move() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];
        q.add(new int[]{ry, rx, by, bx, 0});
        visited[ry][rx][by][bx] = true;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int rY = current[0];
            int rX = current[1];
            int bY = current[2];
            int bX = current[3];
            int count = current[4];
            if (count > 10) return -1;
            for (int i = 0; i < 4; i++) {
                int nry = rY;
                int nrx = rX;
                int nby = bY;
                int nbx = bX;
                boolean rHole = false;
                boolean bHole = false;
                // 빨간공 움직이기
                while (arr[nry + dy[i]][nrx + dx[i]] != '#' && arr[nry][nrx] != 0) {
                    nry += dy[i];
                    nrx += dx[i];
                    if (arr[nry][nrx] == 'O') {
                        rHole = true;
                        break;
                    }
                }
                // 파란공 움직이기
                while (arr[nby + dy[i]][nbx + dx[i]] != '#' && arr[nby][nbx] != 0) {
                    nby += dy[i];
                    nbx += dx[i];
                    if (arr[nby][nbx] == 'O') {
                        bHole = true;
                        break;
                    }
                }
                if (bHole) continue;
                if (rHole) return (count + 1 > 10) ? -1 : count + 1;
                if (nry == nby && nrx == nbx) {
                    if (i == 0) { // 위
                        if (rY > bY) {
                            nry++;
                        } else {
                            nby++;
                        }
                    } else if (i == 1) { // 아래
                        if (rY > bY) {
                            nby--;
                        } else {
                            nry--;
                        }
                    } else if (i == 2) { // 왼쪽
                        if (rX > bX) {
                            nrx++;
                        } else {
                            nbx++;
                        }
                    } else if (i == 3) { // 오른쪽
                        if (rX > bX) {
                            nbx--;
                        } else {
                            nrx--;
                        }
                    }
                }
                if (!visited[nry][nrx][nby][nbx]) {
                    visited[nry][nrx][nby][nbx] = true;
                    q.add(new int[]{nry, nrx, nby, nbx, count + 1});
                }
            }
        }
        return -1;
    }
}
