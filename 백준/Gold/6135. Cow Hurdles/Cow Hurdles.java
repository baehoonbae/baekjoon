import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] arr;
    static int n,m,t;

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        t=Integer.parseInt(st.nextToken());
        arr=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            Arrays.fill(arr[i],Integer.MAX_VALUE);
            arr[i][i]=0;
        }
        for(int i=0;i<m;i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            arr[a][b]=c;
        }
        floyd();
        while(t-->0) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            System.out.println(arr[a][b]==Integer.MAX_VALUE?-1:arr[a][b]);
        }
    }

    private static void floyd() {
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(arr[i][j]>Math.max(arr[i][k],arr[k][j])){
                        arr[i][j]=Math.max(arr[i][k],arr[k][j]);
                    }
                }
            }
        }
    }
}