import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int count=0;
        for(int i=1;i<=n;i++){
            if(n%i==0) count++;
        }
        System.out.println(count==2?"Yes":"No");
    }
}
