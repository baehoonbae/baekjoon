import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] A, B, C, D;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];
        int[] AB = new int[n * n];
        int[] CD = new int[n * n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx++] = A[i] + B[j];
            }
        }
        idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                CD[idx++] = C[i] + D[j];
            }
        }
        Arrays.sort(CD);
        long count = 0;
        for (int i = 0; i < n * n; i++) {
            int lower = lowerBound(CD, -AB[i]);
            int upper = upperBound(CD, -AB[i]);
            count += (upper - lower);
        }
        System.out.println(count);
    }

    private static int lowerBound(int[] CD, int key) {
        int left = 0;
        int right = CD.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (key <= CD[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int upperBound(int[] CD, int key) {
        int left = 0;
        int right = CD.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (key < CD[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}