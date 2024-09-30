import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        int y1=Integer.parseInt(st.nextToken());
        int x1=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int y2=Integer.parseInt(st.nextToken());
        int x2=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int y3=Integer.parseInt(st.nextToken());
        int x3=Integer.parseInt(st.nextToken());

        int result=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1);
        if(result>0){
            System.out.println(-1);
        }else if(result<0){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
}