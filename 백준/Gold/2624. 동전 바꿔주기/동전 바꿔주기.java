import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t,k;
    static int[] dp;

    public static void main(String[] args)throws IOException {
        t=Integer.parseInt(br.readLine());
        k=Integer.parseInt(br.readLine());
        dp=new int[t+1];
        dp[0]=1;
        for(int i=0;i<k;i++){
            st=new StringTokenizer(br.readLine());
            int value=Integer.parseInt(st.nextToken());
            int count=Integer.parseInt(st.nextToken());
            for(int j=t;j>=0;j--){
                for(int k=1;k<=count;k++){
                    if(j-k*value>=0){
                        dp[j]+=dp[j-k*value];
                    }
                }
            }
        }
        System.out.println(dp[t]);
    }
}