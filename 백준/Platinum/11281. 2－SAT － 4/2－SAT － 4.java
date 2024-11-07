import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main extends FI1 {
    static int n,m,id;
    static Map<Integer,Integer> d;
    static Map<Integer,List<Integer>> adj;
    static Set<Integer> finished;
    static Stack<Integer> stack;
    static List<Set<Integer>> SCC;
    static Map<Integer,Integer> sccNum;

    public static void main(String[] args)throws IOException {
        initFI();

        n=nextInt();m=nextInt();id=0;
        d=new HashMap<>();
        adj=new HashMap<>();
        finished=new HashSet<>();
        stack=new Stack<>();
        SCC=new ArrayList<>();
        sccNum=new HashMap<>();

        for(int i=0;i<m;i++){
            int a=nextInt();int b=nextInt();
            adj.computeIfAbsent(-a,no->new ArrayList<>()).add(b);
            adj.computeIfAbsent(-b,no->new ArrayList<>()).add(a);
        }
        for(int i:adj.keySet()){
            if(!d.containsKey(i))dfs(i);
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<SCC.size();i++){
            Set<Integer> scc=SCC.get(i);
            for(int j:scc){
                if(scc.contains(-j)) {
                    System.out.println(0);
                    return;
                }
                sccNum.put(j,i);
            }
        }
        sb.append(1).append("\n");
        for(int i=1;i<=n;i++){
            sb.append(sccNum.getOrDefault(-i,0)<sccNum.getOrDefault(i,0)?0:1).append(" ");
        }
        System.out.print(sb);
    }

    private static int dfs(int v){
        d.put(v,++id);
        stack.push(v);
        int p=d.getOrDefault(v,0);
        for(int next:adj.getOrDefault(v,new ArrayList<>())){
            if(!d.containsKey(next)){
                p=Math.min(p,dfs(next));
            }else if(!finished.contains(next)){
                p=Math.min(p,d.getOrDefault(next,0));
            }
        }
        if(p==d.get(v)){
            Set<Integer> scc=new HashSet<>();
            while(true){
                int t=stack.pop();
                finished.add(t);
                scc.add(t);
                if(t==v)break;
            }
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