import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            int n=Integer.parseInt(br.readLine());
            int[][] dp=new int[n+11][4];
            dp[1][1]=1;
            dp[2][1]=1;
            dp[2][2]=1;
            dp[3][1]=1;
            dp[3][2]=1;
            dp[3][3]=1;

            //1
            //1

            //2
            //11
            //2

            //3
            //111
            //21
            //3

            //4
            //1111
            //211
            //31
            //22

            //5
            //11111
            //2111
            //311
            //221
            //32

            //6
            //111111
            //21111
            //3111
            //2211
            //321
            //312
            //222
            //33

            //7
            //1111111
            //211111
            //31111
            //22111
            //3211
            //3121
            //2221
            //331
            //322
            for(int i=4;i<=n;i++){
                dp[i][1]=dp[i-1][1];
                dp[i][2]=dp[i-2][1]+dp[i-2][2];
                dp[i][3]=dp[i-3][1]+dp[i-3][2]+dp[i-3][3];
            }
            System.out.println(dp[n][1]+dp[n][2]+dp[n][3]);
        }
    }
}