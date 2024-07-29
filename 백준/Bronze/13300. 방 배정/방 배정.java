import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] persons = new int[2][7];

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			persons[a][b]++;
		}

		int answer = 0;
		for (int i = 0; i < persons.length; i++) {
			for (int j = 1; j <= persons[i].length - 1; j++) {
				if (persons[i][j] == 0) {
					continue;
				}
				answer += (persons[i][j] / k) + 1;
				if (persons[i][j] % k == 0) {
					answer--;
				}
			}
		}
		System.out.println(answer);
	}
}