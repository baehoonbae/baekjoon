import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

class Main extends FI{
	public static void main(String[] args) throws IOException{
		initFI();
		String s=next();
		
		if(!s.contains(".")) {System.out.println(s+"/1");return;}
		else if(s.indexOf(".")==s.length()){System.out.println(s+"/1");return;}

		int left=0;
		StringBuilder up=new StringBuilder();
		StringBuilder down=new StringBuilder();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)!='.'&&s.charAt(i)!='('&&s.charAt(i)!=')') {
				up.append(s.charAt(i));
			}
		}
		while(s.charAt(left)!='.')left++;
		int i=s.length()-1;
		
		StringBuilder repeat=new StringBuilder();
		if(s.charAt(i)==')') {
			i--;
			while(s.charAt(i)!='(') {
				down.append("9");
				repeat.append(s.charAt(i));
				i--;
			}
			i--;
			while(s.charAt(i)!='.') {
				down.append("0");
				i--;
			}
			repeat=repeat.reverse();
			int nr = Integer.parseInt(up.substring(0, up.length() - repeat.length()).toString());
			int u = Integer.parseInt(up.toString()) - nr;
			int d=Integer.parseInt(down.toString());
			int gcd=gcd(u,d);
			System.out.println((u/gcd)+"/"+(d/gcd));
		}else {
			down.append("1");
			while(s.charAt(i)!='.') {
				down.append("0");
				i--;
			}
			int u=Integer.parseInt(up.toString());
			int d=Integer.parseInt(down.toString());
			int gcd=gcd(u,d);
			System.out.println((u/gcd)+"/"+(d/gcd));
		}
	}
	
	private static int gcd(int a,int b) {
		if(a<b) {
			int temp=a;
			a=b;
			b=temp;
		}
		while(b!=0) {
			int temp=a%b;
			a=b;
			b=temp;			
		}
		return a;
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
