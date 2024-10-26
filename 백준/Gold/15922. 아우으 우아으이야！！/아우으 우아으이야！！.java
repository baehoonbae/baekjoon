import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Line[] arr;

    static class Line{
        int start,end;
        Line(int start,int end){this.start=start;this.end=end;}
    }

    public static void main(String[] args)throws IOException {
        n=Integer.parseInt(br.readLine());
        arr=new Line[n];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            arr[i]=new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        long sum=0;
        int start=arr[0].start;
        int end=arr[0].end;
        for(int i=1;i<n;i++){
            if(arr[i].start>end){
                sum+=end-start;
                start=arr[i].start;
            }
            if(arr[i].end>end)end=arr[i].end;
        }
        sum+=end-start;
        System.out.println(sum);
    }
}