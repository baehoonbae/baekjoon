import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17387. 선분 교차 2 / 골드2 / 4:30 ~ 5:36
// 새로운 개념: 선분 교차 판정
// - 주로 CCW(반시계방향) 알고리즘을 통해 교차 여부를 판단한다.
// A와 B점이 있고, C와 D점이 있다고 가정
// 이때 선분 AB와 선분 CD가 교차하는지 판단하려면?
// 1. A -> C -> B 가 시계 방향인지 반시계 방향인지 판단
// 2. A -> D -> B 가 시계 방향인지 반시계 방향인지 판단
// 3. 두 식의 결괏값이 다르다면 두 선분은 교차하는 것을 의미한다.
// 두 CCW 값을 곱했을 때 음수라면 교차하는 것이고, 양수라면 교차하지 않는 것
// CCW 값이 0이라면 두 선분이 일직선 상에 있는 것
// 이때 주의해야할 점은 한 선분에 대해서만 다른 선분과의 관계를 판단하지 말고
// CCW 판정을 두 선분 모두에 적용해 선분들이 서로 교차하는지 완전하게 확인해야 함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ax1 = Integer.parseInt(st.nextToken());
        int ay1 = Integer.parseInt(st.nextToken());
        int ax2 = Integer.parseInt(st.nextToken());
        int ay2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int bx1 = Integer.parseInt(st.nextToken());
        int by1 = Integer.parseInt(st.nextToken());
        int bx2 = Integer.parseInt(st.nextToken());
        int by2 = Integer.parseInt(st.nextToken());

        int[] a = new int[]{ax1, ay1};
        int[] b = new int[]{ax2, ay2};
        int[] c = new int[]{bx1, by1};
        int[] d = new int[]{bx2, by2};
        System.out.println(isIntersected(a, b, c, d) ? 1 : 0);
    }

    private static boolean isIntersected(int[] a, int[] b, int[] c, int[] d) {
        int ccw1 = ccw(a, b, c) * ccw(a, b, d);
        int ccw2 = ccw(c, d, a) * ccw(c, d, b);
        if (ccw1 <= 0 && ccw2 <= 0) {
            if (ccw1 == 0 && ccw2 == 0) {
                return Math.min(a[0], b[0]) <= Math.max(c[0], d[0])&&
                        Math.min(c[0], d[0]) <= Math.max(a[0], b[0]) &&
                        Math.min(a[1], b[1]) <= Math.max(c[1], d[1]) &&
                        Math.min(c[1], d[1]) <= Math.max(a[1], b[1]);
            }
            return true;
        }
        return false;
    }

    private static int ccw(int[] a, int[] b, int[] c) {
        long result = (long) (b[0] - a[0]) * (c[1] - a[1]) - (long) (b[1] - a[1]) * (c[0] - a[0]);
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}