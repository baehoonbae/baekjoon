import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends FI1 {
    static int[] parent;
    static int[] size;
    static int n,m;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        initFI();
        n=nextInt();
        m=nextInt();
        parent=new int[n+1];
        size=new int[n+1];
        adj=new List[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
            adj[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int a=nextInt();
            int b=nextInt();
            adj[a].add(b);
            adj[b].add(a);
            union(a,b);
        }
        for(int i=1;i<=n;i++){
            find(i);
            size[parent[i]]++;
        }
        long total=1;
        for(int i=1;i<=n;i++){
            if(size[i]!=0)total*=size[i];
            total%=1_000_000_007;
        }
        System.out.println(total);

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
        double ret = 0, div=1;
        byte c = read();
        while (c <= ' ') c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);
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