import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int[][] participants = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            participants[i][0] = Integer.parseInt(st.nextToken());
            participants[i][1] = Integer.parseInt(st.nextToken());
        }
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int time = 0;
            int remaining = k;
            while (remaining > 0) {
                int drink = Math.min(remaining, participants[i][0] * a);
                remaining -= drink;
                time += drink / a;
                if (remaining > 0) {
                    time += participants[i][1];
                }
            }
            minTime = Math.min(minTime, time);
        }
        System.out.println(minTime);
    }
}