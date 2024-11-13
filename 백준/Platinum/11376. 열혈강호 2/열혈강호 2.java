import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m;
    static int[] p,j;
    static boolean[] visited;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        p =new int[n+1];
        j =new int[m+1];
        visited=new boolean[n+1];
        adj=new List[n+1];
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }
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
            for(int j=0;j<2;j++){
                visited=new boolean[1011];
                if(dfs(i)){
                    match++;
                }
            }
        }
        System.out.println(match);
    }

    private static boolean dfs(int cur){
        visited[cur]=true;
        for(int next:adj[cur]){
            if(j[next]==0||!visited[j[next]]&&dfs(j[next])){
                j[next]=cur;
                return true;
            }
        }
        return false;
    }
}