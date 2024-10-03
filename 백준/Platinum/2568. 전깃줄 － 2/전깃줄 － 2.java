import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] arr;
    static List<Integer> lis;
    static int[] index;

    public static void main(String[] args)throws IOException {
        n=Integer.parseInt(br.readLine());
        arr=new int[n][2];
        index=new int[n];
        lis =new ArrayList<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr,(a,b)->a[0]-b[0]);
        List<Integer> deleted=new ArrayList<>();

        for(int i=0;i<n;i++){
            int num=arr[i][1];
            int idx= Collections.binarySearch(lis,num);
            if(idx<0)idx=-(idx+1);
            if(idx==lis.size()){
                lis.add(num);
            }else {
                lis.set(idx,num);
            }
            index[i]=idx;
        }
        Set<Integer> set=new HashSet<>();
        int j=lis.size()-1;
        for(int i=n-1;i>=0&&j>=0;i--){
            if(index[i]==j){
                set.add(arr[i][0]);
                j--;
            }
        }
        System.out.println(n-lis.size());
        for(int[] info:arr){
            if(!set.contains(info[0])){
                System.out.println(info[0]);
            }
        }
    }
}