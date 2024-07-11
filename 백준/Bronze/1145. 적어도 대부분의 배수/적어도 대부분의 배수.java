import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int min = Integer.MAX_VALUE;
		arr = new int[5];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, arr[i]);
		}
		for (int i = min + 1;; i++) {
			int count = 0;
			for (int j = 0; j < 5; j++) {
				if (i % arr[j] == 0) {
					count++;
				}
				if (count >= 3) {
					bw.write(String.valueOf(i));
					bw.flush();
					bw.close();
					br.close();
					return;
				}
			}
		}
	}
}
