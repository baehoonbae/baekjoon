import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int size;
    static int n;
    static int[] arr, lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        lis = new int[n];
        size = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(lis, arr[i]);
            if (idx > 0 && lis[idx - 1] == arr[i]) {
                continue;
            }
            if (idx < size) {
                lis[idx] = arr[i];
            }else{
                lis[size++] = arr[i];
            }
        }
        System.out.println(size);
    }

    public static int binarySearch(int[] lis, int key) {
        int left = 0;
        int right = size;
        while (left < right) {
            int mid = (left + right) / 2;
            if (key < lis[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}