import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static StringBuilder sb=new StringBuilder();
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,logn;
    static List<Integer>[] adj;
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        n=Integer.parseInt(br.readLine());
        adj=new List[n+1];
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        logn=(int)Math.ceil(Math.log(n)/Math.log(2));
        parent=new int[n+1][logn+1];
        depth=new int[n+1];
        dfs(1,0);
        fill();
        int m=Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            sb.append(lca(a,b)).append("\n");
        }
        System.out.print(sb);
    }

    private static int lca(int a,int b){
        if(depth[a]!=depth[b]){
            if(depth[a]>depth[b]){
                int temp=a;
                a=b;
                b=temp;
            }
            for(int i=logn;i>=0;i--){
                if(depth[a]<=depth[parent[b][i]]) {
                    b=parent[b][i];
                }
            }
        }
        int lca=a;
        if(a!=b){
            for(int i=logn;i>=0;i--){
                if(parent[a][i]!=parent[b][i]){
                    a=parent[a][i];
                    b=parent[b][i];
                }
                lca=parent[a][i];
            }
        }
        return lca;
    }

    private static void dfs(int cur,int par){
        parent[cur][0]=par;
        depth[cur]=depth[par]+1;
        for(int next:adj[cur]){
            if(next!=par){
                dfs(next,cur);
            }
        }
    }
    private static void fill(){
        for(int j=1;j<=logn;j++){
            for(int i=1;i<=n;i++){
                if(parent[i][j-1]!=0){
                    parent[i][j]=parent[parent[i][j-1]][j-1];
                }
            }
        }
    }
}