import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 1838. 버블 정렬 / 골드1 / 8:27~ 9:10
// 정렬된 배열을 하나 해서
// 정렬된 배열의 각 요소랑 거리가 음수인지 양수인지 판단
// 양수라면 어차피 버블 정렬 과정에서 알아서 정렬된다
// 음수라면 버블 정렬을 해야하는 실질적인 주체를 의미함(위치가 inversion 되었음)
// => 이건사실 그냥 inversion 을 count 하는 거고..
// 일단 이문제는 각 숫자가 정렬된 위치로 이동하는 데 몇 번의 패스가 필요한지를 파악하는 것
// 버블 정렬에서는 결국 정렬된 위치로 가장 많이 이동해야 하는 놈이 전체 버블 정렬 과정의 회전 수를 결정짓는다..
public class Main {
    static int[] arr;
    static int[] sorted;
    static int n;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        sorted = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sorted = arr.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        int move = 0;
        for (int i = 0; i < n; i++) {
            move = Math.max(move, map.get(sorted[i]) - i);
        }
        System.out.println(move);
    }

}
