import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1202. 보석 도둑 / 골드2 / 4:12 ~ 4:53
// 가방별, 보석별로 완탐으로 접근 -> 시간 초과
// 메모이제이션 -> 메모리 초과
// 그리디로 접근해보자 -> 가방과 보석 무게 순으로 정렬
// 가방별로 담을 수 있는 후보 보석들을 우선순위 큐에 모두 담는다
// 우선순위 큐는 가장 가치가 높은 보석 먼저 나오게
// 우선순위 큐가 비어있지 않다면 그 가방에 담는다(maxPrice 기록)
public class Main {
    public static class Jewel {
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

    static Jewel[] arr;
    static int[] bags;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new Jewel[n];
        bags = new int[k];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            arr[i] = new Jewel(weight, price);
        }
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, (a, b) -> a.weight - b.weight);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        long maxPrice = 0;
        int idx = 0;
        for (int i = 0; i < k; i++) {
            while (idx < n && arr[idx].weight <= bags[i]) {
                pq.offer(arr[idx].price);
                idx++;
            }
            if (!pq.isEmpty()) {
                maxPrice += pq.poll();
            }
        }
        System.out.println(maxPrice);
    }
}
