import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	static final int[] dy= {-1,1,0,0};
	static final int[] dx= {0,0,-1,1};
	static StringBuilder sb=new StringBuilder();
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] arr;
	static int[][] counts;
	
	public static void main(String[] args) throws IOException{
		int n=Integer.parseInt(br.readLine());
		arr=new char[n][n];
		counts=new int[n][n];
		String s=br.readLine();
		for(int i=0;i<n;i++) {
			arr[0][i]=s.charAt(i);
			sb.append(arr[0][i]);
		}
		sb.append("\n");
		
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]=='#') {
					counts[i][j]++;
					for(int k=0;k<4;k++) {
						int ny=i+dy[k];					
						int nx=j+dx[k];
						if(ny<0||nx<0||ny>=n||nx>=n)continue;
						counts[ny][nx]++;
					}
				}
			}
			for(int j=0;j<n;j++) {
				if(counts[i][j]%2==0) {
					arr[i+1][j]=arr[i][j]=='#'?'#':'.';
					sb.append(arr[i+1][j]);
				}else {
					arr[i+1][j]=arr[i][j]=='#'?'.':'#';
					sb.append(arr[i+1][j]);
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}