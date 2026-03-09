import java.util.*;

public class Problem4_PlagiarismDetector {

    HashMap<String, Set<String>> ngramIndex = new HashMap<>();
    int N = 5;

    public void indexDocument(String docName, String content) {
        String[] words = content.split(" ");
        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder ngram = new StringBuilder();
            for (int j = i; j < i + N; j++) {
                ngram.append(words[j]).append(" ");
            }
            String key = ngram.toString().trim();
            ngramIndex.computeIfAbsent(key, k -> new HashSet<>()).add(docName);
        }
        System.out.println("Indexed: " + docName);
    }

    public void analyzeDocument(String docName, String content) {
        String[] words = content.split(" ");
        HashMap<String, Integer> matchCount = new HashMap<>();
        int totalNgrams = 0;
        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder ngram = new StringBuilder();
            for (int j = i; j < i + N; j++) {
                ngram.append(words[j]).append(" ");
            }
            String key = ngram.toString().trim();
            totalNgrams++;
            if (ngramIndex.containsKey(key)) {
                for (String match : ngramIndex.get(key)) {
                    if (!match.equals(docName)) {
                        matchCount.put(match, matchCount.getOrDefault(match, 0) + 1);
                    }
                }
            }
        }
        System.out.println("\nAnalyzing: " + docName + " (" + totalNgrams + " n-grams)");
        for (Map.Entry<String, Integer> entry : matchCount.entrySet()) {
            double similarity = (entry.getValue() * 100.0) / totalNgrams;
            String verdict = similarity > 50 ? "PLAGIARISM DETECTED" : "Suspicious";
            System.out.printf("Matches with %s -> %d n-grams -> Similarity: %.1f%% (%s)%n",
                    entry.getKey(), entry.getValue(), similarity, verdict);
        }
    }

    public static void main(String[] args) {
        Problem4_PlagiarismDetector detector = new Problem4_PlagiarismDetector();
        String essay1 = "the quick brown fox jumps over the lazy dog near the river bank today";
        String essay2 = "the quick brown fox jumps over the lazy dog near the river bank today and more text";
        String essay3 = "completely different content about science and math and physics experiments done";
        detector.indexDocument("essay_001.txt", essay1);
        detector.indexDocument("essay_003.txt", essay3);
        detector.analyzeDocument("essay_002.txt", essay2);
    }
}