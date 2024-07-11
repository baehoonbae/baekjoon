import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int idx;
	static int next;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] arr = new String[3];
		for (int i = 0; i < 3; i++) {
			arr[i] = br.readLine();
			if (!arr[i].equals("Fizz") && !arr[i].equals("FizzBuzz") && !arr[i].equals("Buzz")) {
				idx = i;
				next = Integer.parseInt(arr[i]);
			}
		}
		for (int i = idx; i < 3; i++) {
			next++;
		}
		if (next % 3 == 0 && next % 5 == 0) {
			bw.write("FizzBuzz");
		} else if (next % 3 == 0 && next % 5 != 0) {
			bw.write("Fizz");
		} else if (next % 3 != 0 && next % 5 == 0) {
			bw.write("Buzz");
		} else {
			bw.write(String.valueOf(next));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}