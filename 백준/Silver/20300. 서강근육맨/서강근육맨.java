import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        long[] arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        long min=Long.MIN_VALUE;
        int i=n-1;
        if(n%2==1)i--;
        int j=0;
        for(;i>=n/2;i--,j++){
            min=Math.max(min,arr[i]+arr[j]);
        }
        System.out.println(min);
    }
}