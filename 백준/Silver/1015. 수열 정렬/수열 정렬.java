import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb=new StringBuilder();
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        int[] arr=new int[n];
        int[] sorted=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            sorted[i]=arr[i];
        }
        Arrays.sort(sorted);
        boolean[] visited=new boolean[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[j]&&arr[i]==sorted[j]) {
                    visited[j]=true;
                    System.out.print(j+" ");
                    break;
                }
            }
        }
    }
}