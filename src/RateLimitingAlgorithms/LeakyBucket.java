package RateLimitingAlgorithms;

import java.util.*;

public class LeakyBucket {
    private final int bucketSize;
    private final double leakRate;
    private final Queue<Long> queue;
    private long lastLeakTime;

    public LeakyBucket(int bucketSize, int leakRate) {
        this.bucketSize = bucketSize;
        this.leakRate = leakRate;
        queue = new LinkedList<>();
        lastLeakTime = System.nanoTime();
    }

    public synchronized boolean allowRequest() {
        leak();

        if (queue.size() < bucketSize) {
            queue.offer(System.nanoTime());
            return true;
        }

        return false;
    }

    private void leak() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastLeakTime) / 1_000_000_000.0;

        int requestsToLeak = (int) (elapsedSeconds * leakRate);

        for (int i = 0; i < requestsToLeak; i++) {
            queue.poll();
        }

        lastLeakTime = now;
    }
}
