import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int dasom = Integer.parseInt(br.readLine());
		int count = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 1; i < n; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		while (!pq.isEmpty() && dasom <= pq.peek()) {
			pq.add(pq.poll() - 1);
			dasom++;
			count++;
		}
		System.out.println(count);
	}
}
