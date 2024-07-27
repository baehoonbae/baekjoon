import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = Stream.generate(st::nextToken).limit(n).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        int minValue = IntStream.range(0, n - 1).map(i -> arr[i + 1] - arr[i]).min().orElse(Integer.MAX_VALUE);
        System.out.println(minValue + " " + IntStream.range(0, n - 1).filter(i -> arr[i + 1] - arr[i] == minValue).count());
    }
}