import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main extends FI1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[] strs=br.readLine().split(" ");
        String[] s=strs[0].split(":");
        String[] e=strs[1].split(":");
        String[] q=strs[2].split(":");
        int sh=Integer.parseInt(s[0]);
        int sm=Integer.parseInt(s[1]);
        int eh=Integer.parseInt(e[0]);
        int em=Integer.parseInt(e[1]);
        int qh=Integer.parseInt(q[0]);
        int qm=Integer.parseInt(q[1]);
        int count=0;
        Set<String> set=new HashSet<>();
        Set<String> check=new HashSet<>();
        while(true){
            String ss=br.readLine();
            if(ss==null||ss.trim().isEmpty())break;
            st=new StringTokenizer(ss);
            String time=st.nextToken();
            String[] times=time.split(":");
            int h=Integer.parseInt(times[0]);
            int m=Integer.parseInt(times[1]);
            String name=st.nextToken();

            if((h<sh)||(h==sh&&m<=sm)){
                set.add(name);
            }
            if ((h>eh||(h==eh&&m>=em))&&(h<qh||(h==qh&&m<=qm))) {
                if(set.contains(name)){
                    check.add(name);
                }
            }
        }
        System.out.println(check.size());
    }
}

class FI1 {
    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    protected static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    protected static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    protected static double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (c == '.') while ((c = read()) >= '0' && c <= '9')
            ret += (c - '0') / (div *= 10);
        return ret;
    }

    protected static long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    protected static String next() throws IOException {
        StringBuilder sb = new StringBuilder();
        byte c = read();

        // 공백 문자는 무시하고 다음 문자를 읽음
        while (c <= ' ') c = read();

        // 공백이 아닌 문자들을 읽어 문자열 생성
        while (c > ' ') {
            sb.append((char) c);
            c = read();
        }
        return sb.toString();
    }

    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}