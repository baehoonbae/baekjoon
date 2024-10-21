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
    		st=new StringTokenizer(br.readLine());
    		String a=st.nextToken();
    		String b=st.nextToken();
    		String s="Distances: ";
    		for(int i=0;i<a.length();i++) {
    			char c=a.charAt(i);
    			char d=b.charAt(i);
    			if(c>d) {
    				s+=('Z'-c+d-'A'+1);
    			}else {
    				s+=(d-c);
    			}
    			s+=" ";
    		}
    		System.out.println(s);
    	}
    }
}