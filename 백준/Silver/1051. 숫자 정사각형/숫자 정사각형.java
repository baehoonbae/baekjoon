import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb=new StringBuilder();
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int[][] arr=new int[n][m];
        for(int i=0;i<n;i++){
            String s=br.readLine();
            for(int j=0;j<m;j++){
                arr[i][j]=s.charAt(j)-'0';
            }
        }
        int answer=0;
        for(int i=0;i<Math.min(n,m);i++){
            for(int j=0;j<n-i;j++){
                for(int k=0;k<m-i;k++){
                    if(arr[j][k]==arr[j+i][k]&&
                        arr[j+i][k]==arr[j][k+i]&&
                        arr[j][k+i]==arr[j+i][k+i]&&
                        arr[j+i][k+i]==arr[j][k]){
                        answer=(int)Math.pow((i+1),2);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}