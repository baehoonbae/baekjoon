import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int v;
	static int e;
	static long minCost;
	static int edgeCount;
	static int[] parents;
	static int[] size;
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		parents = new int[v + 1];
		size = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			parents[i] = i;
			size[i] = 1;
		}
		pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[2]));
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new int[] { a, b, cost });
		}
		while (!pq.isEmpty() && edgeCount < (v - 1)) {
			int[] info = pq.poll();
			if (find(info[0]) != find(info[1])) {
				minCost += info[2];
				union(info[0], info[1]);
				edgeCount++;
			}
		}
		bw.write(String.valueOf(minCost));
		bw.flush();
		br.close();
		br.close();
	}

	public static int find(int u) {
		if (u == parents[u]) {
			return u;
		}
		return find(parents[u]);
	}

	public static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		if (size[u] < size[v]) {
			parents[u] = v;
			size[u] += size[v];
		} else {
			parents[v] = u;
			size[v] += size[u];
		}
	}
}
