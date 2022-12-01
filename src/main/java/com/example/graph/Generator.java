package com.example.graph;

import java.util.Random;

public class Generator {

    private static Random rand = new Random();
    private static int WEIGHT_UPPER_BOUND = 1000000;

    /*
     * The average vertex degree is 6
     */
    public static Graph sparseGraph(int N, int avg) {
        Graph g = new Graph(N);
        int totalEdges = N * avg / 2;

        for (int i = 0; i < totalEdges; i++) {
            while (!g.addEdge(rand.nextInt(N), rand.nextInt(N), randomWeight())) {
            }
        }

        // pre generate all edges
        g.getAllEdges();

        return g;
    }

    /*
     * Each vertex is adjacent to about 20% of the other vertices, which are
     * randomly chosen;
     */
    public static Graph denseGraph(int N, double percentage) {
        Graph g = new Graph(N);

        int remainEdges = (int) (N * N * percentage / 2);
        int nodeIdx = 0;
        while (remainEdges > 0 && nodeIdx < N - 2) {
            int newEdges = (int) (N * percentage) - g.getEdgeNum(nodeIdx);
            if (newEdges <= 0) {
                nodeIdx++;
                continue;
            }

            // At most N-nodeIdx-1 edges
            newEdges = Math.min(newEdges, N - nodeIdx - 1); // at most N-nodeIdx-1 edges, range: [N-nodeIdx-1:N]
            int r = newEdges;
            while (r > 0) {
                if (g.addEdge(nodeIdx, randomInRange(nodeIdx + 1, N - 1), randomWeight())) {
                    r--;
                }
            }
            remainEdges -= newEdges;
            nodeIdx++;
        }

        // pre generate all edges
        g.getAllEdges();

        return g;
    }

    private static int randomWeight() {
        return rand.nextInt(WEIGHT_UPPER_BOUND) + 1;
    }

    private static int randomInRange(int lower, int upper) {
        return lower + rand.nextInt(upper - lower + 1);
    }
}
