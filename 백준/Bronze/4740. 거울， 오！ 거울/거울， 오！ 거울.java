import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	while(true) {
    		StringBuilder sb=new StringBuilder(br.readLine());
    		if(sb.toString().equals("***"))return;
    		System.out.println(sb.reverse());
    	}
    }
}
