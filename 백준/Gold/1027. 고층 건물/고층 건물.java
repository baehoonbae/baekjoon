import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
    public static void main(String[] args)throws IOException {
    	int n=Integer.parseInt(br.readLine());
    	int[] arr=new int[n];
    	st=new StringTokenizer(br.readLine());
    	for(int x=0;x<n;x++) {
    		int y=Integer.parseInt(st.nextToken());
    		arr[x]=y;
    	}
    	int maxCount=0;
    	for(int i=0;i<n;i++) {
    		int count=0;
    		double maxSlope=Double.NEGATIVE_INFINITY;
    		for(int j=i+1;j<n;j++) {
    			double slope=(double)(arr[j]-arr[i])/(j-i);
    			if(maxSlope<slope) {
    				maxSlope=slope;
    				count++;
    			}
    		}
    		maxSlope=Double.POSITIVE_INFINITY;
    		for(int j=i-1;j>=0;j--) {
    			double slope=(double)(arr[j]-arr[i])/(j-i);
    			if(maxSlope>slope) {
    				maxSlope=slope;
    				count++;
    			}
    		}
    		maxCount=Math.max(maxCount, count);
    	}
    	System.out.println(maxCount);
    }
}