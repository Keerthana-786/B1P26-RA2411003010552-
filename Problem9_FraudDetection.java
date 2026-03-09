import java.util.*;

public class Problem9_FraudDetection {

    static class Transaction {
        int id, amount;
        String merchant, time;

        Transaction(int id, int amount, String merchant, String time) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.time = time;
        }
    }

    List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(int id, int amount, String merchant, String time) {
        transactions.add(new Transaction(id, amount, merchant, time));
    }

    public void findTwoSum(int target) {
        HashMap<Integer, Transaction> seen = new HashMap<>();
        for (Transaction t : transactions) {
            int complement = target - t.amount;
            if (seen.containsKey(complement)) {
                Transaction other = seen.get(complement);
                System.out.println("findTwoSum(" + target + ") -> (id:"
                        + other.id + ", id:" + t.id + ") = "
                        + other.amount + " + " + t.amount);
                return;
            }
            seen.put(t.amount, t);
        }
        System.out.println("No pair found for target: " + target);
    }

    public void findKSum(int k, int target) {
        for (int i = 0; i < transactions.size(); i++)
            for (int j = i + 1; j < transactions.size(); j++)
                for (int l = j + 1; l < transactions.size(); l++) {
                    int sum = transactions.get(i).amount
                            + transactions.get(j).amount
                            + transactions.get(l).amount;
                    if (sum == target) {
                        System.out.println("findKSum(k=" + k + ", target=" + target + ") -> (id:"
                                + transactions.get(i).id + ", id:"
                                + transactions.get(j).id + ", id:"
                                + transactions.get(l).id + ")");
                        return;
                    }
                }
    }

    public static void main(String[] args) {
        Problem9_FraudDetection fd = new Problem9_FraudDetection();
        fd.addTransaction(1, 500, "Store A", "10:00");
        fd.addTransaction(2, 300, "Store B", "10:15");
        fd.addTransaction(3, 200, "Store C", "10:30");
        fd.findTwoSum(500);
        fd.findKSum(3, 1000);
    }
}