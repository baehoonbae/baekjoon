import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        List<Integer> a=new ArrayList<>();
        List<Integer> b=new ArrayList<>();
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            a.add(Integer.parseInt(st.nextToken()));
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            b.add(Integer.parseInt(st.nextToken()));
        }
        List<Integer> t=new ArrayList<>();
        t.addAll(a);
        t.addAll(b);
        Collections.sort(t);
        for(int i:t){
            System.out.println(i);
        }
    }
}