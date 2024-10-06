import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // front 를 바라보는 기준
    public static class Cube {
        char[][] up = {"www".toCharArray(), "www".toCharArray(), "www".toCharArray()};
        char[][] down = {"yyy".toCharArray(), "yyy".toCharArray(), "yyy".toCharArray()};
        char[][] front = {"rrr".toCharArray(), "rrr".toCharArray(), "rrr".toCharArray()};
        char[][] back = {"ooo".toCharArray(), "ooo".toCharArray(), "ooo".toCharArray()};
        char[][] left = {"ggg".toCharArray(), "ggg".toCharArray(), "ggg".toCharArray()};
        char[][] right = {"bbb".toCharArray(), "bbb".toCharArray(), "bbb".toCharArray()};

        public void rotate(char[][] target,int dir){
//            System.out.println("돌리기 전, 방향:"+dir);
//            target=new char[][]{"ywg".toCharArray(),"bor".toCharArray(),"yrw".toCharArray()};
//            dir=-1;
//            print2(target);
            if(dir==1){
                String[] rows=new String[3];
                for(int i=0;i<3;i++){
                    String s="";
                    for(int j=2;j>=0;j--){
                        s+=target[j][i];
                    }
                    rows[i]=s;
                }
                for(int i=0;i<3;i++){
                    target[i]=rows[i].toCharArray();
                }
            }else{
                String[] rows=new String[3];
                for(int i=2;i>=0;i--){
                    String s="";
                    for(int j=0;j<3;j++){
                        s+=target[j][i];
                    }
                    rows[2-i]=s;
                }
                for(int i=0;i<3;i++){
                    target[i]=rows[i].toCharArray();
                }
            }
//            System.out.println("돌린 후");
//            print2(target);
        }
        // 위 -> 왼,오,앞,뒤 0행 영향
        // 반시계: 왼 -> 앞 -> 오 -> 뒤
        // 시계: 왼 <- 앞 <- 오 <- 뒤
        public void rotateU(int dir) {
            rotate(up, dir);
            char[] leftTemp=left[0].clone();
            char[] frontTemp=front[0].clone();
            char[] rightTemp=right[0].clone();
            char[] backTemp=back[0].clone();
            if(dir==1){
                left[0]=frontTemp;
                front[0]=rightTemp;
                right[0]=backTemp;
                back[0]=leftTemp;
            }else{
                left[0]=backTemp;
                back[0]=rightTemp;
                right[0]=frontTemp;
                front[0]=leftTemp;
            }
        }

        // 아래 -> 왼,오,앞,뒤 2행 영향
        public void rotateD(int dir) {
            rotate(down, dir);
            char[] leftTemp=left[2].clone();
            char[] frontTemp=front[2].clone();
            char[] rightTemp=right[2].clone();
            char[] backTemp=back[2].clone();
            if(dir==1){
                left[2]=backTemp;
                back[2]=rightTemp;
                right[2]=frontTemp;
                front[2]=leftTemp;
            }else{
                left[2]=frontTemp;
                front[2]=rightTemp;
                right[2]=backTemp;
                back[2]=leftTemp;
            }
        }

        // 왼쪽 -> 위,아래,앞0열 /뒤 2열
        public void rotateL(int dir) {
            rotate(left, dir);
            char[] upTemp=new char[3];
            char[] downTemp=new char[3];
            char[] frontTemp=new char[3];
            char[] backTemp=new char[3];
            for(int i=0;i<3;i++){
                upTemp[i]=up[i][0];
                downTemp[i]=down[i][0];
                frontTemp[i]=front[i][0];
                backTemp[i]=back[i][2];
            }
            if (dir == 1) {
                for(int i=0;i<3;i++){
                    up[i][0]=backTemp[2-i];
                    back[i][2]=downTemp[2-i];
                    down[i][0]=frontTemp[i];
                    front[i][0]=upTemp[i];
                }
            } else {
                for(int i=0;i<3;i++){
                    up[i][0]=frontTemp[i];
                    front[i][0]=downTemp[i];
                    down[i][0]=backTemp[2-i];
                    back[i][2]=upTemp[2-i];
                }
            }
        }

        // 오른쪽 -> 위,아래,앞 2열/ 뒤 0열
        public void rotateR(int dir) {
            rotate(right, dir);
            char[] upTemp=new char[3];
            char[] downTemp=new char[3];
            char[] frontTemp=new char[3];
            char[] backTemp=new char[3];
            for(int i=0;i<3;i++){
                upTemp[i]=up[i][2];
                downTemp[i]=down[i][2];
                frontTemp[i]=front[i][2];
                backTemp[i]=back[i][0];
            }
            if(dir==1){
                for(int i=0;i<3;i++){
                    up[i][2]=frontTemp[i];
                    front[i][2]=downTemp[i];
                    down[i][2]=backTemp[2-i];
                    back[i][0]=upTemp[2-i];
                }
            }else{
                for(int i=0;i<3;i++){
                    up[i][2]=backTemp[2-i];
                    back[i][0]=downTemp[2-i];
                    down[i][2]=frontTemp[i];
                    front[i][2]=upTemp[i];
                }
            }
        }

        // 앞 -> 위 2행, 아래 0행, 왼 2열, 오 0열 영향
        public void rotateF(int dir) {
            rotate(front,dir);
            char[] upTemp=up[2].clone();
            char[] downTemp=down[0].clone();
            char[] rightTemp=new char[3];
            char[] leftTemp=new char[3];
            for(int i=0;i<3;i++){
                rightTemp[i]=right[i][0];
                leftTemp[i]=left[i][2];
            }
            if (dir == 1) {
                for(int i=0;i<3;i++){
                    up[2][i]=leftTemp[2-i];
                }
                for(int i=0;i<3;i++){
                    left[i][2]=downTemp[i];
                }
                for(int i=0;i<3;i++){
                    down[0][i]=rightTemp[2-i];
                }
                for(int i=0;i<3;i++){
                    right[i][0]=upTemp[i];
                }
            } else {
                up[2]=rightTemp.clone();
                for(int i=0;i<3;i++){
                    right[i][0]=downTemp[2-i];
                }
                down[0]=leftTemp.clone();
                for(int i=0;i<3;i++){
                    left[i][2]=upTemp[2-i];
                }
            }
        }

        // 뒤 -> 위 0행, 아래 2행, 왼 0열, 오 2열 영향
        public void rotateB(int dir) {
            rotate(back,dir);
            char[] upTemp=up[0].clone();
            char[] downTemp=down[2].clone();
            char[] rightTemp=new char[3];
            char[] leftTemp=new char[3];
            for(int i=0;i<3;i++){
                rightTemp[i]=right[i][2];
                leftTemp[i]=left[i][0];
            }
            if(dir==1){
                up[0]=rightTemp.clone();
                for(int i=0;i<3;i++){
                    right[i][2]=downTemp[2-i];
                }
                down[2]=leftTemp.clone();
                for(int i=0;i<3;i++){
                    left[i][0]=upTemp[2-i];
                }
            }else{
                for(int i=0;i<3;i++){
                    up[0][i]=leftTemp[2-i];
                }
                for(int i=0;i<3;i++){
                    right[i][2]=upTemp[i];
                }
                for(int i=0;i<3;i++){
                    down[2][i]=rightTemp[2-i];
                }
                for(int i=0;i<3;i++){
                    left[i][0]=downTemp[i];
                }
            }
        }
    }

    static Cube c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            c=new Cube();
            int com = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < com; j++) {
                String s=st.nextToken();
                char command = s.charAt(0);
                int dir = s.charAt(1) == '+' ? 1 : -1;
                switch (command) {
                    case 'U':c.rotateU(dir);break;
                    case 'D':c.rotateD(dir);break;
                    case 'L':c.rotateL(dir);break;
                    case 'R':c.rotateR(dir);break;
                    case 'F':c.rotateF(dir);break;
                    case 'B':c.rotateB(dir);break;
                }
            }
            print();
        }
    }

    private static void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(c.up[i][j]);
            }
            System.out.println();
        }
    }

    private static void print2(char[][] arr) {
        for (int i = 0; i < 3; i++) {
            System.out.print(new String(arr[i]));
            System.out.println();
        }
    }
}