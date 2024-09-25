import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static Map<Integer, Integer> map = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            map.put(arr1[i - 1], i);
        }
        for (int i = 1; i <= n; i++) {
            int prev = map.get(arr2[i - 1]);
            map.put(arr2[i - 1], prev - i);
            maxValue = Math.max(maxValue, prev - i);
        }
        for (int i = 0; i < n; i++) {
            if (map.get(arr2[i]) == maxValue) {
                System.out.print(arr2[i] + " ");
            }
        }
    }
}