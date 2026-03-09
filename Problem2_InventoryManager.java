import java.util.*;

public class Problem2_InventoryManager {

    HashMap<String, Integer> inventory = new HashMap<>();
    HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int stock) {
        inventory.put(productId, stock);
        waitingList.put(productId, new LinkedList<>());
        System.out.println(productId + " added with " + stock + " units.");
    }

    public int checkStock(String productId) {
        int stock = inventory.getOrDefault(productId, 0);
        System.out.println("checkStock(" + productId + ") -> " + stock + " units");
        return stock;
    }

    public void purchaseItem(String productId, int userId) {
        int stock = inventory.getOrDefault(productId, 0);
        if (stock > 0) {
            inventory.put(productId, stock - 1);
            System.out.println("User " + userId + " -> Success! "
                    + (stock - 1) + " units remaining.");
        } else {
            waitingList.get(productId).add(userId);
            int position = waitingList.get(productId).size();
            System.out.println("User " + userId
                    + " -> Out of stock! Waiting list position #" + position);
        }
    }

    public static void main(String[] args) {
        Problem2_InventoryManager manager = new Problem2_InventoryManager();
        manager.addProduct("IPHONE15", 3);
        manager.checkStock("IPHONE15");
        manager.purchaseItem("IPHONE15", 12345);
        manager.purchaseItem("IPHONE15", 67890);
        manager.purchaseItem("IPHONE15", 11111);
        manager.purchaseItem("IPHONE15", 99999);
        manager.purchaseItem("IPHONE15", 88888);
    }
}