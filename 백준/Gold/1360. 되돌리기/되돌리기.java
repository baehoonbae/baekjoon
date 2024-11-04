import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1360. 되돌리기 / / 11:11 ~
public class Main extends FI1 {
    static class Info{
        String command;
        char value;
        int back;
        int time;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder answer=new StringBuilder();
        int n=Integer.parseInt(br.readLine());
        Info[] info=new Info[n];
        boolean[] states=new boolean[n];

        for(int i=0;i<n;i++){
            info[i]=new Info();
            st=new StringTokenizer(br.readLine());
            String command=st.nextToken();
            info[i].command=command;
            if(command.equals("type")){
                char value=st.nextToken().charAt(0);
                int time=Integer.parseInt(st.nextToken());
                info[i].value=value;
                info[i].time=time;
            }else{
                int back=Integer.parseInt(st.nextToken());
                int time=Integer.parseInt(st.nextToken());
                info[i].back=back;
                info[i].time=time;
            }
        }

        for(int i=n-1;i>=0;i--){
            if(states[i])continue;
            if(info[i].command.equals("type"))continue;

            states[i]=true;
            int newTime=info[i].time-info[i].back;

            for(int j=i-1;j>=0;j--){
                if(info[j].time<newTime)break;
                states[j]=true;
            }
        }
        for(int i=0;i<n;i++){
            if(states[i])continue;
            answer.append(info[i].value);
        }
        System.out.println(answer);
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