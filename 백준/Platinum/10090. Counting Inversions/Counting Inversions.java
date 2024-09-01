import java.util.Scanner;

// 10090. Counting Inversions / 플래5 / 3:29 ~ 3:46
// 정렬을 하기 위해 스왑을 해야하는 개수를 세야 하는 문제
// 즉 이는 버블 소트의 스왑 횟수를 세야한다
// 버블 소트의 inversion 은 병합 정렬 과정에서 그 개수를 셀 수 있다.
// 이유는 정렬 과정에서 왼쪽이 더 작은 쪽, 오른쪽이 더 작은 쪽이 와야 하는게 일반적이므로
// 그래서 만약 오른쪽이 먼저 결과 배열에 붙여넣어져야 한다면 이는 원래 결과와는 다르게 inversion 되어있다는 것을 뜻한다.
// 따라서 오른쪽이 결과 배열에 붙여넣어지는 count 를 셌다가, 병합의 끝 과정에서 count 를 더해주면 되겠다.
// + 병합 했으면 다시 원본 배열에 붙여넣는 걸 까먹지 말자.
public class Main {
    static int[] arr;
    static int[] temp;
    static int n;
    static long iCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        arr = new int[n + 1];
        temp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        query(1, n);
        System.out.println(iCount);
    }

    private static void query(int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        query(start, mid);
        query(mid + 1, end);
        merge(start, mid, end);
    }

    private static void merge(int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // 오른쪽 요소가 선택됐으면 왼쪽의 남아있는 요소만큼 inversion 이 생기기 때문에 그만큼 count 해줘야 함
                iCount += (mid - i + 1);
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
