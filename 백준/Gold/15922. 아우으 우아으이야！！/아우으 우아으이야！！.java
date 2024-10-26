import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

class Line implements Comparable<Line>{
    int start,end;
    Line(int start,int end){this.start=start;this.end=end;}
    @Override
    public int compareTo(Line o){
        if(this.start==o.start)return o.end-this.end;
        return this.start-o.start;
    }
}

public class Main extends FastInput {
    public static void main(String[] args)throws IOException {
        initFI();
        int n=nextInt();

        Line[] arr=new Line[n];
        for(int i=0;i<n;i++){
            arr[i]=new Line(nextInt(),nextInt());
        }
        Arrays.sort(arr);

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

class FastInput {
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

    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}