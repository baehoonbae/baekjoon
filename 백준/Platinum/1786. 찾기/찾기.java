import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static StringBuilder sb=new StringBuilder();
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		String t=br.readLine();
		String p=br.readLine();
		int n=t.length();
		int m=p.length();
		int[] pi=getPi(p);		
		int count=0;
		int j=0;
		
		for(int i=0;i<n;i++) {
			while(j>0&&t.charAt(i)!=p.charAt(j)) {
				j=pi[j-1];
			}
			if(t.charAt(i)==p.charAt(j)) {
				j++;
			}
			if(j==m) {
				count++;
				sb.append((i-m+2)+" ");
				j=pi[j-1];
			}
		}
		System.out.println(count);
		System.out.println(sb);
	}

	private static int[] getPi(String p) {
		int m=p.length();
		int[] pi=new int[m];
		int j=0;
		for(int i=1;i<m;i++) {
			while(j>0&&p.charAt(i)!=p.charAt(j)) {
				j=pi[j-1];
			}
			if(p.charAt(i)==p.charAt(j))j++;
			pi[i]=j;
		}
		return pi;
	}
}
