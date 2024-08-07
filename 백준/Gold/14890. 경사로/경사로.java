import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int n, l;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (checkRow(i)) {
				count++;
			}
			if (checkCol(i)) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static boolean checkRow(int y) {
		boolean[] isInstalled = new boolean[n];

		for (int i = 0; i < n - 1; i++) {
			if (arr[y][i] == arr[y][i + 1]) {
				continue;
			} else if (arr[y][i] + 1 == arr[y][i + 1]) {
				for (int j = 0; j < l; j++) {
					if (i - j < 0 || isInstalled[i - j] || arr[y][i] != arr[y][i - j]) {
						return false;
					}
					isInstalled[i - j] = true;
				}
			} else if (arr[y][i] - 1 == arr[y][i + 1]) {
				for (int j = 1; j <= l; j++) {
					if (i + j >= n || isInstalled[i + j] || arr[y][i + 1] != arr[y][i + j]) {
						return false;
					}
					isInstalled[i + j] = true;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean checkCol(int x) {
		boolean[] isInstalled = new boolean[n];

		for (int i = 0; i < n - 1; i++) {
			if (arr[i][x] == arr[i + 1][x]) {
				continue;
			} else if (arr[i][x] + 1 == arr[i + 1][x]) {
				for (int j = 0; j < l; j++) {
					if (i - j < 0 || isInstalled[i - j] || arr[i][x] != arr[i - j][x]) {
						return false;
					}
					isInstalled[i - j] = true;
				}
			} else if (arr[i][x] - 1 == arr[i + 1][x]) {
				for (int j = 1; j <= l; j++) {
					if (i + j >= n || isInstalled[i + j] || arr[i + 1][x] != arr[i + j][x]) {
						return false;
					}
					isInstalled[i + j] = true;
				}
			} else {
				return false;
			}
		}
		return true;
	}
}
