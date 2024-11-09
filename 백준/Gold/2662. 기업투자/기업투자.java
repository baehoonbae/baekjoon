import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends FI1 {

    public static void main(String[] args)throws IOException {
        initFI();
        int n=nextInt();
        int m=nextInt();
        int[][] arr=new int[301][21];
        int[][] dp=new int[21][301];
        int[][][] parent=new int[21][301][2];

        for(int i=1;i<=n;i++){
            nextInt();
            for(int j=1;j<=m;j++){
                arr[i][j]=nextInt();
            }
        }
        for(int i=1; i<=m; i++){
            for(int j=0; j<=n; j++){
                dp[i][j]=Integer.MIN_VALUE;
            }
        }
        dp[0][0] = 0;

        for(int i=1;i<=m;i++){
            for(int j=0;j<=n;j++){
                for(int k=0;k<=j;k++){
                    if(dp[i][j]<dp[i-1][j-k]+arr[k][i]){
                        dp[i][j]=dp[i-1][j-k]+arr[k][i];
                        parent[i][j]=new int[]{i-1,j-k};
                    }
                }
            }
        }
        List<Integer>list=new ArrayList<>();
        int i=m,j=n;
        while(i>0&&j>0){
            int[] prev=parent[i][j];
            int used=j-prev[1];
            list.add(used);
            i=prev[0];
            j=prev[1];
        }
        System.out.println(dp[m][n]);
        while(list.size()!=m){
            list.add(0);
        }
        for(int idx=list.size()-1;idx>=0;idx--){
            System.out.print(list.get(idx)+" ");
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