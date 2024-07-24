import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 11404. 플로이드 / 골드4 / 1시간 12분
// 플로이드-워셜 알고리즘
public class Main {
	final static int INF = 1_000_000_008;
	static int[][] arr;
	static int n, m;

	public static void floyd() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		arr = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(arr[i], INF);
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			arr[a][a] = 0;
			arr[a][b] = Math.min(arr[a][b], cost);
		}

		for (int i = 1; i <= n; i++) {
			floyd();
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] == INF) {
					bw.write(String.valueOf(0) + " ");
				} else {
					bw.write(String.valueOf(arr[i][j]) + " ");
				}
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}