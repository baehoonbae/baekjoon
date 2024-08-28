import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer> list = new ArrayList<>();
    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        bt(0, 0);
        Collections.sort(list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) - list.get(i - 1) > 1) {
                System.out.println(list.get(i - 1) + 1);
                return;
            }
        }
        System.out.println(list.get(list.size() - 1) + 1);
    }

    public static void bt(int idx, int sum) {
        if (idx == n) {
            list.add(sum);
            return;
        }
        bt(idx + 1, sum + arr[idx]);
        bt(idx + 1, sum);
    }
}
