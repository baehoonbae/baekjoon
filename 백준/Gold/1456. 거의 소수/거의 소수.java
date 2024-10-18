import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    private static List<Integer> sieve(int n) {
    	boolean[] isPrime=new boolean[(int) (n+1)];
    	Arrays.fill(isPrime, true);
    	isPrime[0]=isPrime[1]=false;
    	List<Integer> primes=new ArrayList<>();
    	for(int i=2;i*i<=n;i++) {
    		if(!isPrime[i])continue;
    		for(int j=i*i;j<=n;j+=i)isPrime[j]=false;
    	}
        for(int i=2;i<=n;i++) {
        	if(isPrime[i])primes.add(i);
        }
        return primes;
    }

    public static void main(String[] args)throws IOException {
    	st=new StringTokenizer(br.readLine());
        long l=Long.parseLong(st.nextToken());
        long r=Long.parseLong(st.nextToken());
        int n=(int)Math.pow(10, 7);
        
        List<Integer> primes=sieve(n);
        int count=0;
        for(int i:primes) {
        	if(i>Math.sqrt(r))break;
        	for(int j=2;;j++) {
        		long num=(long)Math.pow(i, j);
        		if(num>r)break;
        		if(num>=l&&num<=r)count++;
        	}
        }
        System.out.println(count);
    }
}