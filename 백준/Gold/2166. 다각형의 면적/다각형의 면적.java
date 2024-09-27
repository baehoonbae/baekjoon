import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static double[] x;
	static double[] y;

	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		x = new double[n];
		y = new double[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}

		double ans = cal();
		System.out.printf("%.1f", ans);
	}

	public static double cal() {
		double area = 0;
		int j = n - 1;
		for (int i = 0; i < n; i++) {
			area += (x[j] * y[i]) - (x[i] * y[j]);
			j = i;
		}
		return Math.abs(area / 2);
	}
}