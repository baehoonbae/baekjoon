import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Line {
        long x1,y1,x2,y2;
        Line(long x1, long y1,long x2,long y2){this.x1=x1;this.y1=y1;this.x2=x2;this.y2=y2;}
    }

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Line[] lines;
    static int[] parent,rank;

    public static void main(String[] args)throws IOException {
        n=Integer.parseInt(br.readLine());
        lines =new Line[n];
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            rank[i]=1;
            st=new StringTokenizer(br.readLine());
            long x1=Long.parseLong(st.nextToken());
            long y1=Long.parseLong(st.nextToken());
            long x2=Long.parseLong(st.nextToken());
            long y2=Long.parseLong(st.nextToken());
            lines[i]=new Line(x1,y1,x2,y2);
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(cross(lines[i],lines[j])){
                    if(find(i)!=find(j)){
                        union(i,j);
                    }
                }
            }
        }
        Map<Integer,Integer> group=new HashMap<>();
        for(int i=0;i<n;i++){
            group.merge(find(i),1,Integer::sum);
        }
        int max=0;
        for(Map.Entry<Integer,Integer> entry:group.entrySet()){
            max=Math.max(entry.getValue(),max);
        }
        System.out.println(group.size());
        System.out.println(max);
    }

    private static boolean cross(Line l1,Line l2){
        int ccw1=ccw(l1.x1,l1.y1,l1.x2,l1.y2,l2.x1,l2.y1)*ccw(l1.x1,l1.y1,l1.x2,l1.y2,l2.x2,l2.y2);
        int ccw2=ccw(l2.x1,l2.y1,l2.x2,l2.y2,l1.x1,l1.y1)*ccw(l2.x1,l2.y1,l2.x2,l2.y2,l1.x2,l1.y2);
        if(ccw1<=0&&ccw2<=0){
            if(ccw1==0&&ccw2==0){
                return Math.max(l2.x1, l2.x2) >= Math.min(l1.x1, l1.x2) &&
                        Math.max(l1.x1, l1.x2) >= Math.min(l2.x1, l2.x2) &&
                        Math.max(l2.y1, l2.y2) >= Math.min(l1.y1, l1.y2) &&
                        Math.max(l1.y1, l1.y2) >= Math.min(l2.y1, l2.y2);
            }
            return true;
        }
        return false;
    }

    private static int ccw(long x1,long y1,long x2,long y2,long x3,long y3){
        long res=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1);
        if(res>0) return -1;
        else if(res==0) return 0;
        else return 1;
    }

    private static int find(int x){
        if(x!=parent[x]){
            parent[x]=find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a,int b){
        a=find(a);
        b=find(b);
        if(a==b){
            return;
        }
        if(rank[a]>rank[b]){
            parent[b]=a;
        }else if(rank[a]<rank[b]){
            parent[a]=b;
        }else{
            parent[b]=a;
            rank[a]++;
        }
    }
}