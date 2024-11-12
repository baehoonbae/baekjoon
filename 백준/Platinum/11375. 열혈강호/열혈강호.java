import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m;
    static List<Integer>[] adj;
    static int[] matched;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        adj=new List[n+1];
        matched=new int[m+1];
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }
        Arrays.fill(matched,-1);
        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            int num=Integer.parseInt(st.nextToken());
            for(int j=0;j<num;j++){
                int a=Integer.parseInt(st.nextToken());
                adj[i].add(a);
            }
        }
        int match=0;
        for(int i=1;i<=n;i++){
            visited=new boolean[n+1];
            if(dfs(i))match++;
        }
        System.out.println(match);
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