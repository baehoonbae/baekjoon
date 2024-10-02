import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 왼쪽 정렬 -> 오른쪽 LIS 구하기 -> n - (LIS 길이)
public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] arr;
    static int[] lis;

    public static void main(String[] args) throws IOException {
        n=Integer.parseInt(br.readLine());
        arr=new int[n][2];
        lis=new int[n];
        Arrays.fill(lis,1);
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr,(a,b)->a[0]-b[0]);
        int max=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++) {
                if(arr[j][1]>arr[i][1]){
                    lis[j]=Math.max(lis[j],lis[i]+1);
                    max=Math.max(max,lis[j]);
                }
            }
        }
        System.out.println(n-max);
    }
}