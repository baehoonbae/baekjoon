import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2473. 세 용액 / 골드3 / 10:02~ 11:07
// 하나 포인터 정해놓고 그 이후 범위에서 left right 조절해가며 세 값의 합의 최소 절댓값을 구하면 된다.
public class Main {
    static int n;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        long minSum = Long.MAX_VALUE;
        long l = 0, r = 0, m = 0;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];
                if (Math.abs(minSum) > Math.abs(sum)) {
                    minSum = sum;
                    l = arr[i];
                    m = arr[left];
                    r = arr[right];
                }
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        List<Long> answer = Arrays.asList(l, m, r);
        Collections.sort(answer);
        for (long i : answer) {
            System.out.print(i + " ");
        }
    }
}