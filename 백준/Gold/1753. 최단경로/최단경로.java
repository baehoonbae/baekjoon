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
	static int INF = 3_000_000;
	static int v;
	static int e;
	static int start;
	static List<List<Node>> list = new ArrayList<>();
	static int[] dist;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		dist = new int[v + 1];
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, dist));
		}
		dijkstra(start);
		for (int i = 1; i <= v; i++) {
			if (dist[i] == INF) {
				bw.write("INF\n");
			} else {
				bw.write(dist[i] + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dijkstra(int start) {
		boolean[] visited = new boolean[v + 1];
		Arrays.fill(visited, false);
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
			for (Node next : list.get(curVertex)) {
				if (dist[next.index] > dist[curVertex] + next.cost) {
					dist[next.index] = dist[curVertex] + next.cost;
					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
	}
}
