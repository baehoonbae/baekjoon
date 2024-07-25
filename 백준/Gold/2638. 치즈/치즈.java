import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[][] arr;
	static List<Pos> cl;
	static int n, m, cc;

	static class Pos {
		int y;
		int x;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void fill(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		q.add(new int[] { y, x });
		while (!q.isEmpty()) {
			int hy = q.peek()[0];
			int hx = q.poll()[1];
			arr[hy][hx] = 2;
			for (int i = 0; i < 4; i++) {
				int ny = hy + dy[i];
				int nx = hx + dx[i];
				if (ny < 0 || ny >= n || nx < 0 || nx >= m || visited[ny][nx] || arr[ny][nx] == 1) {
					continue;
				}
				q.add(new int[] { ny, nx });
				visited[ny][nx] = true;
			}
		}
	}

	public static void erase() {
		for (int i = 0; i < cl.size(); i++) {
			int y = cl.get(i).y;
			int x = cl.get(i).x;
			int count = 0;
			for (int j = 0; j < 4; j++) {
				int ny = y + dy[j];
				int nx = x + dx[j];
				if (arr[ny][nx] == 2) {
					count++;
				}
			}
			if (count >= 2) {
				arr[y][x] = 0;
				cc--;
				cl.remove(i);
				i--;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cl = new ArrayList<>();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					cl.add(new Pos(i, j));
					cc++;
				}
			}
		}
		int time = 0;
		while (cc > 0) {
			time++;
			fill(0, 0);
			erase();
		}
		System.out.println(time);
	}
}