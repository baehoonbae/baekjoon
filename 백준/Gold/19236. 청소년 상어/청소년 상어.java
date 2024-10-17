import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 19236. 청소년 상어 / 골드2 / 9:13 ~12:21
class Main {
    static final int[] dy={-1,-1, 0,  1, 1, 1, 0,-1};
    static final int[] dx={0 ,-1,-1, -1, 0, 1, 1, 1};
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int maxScore;
    static class Fish{

        int idx,dir,y,x;
        Fish(int idx,int dir,int y,int x){
            this.idx=idx;
            this.dir=dir;
            this.y=y;
            this.x=x;
        }
    }
    static class Shark{
        int score,dir,y,x;
        Shark(int score,int dir,int y,int x){
            this.score=score;
            this.dir=dir;
            this.y=y;
            this.x=x;
        }
    }

    public static void main(String[] args) throws IOException {
        Fish[][] arr=new Fish[4][4];
        for(int i=0;i<4;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++){
                int idx=Integer.parseInt(st.nextToken());
                int dir=Integer.parseInt(st.nextToken())-1;
                Fish fish=new Fish(idx,dir,i,j);
                arr[i][j]=fish;
            }
        }
        Fish fish=arr[0][0];
        Shark shark=new Shark(fish.idx,fish.dir,0,0);
        arr[0][0].idx=0;
        play(arr,shark,0);
        System.out.println(maxScore);
    }

    private static void play(Fish[][] arrState,Shark sharkState,int depth){
//        System.out.println("상어 몇번째무빙임??: "+depth);
//        System.out.println("상어 몇점임?????: "+sharkState.score);
//        print(arrState);
        maxScore=Math.max(maxScore,sharkState.score);

        for(int i=1;i<=16;i++){
            Fish fish=find(arrState,i);
            if(fish.idx==0)continue;

            int y=fish.y,x=fish.x;
            int origin=fish.dir;
            boolean moved = false;
//            System.out.println("물고기 번호:"+fish.idx+" 이동전 방향:"+fish.dir);
            for(int d=0;d<8;d++){
                int ny=y+dy[fish.dir];
                int nx=x+dx[fish.dir];
                if (ny>=0&&nx>=0&&ny<4&&nx<4&&(ny!=sharkState.y||nx!=sharkState.x)) {
                    swap(arrState,fish.y,fish.x,ny,nx);
                    moved=true;
                    break;
                }
                fish.dir=(fish.dir+1)%8;
            }
            if(!moved){
                fish.dir=origin;
            }
//            System.out.println("물고기 번호:"+fish.idx+" 이동 방향:"+fish.dir);
//            print(arrState);
        }
        int d=sharkState.dir,y=sharkState.y,x=sharkState.x;
        boolean canMove=false;
        for(int i=1;i<=3;i++){
            int ny=y+dy[d]*i;
            int nx=x+dx[d]*i;
            if(ny<0||nx<0||ny>=4||nx>=4||arrState[ny][nx].idx==0)continue;

            canMove=true;

            Fish[][] arrCopy=new Fish[4][4];
            for (int j=0; j<4;j++) {
                for (int k=0; k<4; k++) {
                    if (arrState[j][k] != null) {
                        arrCopy[j][k] = new Fish(arrState[j][k].idx, arrState[j][k].dir, arrState[j][k].y, arrState[j][k].x);
                    }
                }
            }
            Shark sharkCopy=new Shark(sharkState.score,sharkState.dir, sharkState.y, sharkState.x);

            sharkCopy.score+=arrCopy[ny][nx].idx;
            sharkCopy.dir=arrCopy[ny][nx].dir;

            sharkCopy.y=ny;
            sharkCopy.x=nx;
            arrCopy[ny][nx].idx=0;

            play(arrCopy,sharkCopy,depth+1);
        }
        if(!canMove){
            maxScore=Math.max(maxScore,sharkState.score);
        }
    }

    private static Fish find(Fish[][] arrState, int i) {
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                if(arrState[y][x]!=null&&arrState[y][x].idx==i)return arrState[y][x];
            }
        }
        return new Fish(0,0,0,0);
    }

    private static void print(Fish[][] arrState) {
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(arrState[i][j].idx+" ");
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    private static void swap(Fish[][] arrState, int y, int x, int ny, int nx) {
        Fish temp=arrState[y][x];
        arrState[y][x]=arrState[ny][nx];
        arrState[ny][nx]=temp;

        arrState[ny][nx].y=ny;
        arrState[ny][nx].x=nx;
        if (arrState[y][x]!=null) {
            arrState[y][x].y=y;
            arrState[y][x].x=x;
        }
    }
}






















