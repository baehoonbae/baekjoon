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
    		String[] arr=br.readLine().split(" ");
    		for(int i=0;i<arr.length;i++) {
    			StringBuilder s=new StringBuilder(arr[i]);
    			sb.append(s.reverse()+" ");
    		}
    		sb.append("\n");
    	}
    	System.out.print(sb);
    }
}