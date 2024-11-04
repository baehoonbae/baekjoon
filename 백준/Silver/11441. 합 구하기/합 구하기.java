import java.io.DataInputStream;
import java.io.IOException;

public class Main extends FI {
    static int[] arr;
    static int[] tree;
    static int n;

    public static void main(String[] args)throws IOException {
        initFI();
        StringBuilder sb=new StringBuilder();
        n=nextInt();
        arr=new int[n+1];
        tree=new int[4*n];

        for(int i=1;i<=n;i++){
            arr[i]=nextInt();
        }
        build(1,1,n);
        int q=nextInt();
        for(int i=0;i<q;i++){
            int a=nextInt();int b=nextInt();
            sb.append(query(1,1,n,a,b)).append("\n");

        }
        System.out.print(sb);
    }

    private static void build(int node,int start,int end){
        if(start==end){
            tree[node]=arr[start];
            return;
        }
        int mid=(start+end)/2;
        build(node*2,start,mid);
        build(node*2+1,mid+1,end);
        tree[node]=tree[node*2]+tree[node*2+1];
    }

    private static int query(int node,int start,int end,int left,int right){
        if(right<start||end<left){
            return 0;
        }
        if(left<=start&&end<=right){
            return tree[node];
        }
        int mid=(start+end)/2;
        return query(node*2,start,mid,left,right)+query(node*2+1,mid+1,end,left,right);
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