import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[] arr;
    static long n, p, l;
    static long minTotal;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        l = Long.parseLong(st.nextToken());
        arr = new long[(int) n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        minTotal = Long.MAX_VALUE;
        bt(0, 0, new ArrayList<>());
        System.out.println(minTotal);
        System.out.println(ans);
    }

    public static void bt(int idx, int count, List<Integer> list) {
        // 종료조건
        if (count == p) {
            long total = calculate(list);
            if (minTotal > total) {
                minTotal = total;
                StringBuilder sb = new StringBuilder();
                for (Integer i : list) {
                    sb.append(arr[i]).append(" ");
                }
                ans = sb;
            }
            return;
        }
        if (idx == n) {
            return;
        }
        // 재귀
        list.add(idx);
        bt(idx + 1, count + 1, list);
        list.remove(list.size() - 1);
        bt(idx + 1, count, new ArrayList<>(list));
    }

    public static long calculate(List<Integer> list) {
        long total = 0;
        for (int i = 0; i < n; i++) {
            long minDist = Long.MAX_VALUE;
            for (Integer j : list) {
                long dist = Math.min(
                        Math.abs(arr[i] - arr[j]),
                        l - Math.abs(arr[i] - arr[j])
                );
                minDist = Math.min(minDist, dist);
            }
            total += minDist;
        }
        return total;
    }
}
