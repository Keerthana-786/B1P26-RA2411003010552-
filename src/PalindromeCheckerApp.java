import java.util.Scanner;
import java.util.Stack;

public class PalindromeCheckerApp {

    public static boolean twoPointerCheck(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean recursiveCheck(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return recursiveCheck(str, left + 1, right - 1);
    }

    public static boolean stackCheck(String str) {
        Stack<Character> stack = new Stack<>();
        int mid = str.length() / 2;
        for (int i = 0; i < mid; i++) stack.push(str.charAt(i));
        int start = (str.length() % 2 == 0) ? mid : mid + 1;
        for (int i = start; i < str.length(); i++) {
            if (stack.isEmpty() || stack.pop() != str.charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("         Welcome to Palindrome Checker App");
        System.out.println("============================================================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        String[] words = sentence.split(" ");
        String longestPalindrome = "";
        System.out.println("------------------------------------------------------------");
        for (String word : words) {
            String cleaned = word.toLowerCase().replaceAll("[^a-z0-9]", "");
            boolean result = twoPointerCheck(cleaned);
            System.out.println("Word: " + word + " -> Is Palindrome? : " + result);
            if (result && cleaned.length() > longestPalindrome.length()) {
                longestPalindrome = cleaned;
            }
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Longest Palindrome: " + longestPalindrome);
        System.out.println("============================================================");
        scanner.close();
    }
}
