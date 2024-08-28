import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String s;
    static char[] arr;
    static char[] res;
    static boolean[] visited;
    static int size;
    static int index, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while ((str = br.readLine()) != null) {
            String[] st = str.split(" ");
            index = 0;
            
            s = st[0];
            target = Integer.parseInt(st[1]);
            
            size = s.length();
            visited = new boolean[size];
            res = new char[size];
            arr = s.toCharArray();

            bt(0);
            if (index < target) {
                System.out.println(s + " " + target + " = No permutation");
            }
        }
    }

    public static void bt(int count) {
        if (count == size) {
            index++;
            if (index == target) {
                StringBuilder sb = new StringBuilder();
                for (char c : res) {
                    sb.append(c);
                }
                System.out.println(s + " " + target + " = " + sb);
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    res[count] = arr[i];
                    bt(count + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
