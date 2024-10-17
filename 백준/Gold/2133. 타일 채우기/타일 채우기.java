import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long[] dp;
	
	public static void main(String[] args) throws IOException {
		long n=new Scanner(System.in).nextLong();
		dp=new long[32];
		if(n%2==1) {
			System.out.print(0);
			return;
		}
		dp[2]=3;
		dp[4]=11;
		for(int i=6;i<=30;i+=2) {
			dp[i]=dp[i-2]*4-dp[i-4];
		}
		System.out.println(dp[(int)n]);
	}
}