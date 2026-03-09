import java.util.*;

public class Problem5_AnalyticsDashboard {

    HashMap<String, Integer> pageViews = new HashMap<>();
    HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {
        System.out.println("\n===== DASHBOARD =====");
        List<Map.Entry<String, Integer>> sortedPages = new ArrayList<>(pageViews.entrySet());
        sortedPages.sort((a, b) -> b.getValue() - a.getValue());
        System.out.println("\nTop Pages:");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedPages) {
            int unique = uniqueVisitors.get(entry.getKey()).size();
            System.out.println(rank++ + ". " + entry.getKey()
                    + " - " + entry.getValue() + " views ("
                    + unique + " unique)");
            if (rank > 3) break;
        }
        System.out.println("\nTraffic Sources:");
        int total = trafficSources.values().stream().mapToInt(Integer::intValue).sum();
        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            double pct = (entry.getValue() * 100.0) / total;
            System.out.printf("%s: %.0f%%%n", entry.getKey(), pct);
        }
    }

    public static void main(String[] args) {
        Problem5_AnalyticsDashboard dashboard = new Problem5_AnalyticsDashboard();
        dashboard.processEvent("/article/news", "user_1", "Google");
        dashboard.processEvent("/article/news", "user_2", "Facebook");
        dashboard.processEvent("/article/news", "user_1", "Google");
        dashboard.processEvent("/sports/game", "user_3", "Direct");
        dashboard.processEvent("/sports/game", "user_4", "Google");
        dashboard.processEvent("/tech/java", "user_5", "Direct");
        dashboard.getDashboard();
    }
}