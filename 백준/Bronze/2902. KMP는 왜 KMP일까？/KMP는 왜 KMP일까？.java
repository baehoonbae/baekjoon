import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	String[] arr=br.readLine().split("-");
    	String ans="";
    	for(int i=0;i<arr.length;i++) {
    		ans+=arr[i].charAt(0);
    	}
    	System.out.println(ans);
    }
}