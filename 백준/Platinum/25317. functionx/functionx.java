import java.io.*;
import java.util.*;

class Main extends FI1 {
    static StringBuilder sb=new StringBuilder();
    static int gn=0;

    static class FenwickTree {
        int n;
        long[] tree;

        FenwickTree(int n) {
            this.n = n;
            tree = new long[n+1];
        }

        long sum(int i) {
            long ret = 0;
            for (;i>0; i-= i&-i) {
                ret+=tree[i];
            }
            return ret;
        }

        void update(int i, long x) {
            for (i++; i <= n; i += i & -i) {
                tree[i] += x;
            }
        }

        long query(int l, int r) {
            return l > r ? 0 : sum(r + 1) - sum(l);
        }
    }

    public static void main(String[] args) throws IOException {
        initFI();
        int q = nextInt();
        TreeSet<Long> values = new TreeSet<>();
        ArrayList<long[]> query = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            int command=nextInt();
            if (command==1) {
                long a=nextLong(),b=nextLong();
                if (a != 0) {
                    long v=getCeil(-b,a);
                    values.add(v);
                    query.add(new long[]{1,v,a,b});
                } else {
                    query.add(new long[]{1,0,a,b});
                }
            } else {
                long c=nextLong();
                values.add(c);
                query.add(new long[]{2,c,c,c});
            }
        }
        ArrayList<Long> unique=new ArrayList<>(values);
        int size=unique.size();
        FenwickTree ft=new FenwickTree(unique.size());

        boolean flag=false;   // 모든경우가 무조건 0이 되는상황인지
        int bn=0;
        boolean bZero=false;
        HashSet<Long> zero=new HashSet<>();

        for (long[] q1:query) {
            long cmd=q1[0], x=q1[1], a=q1[2], b=q1[3];

            if (cmd==1) {
                if (a!=0 && b%a==0) zero.add(-b/a);
                if (a<0 || (a==0&&b<0)) {
                    a*=-1;
                    b*=-1;
                    gn++;
                }

                if (a==0 && b==0)flag=true;
                if (b==0)bZero=true;
                if (b<0)bn++;

                if(a==0){
                    if(b<0)return;
                }else{
                    ft.update(lowerBound(unique,x),1);
                }
            } else {
                if (flag||zero.contains(x)) {
                    sb.append("0\n");
                } else if (x == 0) {
                    if (bZero) {
                        sb.append("0\n");
                    } else {
                        if(bn%2==1)print('-');
                        else print('+');
                    }
                } else {
                    long cnt = ft.query(lowerBound(unique,x)+1, size-1);
                    if(cnt%2==1)print('-');
                    else print('+');
                }
            }
        }
        System.out.print(sb);
    }

    static int lowerBound(ArrayList<Long> list, long value) {
        int left = 0;
        int right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    static long getCeil(long x, long y) {
        if(x<0!=y<0){
            return x/y;
        }
        return x/y+(x%y!=0?1:0);
    }

    static void print(char c) {
        if (gn%2==1) {
            sb.append(c=='+'?"-\n":"+\n");
        } else {
            sb.append(c).append("\n");
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