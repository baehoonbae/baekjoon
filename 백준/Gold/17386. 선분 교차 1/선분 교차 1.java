import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        long x1=Long.parseLong(st.nextToken());
        long y1=Long.parseLong(st.nextToken());
        long x2=Long.parseLong(st.nextToken());
        long y2=Long.parseLong(st.nextToken());

        st=new StringTokenizer(br.readLine());
        long x3=Long.parseLong(st.nextToken());
        long y3=Long.parseLong(st.nextToken());
        long x4=Long.parseLong(st.nextToken());
        long y4=Long.parseLong(st.nextToken());

        int ccw1=ccw(x1,y1,x2,y2,x3,y3)*ccw(x1,y1,x2,y2,x4,y4);
        int ccw2=ccw(x3,y3,x4,y4,x1,y1)*ccw(x3,y3,x4,y4,x2,y2);
        if(ccw1==0&&ccw2==0){
            if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2) &&
                Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2)) {
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }else if(ccw1<=0&&ccw2<=0){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    private static int ccw(long x1,long y1,long x2,long y2,long x3,long y3){
        long res= (x2-x1)*(y3-y1)-(y2-y1)*(x3-x1);
        if(res>0){
            return 1;
        }else if(res<0){
            return -1;
        }else{
            return 0;
        }
    }
}