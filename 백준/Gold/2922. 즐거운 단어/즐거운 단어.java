import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends FI {
    static List<Integer> indices = new ArrayList<>();
    static int n;
    static long count;
    static char[] s;

    public static void main(String[] args) throws IOException {
        initFI();
        s = next().toCharArray();
        n=s.length;

        play(0,0,0,false,1);
        System.out.println(count);
    }

    private static void play(int idx,int mo,int ja,boolean l,long c) {
        if (mo>=3||ja>=3)return;
        if (idx==s.length) {
            if(l)count+=c;
            return;
        }
        if (s[idx] == '_') {
            play(idx+1, mo+1, 0, l, c * 5);
            play(idx+1, 0, ja+1, l, c * 20);
            play(idx+1, 0, ja+1, true, c);
        }
        else {
            play(idx + 1, isMo(s[idx]) ? mo + 1 : 0, isJa(s[idx]) ? ja + 1 : 0, l || s[idx] == 'L', c);
        }
    }

    private static boolean isMo(char ch) {
        return ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U';
    }

    private static boolean isJa(char ch) {
        return !isMo(ch);
    }
}

class FI {
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