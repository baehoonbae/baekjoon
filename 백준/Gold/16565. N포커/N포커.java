import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static final BigInteger MOD=new BigInteger(String.valueOf(10_007));

    public static void main(String[] args) {
        int n=new Scanner(System.in).nextInt();
        BigInteger res=new BigInteger("0");

        if(n<4){
            System.out.println(0);
        }else{
            int m=1;
            while(m*4<=n){
                BigInteger term = nCr(13, m).multiply(nCr(52 - (4 * m), n - (4 * m))).mod(MOD);
                if (m % 2 != 0) {
                    res = res.add(term).mod(MOD);  // 값을 res에 저장
                } else {
                    res = res.subtract(term).mod(MOD);  // 값을 res에 저장
                }
                m++;
            }
            System.out.println(res);
        }
    }

    private static BigInteger nCr(int n,int r){
        return fact(n,n-r).divide(fact(r,1)).mod(MOD);
    }

    private static BigInteger fact(int n,int r) {
        BigInteger temp=new BigInteger("1");
        for(long i=n;i>r;i--){
            temp=temp.multiply(new BigInteger(String.valueOf(i)));
        }
        return temp;
    }
}