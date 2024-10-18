import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 24891. 단어 마방진 / 골드5 / 10:53~3:44
class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int l,n;
	static String[] words;
	static boolean[] visited;
	static List<String> ans=new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st=new StringTokenizer(br.readLine());
		l=Integer.parseInt(st.nextToken());
		n=Integer.parseInt(st.nextToken());
		
		words=new String[n];
		visited=new boolean[n];
		
		for(int i=0;i<n;i++) {
			words[i]=br.readLine();
		}
		play(0,new ArrayList<>());
		Collections.sort(ans);
		if(ans.isEmpty()) {
			System.out.print("NONE");
		}else {
			System.out.print(ans.get(0));
		}
	}
	
	private static void play(int count,List<String> list) {
		if(count==l) {
			if(check(list)) {
				StringBuilder sb=new StringBuilder();
				for(String s:list) {
					sb.append(s).append("\n");
				}
				ans.add(sb.toString());
			}
			return;
		}
		//n개중 l개 순열 선택하기
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				list.add(words[i]);
				play(count+1,list);
				list.remove(list.size()-1);
				visited[i]=false;
			}
		}
	}
	
	private static boolean check(List<String> list) {
		char[][] arr=new char[l][l];
		int idx=0;
		for(String s:list) {
			arr[idx++]=s.toCharArray();
		}
		for(int i=0;i<l;i++) {
			for(int j=0;j<l;j++) {
				if(arr[i][j]!=arr[j][i]) {
					return false;
				}
			}
		}
		return true;
	}
}