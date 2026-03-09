import java.util.Scanner;

public class PalindromeCheckerApp {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("         Welcome to Palindrome Checker App");
        System.out.println("============================================================");
        Scanner scanner = new Scanner(System.in);
        String choice = "yes";
        while (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter a word or sentence to check: ");
            String input = scanner.nextLine();
            String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
            String reversed = new StringBuilder(cleaned).reverse().toString();
            boolean isPalindrome = cleaned.equals(reversed);
            System.out.println("Input          : " + input);
            System.out.println("Cleaned        : " + cleaned);
            System.out.println("Is Palindrome? : " + isPalindrome);
            System.out.println("------------------------------------------------------------");
            System.out.print("Check another? (yes/no): ");
            choice = scanner.nextLine();
        }
        System.out.println("Thank you for using Palindrome Checker App!");
        System.out.println("============================================================");
        scanner.close();
    }
}
