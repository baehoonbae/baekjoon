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
    	String s=br.readLine();
    	int a=0;
    	int b=0;
    	for(int i=0;i<s.length();i++) {
    		if(s.charAt(i)=='A')a++;
    		if(s.charAt(i)=='B')b++;
    	}
    	if(a>b)System.out.println("A");
    	if(a<b)System.out.println("B");
    	if(a==b)System.out.println("Tie");
    }
}