import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Enemy{
        int row,col;

        public Enemy(int row,int col) {
            this.row=row;
            this.col=col;
        }

        public Enemy clone() {
            return new Enemy(row, col);
        }
    }

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m,d;
    static int[][] arr;
    static List<Enemy> elist =new ArrayList<>();

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        d=Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
                if(arr[i][j]==1)elist.add(new Enemy(i,j));
            }
        }
        int max=0;
        for(int i=0;i<m;i++){
            for(int j=i+1;j<m;j++){
                for(int k=j+1;k<m;k++){
                    List<Enemy> enemies = new ArrayList<>();
                    for(Enemy e: elist){
                        enemies.add(e.clone());
                    }
                    max=Math.max(max,play(i,j,k,enemies));
                }
            }
        }
        System.out.println(max);
    }

    private static int play(int i,int j,int k,List<Enemy>q) {
        int count=0;
        int[] pos={i,j,k};
        while(!q.isEmpty()){
            Set<Enemy>target=select(q,pos);
            count+=remove(q,target);
            move(q);
        }
        return count;
    }

    private static Set<Enemy> select(List<Enemy> enemies, int[] pos) {
        Set<Enemy> t=new HashSet<>();

        for (int ap:pos) {
            Enemy nearest = null;
            int minDist = Integer.MAX_VALUE;
            for (Enemy e:enemies) {
                int dist=dist(e.row,e.col,n,ap);
                if (dist<=d&&(dist<minDist||(dist == minDist && e.col < nearest.col))) {
                    minDist = dist;
                    nearest = e;
                }
            }
            if (nearest != null) {
                t.add(nearest);
            }
        }
        return t;
    }

    private static void move(List<Enemy>q) {
        q.removeIf(e->{
            e.row++;
            return e.row>=n;
        });
    }

    private static int remove(List<Enemy> q,Set<Enemy>target) {
        q.removeAll(target);
        return target.size();
    }

    private static int dist(int y1,int x1,int y2,int x2){
        return Math.abs(y1-y2)+Math.abs(x1-x2);
    }
}