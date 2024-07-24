import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 2448. 별 찍기 - 11 / 골드4 / 1시간 24분
// 분할 정복에 확실히 약하다는걸 깨달음
// 무엇보다 규칙 찾는게 제일 어렵다
public class Main {
	static String[] star = { "  *  ", " * * ", "*****", };
	static char[][] arr = new char[3100][6200];

	public static void recursion(int n, int y, int x) {
		if (n == 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					arr[y + i][x + j] = star[i].charAt(j);
				}
			}
			return;
		}
		recursion(n / 2, y, x + n / 2);
		recursion(n / 2, y + n / 2, x);
		recursion(n / 2, y + n / 2, x + n);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		recursion(n, 0, 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n * 2 - 1; j++) {
				bw.write(arr[i][j] == '*' ? '*' : ' ');
			}
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
		return;
	}
}