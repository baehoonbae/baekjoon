import java.io.*;
import java.util.*;

class Solution {
    int maxDepth;
    List<Integer>[] adj;
    int[] depths;
    
    public int solution(int n, int[][] edge) {
        maxDepth=0;
        adj=new List[n+1];
        depths=new int[n+1];
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int i=0;i<edge.length;i++){
            int a=edge[i][0];
            int b=edge[i][1];
            adj[a].add(b);
            adj[b].add(a);
        }
        Queue<Node>q=new LinkedList<>();
        q.add(new Node(1,0));
        boolean[] visited=new boolean[n+1];
        visited[1]=true;
        while(!q.isEmpty()){
            Node node=q.poll();
            int cur=node.to;
            int depth=node.depth;
            depths[depth]++;
            maxDepth=Math.max(maxDepth,depth);
            for(int next:adj[cur]){
                if(!visited[next]){
                    visited[next]=true;
                    q.add(new Node(next,depth+1));
                }
            }
        }
        
//         System.out.println(maxDepth);
//         for(int i=0;i<n;i++){
//             System.out.println(depths[i]);
//         }
        return depths[maxDepth];
    }
}

class Node{
    int to;
    int depth;
    
    Node(int to,int depth){
        this.to=to;
        this.depth=depth;
    }
}