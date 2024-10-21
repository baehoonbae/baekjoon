import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	long n=Long.parseLong(br.readLine());
    	
    	for(long i=Math.max(0, n-100000);i<=n;i++) {
    		long num=i;
    		long sum=i;
    		while(num>0) {
    			sum+=num%10;
    			num/=10;
    		}
    		if(sum==n) {
    			System.out.println(i);
    			return;
    		}
    	}
    	System.out.println(0);
    }
}