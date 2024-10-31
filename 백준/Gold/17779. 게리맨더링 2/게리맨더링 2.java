import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main extends FI {
    static final int INF = 1234567891;
    static int[][] arr;
    static int[][] gerry;
    static int n, minDiff;
    static int total;

    public static void main(String[] args) throws IOException {
        initFI();
        minDiff = INF;
        n=nextInt();
        arr = new int[n][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                arr[i][j]=nextInt();
                total+=arr[i][j];
            }
        }
        for (int x=0; x<n; x++) {
            for (int y=0; y<n; y++) {
                for (int d1=1; d1<n; d1++) {
                    for (int d2=1; d2<n; d2++) {
                        if(x+d1+d2>=n)continue;
                        if(y-d1<0||y+d2>=n)continue;
                        bt(x,y,d1,d2);
                    }
                }
            }
        }
        System.out.println(minDiff);
    }

    public static void bt(int x, int y, int d1, int d2) {
        gerry=new int[n][n];
        int[] sum=new int[5];

        // 경계선 그리기
        for (int i=0; i<=d1; i++) {
            gerry[x+i][y-i] = 5;
            gerry[x+d2+i][y+d2-i] = 5;
        }
        for (int i=0; i<=d2; i++) {
            gerry[x+i][y+i] = 5;
            gerry[x+d1+i][y-d1+i] = 5;
        }
        for (int i = 0; i < x+d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (gerry[i][j]==5) break;
                sum[0]+=arr[i][j];
            }
        }
        for (int i = 0; i <= x + d2; i++) {
            for (int j = n - 1; j > y; j--) {
                if (gerry[i][j]==5) break;
                sum[1]+=arr[i][j];
            }
        }
        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (gerry[i][j]==5) break;
                sum[2]+=arr[i][j];
            }
        }
        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= y - d1 + d2; j--) {
                if (gerry[i][j]==5) break;
                sum[3]+=arr[i][j];
            }
        }
        sum[4]=total-sum[0]-sum[1]-sum[2]-sum[3];
        Arrays.sort(sum);
        minDiff = Math.min(minDiff, sum[4]-sum[0]);
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