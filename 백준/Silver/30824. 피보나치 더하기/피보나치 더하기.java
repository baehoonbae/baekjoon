import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        next:
        for (int T = 1; T <= t; T++) {
            List<BigInteger> fib = new ArrayList<>();
            fib.add(BigInteger.valueOf(1));
            fib.add(BigInteger.valueOf(1));

            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            BigInteger x = new BigInteger(st.nextToken());

            BigInteger num = BigInteger.ZERO;
            for (int i = 2; num.compareTo(x) < 0; i++) {
                num = fib.get(i - 2).add(fib.get(i - 1));
                fib.add(num);
            }
            for (int i = 0; i < fib.size(); i++) {
                if (k == 1 && fib.get(i).equals(x)) {
                    System.out.println("YES");
                    continue next;
                }
                for (int j = i; j < fib.size(); j++) {
                    if (k == 2) {
                        if (fib.get(i).add(fib.get(j)).equals(x)) {
                            System.out.println("YES");
                            continue next;
                        }
                    } else if (k == 3) {
                        for (int m = j; m < fib.size(); m++) {
                            if (fib.get(i).add(fib.get(j)).add(fib.get(m)).equals(x)) {
                                System.out.println("YES");
                                continue next;
                            }
                        }
                    }
                }
            }
            System.out.println("NO");
        }
    }
}
