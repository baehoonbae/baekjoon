import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[] primes=new boolean[2222222];

    public static void main(String[] args)throws IOException {
    	sieve();
    	
    	String s=br.readLine();
    	int num=0;
    	for(int i=0;i<s.length();i++) {
    		if(s.charAt(i)-'A'>=26) {
    			num+=s.charAt(i)-'a'+1;
    		}else {
    			num+=s.charAt(i)-'A'+27;
    		}
    	}
//    	System.out.println(num);
    	if(!primes[num]) {
    		System.out.println("It is a prime word.");
    	}else {
    		System.out.println("It is not a prime word.");
    	}
    }
    
    private static void sieve() {
    	int n=(int)Math.pow(10, 6);
    	for(int i=2;i<=Math.sqrt(n);i++) {
    		for(int j=i*2;j<=i*i;j+=i) {
    			primes[j]=true;
    		}
    	}
    }
}