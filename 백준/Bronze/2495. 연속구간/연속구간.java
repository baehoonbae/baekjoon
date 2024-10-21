import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	int num=3;
    	while(num-->0) {
    		int maxCount=0;
    		String s=br.readLine();
    		int count=1;
    		char prev=s.charAt(0);
    		for(int i=1;i<s.length();i++) {
    			if(prev==s.charAt(i)) {
    				count++;
    			}else {
    				count=1;
    			}
    			maxCount=Math.max(maxCount, count);
    			prev=s.charAt(i);
    		}
    		System.out.println(maxCount);
    	}
    }
}