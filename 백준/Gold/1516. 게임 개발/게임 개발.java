import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] inDegree;
    static int[] time;
    static int[] dp;
    static List<Integer>[] adj;

    public static void main(String[] args)throws IOException {
        n=Integer.parseInt(br.readLine());

        inDegree=new int[n+1];
        time=new int[n+1];
        dp=new int[n+1];
        adj=new List[n+1];
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }

        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            time[i]=Integer.parseInt(st.nextToken());
            dp[i]=time[i];
            while(true){
                int num=Integer.parseInt(st.nextToken());
                if(num==-1)break;
                adj[num].add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> q=new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(inDegree[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int cur=q.poll();
            for(int next:adj[cur]){
                dp[next]=Math.max(dp[next],dp[cur]+time[next]);
                inDegree[next]--;
                if(inDegree[next]==0){
                    q.add(next);
                }
            }
        }
        for(int i=1;i<=n;i++){
            System.out.println(dp[i]);
        }
    }
}