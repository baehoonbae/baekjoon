import java.io.*;
import java.util.*;

class Main {
	static Set<List<Integer>> set = new HashSet<>();
	static int[] arr;
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
		arr = new int[n];
		visited = new boolean[n];
		set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		recursive(0, 0, new ArrayList<>());

		bw.flush();
		bw.close();
		br.close();
	}

	public static void recursive(int cnt, int idx, List<Integer> ans) throws IOException {
		if (cnt == m) {
			if (!set.contains(ans)) {
				set.add(new ArrayList<>(ans));
				for (int i = 0; i < m; i++) {
					bw.write(ans.get(i) + " ");
				}
				bw.write("\n");
			} else {
				return;
			}
		} else {
			for (int i = idx; i < n; i++) {
				ans.add(arr[i]);
				recursive(cnt + 1, i, ans);
				ans.remove(ans.size() - 1);
			}
		}
	}
}