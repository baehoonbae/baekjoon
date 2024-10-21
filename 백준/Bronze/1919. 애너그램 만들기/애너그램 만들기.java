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
    	String a=br.readLine();
    	String b=br.readLine();
    	Map<Character,Integer> amap=new HashMap<>();
    	Map<Character,Integer> bmap=new HashMap<>();
    	for(char c='a';c<='z';c++) {
    		amap.put(c, 0);
    		bmap.put(c, 0);
    	}
    	for(int i=0;i<a.length();i++) {
    		amap.merge(a.charAt(i), 1, Integer::sum);
    	}
    	for(int i=0;i<b.length();i++) {
    		bmap.merge(b.charAt(i), 1, Integer::sum);    		
    	}
    	int count=0;
    	for(char c:amap.keySet()) {
    		count+=Math.abs(amap.get(c)-bmap.get(c));
    	}
    	System.out.println(count);
    }
}