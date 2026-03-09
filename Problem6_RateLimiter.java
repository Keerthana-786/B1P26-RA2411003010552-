import java.util.*;

public class Problem6_RateLimiter {

    static class TokenBucket {
        int tokens;
        int maxTokens;
        long lastRefillTime;

        TokenBucket(int maxTokens) {
            this.maxTokens = maxTokens;
            this.tokens = maxTokens;
            this.lastRefillTime = System.currentTimeMillis();
        }

        void refillIfNeeded() {
            long now = System.currentTimeMillis();
            long elapsed = now - lastRefillTime;
            if (elapsed >= 3600000) {
                tokens = maxTokens;
                lastRefillTime = now;
            }
        }
    }

    HashMap<String, TokenBucket> clients = new HashMap<>();
    int limit = 5;

    public void checkRateLimit(String clientId) {
        clients.putIfAbsent(clientId, new TokenBucket(limit));
        TokenBucket bucket = clients.get(clientId);
        bucket.refillIfNeeded();
        if (bucket.tokens > 0) {
            bucket.tokens--;
            System.out.println(clientId + " -> Allowed (" + bucket.tokens + " remaining)");
        } else {
            System.out.println(clientId + " -> DENIED (limit reached, retry in 1 hour)");
        }
    }

    public static void main(String[] args) {
        Problem6_RateLimiter limiter = new Problem6_RateLimiter();
        for (int i = 0; i < 7; i++) {
            limiter.checkRateLimit("client_abc");
        }
    }
}