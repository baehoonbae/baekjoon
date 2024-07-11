import java.io.*;
import java.util.*;

public class Main {
	static Map<Integer, Integer> map;
	static int[] arr;
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;
	static int total;
	static int maxLen;

	public static void main(String[] args) throws IOException {
		map = new HashMap<>();
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = 0;
		while (right < n) {
			map.merge(arr[right], 1, Integer::sum);
			while (map.size() > 2) {
				map.merge(arr[left], -1, Integer::sum);
				if (map.get(arr[left]) == 0) {
					map.remove(arr[left]);
				}
				left++;
			}
			maxLen = Math.max(maxLen, right - left + 1);
			right++;
		}
		bw.write(String.valueOf(maxLen));
		bw.flush();
		bw.close();
		br.close();
	}
}
