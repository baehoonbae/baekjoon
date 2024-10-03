import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static boolean[] used;
    static int n,m,k;

    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr=new int[m];
        used=new boolean[m];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            arr[i]=Integer.parseInt(st.nextToken());
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
            while(idx<m&&used[idx]){idx++;}
            used[idx]=true;
            bw.write(arr[idx]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}