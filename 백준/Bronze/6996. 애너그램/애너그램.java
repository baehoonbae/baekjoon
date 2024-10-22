import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static StringBuilder sb=new StringBuilder();
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    public static void main(String[] args)throws IOException {
    	int n=Integer.parseInt(br.readLine());
    	while(n-->0) {
    		int[] A=new int[26];
    		int[] B=new int[26];
    		st=new StringTokenizer(br.readLine());
    		String a=st.nextToken();
    		String b=st.nextToken();
    		for(int i=0;i<a.length();i++) {
    			A[a.charAt(i)-'a']++;
    		}
    		for(int i=0;i<b.length();i++) {
    			B[b.charAt(i)-'a']++;
    		}
    		sb.append(a+" & "+b+" are ");
    		for(int i=0;i<26;i++) {
    			if(A[i]!=B[i]) {
    				sb.append("NOT ");
    				break;
    			}
    		}
    		sb.append("anagrams.\n");
    	}
    	System.out.print(sb);
    }
}