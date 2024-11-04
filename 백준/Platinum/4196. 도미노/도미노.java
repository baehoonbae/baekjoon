import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main extends FI {
    static int n,m,id;
    static int[] d;
    static boolean[] finished;
    static Stack<Integer> stack;
    static List<Integer>[] adj;
    static List<Set<Integer>> SCC;
    static int[] inDegree;
    static int[] sccId;

    public static void main(String[] args)throws IOException {
        initFI();
        int t=nextInt();
        while(t-->0){
            n=nextInt();
            m=nextInt();
            id=0;
            d=new int[n+1];
            finished=new boolean[n+1];
            stack=new Stack<>();
            adj=new List[n+1];
            SCC=new ArrayList<>();
            sccId=new int[n+1];

            for(int i=0;i<=n;i++){
                adj[i]=new ArrayList<>();
            }
            for(int i=0;i<m;i++){
                adj[nextInt()].add(nextInt());
            }
            for(int i=1;i<=n;i++){
                if(d[i]==0)dfs(i);
            }
            for(int i=0;i<SCC.size();i++){
                for(int a:SCC.get(i)){
                    sccId[a]=i;
                }
            }
            inDegree=new int[SCC.size()];
            for(int i=1;i<=n;i++){
                int curscc=sccId[i];
                for(int next:adj[i]){
                    int nextscc=sccId[next];
                    if(curscc!=nextscc)inDegree[nextscc]++;
                }
            }
            int count=0;
            for(int i=0;i<inDegree.length;i++){
                if(inDegree[i]==0)count++;
            }
            System.out.println(count);
        }
    }

    private static int dfs(int v) {
        d[v]=++id;
        stack.push(v);
        int p=d[v];
        for(int next:adj[v]){
            if(d[next]==0){
                p=Math.min(p,dfs(next));
            }else if(!finished[next]){
                p=Math.min(p,d[next]);
            }
        }
        if(p==d[v]){
            Set<Integer> scc=new HashSet<>();
            while(true){
                int t=stack.pop();
                scc.add(t);
                finished[t]=true;
                if(t==v)break;
            }
            SCC.add(scc);
        }
        return p;
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
