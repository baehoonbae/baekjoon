import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] temp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        temp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());
        query(1, n);

        int count = 0;
        int left = 1;
        int right = n;

        while (left < right) {
            if (arr[left] + arr[right] == x) {
                left++;
                right--;
                count++;
            } else if (arr[left] + arr[right] < x) {
                left++;
            } else if (arr[left] + arr[right] > x) {
                right--;
            }
        }

        System.out.println(count);
    }

    public static void query(int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        query(start, mid);
        query(mid + 1, end);
        merge(start, mid, end);
    }

    public static void merge(int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        for (int l = start; l <= end; l++) {
            arr[l] = temp[l];
        }
    }
}
