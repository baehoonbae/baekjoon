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
    	int t=Integer.parseInt(br.readLine());
    	while(t-->0) {
    		String s=br.readLine();
    		int n=s.length();
    		if(s.charAt(n/2-1)==s.charAt(n/2)) {
    			System.out.println("Do-it");
    		}else {
    			System.out.println("Do-it-Not");
    		}
    	}
    }
}