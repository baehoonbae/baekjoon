import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int x = sc.nextInt();
        int[] coin=new int[4];
        int[] value={1,5,10,25};
        int[][] dp=new int[x+1][5];
        for(int i=0;i<4;i++){
            coin[i]=sc.nextInt();
        }
        for(int i=0;i<=x;i++)Arrays.fill(dp[i],-1);
        for(int i=0;i<=4;i++)dp[0][i]=0;

        for(int i=1;i<=x;i++){
            for(int j=0;j<4;j++){
                if(dp[i][j]==-1) dp[i][j]=0;
                if(i-value[j]<0) continue;
                if(dp[i-value[j]][4]>dp[i][4]){
                    if(coin[j]>=(dp[i-value[j]][j]+1)){
                        for(int k=0;k<4;k++){
                            dp[i][k]=dp[i-value[j]][k];
                        }
                        dp[i][4]=dp[i-value[j]][4]+1;
                        dp[i][j]++;
                    }
                }
            }
        }
        //dp[1][1,0,0,0]
        //dp[2][2,0,0,0] => dp[2][0]= dp[1][0]+1 (dp[1][0]+1<=coin[0])
        //dp[3][3,0,0,0] => dp[3][0]= dp[2][0]+1 (dp[2][0]+1<=coin[0])
        //dp[4][4,0,0,0] => dp[4][0]= dp[3][0]+1 (dp[3][0]+1<=coin[0])
        //dp[5][5,0,0,0] => dp[5][0]= dp[4][0]+1 (dp[4][0]+1<=coin[0])
//        dp[6][1,1,0,0] => (dp[5][0]+1>value[0]) => dp[6][0]=1, dp[6][1]=1
//
//        dp[7][2,1,0,0]
//        dp[8][1,0,0,0]
//        dp[9][1,0,0,0]
//        dp[10][1,0,0,0]
//        dp[11][1,0,0,0]
//        dp[12][1,0,0,0]
        for(int i=0;i<4;i++){
            System.out.print(dp[x][i]+" ");
        }
    }
}