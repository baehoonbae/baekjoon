import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int index;
	int cost;

	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n, m, minCost = Integer.MAX_VALUE;
	static int end;
	static ArrayList<Node>[] list;
	static int[][] costs;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		costs = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, cost));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		bw.write(dfs(1, start) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static int dfs(int node, int start) {
		boolean[] visited = new boolean[n + 1];
		int[] dist = new int[n + 1];
		int INF = Integer.MAX_VALUE;
		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			int curVertex = pq.poll().index;
			if (visited[curVertex]) {
				continue;
			}
			visited[curVertex] = true;
			for (Node next : list[curVertex]) {
				if (dist[next.index] > dist[curVertex] + next.cost) {
					dist[next.index] = dist[curVertex] + next.cost;
					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
		return dist[end];
	}
}
