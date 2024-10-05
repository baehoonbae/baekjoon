import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Line{int start,end;Line(int start,int end){this.start=start;this.end=end;}}
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Line[] lines;
    static int n;

    public static void main(String[] args)throws IOException {
        n=Integer.parseInt(br.readLine());
        lines=new Line[n];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            lines[i]=new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lines,(a,b)->{
            if(a.start==b.start){
                return b.end-a.end;
            }
            return a.start-b.start;
        });
        int start=lines[0].start;
        int end=lines[0].end;
        int ans=0;
        for(int i=1;i<n;i++){
            if(lines[i].end<=end){
                continue;
            }
            if(lines[i].start<=end){
                end=lines[i].end;
            }else{
                ans+=end-start;
                start=lines[i].start;
                end=lines[i].end;
            }
        }
        ans+=end-start;
        System.out.println(ans);
    }
}