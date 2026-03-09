import java.util.*;

public class Problem10_MultiLevelCache {

    LinkedHashMap<String, String> L1 = new LinkedHashMap<>(3, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry e) {
            return size() > 3;
        }
    };

    HashMap<String, String> L2 = new HashMap<>();
    HashMap<String, Integer> accessCount = new HashMap<>();
    int l1Hits = 0, l2Hits = 0, l3Hits = 0;

    public void getVideo(String videoId) {
        System.out.print("\ngetVideo(\"" + videoId + "\") -> ");
        if (L1.containsKey(videoId)) {
            l1Hits++;
            System.out.println("L1 Cache HIT (0.5ms)");
            return;
        }
        if (L2.containsKey(videoId)) {
            l2Hits++;
            System.out.println("L2 Cache HIT (5ms) -> Promoted to L1");
            L1.put(videoId, L2.get(videoId));
            return;
        }
        l3Hits++;
        String data = "VideoData_" + videoId;
        System.out.println("L3 Database HIT (150ms) -> Added to L2");
        L2.put(videoId, data);
        accessCount.put(videoId, accessCount.getOrDefault(videoId, 0) + 1);
        if (accessCount.get(videoId) > 2) {
            L1.put(videoId, data);
            System.out.println("  -> Promoted to L1 (popular video!)");
        }
    }

    public void getStatistics() {
        int total = l1Hits + l2Hits + l3Hits;
        System.out.println("\n===== Cache Statistics =====");
        System.out.printf("L1 Hits: %d (%.0f%%)%n", l1Hits, total == 0 ? 0 : (l1Hits * 100.0 / total));
        System.out.printf("L2 Hits: %d (%.0f%%)%n", l2Hits, total == 0 ? 0 : (l2Hits * 100.0 / total));
        System.out.printf("L3 Hits: %d (%.0f%%)%n", l3Hits, total == 0 ? 0 : (l3Hits * 100.0 / total));
    }

    public static void main(String[] args) {
        Problem10_MultiLevelCache cache = new Problem10_MultiLevelCache();
        cache.getVideo("video_123");
        cache.getVideo("video_123");
        cache.getVideo("video_123");
        cache.getVideo("video_456");
        cache.getVideo("video_999");
        cache.getVideo("video_123");
        cache.getStatistics();
    }
}
