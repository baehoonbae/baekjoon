import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int[] dy={-1,1,0,0};
    static final int[] dx={0,0,1,-1};
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st=new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] arr=new char[n][m];
        List<int[]>list=new ArrayList<>();
        for(int i=0;i<n;i++){
            String s=br.readLine();
            for(int j=0;j<m;j++){
                arr[i][j]=s.charAt(j);
                if(arr[i][j]=='.'){
                    arr[i][j]='D';
                }else if(arr[i][j]=='W'){
                    list.add(new int[]{i,j});
                }
            }
        }
        for(int[] pos:list){
            int y=pos[0];
            int x=pos[1];
            for(int i=0;i<4;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                if(ny<0||nx<0||ny>=n||nx>=m)continue;
                if(arr[ny][nx]=='S') {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}