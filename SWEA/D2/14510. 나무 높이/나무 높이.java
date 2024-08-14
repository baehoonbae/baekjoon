import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            int max = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, arr[i]);
            }

            int one = 0, two = 0;
            for (int i = 0; i < n; i++) {
                int num = max - arr[i];
                two += num / 2;
                one += num % 2;
            }
            while (two > one && Math.abs(two - one) > 1) {
                two--;
                one += 2;
            }
            int count = Math.max(one * 2 - 1, two * 2);

            // 위 로직과 완전히 똑같다.
//            if (one > two) {
//                count = one * 2 - 1;
//            } else if (two > one) {
//                count = two * 2;
//            } else {
//                count = one + two;
//            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
