import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            Set<Character> 숫자0에서9까지 = new HashSet<>();

            int n = Integer.parseInt(br.readLine());
            int i = n;
            while (숫자0에서9까지.size() < 10) {
                String s = String.valueOf(n);
                for (int j = 0; j < s.length(); j++) {
                    숫자0에서9까지.add(s.charAt(j));
                }
                n += i;
            }
            sb.append('#').append(t).append(' ').append(n - i).append("\n");
        }
        System.out.print(sb);
    }
}