import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main extends FI1 {
    static final int[] dy={-1,1,0,0};
    static final int[] dx={0,0,-1,1};
    static int n,m;
    static int[][] arr;

    static class Node implements Comparable<Node>{
        int y,x,cnt;

        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Node o){
            return this.cnt-o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        initFI();
        m=nextInt();
        n=nextInt();
        arr=new int[n][m];
        for(int i=0;i<n;i++){
            String s=next();
            for(int j=0;j<m;j++){
                arr[i][j]=s.charAt(j)-'0';
            }
        }
        play();
    }

    private static void play(){
        int[][] time=new int[n][m];
        PriorityQueue<Node>q=new PriorityQueue<>();
        q.add(new Node(0,0,0));
//        int answer=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            Arrays.fill(time[i],Integer.MAX_VALUE);
        }
        time[0][0]=0;

        while(!q.isEmpty()){
            Node cur=q.poll();
            int y=cur.y;
            int x=cur.x;
            int cnt=cur.cnt;

            if(y==n-1&&x==m-1){
                System.out.println(cnt);
                break;
            }
            for(int i=0;i<4;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(ny<0||nx<0||ny>=n||nx>=m)continue;
                if(arr[ny][nx]==0){
                    if(time[ny][nx]>time[y][x]){
                        time[ny][nx]=time[y][x];
                        q.add(new Node(ny,nx,cnt));
                    }
                }else{
                    if(time[ny][nx]>time[y][x]+1){
                        time[ny][nx]=time[y][x]+1;
                        q.add(new Node(ny,nx,cnt+1));
                    }
                }
            }
        }
    }
}
class FI1 {
    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    protected static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    protected static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    protected static double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (c == '.') while ((c = read()) >= '0' && c <= '9')
            ret += (c - '0') / (div *= 10);
        return ret;
    }

    protected static long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    protected static String next() throws IOException {
        StringBuilder sb = new StringBuilder();
        byte c = read();

        // 공백 문자는 무시하고 다음 문자를 읽음
        while (c <= ' ') c = read();

        // 공백이 아닌 문자들을 읽어 문자열 생성
        while (c > ' ') {
            sb.append((char) c);
            c = read();
        }
        return sb.toString();
    }

    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}