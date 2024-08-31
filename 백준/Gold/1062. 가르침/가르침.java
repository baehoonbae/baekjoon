import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1062. 가르침 / 골드4 / 1:05~1:59
// 변수 목록
//  - n: 단어 개수
//  - k: 가르칠 수 있는 글자 개수
// 1. 문자열 배열에 저장
// 1. 각 문자가 뭐뭐나오는지 따로 체크
// 2. a ~ z 까지 k개를 선택하는 조합을 돈다
// 3. 조합이 만들어지면 n개 단어 중 조합의 글자들만으로 이루어진 단어의 개수를 구한다
// 4. 개수의 최댓값을 갱신한다.
// 5. 현재 단순 자료구조들을 사용하고, 만들어진 조합에 대해서도 일일히 다 확인해보기 때문에 시간초과가 날 듯 하다.
// 6. 비트마스킹으로 더 단순하고 빠르게 구현가능할듯
public class Main {
    static int[] arr;
    static int n, k;
    static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()) - 5;
        if (k < 0) {
            System.out.println(0);
            return;
        }
        int base = 0;
        maxCount = 0;
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                arr[i] |= 1 << (s.charAt(j) - 'a');
            }
        }
        // a, c, i, n, t 는 모든 단어에 동일하게 있으므로
        // 미리 켜준다
        base |= 1 << ('a' - 'a');
        base |= 1 << ('c' - 'a');
        base |= 1 << ('i' - 'a');
        base |= 1 << ('n' - 'a');
        base |= 1 << ('t' - 'a');
        alphabet(0, 0, base);
        System.out.println(maxCount);
    }

    public static void alphabet(int idx, int count, int learn) {
        if (count == k) {
            int cnt = 0;
            for (int word : arr) {
                if ((word & learn) == word) {
                    cnt++;
                }
            }
            maxCount = Math.max(maxCount, cnt);
            return;
        }
        if (idx == 26) {
            return;
        }
        if ((learn & (1 << idx)) == 0) {
            alphabet(idx + 1, count + 1, learn |(1 << idx));
        }
        alphabet(idx + 1, count, learn);
    }
}
