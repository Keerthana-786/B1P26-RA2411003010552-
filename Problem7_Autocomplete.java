import java.util.*;

public class Problem7_Autocomplete {

    HashMap<String, Integer> queryFrequency = new HashMap<>();

    public void updateFrequency(String query) {
        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);
        System.out.println("Updated: \"" + query + "\" -> " + queryFrequency.get(query));
    }

    public List<String> search(String prefix) {
        List<Map.Entry<String, Integer>> matches = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : queryFrequency.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                matches.add(entry);
            }
        }
        matches.sort((a, b) -> b.getValue() - a.getValue());
        System.out.println("\nsearch(\"" + prefix + "\") ->");
        List<String> results = new ArrayList<>();
        int count = 1;
        for (Map.Entry<String, Integer> entry : matches) {
            if (count > 3) break;
            System.out.println(count + ". \"" + entry.getKey()
                    + "\" (" + entry.getValue() + " searches)");
            results.add(entry.getKey());
            count++;
        }
        return results;
    }

    public static void main(String[] args) {
        Problem7_Autocomplete ac = new Problem7_Autocomplete();
        ac.updateFrequency("java tutorial");
        ac.updateFrequency("java tutorial");
        ac.updateFrequency("java tutorial");
        ac.updateFrequency("javascript");
        ac.updateFrequency("javascript");
        ac.updateFrequency("java download");
        ac.updateFrequency("java 21 features");
        ac.search("java");
    }
}