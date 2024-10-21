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
    		boolean[] visited=new boolean[26];
    		int sum=0;
    		for(char c='A';c<='Z';c++) {
    			sum+=c;
    		}
    		String s=br.readLine();
    		for(int i=0;i<s.length();i++) {
    			if(!visited[s.charAt(i)-'A']) {
    				sum-=s.charAt(i);    			
    				visited[s.charAt(i)-'A']=true;
    			}
    		}
    		System.out.println(sum);
    	}
    }
}