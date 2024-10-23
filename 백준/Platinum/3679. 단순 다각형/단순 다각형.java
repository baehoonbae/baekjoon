import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static class Point implements Comparable<Point>{
		int x,y,idx;
		static Point base;
		
		Point(int x,int y,int idx){this.x=x;this.y=y;this.idx=idx;}
		
		@Override
		public int compareTo(Point o) {
			long ccw=ccw(base,this,o);
			if(ccw>0) {
				return -1;
			}else if(ccw<0) {
				return 1;
			}else {
				long dist1=dist(base,this);
				long dist2=dist(base,o);
				return Long.compare(dist1,dist2);
			}
		}
	}
	
	static StringBuilder sb=new StringBuilder();
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Point[] arr;
	
    public static void main(String[] args)throws IOException {
    	int t=Integer.parseInt(br.readLine());
    	while(t-->0) {
    		Point.base=new Point(Integer.MAX_VALUE,Integer.MAX_VALUE,-1);
    		st=new StringTokenizer(br.readLine());
    		int n=Integer.parseInt(st.nextToken());
    		arr=new Point[n];
    		for(int i=0;i<n;i++) {
    			int x=Integer.parseInt(st.nextToken());
    			int y=Integer.parseInt(st.nextToken());
    			Point point=new Point(x,y,i);
    			if(x<Point.base.x||(x==Point.base.x&&y<Point.base.y)) {
					Point.base=point;
    			}
    			arr[i]=new Point(x,y,i);
    		}
    		Arrays.sort(arr);
//    		for(Point p:arr) {
//    			System.out.print(p.idx+" ");
//    		}
    		int i=n-1;
    		while(i>1&&ccw(arr[0],arr[i-1],arr[i])==0) {
    			i--;
    		}
    		for(int j=0;j<i;j++) {
    			System.out.print(arr[j].idx+" ");
    		}
    		for(int j=n-1;j>=i;j--) {
    			System.out.print(arr[j].idx+" ");
    		}
    		System.out.println();
    	}
    }

    private static long ccw(Point p1,Point p2,Point p3) {
    	return (p2.x-p1.x)*(p3.y-p1.y)-(p2.y-p1.y)*(p3.x-p1.x);
    }
    
    private static long dist(Point p1,Point p2) {
    	return (p2.y-p1.y)*(p2.y-p1.y)+(p2.x-p1.x)*(p2.x-p1.x);
    }
}