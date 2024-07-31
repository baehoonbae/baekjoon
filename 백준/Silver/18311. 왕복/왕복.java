import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long k = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		long[] origin = new long[n + 1];
		long[] arr = new long[n * 2 + 1];
		long[] order = new long[n * 2];

		int num = 1;
		for (int i = 0; i < order.length / 2; i++) {
			order[i] = num++;
		}
		num--;
		for (int i = order.length / 2; i < order.length; i++) {
			order[i] = num--;
		}

		arr[1] = origin[1] = Long.parseLong(st.nextToken());
		for (int i = 2; i <= n; i++) {
			origin[i] = Long.parseLong(st.nextToken());
			arr[i] = origin[i] + arr[i - 1];
		}

		int idx = n;
		for (int i = n + 1; i < arr.length; i++) {
			arr[i] = arr[i - 1] + origin[idx--];
		}

		for (int i = 0; i < arr.length - 1; i++) {
			if (k >= arr[i] && k < arr[i + 1]) {
				System.out.println(order[i]);
				return;
			}
		}
	}
}
