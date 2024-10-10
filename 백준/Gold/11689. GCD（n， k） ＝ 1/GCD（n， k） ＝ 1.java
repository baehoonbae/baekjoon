import java.util.Scanner;

public class Main {
    static final long max=(long)Math.pow(10L,12);
    static boolean[] che=new boolean[1000001];

    public static void main(String[] args) {
        pre();
        long n=new Scanner(System.in).nextLong();
        long origin=n;

        long res=n;
        for(long i=2;i*i<=origin;i++){
            if(n%i==0){
                while(n%i==0){
                    n/=i;
                }
                res-=res/i;
            }
        }

        if(n>1)res-=res/n;

        System.out.println(res);
    }

    private static void pre(){
        for(long i=2;i<=1000000;i++){
            if(che[(int)i])continue;
            for(long j=i*2;j<=1000000;j+=i){
                che[(int)j]=true;
            }
        }
    }
}