import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	int joi=0;
    	int ioi=0;
    	String s=br.readLine();
    	for(int i=0;i<s.length()-2;i++) {
    		if(s.substring(i,i+3).equals("JOI"))joi++;
    		if(s.substring(i,i+3).equals("IOI"))ioi++;
    	}
    	System.out.println(joi);
    	System.out.println(ioi);
    }
}