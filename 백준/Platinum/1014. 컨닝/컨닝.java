import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1014. 컨닝 / 플래4 / 2시간12분
class Main {
    static StringBuilder sb=new StringBuilder();
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Integer> firstBit;
    static char[][] arr;
    static int[][] dp;
    static int n,m;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while(t-->0){
        	firstBit=new ArrayList<>();
            arr=new char[12][12];
            dp=new int[12][1025];
            st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            m=Integer.parseInt(st.nextToken());

            for(int i=0;i<n;i++) {
                arr[i]=br.readLine().toCharArray();
            }
            generate(0,0,false);
            for(int first:firstBit) {
            	dp[0][first]=Integer.bitCount(first);
//            	play(1,0,Integer.bitCount(first),first,0,false);
            }
            
            for(int row=1;row<n;row++) {
            	for(int prevMask=0;prevMask<(1<<m);prevMask++) {
            		if(dp[row-1][prevMask]==0)continue;
            		
            		for(int curMask=0;curMask<(1<<m);curMask++) {
            			if(isValid(prevMask,curMask,row)) {
            				dp[row][curMask]=Math.max(dp[row][curMask], dp[row-1][prevMask]+Integer.bitCount(curMask));
            			}
            		}
            	}
            }
            int max=0;
        	for(int i=0;i<(1<<m);i++) {
        		max=Math.max(max, dp[n-1][i]);
            }
            sb.append(max).append("\n");
        }
        System.out.print(sb);
    }
    
    private static boolean isValid(int prevMask, int curMask, int row) {
    	for(int i=0;i<m;i++) {
	    	if((curMask&(1<<i))!=0) {
	    		if(arr[row][i]=='x')return false;
	    		if(i+1<m) {
	    			if((prevMask&(1<<(i+1)))!=0)return false;	
	    		}
	    		if(i-1>=0) {
	    			if((prevMask&(1<<(i-1)))!=0)return false;	
	    			if((curMask&(1<<(i-1)))!=0)return false;
	    		}		
    		}
    	}
		return true;
	}

//    private static void play(int row,int idx,int sum,int prevMask,int curMask,boolean prev) {
//    	if(row==n) {
//    		max=Math.max(max, sum);
//    		return;
//    	}
//    	if(idx==m) {
//    		play(row+1,0,sum+Integer.bitCount(curMask),curMask,0,false);
//    		return;
//    	}
//    	boolean lDiagOn=false,rDiagOn=false;
//    	if(idx+1<m) {
//    		lDiagOn=(prevMask&(1<<(idx+1)))!=0;    		
//    	}
//    	if(idx-1>0) {
//    		rDiagOn=(prevMask&(1<<(idx-1)))!=0;    		
//    	}
//    	boolean isBroken=arr[row][idx]=='x';
// 
//    	// 선택함
//    	if(!isBroken&&!prev&&!lDiagOn&&!rDiagOn) {
//    		play(row,idx+1,sum,prevMask,curMask|(1<<idx),true);    		
//    	}
//    	
//    	// 선택 안함
//    	play(row,idx+1,sum,prevMask,curMask,false);
//    }
    
    private static void generate(int idx, int mask, boolean prev) {
        if(idx==m){
            firstBit.add(mask);
            return;
        }
        if(!prev&&arr[0][idx]!='x'){
            generate(idx+1,mask|(1<<idx),true);
        }
        generate(idx+1,mask,false);
    }
}