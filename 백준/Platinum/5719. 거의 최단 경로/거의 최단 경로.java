import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main extends FI1 {
    static final int INF = 1234567891;
    static int n,m;
    static int min;
    static Set<String> minPath;
    static List<Edge>[] fadj;   // forward adjacent
    static List<Edge>[] radj;   // forward adjacent

    static class Edge{
        int from,to,cost;
        Edge(int from,int to,int cost){
            this.from=from;
            this.to=to;
            this.cost=cost;
        }
        String getKey(){
            return from+","+to;
        }
    }

    static class Node implements Comparable<Node>{
        int to,cost;
        Node(int to,int cost){
            this.to=to;
            this.cost=cost;
        }
        @Override
        public int compareTo(Node o){
            return this.cost-o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        initFI();
        while(true){
            min=INF;
            minPath = new HashSet<>();
            n=nextInt();
            m=nextInt();
            if(n==0&&m==0)return;

            fadj =new List[n];
            radj =new List[n];
            for(int i=0;i<n;i++){
                fadj[i]=new ArrayList<>();
                radj[i]=new ArrayList<>();
            }
            int start=nextInt();
            int end=nextInt();
            for(int i=0;i<m;i++){
                int a=nextInt();
                int b=nextInt();
                int c=nextInt();
                fadj[a].add(new Edge(a,b,c));
                radj[b].add(new Edge(b,a,c));
            }
            int[] fmin=new int[n];
            int[] rmin=new int[n];
            dijkstra(start,end,fmin,true);
            dijkstra(end,start,rmin,false);
            for(int i=0;i<n;i++){
                for(Edge e:fadj[i]){
                    if(fmin[e.from]+e.cost+rmin[e.to]==min){
                        minPath.add(e.getKey());
                    }
                }
            }
            System.out.println(dijkstra2(start, end));
        }
    }

    private static void dijkstra(int start,int end,int[] arr,boolean isf){
        PriorityQueue<Node>pq=new PriorityQueue<>();
        pq.add(new Node(start,0));

        Arrays.fill(arr,INF);
        arr[start]=0;

        while(!pq.isEmpty()){
            Node cur=pq.poll();
            List<Edge>[] adj=isf?fadj:radj;

            for(Edge next: adj[cur.to]){
                if(arr[next.to]>arr[cur.to]+next.cost){
                    arr[next.to]=arr[cur.to]+next.cost;
                    pq.add(new Node(next.to,arr[next.to]));
                }
            }
        }
        min=arr[end];
    }

    private static int dijkstra2(int start,int end){
        int[] arr=new int[n];
        PriorityQueue<Node>pq=new PriorityQueue<>();
        Arrays.fill(arr,INF);
        arr[start]=0;
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur=pq.poll();
            if(cur.cost > arr[cur.to]) continue;

            for(Edge next: fadj[cur.to]){
                if(minPath.contains(next.getKey()))continue;

                if(arr[next.to]>arr[cur.to]+next.cost){
                    arr[next.to]=arr[cur.to]+next.cost;
                    pq.add(new Node(next.to,arr[next.to]));
                }
            }
        }
        return arr[end]==INF?-1:arr[end];
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