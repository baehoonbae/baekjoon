import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static boolean[] visited;
    static Set<String> map;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        map = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        bt(0, 0, new ArrayDeque<>());
        System.out.print(sb);
    }

    public static void bt(int idx, int count, Deque<Integer> list) {
        if (count == m) {
            String key = list.toString();
            if (map.contains(key)) {
                return;
            }
            map.add(key);
            for (Integer i : list) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        if (idx == n) {
            return;
        }
        for (int i = 0; i < n; i++) {
            list.add(arr[i]);
            bt(i, count + 1, list);
            list.pollLast();
        }
    }
}
