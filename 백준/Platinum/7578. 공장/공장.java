import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class SegTree{
        long[] tree;
        int size;
        SegTree(int n){
            tree=new long[n*4];
            size=n;
        }
        void update(int node,int start,int end,int idx){
            if(end<idx||idx<start)return;
            if(start==end){
                tree[node]++;
                return;
            }
            int mid=(start+end)/2;
            update(node*2,start,mid,idx);
            update(node*2+1,mid+1,end,idx);
            tree[node]=tree[node*2]+tree[node*2+1];
        }
        long query(int node,int start,int end,int left,int right){
            if(right<start||end<left)return 0;
            if(left<=start&&end<=right)return tree[node];
            int mid=(start+end)/2;
            return query(node*2,start,mid,left,right)+
                    query(node*2+1,mid+1,end,left,right);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n=Integer.parseInt(br.readLine());
        int[] a=new int[n+1];
        int[] b=new int[n+1];
        Map<Integer,Integer> bpos=new HashMap<>();

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            a[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
           b[i]=Integer.parseInt(st.nextToken());
           bpos.put(b[i],i);
        }
        SegTree t=new SegTree(n);
        long inversion=0;
        // a의값이 b에어디에위치한지 ㅇ찾고
        //b 이후에 처리된inversion만큼 더해야함
        for(int i=1;i<=n;i++){
            int idx=bpos.get(a[i]);
            inversion+=t.query(1,1,n,idx+1,n);
            t.update(1,1,n,idx);
        }
        System.out.println(inversion);
    }
}