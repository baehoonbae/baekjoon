import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Egg {
        int hp;
        int power;

        Egg(int dur, int weight) {
            this.hp = dur;
            this.power = weight;
        }
    }

    static Egg[] eggs;
    static int n;
    static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        maxCount = 0;
        eggs = new Egg[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int dur = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(dur, weight);
        }
        solve(1);
        System.out.println(maxCount);
    }

    public static void solve(int idx) {
        if (idx >= eggs.length) {
            int cnt = 0;
            for (int i = 1; i < eggs.length; i++) {
                if (eggs[i].hp <= 0) {
                    cnt++;
                }
            }
            maxCount = Math.max(maxCount, cnt);
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (eggs[idx].hp <= 0) {
                solve(idx + 1);
            } else if (idx == i || eggs[i].hp <= 0) {
                continue;
            } else {
                eggs[idx].hp -= eggs[i].power;
                eggs[i].hp -= eggs[idx].power;
                solve(idx + 1);
                eggs[idx].hp += eggs[i].power;
                eggs[i].hp += eggs[idx].power;
            }
        }
        int cnt = 0;
        for (int i = 1; i < eggs.length; i++) {
            if (eggs[i].hp <= 0) {
                cnt++;
            }
        }
        maxCount = Math.max(maxCount, cnt);
    }
}