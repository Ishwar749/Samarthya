package RateLimitingAlgorithms;

public class TokenBucket {
    private final long capacity;
    private final double refillRate;
    private double currentTokens;
    private long lastRefillTime;

    public TokenBucket(long capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        currentTokens = capacity; // Start with full bucket
        lastRefillTime = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        refill();

        if(currentTokens >= 1) {
            currentTokens -= 1;
            return true;
        }

        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillTime) / 1_000_000_000.0;

        double tokensToAdd = elapsedSeconds * refillRate;

        currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
        lastRefillTime = now;
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();

        if(currentTokens >= tokens) {
            currentTokens -= tokens;
            return true;
        }

        return false;
    }
}
