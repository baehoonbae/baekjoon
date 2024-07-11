import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String sb = br.readLine();
			if (isPalindrome(sb)) {
				System.out.println(0);
			} else if (isPseudoLeft(sb) || isPseudoRight(sb)) {
				System.out.println(1);
			} else {
				System.out.println(2);
			}
		}
	}

	public static boolean isPalindrome(String sb) {
		int left = 0;
		int right = sb.length() - 1;
		for (left = 0; left < sb.length() / 2; left++) {
			if (sb.charAt(left) != sb.charAt(right)) {
				return false;
			}
			right--;
		}
		return true;
	}

	// 오른쪽먼저 줄어듦
	public static boolean isPseudoRight(String sb) {
		int count = 0;
		int left = 0;
		int right = sb.length() - 1;
		while (left < right) {
			while (sb.charAt(left) != sb.charAt(right)) {
				count++;
				right--;
			}
			if (count >= 2) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	// 왼쪽 줄어듦
	public static boolean isPseudoLeft(String sb) {
		int count = 0;
		int left = 0;
		int right = sb.length() - 1;
		while (left < right) {
			while (sb.charAt(left) != sb.charAt(right)) {
				count++;
				left++;
			}
			if (count >= 2) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

}