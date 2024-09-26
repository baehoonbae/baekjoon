import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k;
    static long[] arr;
    static int[] zeroCount;
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[n];
        zeroCount = new int[n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[i] = Long.parseLong(s, 2);
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            zeroCount[i] = m - Long.bitCount(arr[i]);
        }
        for (int i = 0; i < n; i++) {
            if (zeroCount[i] <= k) {
                if (zeroCount[i] % 2 == 0 && k % 2 == 0 || zeroCount[i] % 2 == 1 && k % 2 == 1) {
                    int count = 1;
                    for (int j = i + 1; j < n; j++) {
                        if (arr[i] == arr[j]) {
                            count++;
                        }
                    }
                    maxCount = Math.max(maxCount, count);
                }
            }
        }
        System.out.println(maxCount);
    }
}