import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] in;
    static int[] post;
    static int[] inIdx;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        in = new int[n + 1];
        post = new int[n + 1];
        inIdx=new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
            inIdx[in[i]] = i;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, n, 1, n);
    }

    private static void dfs(int ins, int ine, int posts, int poste) {
        if (ins > ine || posts > poste) {
            return;
        }
        int root = post[poste];
        System.out.print(root+" ");

        int rootIdx = inIdx[root];
        int lSize = rootIdx - ins;

        // 왼쪽 서브트리
        dfs(ins, rootIdx - 1, posts, posts + lSize - 1);
        // 오른쪽 서브트리
        dfs(rootIdx + 1, ine, posts + lSize, poste - 1);
    }
}