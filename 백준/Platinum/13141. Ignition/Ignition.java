import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main extends FI {
    static final int INF=1234567891;
    static int n, m;
    static double[][] arr;
    static double[][] d;

    public static void main(String[] args) throws IOException {
        initFI();
        n = nextInt();
        m = nextInt();
        arr=new double[n+1][n+1];
        for(int i=1;i<=n;i++){
            Arrays.fill(arr[i],INF);
            arr[i][i]=0;
        }
        d=new double[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int a=nextInt(),b=nextInt();
            double len=nextDouble();
            arr[a][b]=Math.min(len,arr[a][b]);
            arr[b][a]=Math.min(len,arr[b][a]);
            d[a][b]=Math.max(len,d[a][b]);
            d[b][a]=Math.max(len,d[b][a]);
        }
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if (arr[i][j]>arr[i][k]+arr[k][j]){
                        arr[i][j]=arr[i][k]+arr[k][j];
                    }
                }
            }
        }
        double answer=INF;
        for(int i=1;i<=n;i++){
            double time=0;
            for(int from=1;from<=n;from++){
                for(int to=1;to<=n;to++){
                    double l=arr[i][from];
                    double r=arr[i][to];

                    if(d[from][to]==0)continue;

                    double start=Math.min(l,r);
                    double burn=Math.abs(l-r)+(d[from][to]-Math.abs(l-r))/2;

                    time=Math.max(time,start+burn);
                }
            }
            answer=Math.min(answer,time);
        }
        System.out.printf("%.1f",answer);
    }
}

class FI {
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
