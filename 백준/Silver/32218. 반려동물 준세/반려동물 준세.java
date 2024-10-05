import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] arr;

    public static void main(String[] args)throws IOException {
        n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        int count=0;
        while(true){
            count++;
            int[] newArr=arr.clone();
            for(int i=0;i<n;i++){
                int cnt=0;
                for(int j=i+1;j<n;j++){
                    if(arr[i]<arr[j]){
                        cnt++;
                    }
                }
                newArr[i]=cnt;
            }
            boolean flag=true;
            for(int i=0;i<n;i++){
                if(arr[i]!=newArr[i]){
                    flag=false;
                    break;
                }
            }
            if(flag){
                System.out.println(count);
                return;
            }
            arr=newArr;
        }
    }
}