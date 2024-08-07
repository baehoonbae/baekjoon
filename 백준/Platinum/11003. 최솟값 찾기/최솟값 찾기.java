import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Deque<Integer> d = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (!d.isEmpty() && d.peekFirst() <= i - l) {
				d.pollFirst();
			}
			while (!d.isEmpty() && arr[d.peekLast()] > arr[i]) {
				d.pollLast();
			}
			d.addLast(i);
			sb.append(arr[d.peekFirst()]).append(" ");
		}

		System.out.println(sb.toString().trim());
	}
}
