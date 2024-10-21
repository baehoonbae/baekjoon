import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	String s=br.readLine();
    	for(int i=1;i<s.length();i++) {
    		String a=s.substring(0,i);
    		String b=s.substring(i,s.length());
    		int num1=1;
    		int num2=1;
    		for(int j=0;j<a.length();j++) {
    			num1*=a.charAt(j)-'0';
    		}
    		for(int j=0;j<b.length();j++) {
    			num2*=b.charAt(j)-'0';    			
    		}
    		if(num1==num2) {
    			System.out.println("YES");
    			return;
    		}
    	}
    	System.out.println("NO");
    }
}