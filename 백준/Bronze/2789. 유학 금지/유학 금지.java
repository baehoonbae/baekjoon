import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	String comp="CAMBRIDGE";
    	String s=br.readLine();
    	String ans="";
    	for(int i=0;i<s.length();i++) {
    		if(!comp.contains(s.charAt(i)+"")){
    			ans+=s.charAt(i);
    		}
    	}
    	System.out.println(ans);
    }
}
