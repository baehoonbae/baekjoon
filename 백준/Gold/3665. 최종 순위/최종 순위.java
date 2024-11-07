import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main extends FI1 {
    static int n;
    static int[] inDegree;
    static List<Integer>[] adj;
    static int[] arr;

    public static void main(String[] args)throws IOException {
        initFI();
        int t=nextInt();
        next:
        while(t-->0){
            n=nextInt();
            inDegree=new int[n+1];
            adj=new List[n+1];
            arr=new int[n+1];
            Arrays.fill(inDegree,0);

            for(int i=1;i<=n;i++){
                arr[i]=nextInt();
                adj[i]=new ArrayList<>();
            }
            for(int i=1;i<=n;i++){
                for(int j=i+1;j<=n;j++){
                    adj[arr[i]].add(arr[j]);
                    inDegree[arr[j]]++;
                }
            }
            int m=nextInt();
            for(int i=0;i<m;i++){
                int a=nextInt();
                int b=nextInt();
                if(adj[b].remove(Integer.valueOf(a))){
                    inDegree[a]--;
                    adj[a].add(b);
                    inDegree[b]++;
                }else if(adj[a].remove(Integer.valueOf(b))){
                    inDegree[b]--;
                    adj[b].add(a);
                    inDegree[a]++;
                }
            }
            StringBuilder sb=new StringBuilder();
            Queue<Integer>q=new LinkedList<>();
            for(int i=1;i<=n;i++){
                if(inDegree[i]==0)q.add(i);
            }
            if(q.size()>=2){
                System.out.println("?");
                continue;
            }
            while(!q.isEmpty()){
                int cur=q.poll();
                sb.append(cur).append(" ");
                for(int next:adj[cur]){
                    inDegree[next]--;
                    if(inDegree[next]==0)q.add(next);
                }
            }
            for(int i=1;i<=n;i++){
                if(inDegree[i]!=0){
                    System.out.println("IMPOSSIBLE");
                    continue next;
                }
            }
            System.out.println(sb);
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