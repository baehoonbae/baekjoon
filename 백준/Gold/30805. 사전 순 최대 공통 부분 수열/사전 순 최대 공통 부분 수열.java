import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 30805. 사전 순 최대 공통 부분 수열 / 골드4 / 8:53 ~ 10:46
public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n1 = Integer.parseInt(br.readLine());
        List<int[]> a = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n1; i++) {
            int num = Integer.parseInt(st.nextToken());
            a.add(new int[]{i, num});
        }

        int n2 = Integer.parseInt(br.readLine());
        List<int[]> b = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n2; i++) {
            int num = Integer.parseInt(st.nextToken());
            b.add(new int[]{i, num});
        }

        a.sort((x, y) -> Integer.compare(y[1], x[1]));
        b.sort((x, y) -> Integer.compare(y[1], x[1]));

        List<int[]> list = new ArrayList<>();
        int aIdx = 0, bIdx = 0, aMax = 0, bMax = 0;
        while (aIdx < n1 && bIdx < n2) {
            if (a.get(aIdx)[1] == b.get(bIdx)[1]) {
                if(aMax>a.get(aIdx)[0]){
                    aIdx++;
                } else if(bMax > b.get(bIdx)[0]){
                    bIdx++;
                } else {
                    aMax = a.get(aIdx)[0];
                    bMax = b.get(bIdx)[0];
                    list.add(a.get(aIdx));
                    aIdx++;
                    bIdx++;
                }
                
            } else if (a.get(aIdx)[1] > b.get(bIdx)[1]) {
                aIdx++;
            } else {
                bIdx++;
            }
        }

        sb.append(list.size()).append("\n");
        for (int[] ans : list) {
            sb.append(ans[1]).append(" ");
        }
        System.out.println(sb);
    }
}
