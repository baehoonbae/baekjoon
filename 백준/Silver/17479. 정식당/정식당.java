import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main extends FI {


    public static void main(String[] args) throws IOException {
        initFI();
        int a=nextInt();
        int b=nextInt();
        int c=nextInt();
        Map<String,Integer> menu1=new HashMap<>();
        Map<String,Integer> menu2=new HashMap<>();
        Set<String> menu3=new HashSet<>();
        for(int i=0;i<a;i++){
            String s=next();
            int num=nextInt();
            menu1.put(s,num);
        }
        for(int i=0;i<b;i++){
            String s=next();
            int num=nextInt();
            menu2.put(s,num);
        }
        for(int i=0;i<c;i++){
            String s=next();
            menu3.add(s);
        }
        int n=nextInt();
        long sum1=0;
        long sum2=0;
        int special=0;
        boolean flag=false;
        String[] order=new String[n];
        for(int i=0;i<n;i++){
            String s=next();
            order[i]=s;
            if(menu1.containsKey(s)){
                sum1+=menu1.get(s);
            }else if(menu2.containsKey(s)){
                sum2+=menu2.get(s);
            }else if(menu3.contains(s)){
                flag=true;
                special++;
            }
        }
        if((sum2>0&&sum1<20000)||(sum1+sum2<50000&&flag)||special>1){
            System.out.println("No");
            return;
        }
//        while(n-->0){
//            String s=next();
//
//            if(menu1.containsKey(s)){
//                sum1+=menu1.get(s);
//            }else if(menu2.containsKey(s)){
//                if(sum1<20000){
//                    System.out.println("No");
//                    return;
//                }
//                sum2+=menu2.get(s);
//            }else if(menu3.contains(s)){
//                if(sum1+sum2<50000||flag){
//                    System.out.println("No");
//                    return;
//                }
//                flag=true;
//            }
//        }
        System.out.println("Okay");
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