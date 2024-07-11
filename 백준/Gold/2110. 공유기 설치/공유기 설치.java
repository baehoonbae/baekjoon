import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        long result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] position = new int[n];
        for (int i = 0; i < n; i++) {
            position[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(position);
        long left = 0;
        long right = position[position.length - 1] - position[0];
        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(position, mid, m)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }

    private static boolean check(int[] position, long mid, int m) {
        int count = 1;
        int prev = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - prev >= mid) {
                count++;
                prev = position[i];
            }
        }
        return count >= m;
    }
}