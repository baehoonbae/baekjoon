import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        long x,y;
        Point(long x,long y){
            this.x=x;
            this.y=y;
        }
    }
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

        Point A=new Point(x1,y1);
        Point B=new Point(x2,y2);
        Point C=new Point(x3,y3);
        Point D=new Point(x4,y4);
        play(A,B,C,D);
    }

    private static void play(Point A,Point B,Point C,Point D) {
        long S1,S2,S3,S4;
        long S12,S34;
        S1=ccw(A,B,C);
        S2=ccw(A,B,D);
        S3=ccw(C,D,A);
        S4=ccw(C,D,B);
        S12=S1*S2;
        S34=S3*S4;
        if(S12<=0&&S34<0||S12<0&&S34<=0){   // 1
            System.out.println(1);
            double[] ans=slope(A,B,C,D);
            System.out.println(ans[0]+" "+ans[1]);
        }else if(S12==0&&S34==0){   // 2
            if(S1==0&&S2==0&&S3==0&&S4==0){
                int n=isCrossed(A,B,C,D);
                if(n>0){
                    System.out.println(1);
                }else{
                    System.out.println(0);
                }
                if(n==2){
                    if(A.x==C.x&&A.y==C.y||A.x==D.x&&A.y==D.y){
                        System.out.println(A.x+" "+A.y);
                    } else if(B.x==C.x&&B.y==C.y||B.x==D.x&&B.y==C.y){
                        System.out.println(B.x+" "+B.y);
                    }
                }
            }else {
                System.out.println(1);
                if(A.x==C.x&&A.y==C.y||A.x==D.x&&A.y==D.y){
                    System.out.println(A.x+" "+A.y);
                } else if(B.x==C.x&&B.y==C.y||B.x==D.x&&B.y==D.y){
                    System.out.println(B.x+" "+B.y);
                }
            }
        }else{  // 3
            System.out.println(0);
        }
    }

    private static long ccw(Point A,Point B,Point C) {
        long ccw=(B.x-A.x)*(C.y-A.y)-(B.y-A.y)*(C.x-A.x);
        if(ccw<0)return -1;
        if(ccw>0)return 1;
        return 0;
    }

    private static double[] slope(Point A,Point B,Point C,Point D) {
        double m1=(double)(B.y-A.y)/(B.x-A.x);
        double m2=(double)(D.y-C.y)/(D.x-C.x);

        double b1=A.y-m1*A.x;
        double b2=C.y-m2*C.x;

        double x=(b1-b2)/(m2-m1);
        double y=m1*x+b1;

        if(A.x==B.x) {
            x=A.x;
            y=m2*x+b2;
        }else if(C.x==D.x) {
            x=C.x;
            y=m1*x+b1;
        }
        return new double[] {x,y};
    }

    private static int isCrossed(Point A,Point B,Point C,Point D){
        long a,b,c,d;
        if(A.x==B.x){
            a=Math.min(A.y,B.y);
            b=Math.max(A.y,B.y);
            c=Math.min(C.y,D.y);
            d=Math.max(C.y,D.y);
        }else{
            a=Math.min(A.x,B.x);
            b=Math.max(A.x,B.x);
            c=Math.min(C.x,D.x);
            d=Math.max(C.x,D.x);
        }
        if(a==d||b==c){
            return 2;
        }else if(a<d&&c<b){
            return 1;
        }else {
            return 0;
        }
    }
}