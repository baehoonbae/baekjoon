import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	while(true) {
    		Map<Character,Integer> map=new HashMap<>();
    		for(char c='a';c<='z';c++) {
    			map.put(c, 0);
    		}
    		st=new StringTokenizer(br.readLine());
    		char c=st.nextToken().charAt(0);
    		if(c=='#')return;
    		
    		while(st.hasMoreTokens()) {
	    		String s=st.nextToken();
	    		for(int i=0;i<s.length();i++) {
	    			char ch=s.charAt(i);
	    			if(ch-'A'<=26)ch+=32;
	    			map.merge(ch, 1, Integer::sum);
	    		}
    		}
    		System.out.println(c+" "+map.get(c));
    	}
    }
}