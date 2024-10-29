import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Main extends FI1 {
    static class Sand{
        int y,x,hp,space;
        Sand(int y,int x,int hp,int space){
            this.y=y;
            this.x=x;
            this.hp=hp;
            this.space=space;
        }
    }

    static final int[] dy={-1,1,0,0,-1,-1,1,1};
    static final int[] dx={0,0,-1,1,-1,1,-1,1};
    static Sand[][] arr;
    static int n,m;
    static Queue<Sand> sdq =new LinkedList<>(); //Sand Delete Queue

    public static void main(String[] args)throws IOException {
        initFI();
        n=nextInt();m=nextInt();
        arr=new Sand[n][m];
        for(int i=0;i<n;i++){
            String s=next();
            for(int j=0;j<m;j++){
                if(s.charAt(j)=='.'){
                    arr[i][j]=new Sand(i,j,0,0);
                }else{
                    arr[i][j]=new Sand(i,j,s.charAt(j)-'0',0);
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j].hp!=0){
                    for(int k=0;k<8;k++){
                        int ny=i+dy[k];
                        int nx=j+dx[k];
                        if(ny<0||nx<0||ny>=n||nx>=m)continue;
                        if(arr[ny][nx].hp==0)arr[i][j].space++;
                    }
                    if (arr[i][j].hp<=arr[i][j].space){
                        sdq.add(arr[i][j]);
                    }
                }
            }
        }
        int time=0;
        while(true){
            if(sdq.isEmpty())break;
//            print();
            delete();
            time++;
        }
        System.out.println(time);
    }

    private static void print() {
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j].hp==0) System.out.print('.');
                else System.out.print(arr[i][j].hp);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("--------------------");
    }

    private static void delete() {
        int size=sdq.size();
        for(int s=0;s<size;s++){
            Sand cur=sdq.poll();
            arr[cur.y][cur.x]=new Sand(cur.y,cur.x,0,0);
            for(int i=0;i<8;i++){
                int ny=cur.y+dy[i];
                int nx=cur.x+dx[i];

                if(ny<0||nx<0||ny>=n||nx>=m)continue;
                if(arr[ny][nx].hp==0)continue;
                if(arr[ny][nx].hp<=arr[ny][nx].space)continue;

                arr[ny][nx].space++;
                if(arr[ny][nx].hp<=arr[ny][nx].space){
                    sdq.add(arr[ny][nx]);
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