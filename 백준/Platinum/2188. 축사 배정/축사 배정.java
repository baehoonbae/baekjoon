import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n,m;
	static int[] A,B;
	static boolean[] visited;
	static List<Integer>[] adj;
	
    public static void main(String[] args)throws IOException {
    	st=new StringTokenizer(br.readLine());
    	n=Integer.parseInt(st.nextToken());
    	m=Integer.parseInt(st.nextToken());
    	A=new int[n+1];
    	B=new int[m+1];
    	visited=new boolean[n+1];
    	adj=new List[n+1];
    	Arrays.fill(A,-1);
    	Arrays.fill(B,-1);
    	for(int i=1;i<=n;i++) {
    		adj[i]=new ArrayList<>();
    	}
    	for(int i=1;i<=n;i++) {
    		st=new StringTokenizer(br.readLine());
    		int cnt=Integer.parseInt(st.nextToken());
    		for(int j=0;j<cnt;j++) {
    			int num=Integer.parseInt(st.nextToken());
    			adj[i].add(num);
    		}
    	}
    	int match=0;
    	for(int i=1;i<=n;i++) {
    		if(A[i]==-1) {
    			Arrays.fill(visited,false);
    			if(dfs(i))match++;
    		}
    	}
    	System.out.println(match);
    }
    
    private static boolean dfs(int a) {
    	visited[a]=true;
    	for(int b:adj[a]) {
    		if(B[b]==-1||(!visited[B[b]]&&dfs(B[b]))) {
    			A[a]=b;
    			B[b]=a;
    			return true;
    		}
    	}
    	return false;
    }
}