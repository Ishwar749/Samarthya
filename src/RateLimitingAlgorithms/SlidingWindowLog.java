package RateLimitingAlgorithms;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLog {
    private final Queue<Long> requestLog;
    private final int maxRequests;
    private long windowSizeMs;

    public SlidingWindowLog(int maxRequests, int windowSizeMs) {
        requestLog = new LinkedList<>();
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }

    public synchronized boolean allowRequest() {
        long now = System.nanoTime();
        long windowStart = now - windowSizeMs;

        removeOldTimestamps(windowStart);

        if (requestLog.size() < maxRequests) {
            requestLog.offer(now);
            return true;
        }

        return false;
    }

    private void removeOldTimestamps(long windowStart) {
        while (!requestLog.isEmpty() && requestLog.peek() < windowStart) {
            requestLog.poll();
        }
    }
}
