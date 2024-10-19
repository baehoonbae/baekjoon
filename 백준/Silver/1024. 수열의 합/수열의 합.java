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
        int l=Integer.parseInt(st.nextToken());
        for(int i=l;i<=100;i++){
            int temp=n-(i*(i-1))/2;
            if(temp%i==0&&temp/i>=0){
                int x=temp/i;
                for(int j=0;j<i;j++){
                    System.out.print((x+j)+" ");
                }
                return;
            }
        }
        System.out.println(-1);
    }
}