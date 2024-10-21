import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	int t=Integer.parseInt(br.readLine());
    	while(t-->0) {
    		StringBuilder sb=new StringBuilder(br.readLine());
    		String s="";
    		if(sb.charAt(0)-'A'>=26) {
    			s+=((char)(sb.charAt(0)-32));
    		}else {
    			s+=sb.charAt(0);
    		}
    		s+=sb.substring(1);
    		System.out.println(s);
    	}
    }
}