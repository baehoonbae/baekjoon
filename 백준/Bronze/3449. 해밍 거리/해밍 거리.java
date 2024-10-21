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
    		String a=br.readLine();
    		String b=br.readLine();
    		if(a.length()>b.length()) {
    			String temp=a;
    			a=b;
    			b=temp;
    		}
    		int count=0;
    		for(int i=0;i<a.length();i++) {
    			if(a.charAt(i)!=b.charAt(i)) {
    				count++;
    			}
    		}
    		count+=b.length()-a.length();
    		System.out.println("Hamming distance is "+count+".");
    	}
    }
}