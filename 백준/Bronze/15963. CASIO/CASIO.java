import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    //    static BufferedWriter bw;
    static long n;
    static long m;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        System.out.println(n == m ? 1 : 0);
    }
}
