import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String s = "aeiou";
    static int n, m;
    static char[] arr;
    static List<Character> list = new ArrayList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        sc.nextLine();

        arr = new char[n];

        String s = sc.nextLine();
        arr = s.replace(" ", "").toCharArray();
        Arrays.sort(arr);

        bt(0, 0, 0, 0);
        bw.flush();
        bw.close();
    }

    public static void bt(int idx, int count, int moeum, int jaeum) throws IOException {
        if (count == m) {
            if (moeum >= 1 && jaeum >= 2) {
                for (char c : list) {
                    bw.write(c);
                }
                bw.write("\n");
            }
            return;
        }
        if (idx == n) {
            return;
        }
        list.add(arr[idx]);
        if (s.contains(String.valueOf(arr[idx]))) {
            bt(idx + 1, count + 1, moeum + 1, jaeum);
            list.remove(list.size() - 1);
            bt(idx + 1, count, moeum, jaeum);
        } else {
            bt(idx + 1, count + 1, moeum, jaeum + 1);
            list.remove(list.size() - 1);
            bt(idx + 1, count, moeum, jaeum);
        }
    }
}
