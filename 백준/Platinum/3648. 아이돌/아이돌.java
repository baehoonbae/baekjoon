import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int v,e,id;
    static int[] d;
    static boolean[] finished;
    static List<Integer>[] adj;
    static Stack<Integer> stack;
    static List<Set<Integer>> SCC;

    public static void main(String[] args) throws IOException {
        next:
        while(true){
            String s=br.readLine();
            if(s==null)break;

            st=new StringTokenizer(s);
            v=Integer.parseInt(st.nextToken());
            e=Integer.parseInt(st.nextToken());
            id=0;

            d=new int[2*v+2];
            finished=new boolean[2*v+2];
            adj=new List[2*v+2];
            stack=new Stack<>();
            SCC=new ArrayList<>();

            for(int i=0;i<2*v+2;i++){
                adj[i]=new ArrayList<>();
            }
            adj[convert(-1)].add(convert(1));
            for(int i=0;i<e;i++){
                st=new StringTokenizer(br.readLine());
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                if(a==1){
                    adj[convert(-b)].add(convert(b));
                }else if(b==1){
                    adj[convert(-a)].add(convert(a));
                }else{
                    adj[convert(-a)].add(convert(b));
                    adj[convert(-b)].add(convert(a));
                }
            }
            for(int i=2;i<=2*v+1;i++){
                if(d[i]==0)dfs(i);
            }
            for(Set<Integer> scc:SCC){
                for(int i:scc){
                    if(scc.contains(i^1)){
                        System.out.println("no");
                        continue next;
                    }
                }
            }
            System.out.println("yes");
        }
    }

    private static int dfs(int v){
        d[v]=++id;
        stack.push(v);
        int p=d[v];

        for(int next:adj[v]){
            if(d[next]==0){
                p=Math.min(p,dfs(next));
            }else if(!finished[next]){
                p=Math.min(p,d[next]);
            }
        }
        if(p==d[v]){
            Set<Integer> scc=new HashSet<>();
            while(true){
                int t=stack.pop();
                finished[t]=true;
                scc.add(t);
                if(t==v)break;
            }
            SCC.add(scc);
        }

        return p;
    }

    // 음수->짝수, 양수->홀수
    private static int convert(int x){
        if(x<0)return (-x)*2;
        return x*2+1;
    }
}