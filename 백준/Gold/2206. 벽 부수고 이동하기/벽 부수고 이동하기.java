import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2206. 벽 부수고 이동하기 / 골드3 / 1시간27분
public class Main {
	final static int MAX = Integer.MAX_VALUE;
	static int n, m, minDist = MAX;
	static int[][] arr;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void bfs(int y, int x) {
		int[][][] minTime = new int[n][m][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Arrays.fill(minTime[i][j], MAX);				
			}
		}
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { y, x, 0, 1 });
		minTime[y][x][0] = 1;

		while (!q.isEmpty()) {
			int[] info = q.poll();
			int hy = info[0];
			int hx = info[1];
			int broken = info[2];
			int dist = info[3];
			if (hy == n - 1 && hx == m - 1) {
				minDist = Math.min(minTime[hy][hx][broken], minDist);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int ny = hy + dy[i];
				int nx = hx + dx[i];
				if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
					continue;
				}
				if (arr[ny][nx] == 1 && broken == 0) {
					if (minTime[ny][nx][1] > dist + 1) {
						minTime[ny][nx][1] = dist + 1;
						q.add(new int[] { ny, nx, 1, dist + 1 });
					}
				} else if (arr[ny][nx] == 0) {
					if (minTime[ny][nx][broken] > dist + 1) {
						minTime[ny][nx][broken] = dist + 1;
						q.add(new int[] { ny, nx, broken, dist + 1 });
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		bfs(0, 0);
		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
	}
}