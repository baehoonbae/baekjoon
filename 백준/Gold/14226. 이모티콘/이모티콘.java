import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int minTime=Integer.MAX_VALUE;

    public static void main(String[] args)throws IOException {
        n=Integer.parseInt(br.readLine());
        play();
        System.out.println(minTime);
    }

    private static void play(){
        boolean[][] visited=new boolean[1111][1111];
        Queue<int[]>q=new LinkedList<>();
        q.add(new int[]{0,1,0});
        visited[1][0]=true;

        while(!q.isEmpty()){
            int[] cur=q.poll();

            int time=cur[0];
            int num=cur[1];
            int clipboard=cur[2];

            if(num==n)minTime=Math.min(minTime,time);

            if(num+clipboard<=n&&!visited[num+clipboard][clipboard]){
                if(time>=minTime)continue;
                if(num>=n)continue;
                q.add(new int[]{time+1,num+clipboard,clipboard});
                visited[num+clipboard][clipboard]=true;
            }

            if(num-1>0&&!visited[num-1][clipboard]){
                q.add(new int[]{time+1,num-1,clipboard});
                visited[num-1][clipboard]=true;
            }
            if(num!=clipboard&&!visited[num][num]){
                q.add(new int[]{time+1,num,num});
            }

        }
    }
}