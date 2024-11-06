import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m;
    static int[] parent;
    static List<Node>[] adj;

    static class Node implements Comparable<Node> {
        int from;
        int to;
        double dist;

        Node(int from,int to,double dist){
            this.from=from;
            this.to=to;
            this.dist =dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist,o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        parent=new int[n+1];
        int[][] points=new int[n+1][2];
        adj=new List[n+1];

        for(int i=1;i<=n;i++){
            parent[i]=i;
            adj[i]=new ArrayList<>();
            st=new StringTokenizer(br.readLine());
            points[i][0]=Integer.parseInt(st.nextToken());
            points[i][1]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            union(a,b);
        }
        PriorityQueue<Node> pq=new PriorityQueue<>();
        for (int i=1; i<=n; i++) {
            for(int j=1;j<=n;j++){
                if(i==j)continue;
                pq.add(new Node(i, j, dist(points[i], points[j])));
            }
        }
        double answer=0;
        int edgeCount=0;
        while(!pq.isEmpty()&&edgeCount!=n-1){
            Node node=pq.poll();
            if(find(node.from)!=find(node.to)){
                edgeCount++;
                answer+=node.dist;
                union(node.from,node.to);
            }
        }
        System.out.printf("%.2f",answer);
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

    private static double dist(int[] a,int[] b){
        double dist=Math.pow(b[0]-a[0],2)+Math.pow(b[1]-a[1],2);
        return Math.sqrt(dist);
    }
}