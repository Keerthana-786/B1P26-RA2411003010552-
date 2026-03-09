import java.util.*;

public class Problem3_DNSCache {

    static class DNSEntry {
        String ipAddress;
        long expiryTime;

        DNSEntry(String ip, int ttlSeconds) {
            this.ipAddress = ip;
            this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000L);
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    HashMap<String, DNSEntry> cache = new HashMap<>();
    int hits = 0;
    int misses = 0;

    public String resolve(String domain) {
        DNSEntry entry = cache.get(domain);
        if (entry != null && !entry.isExpired()) {
            hits++;
            System.out.println("resolve(\"" + domain + "\") -> Cache HIT -> " + entry.ipAddress);
            return entry.ipAddress;
        }
        misses++;
        String fakeIP = "172.0." + (int)(Math.random() * 255) + "." + (int)(Math.random() * 255);
        cache.put(domain, new DNSEntry(fakeIP, 5));
        System.out.println("resolve(\"" + domain + "\") -> Cache MISS -> Queried upstream -> " + fakeIP);
        return fakeIP;
    }

    public void getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        System.out.printf("Cache Stats -> Hits: %d, Misses: %d, Hit Rate: %.1f%%%n",
                hits, misses, hitRate);
    }

    public static void main(String[] args) throws InterruptedException {
        Problem3_DNSCache dns = new Problem3_DNSCache();
        dns.resolve("google.com");
        dns.resolve("google.com");
        dns.resolve("youtube.com");
        dns.resolve("google.com");
        System.out.println("Waiting 6 seconds for TTL to expire...");
        Thread.sleep(6000);
        dns.resolve("google.com");
        dns.getCacheStats();
    }
}