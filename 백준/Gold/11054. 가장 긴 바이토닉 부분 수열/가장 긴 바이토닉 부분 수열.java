import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 11054. 가장 긴 바이토닉 부분 수열 / 골드4 / 5:16~
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] lis = new int[n];
        int[] lds = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            lis[i] = lds[i] = 1;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    lis[j] = Math.max(lis[j], lis[i] + 1);
                }
            }
        }
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    lds[j] = Math.max(lds[j], lds[i] + 1);
                }
            }
        }
//        int maxLen = 0;
//        for (int i = 0; i < n; i++) {
//            int incLen = lis[i];
//            int decLen = 0;
//            for (int j = i + 1; j < n; j++) {
//                decLen = Math.max(decLen, lds[j]);
//            }
//            maxLen = Math.max(incLen + decLen, maxLen);
//        }

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, lis[i] + lds[i] - 1); // i가 중복되는 부분을 제거
        }

        System.out.println(maxLen);
    }
}