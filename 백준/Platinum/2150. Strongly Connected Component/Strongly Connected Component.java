import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Main extends FI1 {
    static int v,e,id;
    static int[] d;
    static boolean[] finished;
    static List<Integer>[] adj;
    static Stack<Integer> stack;
    static List<List<Integer>> SCC;


    public static void main(String[] args) throws IOException {
        initFI();
        v=nextInt();
        e=nextInt();

        adj=new List[v+1];
        for(int i=0;i<=v;i++){
            adj[i]=new ArrayList<>();
        }
        d=new int[v+1];
        finished=new boolean[v+1];
        stack=new Stack<>();
        SCC =new ArrayList<>();
        id=0;
        for(int i=0;i<e;i++){
            int a=nextInt();
            int b=nextInt();
            adj[a].add(b);
        }
        for(int i=1;i<=v;i++){
            if(d[i]==0)dfs(i);
            Collections.sort(SCC,(a, b)->a.get(0)-b.get(0));
        }
        System.out.println(SCC.size());
        for(int i = 0; i< SCC.size(); i++){
            for(int num: SCC.get(i)){
                System.out.print(num+" ");
            }
            System.out.println(-1);
        }
    }

    private static int dfs(int v) {
        d[v]=++id;
        stack.push(v);
        int p=d[v];

        for(int next:adj[v]){
            if(d[next]==0){             // 방문하지 않은 곳이라면
                p=Math.min(p,dfs(next));
            }else if(!finished[next]){  // 방문했지만 아직 처리중이라면(아직 scc가 아닌 정점)
                p=Math.min(p,d[next]);
            }
        }
        // 부모노드가 자기 자신인 경우이다(
        if(p==d[v]){
            ArrayList<Integer>scc=new ArrayList<>();
            while(true){
                int t=stack.pop();
                scc.add(t);
                finished[t]=true;
                if(t==v)break;
            }
            Collections.sort(scc);
            SCC.add(scc);
        }
        return p;
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