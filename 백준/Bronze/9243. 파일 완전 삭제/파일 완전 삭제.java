import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	int n=Integer.parseInt(br.readLine());
    	String a=br.readLine();
    	String b=br.readLine();
    	if(n%2==0) {
    		for(int i=0;i<a.length();i++) {
    			if(a.charAt(i)!=b.charAt(i)) {
    				System.out.println("Deletion failed");
    				return;
    			}
    		}
			System.out.println("Deletion succeeded");
    	}else {
    		for(int i=0;i<a.length();i++) {
    			if(a.charAt(i)==b.charAt(i)) {
    				System.out.println("Deletion failed");
    				return;
    			}
    		}
			System.out.println("Deletion succeeded");
    	}
    }
}