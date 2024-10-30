import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

class Main extends FI1 {
    static int n,m;
    static int start,end;
    static int max;
    static List<Node>[] adj1;
    static List<Node>[] adj2;
    static int[] fdegree;
    static int[] ftime;
    static int[] rtime;

    static class Node{
        int to,cost;
        Node(int to,int cost){
            this.to=to;
            this.cost=cost;
        }
    }

    public static void main(String[] args)throws IOException {
        initFI();
        n=nextInt();m=nextInt();
        adj1=new List[n+1];
        adj2=new List[n+1];
        fdegree=new int[n+1];
        ftime=new int[n+1];
        rtime=new int[n+1];
        Arrays.fill(ftime,-1);
        Arrays.fill(rtime,-1);
        for(int i=1;i<=n;i++){
            adj1[i]=new ArrayList<>();
            adj2[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            int a=nextInt();
            int b=nextInt();
            int c=nextInt();
            adj1[a].add(new Node(b,c));
            adj2[b].add(new Node(a,c));
            fdegree[b]++;
        }

        start=nextInt();
        end=nextInt();
        max=ftopol();
        System.out.println(max);
        System.out.println(rtopol());
    }

    // 정방향, 역방향 각 인덱스 값의 합이 topology 결괏값과 동일하면 그 인덱스는 경로에 포함됨
    private static int ftopol() {
        Queue<Node>q=new LinkedList<>();
        q.add(new Node(start,0));
        ftime[start]=0;

        while(!q.isEmpty()){
            Node cur=q.poll();
            for(Node next:adj1[cur.to]){
                ftime[next.to]=Math.max(ftime[next.to],ftime[cur.to]+next.cost);
                fdegree[next.to]--;
                if(fdegree[next.to]==0){
                    q.add(next);
                }
            }
        }
        return ftime[end];
    }

    private static int rtopol() {
        boolean[] visited=new boolean[n+1];
        Queue<Node>q=new LinkedList<>();
        q.add(new Node(end,0));
        visited[end]=true;

        int count=0;
        while(!q.isEmpty()){
            Node cur=q.poll();
            for(Node next:adj2[cur.to]){
                if(ftime[next.to]+next.cost==ftime[cur.to]){
                    count++;
                    if(!visited[next.to]){
                        q.add(next);
                        visited[next.to]=true;
                    }
                }
            }
        }
        return count;
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