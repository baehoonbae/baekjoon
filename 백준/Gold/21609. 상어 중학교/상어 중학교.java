import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 21609. 상어 중학교 / 골드2 / 20:37~22:26
// -1: 검은색 블록
// 0: 무지개 블록
// 그외: 일반 블록
// 필요로직
// 1. 그룹핑하기
// 1. 제일 큰그룹 없애고 점수획득
// 2. 중력
// 2. 왼쪽으로 90도 배열 돌리기
// 3. 위에떠있는애들 밑으로내리기
class Main {
    static class Info{
        List<int[]>removeList;
        int rainbow;
        int y,x;
        Info(List<int[]> removeList,int rainbow,int y,int x){
            this.removeList=removeList;
            this.rainbow=rainbow;
            this.y=y;this.x=x;
        }
    }

    static int[] dy= {-1,1,0,0};
    static int[] dx= {0,0,-1,1};
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] arr;
    static int n,m;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        arr=new int[n][n];
        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        int total=0;
        while(true) {
            Info removeInfo=new Info(new ArrayList<>(),0,0,0);
            boolean[][] visited=new boolean[n][n];

            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(arr[i][j]>0&&!visited[i][j]) {
                        visited[i][j]=true;
                        Info newInfo=bfs(i,j,arr[i][j],visited);

                        if(newInfo.removeList.size()<2) continue;

                        if(removeInfo.removeList.size()<newInfo.removeList.size()) {
                            removeInfo=newInfo;
                        }else if(removeInfo.removeList.size()==newInfo.removeList.size()){
                            if(removeInfo.rainbow<newInfo.rainbow){
                                removeInfo=newInfo;
                            }else if(removeInfo.rainbow==newInfo.rainbow){
                                if(removeInfo.y<newInfo.y){
                                    removeInfo=newInfo;
                                }else if(removeInfo.y==newInfo.y){
                                    if(removeInfo.x<newInfo.x){
                                        removeInfo=newInfo;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            List<int[]> removeList=removeInfo.removeList;
            if(removeList.isEmpty())break;

            int size=removeList.size();
            total+=size*size;

            for(int[] pos:removeList) arr[pos[0]][pos[1]]=-2;

            down();
            rotate();
            down();
        }
        System.out.println(total);
    }

    private static void rotate() {
        int[][] temp=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                temp[n-j-1][i]=arr[i][j];
            }
        }
        arr=temp;
    }

    private static void down() {
        for(int j=0;j<n;j++){
            for(int i=n-2;i>=0;i--){
                if(arr[i][j]>=0){
                    int y=i;
                    while(y+1 < n && arr[y+1][j] == -2){
                        arr[y+1][j] = arr[y][j];
                        arr[y][j] = -2;
                        y++;
                    }
                }
            }
        }
    }

    private static Info bfs(int i,int j,int num,boolean[][] visited) {
        List<int[]> rainbowList=new ArrayList<>();
        List<int[]> removeList=new ArrayList<>();
        removeList.add(new int[]{i,j});

        Queue<int[]>q=new LinkedList<>();
        q.add(new int[] {i,j});

        while(!q.isEmpty()) {
            int[] pos=q.poll();
            int y=pos[0];
            int x=pos[1];
            for(int d=0;d<4;d++) {
                int ny=y+dy[d];
                int nx=x+dx[d];

                if(ny<0||nx<0||ny>=n||nx>=n)continue;
                if(visited[ny][nx])continue;

                if(arr[ny][nx]==num||arr[ny][nx]==0){
                    if(arr[ny][nx]==0){
                        rainbowList.add(new int[]{ny, nx});
                    }
                    visited[ny][nx]=true;
                    q.add(new int[] {ny,nx});
                    removeList.add(new int[] {ny,nx});
                }
            }
        }
        for(int[] pos:rainbowList){
            visited[pos[0]][pos[1]]=false;
        }
        return new Info(removeList,rainbowList.size(),i,j);
    }
}