public class UnionFind {

    // TODO - Add instance variables?
    private int length;
    private int[] vertices;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        vertices = new int[n];
        for (int i = 0; i < n; i += 1) {
            vertices[i] = i;
        }
        length = n;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex)  {
        // TODO
        if (vertex >= this.length) {
            throw new IllegalArgumentException("Out of Range!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int parent = vertices[v1];

        if (parent == v1) {
            return 1;
        }

        while (parent > 0) {
            parent = vertices[parent];
        }

        return -parent;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        int parent = vertices[v1];

        return parent;
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1); validate(v2);

        int root2 = find(v2);
        int root1 = find(v1);

        if (sizeOf(v1) == 1 && sizeOf(v2) == 1) {
            vertices[root1] = root2;
            vertices[root2] = -2;
        } else if (sizeOf(v1) > sizeOf(v2)) {
            vertices[root2] = root1;
            vertices[root1] -= 1;
        } else if (sizeOf(v1) < sizeOf(v2)) {
            vertices[root1] = root2;
            vertices[root2] -= 1;
        } else if (sizeOf(v1) > sizeOf(v2)){
            vertices[root1] = root2;
            vertices[root2] -= 1;
        } else if (root1 == root2) {
            return;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        int parent = vertices[vertex];

        if (parent < 0) {
            return vertex;
        }

        while (vertices[parent] > 0 && parent != vertex) {
            parent = vertices[parent];
        }

        return parent;
    }

    public static void main(String[] args) {
        /** Informal tests */
        /* Test Initialization */
        UnionFind uf = new UnionFind(7);
        /* Test validate */
        uf.validate(0);
        // uf.validate(10);
        // Test union & connected
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(0, 4);
        uf.union(3, 5);
        System.out.println("Expected: true; Actual: " + uf.connected(2, 4));
        System.out.println("Expected: false; Actual: " + uf.connected(3, 0));
        uf.union(4, 2);
        uf.union(4, 6);
        uf.union(3, 6);
        System.out.println("Expected: true; Actual: " + uf.connected(3, 0));
        // Test parent
        System.out.println("Expected: -6; Actual: " + uf.parent(1));
        System.out.println("Expected: 1; Actual: " + uf.parent(0));
        System.out.println("Expected: 5; Actual: " + uf.parent(3));
        // Test find
        System.out.println("Expected: 1; Actual: " + uf.find(1));
        System.out.println("Expected: 1; Actual: " + uf.find(0));
        System.out.println("Expected: 1; Actual: " + uf.find(3));
    }

}
