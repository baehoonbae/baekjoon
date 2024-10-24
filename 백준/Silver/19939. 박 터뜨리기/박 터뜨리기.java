import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args)throws IOException {
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        int sum=0;
        for(int i=1;i<=k;i++){
            sum+=i;
        }
        if(sum>n){
            System.out.println(-1);
            return;
        }
        int min=1;
        int max=k;
        int num=n-sum;
        if(num%k!=0){
            System.out.println(k-min+1); 
        }else{
            System.out.println(k-min);
        }
        
    }
}