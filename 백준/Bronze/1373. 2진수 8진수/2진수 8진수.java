import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	StringBuilder s=new StringBuilder(br.readLine());
    	if(s.toString().equals("0")) {
    		System.out.println(0);
    		return;
    	}
    	while(s.length()%3!=0) {
    		s.insert(0, 0);
    	}
    	StringBuilder num=new StringBuilder();
    	for(int i=0;i<s.length();i+=3) {
    		int number=0;
    		number+=(s.charAt(i)-'0')*4;
    		number+=(s.charAt(i+1)-'0')*2;
    		number+=(s.charAt(i+2)-'0');
    		num.append(number);
    	}
    	while(num.charAt(0)=='0') {
    		num.deleteCharAt(0);
    	}
    	System.out.println(num);
    }
}
