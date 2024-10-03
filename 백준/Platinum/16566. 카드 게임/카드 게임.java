import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
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
            idx=idx<0?-(idx+1):idx+1;
            while(idx<m&&used[idx]){idx++;}
            used[idx]=true;
            System.out.println(arr[idx]);
        }
    }
}