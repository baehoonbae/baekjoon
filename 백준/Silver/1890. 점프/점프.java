import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long[][] arr,dp;
    static int n;

    public static void main(String[] args) throws IOException {
        n=Integer.parseInt(br.readLine());
        arr=new long[n][n];
        dp=new long[n][n];
        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0]=1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]==0)continue;
                int k=(int)arr[i][j];
                int ny=i+k,nx=j+k;
                if(ny<n) dp[ny][j]+=dp[i][j];
                if(nx<n) dp[i][nx]+=dp[i][j];
            }
        }
        System.out.println(dp[n-1][n-1]);
    }
}