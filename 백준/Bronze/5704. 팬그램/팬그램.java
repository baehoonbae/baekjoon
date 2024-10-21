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
    	while(true) {
    		Set<Character> set=new HashSet<>();
    		String s=br.readLine();
    		
    		if(s.equals("*"))return;
    		
    		for(int i=0;i<s.length();i++) {
    			if(s.charAt(i)==' ')continue;
    			set.add(s.charAt(i));
    		}
    		System.out.println(set.size()==26?"Y":"N");
    	}
    }
}