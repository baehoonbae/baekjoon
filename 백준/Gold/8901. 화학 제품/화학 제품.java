import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int a,b,c;
	static int ab,bc,ca;
	static long maxProfit;
	
	public static void main(String[] args) throws IOException {
		int t=Integer.parseInt(br.readLine());
		while(t-->0) {
			maxProfit=0;
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			ab=Integer.parseInt(st.nextToken());
			bc=Integer.parseInt(st.nextToken());
			ca=Integer.parseInt(st.nextToken());
			
			for(int i=0;i<=Math.min(a, b);i++) {
				for(int j=0;j<=Math.min(b-i, c);j++) {
					long profit=(i*ab)+(j*bc);
					profit+=Math.min(a-i,c-j)*ca;
					maxProfit=Math.max(maxProfit, profit);
				}
			}
			
			System.out.println(maxProfit);
		}
	}
}