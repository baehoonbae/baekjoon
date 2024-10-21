import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	st=new StringTokenizer(br.readLine());
    	StringBuilder a=new StringBuilder(st.nextToken());
    	StringBuilder b=new StringBuilder(st.nextToken());
    	int num=Integer.parseInt(a.reverse().toString())+Integer.parseInt(b.reverse().toString());
    	StringBuilder c=new StringBuilder(String.valueOf(num));
    	c=c.reverse();
    	while(c.charAt(0)=='0') {
    		c.deleteCharAt(0);
    	}
    	
    	System.out.println(c);
    }
}