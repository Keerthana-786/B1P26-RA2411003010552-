import java.util.Scanner;

public class PalindromeCheckerApp {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("         Welcome to Palindrome Checker App");
        System.out.println("============================================================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to check: ");
        String input = scanner.nextLine();

        String reversed = new StringBuilder(input).reverse().toString();
        boolean isPalindrome = input.equals(reversed);

        System.out.println("Input          : " + input);
        System.out.println("Reversed       : " + reversed);
        System.out.println("Is Palindrome? : " + isPalindrome);
        System.out.println("============================================================");
        scanner.close();
    }
}
