import java.io.*;
import java.util.*;

class Main {
	static Set<List<Integer>> set = new HashSet<>();
	static int[] arr;
	static int[] ans;
	static boolean[] visited;
	static int n;
	static int m;
	static BufferedReader br;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		arr = new int[n];
		ans = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		recursive(0);

		bw.flush();
		bw.close();
		br.close();
	}

	public static void recursive(int cnt) throws IOException {
		if (cnt == m) {
			for (int i = 0; i < m; i++) {
				bw.write(ans[i] + " ");
			}
			bw.write("\n");
		} else {
			int prev = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					continue;
				}
				if (prev != arr[i]) {
					visited[i] = true;
					ans[cnt] = arr[i];
					prev = arr[i];
					recursive(cnt + 1);
					visited[i] = false;
				}
			}
		}
	}
}