import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { -1, 0, 1, 0 };
	static int[][] arr;
	static int[][] temp;
	static int n, m, r, tempN, tempM;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		tempN = n = Integer.parseInt(st.nextToken());
		tempM = m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		temp = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int rep = Math.min(n, m);
		for (int i = 0; i < rep / 2; i++) {
			fill(i, i);
		}

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				sb.append(temp[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void fill(int y, int x) {
		int prevDir = 0;
		int hy = y;
		int hx = x;
		int count = (tempN * 2) + (tempM - 2) * 2;
		tempN -= 2;
		tempM -= 2;

		for (int num = 0; num < count; num++) {
			int dir = prevDir;
			int ny = hy;
			int nx = hx;
			for (int i = 0; i < r; i++) {
				if (ny + dy[dir] < y || nx + dx[dir] < x || ny + dy[dir] >= n || nx + dx[dir] >= m) {
					dir = (dir + 1) % 4;
				}
				ny += dy[dir];
				nx += dx[dir];
			}
			temp[ny][nx] = arr[hy][hx];

			if (hy + dy[prevDir] < y || hx + dx[prevDir] < x || hy + dy[prevDir] >= n || hx + dx[prevDir] >= m) {
				prevDir = (prevDir + 1) % 4;
			}
			hy += dy[prevDir];
			hx += dx[prevDir];
		}
		n--;
		m--;
	}
}