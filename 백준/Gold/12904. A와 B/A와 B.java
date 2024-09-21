import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String a, b;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        a = br.readLine();
        b = br.readLine();
        play(new StringBuilder(b));
        System.out.println(flag ? 1 : 0);
    }

    public static void play(StringBuilder str) {
        if (str.toString().equals(a)) {
            flag = true;
            return;
        }
        if (str.toString().isEmpty()) {
            return;
        }
        if (str.charAt(str.length() - 1) == 'B') {
            str = str.deleteCharAt(str.length() - 1);
            str = str.reverse();
            play(str);
            str = str.reverse();
            str.append('B');
        } else {
            str.deleteCharAt(str.length() - 1);
            play(str);
            str.append('B');
        }
    }
}