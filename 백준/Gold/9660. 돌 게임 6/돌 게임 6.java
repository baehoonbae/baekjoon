import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long n = new Scanner(System.in).nextLong();
        if (n % 7 == 0 || n % 7 == 2) {
            System.out.println("CY");
        } else{
            System.out.println("SK");
        }
    }
}