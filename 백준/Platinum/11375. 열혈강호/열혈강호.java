import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends FI1 {
    static int n,m;
    static List<Integer>[] adj;
    static int[] matched;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        initFI();
        n=nextInt();
        m=nextInt();
        adj=new List[n+1];
        matched=new int[m+1];
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }
        Arrays.fill(matched,-1);

        for(int i=1;i<=n;i++){
            int num=nextInt();
            for(int j=0;j<num;j++){
                int a=nextInt();
                adj[i].add(a);
            }
        }
        int matches=0;
        for(int i=1;i<=n;i++){
            visited=new boolean[n+1];
            if(dfs(i))matches++;
        }
        System.out.println(matches);
    }

    private static boolean dfs(int cur){
        if(visited[cur])return false;

        visited[cur]=true;

        for(int next:adj[cur]){
            if(matched[next]==-1||dfs(matched[next])){
                matched[next]=cur;
                return true;
            }
        }
        return false;

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