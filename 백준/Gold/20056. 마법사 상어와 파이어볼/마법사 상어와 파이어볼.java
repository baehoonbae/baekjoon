import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 20056. 마법사 상어와 파이어볼 / 골드4 / 9:28~
class Main {
    static class FireBall{
        int y,x,m,s,d;
        FireBall(int y,int x,int m,int s,int d){
            this.y=y;this.x=x;this.m=m;this.s=s;this.d=d;
        }
    }

    static final int[] dy={-1,-1,0,1,1,1,0,-1};
    static final int[] dx={0,1,1,1,0,-1,-1,-1};
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m,k;
    static List<FireBall>[][] arr;
    static List<FireBall> fbList=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr=new List[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=new ArrayList<>();
            }
        }
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken())-1;
            int x=Integer.parseInt(st.nextToken())-1;
            int m=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            FireBall fb=new FireBall(y,x,m,s,d);
            arr[y][x].add(fb);
            fbList.add(fb);
        }
        while(k-->0){
            move();
            check();
        }
        int answer=0;
        for(FireBall fb:fbList){
            answer+=fb.m;
        }
        System.out.println(answer);
    }

    private static void move() {
        List<FireBall> moved=new ArrayList<>();  // 이동 후의 파이어볼 리스트

        for(FireBall fb:fbList){
            int ny=(fb.y+dy[fb.d]*fb.s)%n;
            int nx=(fb.x+dx[fb.d]*fb.s)%n;

            if(ny<0)ny=(ny+n)%n;
            if(nx<0)nx=(nx+n)%n;
            fb.y=ny;
            fb.x=nx;
            moved.add(fb);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j].clear();
            }
        }
        for(FireBall fb:moved){
            arr[fb.y][fb.x].add(fb);
        }
        fbList=moved;
    }

    private static void check() {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j].size()>=2){
                    arr[i][j]=merge(arr[i][j],i,j);
                }
            }
        }
    }

    private static List<FireBall> merge(List<FireBall> fireBalls,int y,int x) {
        List<FireBall> newFbList=new ArrayList<>();
        int mTotal=0;
        int sTotal=0;
        int even=0,odd=0;
        for(FireBall fb:fireBalls){
            mTotal+=fb.m;
            sTotal+=fb.s;
            if(fb.d%2==0)even++;
            else odd++;
        }
        mTotal/=5;
        sTotal/=fireBalls.size();

        clear(fireBalls);

        if(mTotal==0)return newFbList;

        if(odd==0||even==0){
            for(int i=0;i<=6;i+=2){
                newFbList.add(new FireBall(y,x,mTotal,sTotal,i));
                fbList.add(new FireBall(y,x,mTotal,sTotal,i));
            }
        }else{
            for(int i=1;i<=7;i+=2){
                newFbList.add(new FireBall(y,x,mTotal,sTotal,i));
                fbList.add(new FireBall(y,x,mTotal,sTotal,i));
            }
        }
        return newFbList;
    }

    private static void clear(List<FireBall> fireBalls) {
        for(FireBall fb:fireBalls){
            fbList.remove(fb);
        }
    }
}






















