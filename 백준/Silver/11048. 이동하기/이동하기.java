import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		arr=new int[n+1][m+1];
		dp=new int[n+1][m+1];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0]=arr[0][0];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(i-1>=0) {
					dp[i][j]=Math.max(dp[i][j], dp[i-1][j]+arr[i][j]);
				}
				if(j-1>=0) {
					dp[i][j]=Math.max(dp[i][j], dp[i][j-1]+arr[i][j]);					
				}
				if(i-1>=0&&j-1>=0) {
					dp[i][j]=Math.max(dp[i][j], dp[i-1][j-1]+arr[i][j]);
				}
			}
		}
		
		System.out.println(dp[n-1][m-1]);
	}
}