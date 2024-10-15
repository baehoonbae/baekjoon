import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n = 6;
	static int res;
	static int[][] capacity = new int[701][701];
	static int[][] flows = new int[701][701];
	static int[] prev = new int[701];	// 경로를 기억하는 배열
	static List<Integer>[] adj = new List[701];

	static void edmondsKarp(int start, int end) {
		while (true) {
			Arrays.fill(prev, -1);
			Queue<Integer> q = new LinkedList<>();
			q.add(start);

			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int i = 0; i < adj[cur].size(); i++) {
					int next = adj[cur].get(i);
					if (capacity[cur][next] - flows[cur][next] > 0 && prev[next] == -1) {
						q.add(next);
						prev[next] = cur;
						if (next == end)break;
					}
				}
			}

			// 모든 
			if (prev[end] == -1)break;

			int flow = Integer.MAX_VALUE;
			for (int i = end; i != start; i = prev[i]) {
				flow = Math.min(flow, capacity[prev[i]][i] - flows[prev[i]][i]);
			}
			for (int i = end; i != start; i = prev[i]) {
				flows[prev[i]][i] += flow;
				flows[i][prev[i]] += flow;
			}
			res += flow;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		for(int i=0;i<=200;i++) {
			adj[i]=new ArrayList<>();
		}
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int a=st.nextToken().charAt(0)-'A';
			int b=st.nextToken().charAt(0)-'A';
			int f=Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
			capacity[a][b]+=f;
			capacity[b][a]+=f;
		}
		edmondsKarp(0,25);
		System.out.println(res);
	}
}