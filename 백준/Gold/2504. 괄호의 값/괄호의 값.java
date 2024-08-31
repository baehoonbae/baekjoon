import java.util.Scanner;
import java.util.Stack;

// 2504. 괄호의 값 / 골드5 / 12:22 ~ 12:59
// 여는 괄호가 중첩될 때마다 ( 라면 2, [ 라면 3을 계속 곱해준다
// 닫는 괄호가 나오면 바로 이전꺼가 매칭되는 괄호 짝일때에만 이때까지 곱한걸 더해준다
// => 괄호값들을 계속 중첩하고 닫히면 더해주는 과정이다
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Stack<Character> stack = new Stack<>();

        int sum = 0;
        int nesting = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add(c);
                nesting *= 2;
            } else if (c == '[') {
                stack.add(c);
                nesting *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }
                if (s.charAt(i - 1) == '(') {
                    sum += nesting;
                }
                stack.pop();
                nesting /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }
                if (s.charAt(i - 1) == '[') {
                    sum += nesting;
                }
                stack.pop();
                nesting /= 3;
            }
        }
        if (stack.isEmpty()) {
            System.out.println(sum);
        } else {
            System.out.println(0);
        }
    }
}
