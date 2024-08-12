import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int bIdx = 1, oIdx = 1;
            int time = 0, bTime = 0, oTime = 0;
            for (int i = 0; i < n; i++) {
                String r = st.nextToken();
                int next = Integer.parseInt(st.nextToken());
                if (r.equals("B")) {
                    bTime += Math.abs(next - bIdx) + 1;
                    if (time < bTime) {
                        time = bTime;
                    } else {
                        time++;
                        bTime = time;
                    }
                    bIdx = next;
                } else {
                    oTime += Math.abs(next - oIdx) + 1;
                    if (time < oTime) {
                        time = oTime;
                    } else {
                        time++;
                        oTime = time;
                    }
                    oIdx = next;
                }
            }
            sb.append("#").append(t).append(" ").append(time).append("\n");
        }
        System.out.print(sb);
    }
}