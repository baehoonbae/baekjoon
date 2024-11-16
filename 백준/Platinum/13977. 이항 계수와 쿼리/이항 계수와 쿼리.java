import java.io.DataInputStream;
import java.io.IOException;

public class Main extends FI1 {
    static final long MOD=1_000_000_007;

    public static void main(String[] args) throws IOException {
        initFI();

        long[] fact=new long[4000011];
        long[] ifact=new long[4000011];

        fact[0]=fact[1]=1;
        for(int i=2;i<=4000000;i++){
            fact[i]=(fact[i-1]*i)%MOD;
        }

        ifact[4000000]=mpow(fact[4000000],MOD-2);
        for(int i=3999999;i>=1;i--){
            ifact[i]=(ifact[i+1]*(i+1))%MOD;
        }
        ifact[0]=1;

        StringBuilder sb=new StringBuilder();
        int m=nextInt();
        for(int t=0;t<m;t++){
            int n=nextInt();
            int k=nextInt();

            // 4,000,000*100,000 이될수있어서 터진다
            // 전처리해서 처리해야할듯하다
            long num1=fact[n];
            long num2=(ifact[k]*ifact[n-k])%MOD;

            // 모듈러한것끼리 나누기 불가능함(원래의 비율을 나타낼 수 없다)
            // a/b %MOD => (a*b^(-1)) %MOD  (페르마 소정리)
            // => (a*b^(MOD-2)%MOD)%MOD
            // num2 를 MOD-2번 거듭제곱(하면서 %MOD)
            // 애초에 팩토리얼의 역원을 먼저 구해놓는것도 더 최적화가능할것같다.

            sb.append((num1*num2)%MOD).append("\n");
        }
        System.out.print(sb);
    }

    private static long mpow(long mul,long b){
        long res=1;
        while(b>0){
            if(b%2==1) res=(res*mul)%MOD;

            mul=(mul*mul)%MOD;
            b/=2;
        }
        return res;
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