import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 17140. 이차원 배열과 연산 / 골드4 / 2시간 6분

// r -> 행의 개수 >= 열의 개수 -> 모든 행 정렬
// c -> 행의 개수 < 열의 개수 -> 모든 열 정렬
// 정렬은 특정 규칙으로 정렬됨
// 수의 등장 횟수 오름차순 -> 숫자 오름차순
public class Main {
    static int r, c, k;
    static List<List<Integer>> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            arr.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                arr.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        int time = 0;
        while (time <= 100) {
            if (r < arr.size() && c < arr.get(0).size() && arr.get(r).get(c) == k) {
                System.out.println(time);
                return;
            }
            if (arr.size() >= arr.get(0).size()) {
                rowSort();
            } else {
                colSort();
            }
            time++;
        }
        System.out.println(-1);
    }

    // 각 행마다 숫자가 몇개 나왔는지 개수 세기(HashMap)
    // 개수 기준 오름차순 정렬 -> 숫자 기준 오름차순 정렬
    // 새로운 2차원 리스트에다 추가
    // 빈 부분은 0으로 채워넣기
    // 기존 2차원 리스트에 덮어씌우기
    public static void rowSort() {
        List<List<Integer>> newList = new ArrayList<>();
        int maxSize = 0;

        for (List<Integer> row : arr) {
            Map<Integer, Integer> numCount = new HashMap<>();
            for (int num : row) {
                if (num == 0) continue;
                numCount.merge(num, 1, Integer::sum);
            }

            List<int[]> countList = new ArrayList<>();
            for (int i : numCount.keySet()) {
                countList.add(new int[]{i, numCount.get(i)});
            }
            countList.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

            List<Integer> newRow = new ArrayList<>();
            for (int[] pair : countList) {
                newRow.add(pair[0]);
                newRow.add(pair[1]);
                if (newRow.size() >= 100) break;
            }
            newList.add(newRow);
            maxSize = Math.max(maxSize, newRow.size());
        }
        for (List<Integer> row : newList) {
            while (row.size() < maxSize) {
                row.add(0);
            }
        }
        arr = newList;
    }

    // 배열의 행과 열을 바꾸고 rowSort 한 후 다시 행과 열을 바꾸면 열을 기준으로 정렬한게 된다.
    public static void colSort() {
        List<List<Integer>> newList = new ArrayList<>();
        for (int i = 0; i < arr.get(0).size(); i++) {
            List<Integer> col = new ArrayList<>();
            for (List<Integer> row : arr) {
                col.add(row.get(i));
            }
            newList.add(col);
        }
        arr = newList;
        rowSort();
        newList = new ArrayList<>();
        for (int i = 0; i < arr.get(0).size(); i++) {
            List<Integer> col = new ArrayList<>();
            for (List<Integer> row : arr) {
                col.add(row.get(i));
            }
            newList.add(col);
        }
        arr = newList;
    }
}
