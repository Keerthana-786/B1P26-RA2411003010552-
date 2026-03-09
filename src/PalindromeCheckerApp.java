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

    public static boolean recursiveCheck(String str, int left, int right) {
        if (left >= right) return true;
        if (str.charAt(left) != str.charAt(right)) return false;
        return recursiveCheck(str, left + 1, right - 1);
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("         Welcome to Palindrome Checker App");
        System.out.println("============================================================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to check: ");
        String input = scanner.nextLine();
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        System.out.println("Input          : " + input);
        System.out.println("Cleaned        : " + cleaned);
        System.out.println("Two Pointer    : " + twoPointerCheck(cleaned));
        System.out.println("Recursive      : " + recursiveCheck(cleaned, 0, cleaned.length() - 1));
        System.out.println("============================================================");
        scanner.close();
    }
}
