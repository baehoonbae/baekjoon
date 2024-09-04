import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static char[][] arr;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            arr[i] = s.toCharArray();
        }
        count = 0;
        solve(0, 0, 0, new ArrayList<>());
        System.out.println(count);
    }

    private static void solve(int idx, int dasom, int doyeon, List<int[]> newList) {
        if (doyeon >= 4) {
            return;
        }
        if (dasom + doyeon == 7) {
            if (isConnected(newList)) {
                count++;
            }
            return;
        }
        if (idx == 25) {
            return;
        }
        int y = idx / 5;
        int x = idx % 5;
        if (arr[y][x] == 'Y') {
            newList.add(new int[]{y, x});
            solve(idx + 1, dasom, doyeon + 1, newList);
            newList.remove(newList.size() - 1);
        } else {
            newList.add(new int[]{y, x});
            solve(idx + 1, dasom + 1, doyeon, newList);
            newList.remove(newList.size() - 1);
        }
        solve(idx + 1, dasom, doyeon, newList);
    }

    private static boolean isConnected(List<int[]> newList) {
        boolean[][] visited = new boolean[5][5];
        boolean[] checked = new boolean[7];
        Queue<int[]> q = new LinkedList<>();
        q.add(newList.get(0));

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int y = pos[0];
            int x = pos[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5 || visited[ny][nx]) {
                    continue;
                }
                for (int j = 0; j < 7; j++) {
                    if (newList.get(j)[0] == ny && newList.get(j)[1] == nx) {
                        visited[ny][nx] = true;
                        q.add(newList.get(j));
                        checked[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (!checked[i]) {
                return false;
            }
        }
        return true;
    }
}