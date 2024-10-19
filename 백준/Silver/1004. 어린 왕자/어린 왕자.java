import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb=new StringBuilder();
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int t=Integer.parseInt(br.readLine());
        while(t-->0){
            st=new StringTokenizer(br.readLine());
            int y1=Integer.parseInt(st.nextToken());
            int x1=Integer.parseInt(st.nextToken());
            int y2=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());

            int n=Integer.parseInt(br.readLine());
            int count=0;
            while(n-->0){
                st=new StringTokenizer(br.readLine());
                int y=Integer.parseInt(st.nextToken());
                int x=Integer.parseInt(st.nextToken());
                int r=Integer.parseInt(st.nextToken());

                if (Math.sqrt(Math.pow(y-y1,2)+Math.pow(x-x1,2))<=r !=
                    Math.sqrt(Math.pow(y-y2,2)+Math.pow(x-x2,2))<=r) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}