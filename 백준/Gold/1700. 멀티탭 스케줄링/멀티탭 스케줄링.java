import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1700. 멀티탭 스케줄링 / 골드1 / 2:06 ~ 3:19
public class Main {
    static int[] order;
    static List<Integer> list = new ArrayList<>();
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        order = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (list.size() < n) {
                if (!list.contains(order[i])) {
                    list.add(order[i]);
                }
            } else {
                if (!list.contains(order[i])) {
                    boolean[] check = new boolean[k + 1];
                    int escapeCount = 0;
                    int lastItem = -1;

                    // 멀티탭에 꽂힌 것 중에서 가장 나중에 사용될 것의 번호를 찾기
                    for (int j = i + 1; j < k; j++) {
                        if (list.contains(order[j]) && !check[order[j]]) {
                            escapeCount++;
                            check[order[j]] = true;
                            lastItem = order[j];
                        }
                        if (escapeCount == n) {
                            break;
                        }
                    }
                    if (escapeCount < n) {
                        for (int j = 0; j < list.size(); j++) {
                            if (!check[list.get(j)]) {
                                list.set(j, order[i]);
                                count++;
                                break;
                            }
                        }
                    } else {
                        list.set(list.indexOf(lastItem), order[i]);
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
