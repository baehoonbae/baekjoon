import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        long a1=Long.parseLong(st.nextToken());
        long b1=Long.parseLong(st.nextToken());
        long c1=Long.parseLong(st.nextToken());

        st=new StringTokenizer(br.readLine());
        long a2=Long.parseLong(st.nextToken());
        long b2=Long.parseLong(st.nextToken());
        long c2=Long.parseLong(st.nextToken());
        while(true) {
            boolean flag=false;
            if(a1<a2&&b1<b2&&c1<c2)break;
            if (a1 < a2-3) {
                long num = (a2 - a1) / 3;
                a2 -= num * 3;
                b2 += num;
                flag=true;
            }
            if (b1 < b2-3) {
                long num = (b2 - b1) / 3;
                b2 -= num * 3;
                c2 += num;
                flag=true;
            }
            if (c1 < c2-3) {
                long num = (c2 - c1) / 3;
                c2 -= num * 3;
                a2 += num;
                flag=true;
            }
            if(!flag){
                break;
            }
        }
        long count=0;
        count+=Math.min(a1,a2);
        count+=Math.min(b1,b2);
        count+=Math.min(c1,c2);
        System.out.println(count);
    }
}