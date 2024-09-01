import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 1838. 버블 정렬 / 골드1 / 8:27~ 8:49
// inversion counting
// 정렬된 배열을 하나 해서
// 정렬된 배열의 각 요소랑 거리가 음수인지 양수인지 판단
// 양수라면 어차피 버블 정렬 과정에서 알아서 정렬된다
// 음수라면 버블 정렬을 해야하는 실질적인 주체를 의미함(위치가 inversion 되었음)
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
