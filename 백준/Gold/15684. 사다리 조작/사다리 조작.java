import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = { 0, 0, 1 };
	static int[] dx = { -1, 1, 0 };
	static int[][] arr;
	static int n, m, ladder;
	static int minCount = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		ladder = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m *= 2;
		arr = new int[n + 1][m + 1];
		for (int i = 1; i < m; i += 2) {
			for (int j = 0; j <= n; j++) {
				arr[j][i] = 1;
			}
		}
		for (int i = 0; i < ladder; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) * 2;
			arr[a][b] = 1;
		}
		play(1, 2, 0);
		System.out.println(minCount == 4 ? -1 : minCount);
	}

	public static void play(int y, int x, int count) {
		if (count >= minCount) {
			return;
		}
		if (isValid()) {
			minCount = Math.min(minCount, count);
			return;
		}
		for (int i = y; i <= n; i++) {
			for (int j = (i == y ? x : 2); j < m; j += 2) {
				if (arr[i][j] == 0 && arr[i][j - 2] == 0 && arr[i][j + 2] == 0) {
					arr[i][j] = 1;
					play(i, j + 2, count + 1);
					arr[i][j] = 0;
				}
			}
		}
	}

	public static boolean browse(int y, int x) {
		int start = x;
		while (y <= n) {
			if (arr[y][x + 1] == 1) {
				x += 2;
			} else if (arr[y][x - 1] == 1) {
				x -= 2;
			}
			y++;
		}
		return start == x;
	}

	public static boolean isValid() {
		for (int i = 1; i <= m; i += 2) {
			if (!browse(1, i)) {
				return false;
			}
		}
		return true;
	}
}
