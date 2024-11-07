import java.io.DataInputStream;
import java.io.IOException;

public class Main extends FI1 {
    static int[] parent;

    public static void main(String[] args)throws IOException {
        initFI();
        int t=nextInt();
        for(int T=1;T<=t;T++){
            int n=nextInt();
            parent=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
            }
            int k=nextInt();
            for(int i=0;i<k;i++){
                int a=nextInt();int b=nextInt();
                union(a,b);
            }
            int m=nextInt();
            StringBuilder sb=new StringBuilder();
            sb.append("Scenario ").append(T).append(":\n");
            for(int i=0;i<m;i++){
                int a=nextInt();int b=nextInt();
                sb.append(find(a)==find(b)?1:0).append("\n");
            }
            System.out.println(sb);
        }
    }

    private static int find(int x){
        if(x==parent[x])return x;
        return parent[x]=find(parent[x]);
    }

    private static void union(int a,int b){
        a=find(a);
        b=find(b);
        if(a==b)return;
        parent[b]=a;
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