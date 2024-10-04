import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Line {
        long x1,y1,x2,y2;
        int id;
        Line(long x1,long y1,long x2,long y2,int id){
            this.x1=x1;this.y1=y1;this.x2=x2;this.y2=y2;
            this.id=id;
        }

        long getY(long x){
            if(x2==x1)return Math.min(y1,y2);
            return y1+(y2-y1)*(x-x1)/(x2-x1);
        }
    }

    static class Event implements Comparable<Event>{
        long x;
        int type,id;
        Line line;

        Event(long x,int type,Line line){
            this.x=x;
            this.type=type;
            this.id=line.id;
            this.line=line;
        }
        @Override
        public int compareTo(Event o) {
            if(this.x==o.x)return Integer.compare(this.type,o.type);
            return Long.compare(this.x,o.x);
        }
    }

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Line[] lines;

    public static void main(String[] args) throws IOException {
        n=Integer.parseInt(br.readLine());
        lines=new Line[n];
        List<Event> events=new ArrayList<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            long x1=Long.parseLong(st.nextToken());
            long y1=Long.parseLong(st.nextToken());
            long x2=Long.parseLong(st.nextToken());
            long y2=Long.parseLong(st.nextToken());
            if(x1>x2||(x1==x2&&y1>y2)){
                long tempX=x1,tempY=y1;
                x1=x2;
                y1=y2;
                x2=tempX;
                y2=tempY;
            }
            lines[i]=new Line(x1,y1,x2,y2,i);
            events.add(new Event(x1,1,lines[i]));
            events.add(new Event(x2,-1,lines[i]));
        }
        Collections.sort(events);
        TreeSet<Line>active=new TreeSet<>((a,b)->{
            if(a.equals(b))return 0;
            long ay=a.getY(events.get(0).x);
            long by=b.getY(events.get(0).x);
            if(ay==by)return Integer.compare(a.id,b.id);
            return Long.compare(ay,by);
        });
        for(Event e:events){
            Line cur=e.line;
            if(e.type==1){
                Line lower=active.lower(cur);
                Line higher=active.higher(cur);
                if(lower!=null&&intersect(lower,cur)){
                    System.out.println(1);
                    return;
                }
                if(higher!=null&&intersect(higher,cur)){
                    System.out.println(1);
                    return;
                }
                active.add(cur);
            }else{
                Line lower=active.lower(cur);
                Line higher=active.higher(cur);
                if(lower!=null&&higher!=null&&intersect(lower,higher)){
                    System.out.println(1);
                    return;
                }
                active.remove(cur);
                if(!events.isEmpty()){
                    Event nextE=events.get(0);
                    if(nextE!=null&&nextE.line!=cur&&intersect(cur,nextE.line)){
                        System.out.println(1);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }

    private static boolean intersect(Line l1, Line l2) {
        long ccw1=ccw(l1.x1,l1.x2,l2.x1,l1.y1,l1.y2,l2.y1)*ccw(l1.x1,l1.x2,l2.x2,l1.y1,l1.y2,l2.y2);
        long ccw2=ccw(l2.x1,l2.x2,l1.x1,l2.y1,l2.y2,l1.y1)*ccw(l2.x1,l2.x2,l1.x2,l2.y1,l2.y2,l1.y2);
        if(ccw1<=0&&ccw2<=0){
            if(ccw1==0&&ccw2==0){
                return Math.max(l1.x1,l1.x2)>=Math.min(l2.x1,l2.x2)&&
                        Math.max(l2.x1,l2.x2)>=Math.min(l1.x1,l1.x2)&&
                        Math.max(l1.y1,l1.y2)>=Math.min(l2.y1,l2.y2)&&
                        Math.max(l2.y1,l2.y2)>=Math.min(l1.y1,l1.y2);
            }
            return true;
        }
        return false;
    }

    private static long ccw(long x1,long x2,long x3,long y1,long y2,long y3){
        long res=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1);
        if(res>0)return 1;
        if(res<0)return -1;
        return 0;
    }
}