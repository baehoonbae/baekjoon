import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int[] state;
        int cost;

        Node(int[] a,int cost){
            this.state=a;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n,m;
    static int[][] info;
    static int[] arr;
    static String answer;
    static Map<String,Integer> visited=new HashMap<>();
    static int minCost=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        int n=Integer.parseInt(br.readLine());
        int[] sorted=new int[n];
        arr=new int[n];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            sorted[i]=Integer.parseInt(st.nextToken());
            arr[i]=sorted[i];
        }
        Arrays.sort(sorted);
        answer=Arrays.toString(sorted);

        m=Integer.parseInt(br.readLine());
        info=new int[m][3];

        for(int i=0;i<m;i++) {
            st=new StringTokenizer(br.readLine());
            int l=Integer.parseInt(st.nextToken())-1;
            int r=Integer.parseInt(st.nextToken())-1;
            int c=Integer.parseInt(st.nextToken());
            info[i]=new int[] {l,r,c};
        }
        play();
        System.out.println(minCost==Integer.MAX_VALUE?-1:minCost);
    }

    private static void play() {
        PriorityQueue<Node> q=new PriorityQueue<>();
        q.add(new Node(arr.clone(), 0));
        visited.put(Arrays.toString(arr),0);

        while(!q.isEmpty()) {
            Node node=q.poll();
            int[] cur=node.state;
            int cost=node.cost;

            String curKey=Arrays.toString(cur);
            if(curKey.equals(answer)){
                minCost=Math.min(minCost,cost);
            }

            for(int i=0;i<m;i++) {
                int[] temp=cur.clone();
                int[] I=info[i];
                swap(temp,I[0],I[1]);
                String next=Arrays.toString(temp);
                int nextCost=cost+I[2];
                if(!visited.containsKey(next)||visited.get(next)>nextCost){
                    visited.put(next,nextCost);
                    q.add(new Node(temp,nextCost));
                }
            }
        }
    }

    private static void swap(int[] array,int a,int b) {
        int temp=array[a];
        array[a]=array[b];
        array[b]=temp;
    }
}