import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            int idx = Collections.binarySearch(list, arr[i]);
            if (idx < 0) {
                if (list.isEmpty()) {
                    list.add(arr[i]);
                } else {
                    idx += 1;
                    idx = -idx;
                    if (idx >= list.size()){
                        list.add(arr[i]);
                    }else {
                        list.set(idx, Math.min(list.get(idx), arr[i]));
                    }
                }
            }
        }
        System.out.println(list.size());
    }
}