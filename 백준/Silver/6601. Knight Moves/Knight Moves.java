import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dy={-2,-2,-1,-1,1,1,2,2};
    static final int[] dx={-1,1,-2,2,-2,2,-1,1};

    public static void main(String[] args) throws IOException {
        String s="";
        while((s=br.readLine())!=null&&!s.trim().isEmpty()){
            st=new StringTokenizer(s);

            String s1=st.nextToken();
            String s2=st.nextToken();

            int y1=(s1.charAt(1)-'0')-1;
            int y2=(s2.charAt(1)-'0')-1;
            int x1=s1.charAt(0)-'a';
            int x2=s2.charAt(0)-'a';
            int move= play(y1,x1,y2,x2);
            System.out.println("To get from "+s1+" to "+s2+" takes "+move+ " knight moves. ");
        }
    }

    private static int play(int y1,int x1,int y2,int x2){
        boolean[][] visited=new boolean[8][8];
        Queue<int[]>q=new LinkedList<>();
        q.add(new int[]{y1,x1,0});
        visited[y1][x1]=true;

        while(!q.isEmpty()){
            int[] pos=q.poll();
            int y=pos[0];
            int x=pos[1];
            int move=pos[2];

            if(y==y2&&x==x2)return move;

            for(int i=0;i<8;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(ny<0||nx<0||ny>=8||nx>=8)continue;
                if(visited[ny][nx])continue;

                visited[ny][nx]=true;
                q.add(new int[]{ny,nx,move+1});
            }
        }
        return -1;
    }
}