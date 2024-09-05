import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 14003. 가장 긴 증가하는 부분 수열 5 / 플래5 / 10:47~
// 정석적인 n log n 으로 풀어야 하는 LIS 문제로 보인다.
// 수열의 크기가 1_000_000 까지 주어지니, 브루트포스는 절대 통과할 수 없다
// 따라서 뒤에 붙을 수 있는 길이를 인덱스로 하는 배열(리스트)을 정의하고, 거기에 해당하는 값만 기록
// 이때 이 배열은 반드시 정렬되므로, 배열을 순회할 때 해당 요소가 어디 뒤에 붙을 수 있는지를 이분 탐색으로 값만 업데이트하자.
public class Main {
    static int[] arr, lis, dp, prev;
    static int n;
    static int size;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        lis = new int[n];
        dp = new int[n];
        prev = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = new ArrayList<>();
        size = 0;

        for (int i = 0; i < n; i++) {
            int pos = bs(list, arr[i]);
            if (pos == list.size()) {
                list.add(arr[i]);
            } else {
                list.set(pos, arr[i]);
            }
            dp[i] = pos + 1;
            prev[i] = (pos > 0) ? lis[pos - 1] : -1;
            lis[pos] = i;
            if (pos + 1 > size) {
                size = pos + 1;
            }
        }
        sb.append(size).append("\n");
        Stack<Integer> stack = new Stack<>();
        int idx = lis[size - 1];
        while (idx != -1) {
            stack.push(arr[idx]);
            idx = prev[idx];
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    public static int bs(List<Integer> list, int key) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}