import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static int n;
    static int[] maxArr;
    static int[] minArr;

    // 한줄 읽고 다음 줄읽고 계산하고 이전줄 버리고 다음줄 읽고 이전줄 버리고 반복하기
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int maxValue = 0;
        int minValue = Integer.MAX_VALUE;
        maxArr = new int[3];
        minArr = new int[3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxArr[0] = minArr[0] = x1;
                maxArr[1] = minArr[1] = x2;
                maxArr[2] = minArr[2] = x3;
            } else {
                int maxPrev0 = maxArr[0];
                int maxPrev2 = maxArr[2];
                maxArr[0] = Math.max(maxArr[0], maxArr[1]) + x1;
                maxArr[2] = Math.max(maxArr[2], maxArr[1]) + x3;
                maxArr[1] = Math.max(maxPrev0, Math.max(maxArr[1], maxPrev2)) + x2;

                int minPrev0 = minArr[0];
                int minPrev2 = minArr[2];
                minArr[0] = Math.min(minArr[0], minArr[1]) + x1;
                minArr[2] = Math.min(minArr[2], minArr[1]) + x3;
                minArr[1] = Math.min(minPrev0, Math.min(minArr[1], minPrev2)) + x2;
            }
        }

        for (int i = 0; i < 3; i++) {
            maxValue = Math.max(maxValue, maxArr[i]);
            minValue = Math.min(minValue, minArr[i]);
        }

        System.out.println(maxValue + " " + minValue);
    }
}
