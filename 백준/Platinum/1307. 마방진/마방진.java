import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] map;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		if (n % 2 == 1) {
			map = odd(n, 1);
		} else if (n % 4 == 0) {
			four();
		} else {
			notFour();
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 홀수 마방진
	public static int[][] odd(int N, int num) {
		int map[][] = new int[N + 1][N + 1];
		int r = 1;
		int c = N / 2 + 1;
		map[r][c] = num;
		for (int i = num + 1; i < num + N * N; i++) {
			int y = r;
			int x = c;
			y--;
			x--;
			if (y < 1) {
				y += N;
			}
			if (x < 1) {
				x += N;
			}
			if (map[y][x] != 0) {
				r++;
				map[r][c] = i;
			} else {
				map[y][x] = i;
				r = y;
				c = x;
			}
		}
		return map;
	}

	// 4의 배수 마방진
	public static void four() {
		int num = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = num++;
			}
		}
		// 왼 + 오
		int x1 = 1;
		int x2 = n;
		for (int j = 0; j < n / 4; j++) {
			int y = n / 4 * 3;
			for (int i = n / 4 + 1; i <= n / 4 * 3; i++) {
				swap(i, x1, y, x2);
				y--;
			}
			x1++;
			x2--;
		}

		// 위 + 아래
		x1 = 1;
		x2 = n;
		for (int j = 0; j < n / 4; j++) {
			int y = n / 4 * 3;
			for (int i = n / 4 + 1; i <= n / 4 * 3; i++) {
				swap(x1, i, x2, y);
				y--;
			}
			x1++;
			x2--;
		}
	}

	// 4의 배수 아닌 짝수 마방진
	public static void notFour() {
		int[] dy = { 1, n / 2, -n / 2, n / 2 };
		int[] dx = { 1, n / 2, 0, -n / 2 };
		int next = n * n / 4;
		map = new int[n + 1][n + 1];
		int[][] ref = new int[n / 2][n / 2];
		int r = 0;
		int c = 0;
		for (int i = 0; i < 4; i++) {
			r += dy[i];
			c += dx[i];
			ref = odd(n / 2, next * i + 1);
			for (int j = 1; j <= n / 2; j++) {
				for (int k = 1; k <= n / 2; k++) {
					map[r + j - 1][c + k - 1] = ref[j][k];
				}
			}
		}
		int cnt = (n - 2) / 4;
		for (int i = 1; i <= n / 2; i++) {
			for (int j = 1; j <= cnt; j++) {
				swap(i, j, i + n / 2, j);
			}
		}
		for (int i = 1; i <= n / 2; i++) {
			for (int j = n; j > n - cnt + 1; j--) {
				swap(i, j, i + n / 2, j);
			}
		}
		swap((n / 2) / 2 + 1, (n / 2) / 2 + 1, (n / 2) / 2 + 1 + (n / 2), (n / 2) / 2 + 1);
		swap((n / 2) / 2 + 1, (n / 2) / 2, (n / 2) / 2 + 1 + (n / 2), (n / 2) / 2);
	}

	public static void swap(int y, int x, int ty, int tx) {
		int temp = map[y][x];
		map[y][x] = map[ty][tx];
		map[ty][tx] = temp;
	}
}
