package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grids;
    private int openSites;
    private int gridSize;
    private WeightedQuickUnionUF ufPercolate;
    private WeightedQuickUnionUF ufBackwash;
    private int topVisualIdx;
    private int bottomVisualIdx;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N should be larger than 0.");
        }

        // Record grid size
        gridSize = N;
        // Initialize the number of open sites
        openSites = 0;
        // There are two extra elements: one for visual top and one for visual bottom
        grids = new boolean[N * N + 2];
        // Used to check percolation
        ufPercolate = new WeightedQuickUnionUF(N * N + 2);
        // Used to check backwash
        ufBackwash = new WeightedQuickUnionUF(N * N + 1);
        // Record the indices of top and bottom visual sites, respectively
        topVisualIdx = gridSize * gridSize;
        bottomVisualIdx = gridSize * gridSize + 1;
    }

    private int xyToInt(int row, int col) {
        return row * gridSize + col;
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of range.");
        }

        if (isOpen(row, col)) {return;}

        int idx = xyToInt(row, col);
        grids[idx] = true;
        openSites += 1; // increment openSites

        if (row == 0) { // Connect the open grid in the first row to visual top site
            ufPercolate.union(idx, topVisualIdx);
            ufBackwash.union(idx, topVisualIdx);
        } else if (row == gridSize - 1) { // connect the open grid in the last row to visual bottom site
            ufPercolate.union(idx, bottomVisualIdx);
        }

        if (col > 0 && isOpen(row, col - 1)) { // Is the left grid open?
            ufPercolate.union(idx, idx - 1);
            ufBackwash.union(idx, idx - 1);
        }
        if (col < gridSize - 1 && isOpen(row, col + 1)) { // Is the right grid open?
            ufPercolate.union(idx, idx + 1);
            ufBackwash.union(idx, idx + 1);
        }
        if (row < gridSize - 1 && isOpen(row + 1, col)) { // Is the bottom grid open?
            ufPercolate.union(idx, idx + gridSize);
            ufBackwash.union(idx, idx + gridSize);
        }
        if (row > 0 && isOpen(row - 1, col)) { // Is the top grid open?
            ufPercolate.union(idx, idx - gridSize);
            ufBackwash.union(idx, idx - gridSize);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of range.");
        }

        int idx = xyToInt(row, col);
        return grids[idx];
    }

    // Is the specific grid connected to the top row?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of range.");
        }

        int idx = xyToInt(row, col);
        return ufBackwash.connected(idx, topVisualIdx);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        // Check if the visual top site is connected the visual bottom site
        return ufPercolate.connected(topVisualIdx, bottomVisualIdx);
    }

    public static void main(String[] args) {

    }
}
