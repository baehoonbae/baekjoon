import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static final int INF=1234567891;
    static int[] dy={0,1,0,-1};
    static int[] dx={1,0,-1,0};
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m,k;
    static int y1,x1,y2,x2;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr=new char[n][m];
        for(int i=0;i<n;i++){
            arr[i]=br.readLine().toCharArray();
        }
        st=new StringTokenizer(br.readLine());
        y1=Integer.parseInt(st.nextToken())-1;
        x1=Integer.parseInt(st.nextToken())-1;
        y2=Integer.parseInt(st.nextToken())-1;
        x2=Integer.parseInt(st.nextToken())-1;
        int res=play();
        System.out.println(res==INF?-1:res);
    }

    private static int play(){
        int[][] time=new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(time[i],INF);
        }

        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{y1,x1,0});
        time[y1][x1]=0;

        while(!q.isEmpty()){
            int[] pos=q.poll();
            int y=pos[0];
            int x=pos[1];
            int t=pos[2];

            if(t>time[y][x])continue;
            if(y==y2&&x==x2)break;

            for(int i=0;i<4;i++){
                for(int j=1;j<=k;j++){
                    int ny=y+j*dy[i];
                    int nx=x+j*dx[i];
                    if(ny<0||nx<0||ny>=n||nx>=m)continue;
                    if(arr[ny][nx]=='#'||time[ny][nx]==t)break;
                    if(time[ny][nx]>t+1){
                        time[ny][nx]=Math.min(time[ny][nx],t+1);
                        q.add(new int[]{ny,nx,t+1});
                    }
                }
            }
        }
        return time[y2][x2];
    }
}