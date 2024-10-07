import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF=1234567891;
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m;
    static int[] price;
    static List<Node>[] adj;

    static class Node implements Comparable<Node> {
        int to;
        int dist;

        Node(int to,int dist){
            this.to=to;this.dist=dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist-o.dist;
        }
    }

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        adj=new List[n+1];
        price=new int[n+1];

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            price[i]=Integer.parseInt(st.nextToken());
            adj[i]=new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int idx=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int dist=Integer.parseInt(st.nextToken());
            adj[idx].add(new Node(to, dist));
            adj[to].add(new Node(idx, dist));
        }

        System.out.println(bfs());
    }

    private static int bfs(){
        int[] dist=new int[n+1];
        Arrays.fill(dist, INF);
        dist[1]=0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node node=pq.poll();
            int cur=node.to;
            int d=node.dist;

            for(Node next:adj[cur]){
                if(dist[next.to]>dist[cur]+next.dist){
                    dist[next.to]=dist[cur]+next.dist;
                    pq.add(new Node(next.to,dist[next.to]));
                }
            }
        }
        return dist[n];
    }
}