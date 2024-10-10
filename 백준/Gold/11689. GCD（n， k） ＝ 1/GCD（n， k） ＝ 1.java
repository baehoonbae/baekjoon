import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long n=new Scanner(System.in).nextLong();
        long origin=n,res=n;
        for(long i=2;i*i<=origin;i++){
            if(n%i==0){
                while(n%i==0)n/=i;
                res-=res/i;
            }
        }
        System.out.println(n>1?res-(res/n):res);
    }
}