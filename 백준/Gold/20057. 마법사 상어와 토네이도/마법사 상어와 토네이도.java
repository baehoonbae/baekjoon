import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    static int[] dy={0,1,0,-1};
    static int[] dx={1,0,-1,0};
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int out=0;
    static int[][] arr;
    static Stack<Integer> path=new Stack<>();

    public static void main(String[] args) throws IOException {
        init();
        getPath();
        play();
        System.out.println(out);
    }

    private static void play() {
        int y=n/2+1,x=n/2+1;
        while(!path.isEmpty()){
            int d=path.pop();
            int ny=y+dy[d];
            int nx=x+dx[d];
            if(arr[ny][nx]!=0){
                spreadSand(ny,nx,d);
            }
            y=ny;
            x=nx;
        }
    }

    private static void spreadSand(int y, int x, int d) {
        int num=arr[y][x];
        arr[y][x]=0;
        int two = (int) (num*0.02);
        int seven = (int) (num*0.07);
        int ten = (int) (num*0.10);
        int one = (int) (num*0.01);
        int five = (int) (num*0.05);
        int remain = num - (two*2 + seven*2 + ten*2 + one*2 + five);
        if (d == 2) {
            spread(y-2, x, two);
            spread(y+2, x, two);

            spread(y-1, x, seven);
            spread(y+1, x, seven);

            spread(y-1, x-1, ten);
            spread(y+1, x-1, ten);

            spread(y-1, x+1, one);
            spread(y+1, x+1, one);

            spread(y, x-2, five);

            spread(y, x-1, remain);
        } else if (d==0) {
            spread(y-2, x, two);
            spread(y+2, x, two);

            spread(y-1, x, seven);
            spread(y+1, x, seven);

            spread(y-1, x+1, ten);
            spread(y+1, x+1, ten);

            spread(y-1, x-1, one);
            spread(y+1, x-1, one);

            spread(y, x+2, five);

            spread(y, x+1, remain);
        } else if (d==3) {
            spread(y, x-2, two);
            spread(y, x+2, two);

            spread(y, x-1, seven);
            spread(y, x+1, seven);

            spread(y-1, x-1, ten);
            spread(y-1, x+1, ten);

            spread(y+1, x-1, one);
            spread(y+1, x+1, one);

            spread(y-2, x, five);

            spread(y-1, x, remain);
        } else if (d==1) {
            spread(y,x-2, two);
            spread(y, x+2, two);

            spread(y, x-1, seven);
            spread(y, x+1, seven);

            spread(y+1, x-1, ten);
            spread(y+1, x+1, ten);

            spread(y-1, x-1, one);
            spread(y-1, x+1, one);

            spread(y+2, x, five);

            spread(y+1, x, remain);
        }
    }

    private static void spread(int ny,int nx,int amount) {
        if (ny<1||nx<1||ny>n||nx>n) {
            out+=amount;
        } else {
            arr[ny][nx]+=amount;
        }
    }

    private static void getPath() {
        int y=1,x=1;
        int d=0;
        path.add(2);

        boolean[][] visited=new boolean[n+1][n+1];
        visited[y][x]=true;

        for(int i=0;i<n*n-1;i++){
            int ny=y+dy[d];
            int nx=x+dx[d];
            if(ny<1||nx<1||ny>n||nx>n||visited[ny][nx]){
                d=(d+1)%4;
                ny=y+dy[d];
                nx=x+dx[d];
            }
            y=ny;
            x=nx;
            visited[y][x]=true;
            path.add((d+2)%4);
        }
    }

    private static void init() throws IOException {
        n=Integer.parseInt(br.readLine());
        arr=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
    }
}