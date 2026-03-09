public class PalindromeCheckerApp {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("         Welcome to Palindrome Checker App");
        System.out.println("============================================================");

        String input = "level";
        String reversed = new StringBuilder(input).reverse().toString();
        boolean isPalindrome = input.equals(reversed);

        System.out.println("Input          : " + input);
        System.out.println("Reversed       : " + reversed);
        System.out.println("Is Palindrome? : " + isPalindrome);
        System.out.println("============================================================");
    }
}
