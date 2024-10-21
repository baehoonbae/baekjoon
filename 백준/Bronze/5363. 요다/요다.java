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
    		String[] arr=br.readLine().split(" ");
    		String s="";
    		for(int i=2;i<arr.length;i++) {
    			s+=(arr[i]+" ");
    		}
    		s+=(arr[0]+" ");
    		s+=(arr[1]+" ");
    		System.out.println(s);
    	}
    }
}