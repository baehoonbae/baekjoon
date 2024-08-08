import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int bsCnt = Integer.MAX_VALUE;
	static int[][] arr;
	static List<CCTV> cctvs = new ArrayList<>();
	static int n, m;

	public static class CCTV {
		int y, x, type;

		public CCTV(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > 0 && arr[i][j] < 6) {
					cctvs.add(new CCTV(i, j, arr[i][j]));
				}
			}
		}
		recursion(0);
		System.out.println(bsCnt);
	}

	public static void recursion(int idx) {
		if (idx == cctvs.size()) {
			countBlindSpot();
			return;
		}
		CCTV c = cctvs.get(idx);
		int[][] origin = new int[n][m];
		for (int i = 0; i < n; i++) {
			origin[i] = arr[i].clone();
		}
		switch (c.type) {
		case 1:
			for (int k = 0; k < 4; k++) {
				fill(c.y, c.x, k);
				recursion(idx + 1);
				revert(origin);
			}
			break;
		case 2:
			for (int k = 0; k < 2; k++) {
				fill(c.y, c.x, k);
				fill(c.y, c.x, k + 2);
				recursion(idx + 1);
				revert(origin);
			}
			break;
		case 3:
			for (int k = 0; k < 4; k++) {
				fill(c.y, c.x, k);
				fill(c.y, c.x, (k + 1) % 4);
				recursion(idx + 1);
				revert(origin);
			}
			break;
		case 4:
			for (int k = 0; k < 4; k++) {
				fill(c.y, c.x, k);
				fill(c.y, c.x, (k + 1) % 4);
				fill(c.y, c.x, (k + 2) % 4);
				recursion(idx + 1);
				revert(origin);
			}
			break;
		case 5:
			for (int k = 0; k < 4; k++) {
				fill(c.y, c.x, k);
			}
			recursion(idx + 1);
			revert(origin);
			break;
		}
	}

	public static void fill(int y, int x, int dir) {
		int ny = y;
		int nx = x;
		while (true) {
			ny += dy[dir];
			nx += dx[dir];
			if (ny < 0 || nx < 0 || ny >= n || nx >= m || arr[ny][nx] == 6) {
				break;
			}
			if (arr[ny][nx] == 0) {
				arr[ny][nx] = 9;
			}
		}
	}

	public static void revert(int[][] origin) {
		for (int i = 0; i < n; i++) {
			arr[i] = origin[i].clone();
		}
	}

	public static void countBlindSpot() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					count++;
				}
			}
		}
		bsCnt = Math.min(bsCnt, count);
	}

}
