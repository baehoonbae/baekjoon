import java.io.*;

public class Main {
    static char[][] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new char[n + 1][n + 1];
        int y = n / 2 + 1;
        int x = n / 2 + 1;
        recursion(n, y, x);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                bw.write(arr[i][j] == '*' ? '*' : ' ');
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void recursion(int n, int y, int x) {
        int go = n / 3;
        if (n == 3) {
            arr[y - go][x - go]
                    = arr[y - go][x]
                    = arr[y - go][x + go]
                    = arr[y][x - go]
                    = arr[y][x + go]
                    = arr[y + go][x - go]
                    = arr[y + go][x]
                    = arr[y + go][x + go]
                    ='*';
            return;
        }

        // 위 3곳
        recursion(n / 3, y - go, x - go);
        recursion(n / 3, y - go, x);
        recursion(n / 3, y - go, x + go);

        // 가운데 2곳
        recursion(n / 3, y, x - go);
        recursion(n / 3, y, x + go);

        // 아래 3곳
        recursion(n / 3, y + go, x - go);
        recursion(n / 3, y + go, x);
        recursion(n / 3, y + go, x + go);
    }
}
