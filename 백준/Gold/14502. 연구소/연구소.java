import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 14502. 연구소 / 골드4 / 11:12~11:41
public class Main {
    static int[][] arr;
    static int n, m, result = 0;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static List<int[]> list=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==2){
                    list.add(new int[]{i,j});
                }
            }
        }
        wall(0,0,0);
        System.out.println(result);
    }

    private static void wall(int y,int x,int count){
        if(count==3){
            result = Math.max(result, play());
            return;
        }
        for(int i=y;i<n;i++){
            for(int j=(i==y?x:0);j<m;j++){
                if(arr[i][j]==0){
                    arr[i][j]=1;
                    wall(i,j+1,count+1);
                    arr[i][j]=0;
                }
            }
        }
    }

    private static int play(){
        int[][] temp=new int[n][m];
        for(int i=0;i<n;i++){
            temp[i]=arr[i].clone();
        }
        boolean[][] visited=new boolean[n][m];
        Queue<int[]> q=new LinkedList<>(list);
        while(!q.isEmpty()){
            int[] pos=q.poll();
            int y=pos[0];
            int x=pos[1];
            for(int i=0;i<4;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(ny<0||nx<0||ny>=n||nx>=m)continue;
                if(visited[ny][nx]||temp[ny][nx]!=0)continue;
                q.add(new int[]{ny,nx});
                visited[ny][nx]=true;
                temp[ny][nx]=2;
            }
        }
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(temp[i][j]==0){
                    count++;
                }
            }
        }
        return count;
    }
}