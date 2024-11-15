import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static class Point{
        long x,y;

        Point(long x,long y){
            this.x=x;
            this.y=y;
        }

        static Point base;

        static int ccw(Point a,Point b,Point c){
            long val=(b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
            if(val==0)return 0;
            return val>0?1:-1;
        }
    }

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;

    public static void main(String[] args) throws IOException {
        n=Integer.parseInt(br.readLine());
        Point[] points=new Point[n];

        st=new StringTokenizer(br.readLine());
        long x=Long.parseLong(st.nextToken());
        long y=Long.parseLong(st.nextToken());
        points[0]=new Point(x,y);
        Point.base=points[0];

        for(int i=1;i<n;i++){
            st=new StringTokenizer(br.readLine());
            x=Long.parseLong(st.nextToken());
            y=Long.parseLong(st.nextToken());
            points[i]=new Point(x,y);
            if(x<Point.base.x||(x==Point.base.x&&y<Point.base.y)){
                Point temp=points[i];
                points[i]=points[0];
                points[0]=temp;
                Point.base=points[0];
            }
        }
        // 반시계방향이고 거리가 가까운거부터 정렬(1: 반시계, -1:시계)
        Arrays.sort(points,1,points.length,(a,b)->{
            int ccw=Point.ccw(Point.base,a,b);
            if(ccw==0){
                return Long.compare(dist(Point.base,a),dist(Point.base,b));
            }
            return (ccw==1)?-1:1;
        });
        System.out.println(scan(points));
    }

    private static int scan(Point[] points) {
        List<Point> s=new ArrayList<>();
        s.add(points[0]);
        s.add(points[1]);
        for(int i=2;i<n;i++){
            while(s.size()>1&&Point.ccw(s.get(s.size()-2),s.get(s.size()-1),points[i])!=1){
                s.remove(s.size()-1);
            }
            s.add(points[i]);
        }
        return s.size();
    }

    private static long dist(Point a, Point b) {
        return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
    }

}