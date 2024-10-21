import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

    public static void main(String[] args)throws IOException {
    	int n=Integer.parseInt(br.readLine());
    	while(n-->0) {
    		boolean flag=false;
    		String s=br.readLine();
    		int j=s.length()-1;
    		for(int i=0;i<s.length()/2;i++,j--) {
    			char left=s.charAt(i);
    			char right=s.charAt(j);
    			left=Character.toLowerCase(left);
    			right=Character.toLowerCase(right);
    			if(left!=right) {
    				flag=true;
    				System.out.println("No");
    				break;
    			}
    		}
    		if(!flag)System.out.println("Yes");
    	}
    }
}