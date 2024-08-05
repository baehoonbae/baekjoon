import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int standard = 5 * (n / 5);
		while (standard > 0 && (n - standard) % 2 != 0) {
			standard -= 5;
		}
		int count = standard / 5;
		n-=standard;
		if(n%2==0) {
			count+=n/2;
		}
		System.out.println(count > 0 ? count : -1);
	}
}