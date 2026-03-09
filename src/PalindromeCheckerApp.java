import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

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
        List<String> words = new ArrayList<>();
        words.add("level");
        words.add("hello");
        words.add("racecar");
        words.add("world");
        words.add("madam");
        words.add("java");
        int palindromeCount = 0;
        System.out.println("Checking list of words:");
        System.out.println("------------------------------------------------------------");
        for (String word : words) {
            String cleaned = word.toLowerCase().replaceAll("[^a-z0-9]", "");
            boolean result = twoPointerCheck(cleaned);
            System.out.println("Word: " + word + " -> Is Palindrome? : " + result);
            if (result) palindromeCount++;
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Total Words      : " + words.size());
        System.out.println("Total Palindromes: " + palindromeCount);
        System.out.println("============================================================");
    }
}
