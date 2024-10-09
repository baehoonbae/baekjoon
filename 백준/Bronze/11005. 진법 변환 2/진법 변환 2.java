import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        int a=Integer.parseInt(st.nextToken());
        int b=Integer.parseInt(st.nextToken());
        StringBuilder s= new StringBuilder();
        if(b==10){
            System.out.println(a);
            return;
        }
        while(a>0){
            if(a%b<10) s.append(a % b);
            else s.append((char)(a%b-10+'A'));
            a/=b;
        }
        System.out.println(s.reverse());
    }
}