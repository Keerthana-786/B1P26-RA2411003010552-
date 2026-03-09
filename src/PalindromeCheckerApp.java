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

    public static boolean numberPalindromeCheck(int number) {
        String numStr = String.valueOf(number);
        return twoPointerCheck(numStr);
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("        UC13: Performance Comparison - Palindrome");
        System.out.println("============================================================");
        String input = "level";
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        System.out.println("Input : " + input);
        System.out.println();
        long start1 = System.nanoTime();
        boolean result1 = twoPointerCheck(cleaned);
        long end1 = System.nanoTime();
        System.out.println("Algorithm 1 : Two Pointer Approach");
        System.out.println("Is Palindrome? : " + result1);
        System.out.println("Execution Time : " + (end1 - start1) + " ns");
        System.out.println();
        long start2 = System.nanoTime();
        boolean result2 = recursiveCheck(cleaned, 0, cleaned.length() - 1);
        long end2 = System.nanoTime();
        System.out.println("Algorithm 2 : Recursive Approach");
        System.out.println("Is Palindrome? : " + result2);
        System.out.println("Execution Time : " + (end2 - start2) + " ns");
        System.out.println();
        long start3 = System.nanoTime();
        boolean result3 = stackCheck(cleaned);
        long end3 = System.nanoTime();
        System.out.println("Algorithm 3 : Stack Based Approach");
        System.out.println("Is Palindrome? : " + result3);
        System.out.println("Execution Time : " + (end3 - start3) + " ns");
        System.out.println();
        long d1 = end1 - start1;
        long d2 = end2 - start2;
        long d3 = end3 - start3;
        long fastest = Math.min(d1, Math.min(d2, d3));
        System.out.println("============================================================");
        System.out.println("                  Performance Summary");
        System.out.println("============================================================");
        System.out.println("Two Pointer Time   : " + d1 + " ns");
        System.out.println("Recursive Time     : " + d2 + " ns");
        System.out.println("Stack Based Time   : " + d3 + " ns");
        if (fastest == d1) {
            System.out.println("Fastest Algorithm  : Two Pointer Approach");
        } else if (fastest == d2) {
            System.out.println("Fastest Algorithm  : Recursive Approach");
        } else {
            System.out.println("Fastest Algorithm  : Stack Based Approach");
        }
        System.out.println("============================================================");
    }
}
