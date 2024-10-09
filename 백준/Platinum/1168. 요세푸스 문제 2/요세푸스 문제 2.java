import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,k;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        tree=new int[4*n+1];

        init(1,1,n);
        StringBuilder sb=new StringBuilder();
        sb.append("<");
        int idx=k-1;
        for(int i=0;i<n;i++){
            int index=query(1,1,n,idx+1);
            sb.append(index).append(", ");
            if(tree[1]==0)break;
            idx+=k-1;
            idx%=tree[1];
        }
        sb.setLength(sb.length()-2);
        sb.append(">");
        System.out.print(sb);
    }

    private static void init(int node, int start, int end) {
        if(start==end){
            tree[node]=1;
            return;
        }
        int mid=(start+end)/2;
        init(node*2,start,mid);
        init(node*2+1,mid+1,end);
        tree[node]=tree[node*2]+tree[node*2+1];
    }

    private static int query(int node,int start,int end,int index) {
        tree[node]--;
        if(start==end){
            return start;
        }
        int mid=(start+end)/2;
        if(tree[node*2]>=index){
            return query(node*2,start,mid,index);
        }else{
            return query(node*2+1,mid+1,end,index-tree[node*2]);
        }
    }
}