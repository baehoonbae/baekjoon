import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Student {
        int y, x;
        int value;
        List<Integer> like;

        Student(int value) {
            this.value = value;
            like = new ArrayList<>();
        }
    }

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Queue<Student> q;
    static int[][] arr;
    static int[][] zero;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        arr = new int[n][n];
        zero = new int[n][n];
        for (int i = 1; i <= n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            Student student = new Student(num);
            for (int j = 0; j < 4; j++) {
                student.like.add(Integer.parseInt(st.nextToken()));
            }
            q.add(student);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                        continue;
                    }
                    if (arr[ny][nx] == 0) {
                        count++;
                    }
                }
                zero[i][j] = count;
            }
        }
        int size = q.size();
        while (size-- > 0) {
            Student me = q.poll();
            int[][] likes = new int[n][n];
            // 1. 인접한 칸 중 자신이 좋아하는 학생이 가장 많은 칸
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 0) {
                        continue;
                    }
                    if (!me.like.contains(arr[i][j])) {
                        continue;
                    }
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                            continue;
                        }
                        likes[ny][nx]++;
                    }
                }
            }
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 0) {
                        list.add(new int[]{i, j, likes[i][j], zero[i][j]});
                    }
                }
            }
            Collections.sort(list, (a, b) -> {
                if (a[2] == b[2]) {
                    if (a[3] == b[3]) {
                        if (a[0] == b[0]) {
                            return a[1] - b[1];
                        }
                        return a[0] - b[0];
                    }
                    return b[3] - a[3];
                }
                return b[2] - a[2];
            });
            int[] pos = list.get(0);
            arr[pos[0]][pos[1]] = me.value;
            for (int i = 0; i < 4; i++) {
                int ny = pos[0] + dy[i];
                int nx = pos[1] + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                    continue;
                }
                zero[ny][nx]--;
            }
            me.y = pos[0];
            me.x = pos[1];
            q.add(me);
        }
        int answer = 0;
        while (!q.isEmpty()) {
            int val = 0;
            Student me = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = me.y + dy[i];
                int nx = me.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                    continue;
                }
                if (me.like.contains(arr[ny][nx])) {
                    if (val == 0) {
                        val = 1;
                    } else {
                        val *= 10;
                    }
                }
            }
            answer += val;
        }
        System.out.println(answer);
    }
}