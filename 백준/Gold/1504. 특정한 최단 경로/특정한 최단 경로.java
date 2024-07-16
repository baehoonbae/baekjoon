import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1504. 특정한 최단 경로
// 다익스트라 문제.
// 기존 다익스트라 문제와 비슷하지만 지나가야 할 경로들에 대한 최단경로를 먼저 구해야 한다.
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
	static List<List<Node>> list = new ArrayList<>();
	static int n;
	static int e;
	static int INF = 200_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			list.get(u).add(new Node(v, dist));
			list.get(v).add(new Node(u, dist));
		}
		st = new StringTokenizer(br.readLine());
		int target1 = Integer.parseInt(st.nextToken());
		int target2 = Integer.parseInt(st.nextToken());

		int res1 = 0;
		res1 += dfs(1, target1);
		res1 += dfs(target1, target2);
		res1 += dfs(target2, n);

		int res2 = 0;
		res2 += dfs(1, target2);
		res2 += dfs(target2, target1);
		res2 += dfs(target1, n);

		int answer = (res1 >= INF && res2 >= INF ? -1 : Math.min(res1, res2));
		System.out.println(answer);
	}

	public static int dfs(int start, int end) {
		boolean[] visited = new boolean[n + 1];
		int[] dist = new int[n + 1];
		Arrays.fill(visited, false);
		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			int curVertex = pq.poll().index;
			if (visited[curVertex]) {
				continue;
			}
			visited[curVertex] = true;
			for (Node next : list.get(curVertex)) {
				if (!visited[next.index] && dist[next.index] > dist[curVertex] + next.cost) {
					dist[next.index] = dist[curVertex] + next.cost;
					pq.add(new Node(next.index, dist[next.index]));
				}
			}
		}

		return dist[end];
	}
}
