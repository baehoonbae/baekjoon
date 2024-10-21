import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
	static class Node implements Comparable<Node>{
		String to;
		int cost;
		Node(String to,int cost){
			this.to=to;
			this.cost=cost;
		}
		@Override
		public int compareTo(Node o) {
			return o.cost-this.cost;
		}
	}
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Map<String,List<Node>> adj;

    public static void main(String[] args)throws IOException {
    	int number=1;
    	while(true) {
    		st=new StringTokenizer(br.readLine());
    		int n=Integer.parseInt(st.nextToken());
    		int m=Integer.parseInt(st.nextToken());
    		if(n==0&&m==0) return;
    		
    		adj=new HashMap<>();
    		for(int i=0;i<m;i++) {
    			st=new StringTokenizer(br.readLine());
    			String a=st.nextToken();
    			String b=st.nextToken();
    			int cost=Integer.parseInt(st.nextToken());
    			adj.computeIfAbsent(a, key->new ArrayList<>()).add(new Node(b,cost));
    			adj.computeIfAbsent(b, key->new ArrayList<>()).add(new Node(a,cost));    			
    		}
    		st=new StringTokenizer(br.readLine());
    		String start=st.nextToken();
    		String end=st.nextToken();
    		
    		
    		System.out.println("Scenario #"+number);
    		System.out.println(bfs(start,end)+" tons");
    		System.out.println();
    		number++;
    	}
    }
    
    private static int bfs(String start,String end) {
    	Map<String,Integer> maxCapacity=new HashMap<>();
    	PriorityQueue<Node>q=new PriorityQueue<>();
    	
    	q.add(new Node(start,Integer.MAX_VALUE));
    	maxCapacity.put(start, Integer.MAX_VALUE);
    	
    	while(!q.isEmpty()) {
    		Node node=q.poll();
    		String cur=node.to;
    		int cost=node.cost;
    		
    		if(cur.equals(end))return cost;
    		if(cost<maxCapacity.get(cur)) continue;
    		
    		for(Node next:adj.get(cur)) {
    			int newCost=Math.min(cost,next.cost);
    			if(!maxCapacity.containsKey(next.to)||maxCapacity.get(next.to)<newCost) {
    				q.add(new Node(next.to,newCost));    				
    				maxCapacity.put(next.to, newCost);
    			}
    		}
    	}
    	
    	return -1;
    }
}