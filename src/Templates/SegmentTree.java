package Templates;

public class SegmentTree {

    private int size;
    private long[] sums;

    public SegmentTree(int n) {
        size = 1;
        while (size < n) size *= 2;
        sums = new long[size * 2];
    }

    public void set(int i, int v) {
        set(i, v, 0, 0, size);
    }

    public void set(int i, int v, int x, int lx, int rx) {
        if (rx - lx == 1) {
            sums[x] = v;
        }
        int m = (lx + rx) / 2;
        if (i < m) {
            set(i, v, (2 * x) + 1, lx, m);
        } else {
            set(i, v, (2 * x) + 2, m, rx);
        }
        sums[x] = sums[(2 * x) + 1] + sums[(2 * x) + 2];
    }

    public long sum(int l, int r) {
        return sum(l, r, 0, 0, size);
    }

    public long sum(int l, int r, int x, int lx, int rx) {
        if (lx >= r || l >= rx) return 0L;
        if (lx >= l && rx <= r) return sums[x];
        int m = (lx + rx) / 2;
        long s1 = sum(l, r, (2 * x) + 1, lx, m);
        long s2 = sum(l, r, (2 * x) + 2, m, rx);
        return s1 + s2;
    }
}
