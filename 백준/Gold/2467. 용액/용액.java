import java.util.*;

// 2467. 용액 / 골드5 / 11:20 ~ 11:30
// 두개 고르는 경우 모두 백트래킹으로 탐색하기.
// 주의할 점은 특성값이 0에 가까운 값을 구해야하는 것이므로 두 용액 특성값의 합의 절댓값이 가장 작은걸 고르기!
// 인 줄 알았으나 메모리 초과남.. 투 포인터로 푸는게 굉장히 효율적일 듯ㅠㅠ
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int[] arr;
    static int n, minSumAbs;

    public static void main(String[] args) {
        StringTokenizer st;
        Scanner sc = new Scanner(System.in);

        minSumAbs = Integer.MAX_VALUE;
        n = sc.nextInt();
        arr = new int[n];
        sc.nextLine();
        st = new StringTokenizer(sc.nextLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;

        while (left < right) {
            StringBuilder sb = new StringBuilder();
            sb.append(arr[left]).append(" ").append(arr[right]);

            int sum = arr[left] + arr[right];
            int sumAbs = Math.abs(sum);

            if (minSumAbs > sumAbs) {
                minSumAbs = sumAbs;
                ans = sb;
            }

            // sum이 0보다 크면 오른쪽을 줄여주고
            // 0보다 작으면 왼쪽을 늘려준다
            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(ans);
    }
}
