import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] arr;
	static int n, m, y, x, d, count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move();
		System.out.println(count);
	}

	public static void move() {
		while (true) {
			if (arr[y][x] == 0) {
				arr[y][x] = 2;
				count++;
			}
			boolean cleaned = false;
			for (int i = 0; i < 4; i++) {
				d = (d + 3) % 4;
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (arr[ny][nx] == 0) {
					y = ny;
					x = nx;
					cleaned = true;
					break;
				}
			}
			if (!cleaned) {
				int ny = y + dy[(d + 2) % 4];
				int nx = x + dx[(d + 2) % 4];
				if (arr[ny][nx] == 1) {
					break;
				}
				y = ny;
				x = nx;
			}
		}
	}
}