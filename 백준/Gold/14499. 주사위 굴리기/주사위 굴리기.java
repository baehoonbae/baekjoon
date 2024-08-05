import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Dice {
		int up;
		int down;
		int upLeft;
		int upRight;
		int upUp;
		int upDown;
	}

	static int[] dy = { 0, 0, 0, -1, 1 };
	static int[] dx = { 0, 1, -1, 0, 0 };
	static int[][] arr;
	static int n, m, y, x;
	static Dice d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		int[] commands = new int[num];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < num; i++) {
			commands[i] = Integer.parseInt(st.nextToken());
		}

		d = new Dice();
		for (int i = 0; i < commands.length; i++) {
			move(commands[i]);
		}
	}

	public static void move(int dir) {
		if (y + dy[dir] < 0 || y + dy[dir] >= n || x + dx[dir] < 0 || x + dx[dir] >= m) {
			return;
		}
		y += dy[dir];
		x += dx[dir];
		if (dir == 1) { // 오른쪽
			int down = d.upRight;
			int up = d.upLeft;
			int upRight = d.up;
			int upLeft = d.down;
			d.down = down;
			d.up = up;
			d.upRight = upRight;
			d.upLeft = upLeft;
		} else if (dir == 2) { // 왼쪽
			int down = d.upLeft;
			int up = d.upRight;
			int upRight = d.down;
			int upLeft = d.up;
			d.down = down;
			d.up = up;
			d.upRight = upRight;
			d.upLeft = upLeft;
		} else if (dir == 3) { // 위
			int down = d.upDown;
			int up = d.upUp;
			int upUp = d.down;
			int upDown = d.up;
			d.down = down;
			d.up = up;
			d.upUp = upUp;
			d.upDown = upDown;
		} else if (dir == 4) { // 아래
			int down = d.upUp;
			int up = d.upDown;
			int upUp = d.up;
			int upDown = d.down;
			d.down = down;
			d.up = up;
			d.upUp = upUp;
			d.upDown = upDown;
		}
		if (arr[y][x] == 0) {
			arr[y][x] = d.down;
		} else {
			d.down = arr[y][x];
			arr[y][x] = 0;
		}
		System.out.println(d.up);
	}
}
