import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args)throws IOException {
        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n];
        int[] lis=new int[n];
        Arrays.fill(lis,1);
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    lis[i]=Math.max(lis[i],lis[j]+1);
                }
            }
        }
        int max=0;
        for(int i=0;i<n;i++){
            max=Math.max(max,lis[i]);
        }
        System.out.println(max);
    }
}