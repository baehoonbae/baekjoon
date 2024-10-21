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
    static boolean[] primes=new boolean[2333];
    static boolean[] visited;
    static List<Integer>[] adj;
    static int[] A,B,arr;
    static int n;

    public static void main(String[] args)throws IOException {
        Arrays.fill(primes,true);
        sieve();

        n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        arr=new int[n];
        adj=new List[n];
        visited=new boolean[n];
        A=new int[n];

        for(int i=0;i<n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;
                if(primes[arr[i]+arr[j]]){
                    adj[i].add(j);
                }
            }
        }
        List<Integer> answer=new ArrayList<>();
        for(int next:adj[0]){
            Arrays.fill(A,-1);
            A[0]=next;
            A[next]=0;
            int match=1;
            for(int i=1;i<n;i++){
                if(i!=next&&A[i]==-1){
                    Arrays.fill(visited,false);
                    visited[0]=visited[next]=true;
                    if(dfs(i)){
                        match++;
                    }
                }
            }
            if(match==n/2){
                answer.add(arr[next]);
            }
        }
        if(answer.isEmpty()) {
            System.out.println(-1);
        }else{
            answer.sort(null);
            for(int x:answer){
                System.out.print(x+" ");
            }
        }
    }

    private static boolean dfs(int a){
        visited[a]=true;
        for(int b:adj[a]){
            if(A[b]==-1||(!visited[A[b]]&&dfs(A[b]))){
                A[a]=b;
                A[b]=a;
                return true;
            }
        }
        return false;
    }

    private static void sieve(){
        primes[0]=primes[1]=false;
        for(int i=2;i<=Math.sqrt(2333);i++){
            if(!primes[i])continue;
            for(int j=i*i;j<2333;j+=i){
                primes[j]=false;
            }
        }
    }
}