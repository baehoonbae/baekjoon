import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Main extends FI1 {
    static int max;
    static String s;
    static int n;
    static Set<String> set=new HashSet<>();

    public static void main(String[] args)throws IOException {
        initFI();
        n=nextInt();
        s=next();
        play();
        System.out.println(max);
    }

    // 1. left
    // 2. right
    // 3. flag
    // 4. count
    private static void play(){
        Queue<int[]>q=new LinkedList<>();
        q.add(new int[]{0,s.length()-1,0,0});
        set.add(0+","+(s.length()-1));

        while(!q.isEmpty()){
            int[] info=q.poll();

            int left=info[0];
            int right=info[1];
            int flag=info[2];
            int count=info[3];

            max=Math.max(max,count);

            if(left>right) return;

            if(flag==0){
                if(s.charAt(left)=='B'){
                    String key=(left+1)+","+right;
                    if (!set.contains(key)) {
                        set.add(key);
                        q.add(new int[]{left+1,right,1,count+1});
                    }
                }
                if(s.charAt(right)=='B'){
                    String key=left+","+(right-1);
                    if (!set.contains(key)) {
                        set.add(key);
                        q.add(new int[]{left,right-1,1,count+1});
                    }
                }
            }else if(flag==1){
                if(s.charAt(left)=='L'){
                    String key=(left+1)+","+right;
                    if (!set.contains(key)) {
                        set.add(key);
                        q.add(new int[]{left+1,right,2,count+1});
                    }
                }
                if(s.charAt(right)=='L'){
                    String key=left+","+(right-1);
                    if (!set.contains(key)) {
                        set.add(key);
                        q.add(new int[]{left,right-1,2,count+1});
                    }
                }
            }else if(flag==2){
                if(s.charAt(left)=='D'){
                    String key=(left+1)+","+right;
                    if(!set.contains(key)){
                        set.add(key);
                        q.add(new int[]{left+1,right,0,count+1});
                    }
                }
                if(s.charAt(right)=='D'){
                    String key=left+","+(right-1);
                    if (!set.contains(key)) {
                        set.add(key);
                        q.add(new int[]{left,right-1,0,count+1});
                    }
                }
            }

        }
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