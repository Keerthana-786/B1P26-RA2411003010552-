import java.util.*;

public class Problem1_UsernameChecker {

    HashMap<String, Integer> registeredUsers = new HashMap<>();
    HashMap<String, Integer> attemptFrequency = new HashMap<>();

    public void setupExistingUsers() {
        registeredUsers.put("john_doe", 1001);
        registeredUsers.put("admin", 1002);
        registeredUsers.put("jane_smith", 1003);
    }

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        if (!registeredUsers.containsKey(username)) {
            System.out.println(username + " -> true (available)");
            return true;
        } else {
            System.out.println(username + " -> false (taken)");
            return false;
        }
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        suggestions.add(username + "1");
        suggestions.add(username + "2");
        suggestions.add(username.replace("_", "."));
        System.out.println("Suggestions: " + suggestions);
        return suggestions;
    }

    public String getMostAttempted() {
        String top = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                top = entry.getKey();
            }
        }
        System.out.println("Most attempted: " + top + " (" + max + " times)");
        return top;
    }

    public static void main(String[] args) {
        Problem1_UsernameChecker checker = new Problem1_UsernameChecker();
        checker.setupExistingUsers();

        checker.checkAvailability("john_doe");
        checker.checkAvailability("new_user99");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");

        checker.suggestAlternatives("john_doe");
        checker.getMostAttempted();
    }
}
