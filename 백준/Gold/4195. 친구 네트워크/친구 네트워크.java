import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends FI1 {
    static int id;
    static int[] parent;
    static int[] rank;
    static int[] count;
    static Map<String,Integer> ids;

    public static void main(String[] args) throws IOException {
        initFI();
        int t=nextInt();
        while(t-->0){
            int n=nextInt();
            id=1;
            parent=new int[200011];
            rank=new int[200011];
            count=new int[200011];
            ids=new HashMap<>();
            for(int i=1;i<=200000;i++){
                parent[i]=i;
                rank[i]=1;
                count[i]=1;
            }
            for(int i=1;i<=n;i++){
                String a=next();
                String b=next();
                ids.putIfAbsent(a,id++);
                ids.putIfAbsent(b,id++);

                int aid=ids.get(a);
                int bid=ids.get(b);
                union(aid,bid);
                int root=find(aid);
                System.out.println(count[root]);
            }
        }
    }

    private static int find(int x){
        if(x==parent[x]){
            return x;
        }
        return parent[x]=find(parent[x]);
    }

    private static void union(int a,int b){
        a=find(a);
        b=find(b);
        if(a==b)return;
        if(rank[a]>rank[b]){
            parent[b]=a;
            count[a]+=count[b];
        }else if(rank[b]>rank[a]){
            parent[a]=b;
            count[b]+=count[a];
        }else{
            rank[a]++;
            parent[b]=a;
            count[a]+=count[b];
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