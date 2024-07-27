import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        boolean inc = true;
        boolean dec = true;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1].compareTo(arr[i]) < 0) {
                dec = false;
            } else if (arr[i - 1].compareTo(arr[i]) > 0) {
                inc = false;
            }
        }
        if (inc) {
            System.out.println("INCREASING");
        } else if (dec) {
            System.out.println("DECREASING");
        } else {
            System.out.println("NEITHER");
        }
    }
}