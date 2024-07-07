import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] prefs = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            prefs[i + 1] = prefs[i] + num;
        }
        int count = 0;
        int left = 1;
        int right = 1;
        while (right <= n) {
            while (prefs[right] - prefs[left] > m) {
                left++;
            }
            if (prefs[right] - prefs[left] == m || prefs[right] == m) {
                count++;
            }
            right++;
        }
        System.out.println(count);
        return;
    }
}