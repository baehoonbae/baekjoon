import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int count = 1;
			int min1 = Integer.MAX_VALUE;
			int min2 = Integer.MAX_VALUE;
			int n = Integer.parseInt(br.readLine());
			int[][] emps = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				emps[i][0] = Integer.parseInt(st.nextToken());
				emps[i][1] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(emps, (a, b) -> {
				int cmp = Integer.compare(a[0], b[0]);
				if (cmp != 0) {
					return cmp;
				} else {
					return Integer.compare(a[1], b[1]);
				}
			});
			int standard1 = emps[0][0];
			int standard2 = emps[0][1];
			for (int i = 1; i < n; i++) {
				if (emps[i][0] <= standard1) {
					count++;
				}
				if (emps[i][1] <= standard2) {
					count++;
					standard2 = Math.min(standard2, emps[i][1]);
				}
			}
			System.out.println(count);
		}
	}
}
