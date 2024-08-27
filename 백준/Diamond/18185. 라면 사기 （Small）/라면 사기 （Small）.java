import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 18185. 라면 사기 (Small) / 다이아5 / 11:53 ~ 4:39
// 스위핑으로 앞에서부터 그리디하게 접근하면 된다.
// 단 주의해야할 점은 1칸 앞의 라면과 2칸 앞의 라면을 사는 경우를 잘 분기해야 한다.
// 그리고 그 중에서도 현재 칸은 현재 한칸 뒤 보다 작거나 같아하고, 한칸 뒤는 두칸 뒤 보다 작거나 같아야 함
// 이유는 결국 최소의 비용으로 라면을 사야하기 때문인데, 뒤에꺼가 더 많이 남아 있을수록 필연적으로 연속으로 샀을 때 더 저렴해지는 구조가 된다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int cost = 0;
        for (int i = 1; i <= n; i++) {
            while (arr[i] != 0) {
                if (i + 1 <= n && arr[i] <= arr[i + 1] && arr[i + 1] != 0) {
                    if (i + 2 <= n && arr[i + 1] <= arr[i + 2] && arr[i + 2] != 0) {
                        cost += 7;
                        arr[i + 2]--;
                    } else {
                        cost += 5;
                    }
                    arr[i + 1]--;
                } else {
                    cost += 3;
                }
                arr[i]--;
            }
        }
        System.out.println(cost);
    }
}
