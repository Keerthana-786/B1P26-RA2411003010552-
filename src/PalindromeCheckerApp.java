import java.util.Scanner;

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

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("         Welcome to Palindrome Checker App");
        System.out.println("============================================================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to check: ");
        String input = scanner.nextLine();
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        boolean isPalindrome = twoPointerCheck(cleaned);
        System.out.println("Input          : " + input);
        System.out.println("Cleaned        : " + cleaned);
        System.out.println("Is Palindrome? : " + isPalindrome);
        System.out.println("Algorithm      : Two Pointer");
        System.out.println("============================================================");
        scanner.close();
    }
}
