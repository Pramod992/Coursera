package Assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by pramod on 16.15.4.
 */
public class Percolation {
    private WeightedQuickUnionUF quickUnionUF, quickUnionUF2;
    private boolean[] isGridOpen;
    private int virtualTop;
    private int virtualBottom;
    private int N;

    // create N-by-N quickUnionUF, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;

        quickUnionUF = new WeightedQuickUnionUF( N * N + 2);
        quickUnionUF2 = new WeightedQuickUnionUF( N * N + 1);
        isGridOpen = new boolean[ N * N + 2];

        virtualTop = 0;
        virtualBottom = ( N * N + 1);
        // Initialize all sites to be blocked.
        for (int i = 1; i <=  N * N; i++) {
            isGridOpen[i] = false;
        }

        isGridOpen[virtualTop] = true;
        isGridOpen[virtualBottom] = true;
    }

    private int get1DIndex(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N) {
            throw new IndexOutOfBoundsException();
        }
        return (i - 1) * N + j;
    }

    private boolean isTopSite(int index) {
        return index <= N;
    }

    private boolean isBottomSite(int index) {
        return index >= (N - 1) * N + 1;
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {

        int index = get1DIndex(i, j);
        isGridOpen[index] = true;

        if (i != 1 && isOpen(i - 1, j)) { //connect up if open
            quickUnionUF.union(index, get1DIndex(i - 1, j));
            quickUnionUF2.union(index, get1DIndex(i - 1, j));
        }
        if (i != N && isOpen(i + 1, j)) { //connect down if open
            quickUnionUF.union(index, get1DIndex(i + 1, j));
            quickUnionUF2.union(index, get1DIndex(i + 1, j));
        }
        if (j != 1 && isOpen(i, j - 1)) { // connect left if open
            quickUnionUF.union(index, get1DIndex(i, j - 1));
            quickUnionUF2.union(index, get1DIndex(i, j - 1));
        }
        if (j != N && isOpen(i, j + 1)) { // connect right if open
            quickUnionUF.union(index, get1DIndex(i, j + 1));
            quickUnionUF2.union(index, get1DIndex(i, j + 1));
        }
        // if site is on top or bottom, connect to corresponding virtual site.
        if (isTopSite(index)) {
            quickUnionUF.union(virtualTop, index);
            quickUnionUF2.union(virtualTop, index);
        }
        if (isBottomSite(index)) {
            quickUnionUF.union(virtualBottom, index);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return isGridOpen[get1DIndex(i, j)];
    }

    // Check if this site is connected to virtual top site
    public boolean isFull(int i, int j) {
        int index = get1DIndex(i, j);
        return quickUnionUF.connected(virtualTop, index) && quickUnionUF2.connected(virtualTop, index);
    }

    // Check whether virtual top and bottom sites are connected
    public boolean percolates() {
        return quickUnionUF.connected(virtualTop, virtualBottom);
    }
}