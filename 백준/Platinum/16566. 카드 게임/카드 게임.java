import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static int[] parent;
    static int n,m,k;

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr=new int[m];
        parent=new int[m];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            parent[i]=i;
        }
        Arrays.sort(arr);
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            int num=Integer.parseInt(st.nextToken());
            int idx=Arrays.binarySearch(arr,num);
            if(idx<0){
                idx=-(idx+1);
            }else{
                idx++;
            }
            int cardIdx=find(idx);
            if(cardIdx<m-1){
                union(cardIdx+1,cardIdx);
            }
            bw.write(arr[cardIdx]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int x){
        if(x!=parent[x]){
            return parent[x]=find(parent[x]);
        }
        return x;
    }

    private static void union(int a,int b){
        a=find(a);
        b=find(b);
        if(a==b) return;
        parent[b]=a;
    }
}