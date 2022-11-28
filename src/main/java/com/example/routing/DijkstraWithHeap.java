package com.example.routing;

import com.example.graph.Graph;
import com.example.graph.Node;
import com.example.utils.heap.MaxHeap;

public class DijkstraWithHeap {
    private final int STATUS_UNSEEN = 0;
    private final int STATUS_FRINGER = 1;
    private final int STATUS_INTREE = 2;
    private final int NO_DAD = -1;

    private Graph graph;
    private int[] status;
    private int[] bWidth;
    private int[] dad;
    private MaxHeap heap;
    private int[] maxBwPath;
    private int maxBw;

    /*
     * An algorithm for Max-Bandwidth-Path based on a modification of
     * Dijkstra’s algorithm using a heap structure for fringes
     */
    public DijkstraWithHeap(Graph g, int s, int t) {
        init(g);

        Node it;
        status[s] = STATUS_INTREE;
        bWidth[s] = Integer.MAX_VALUE;

        it = g.getEdgesHead(s);
        while (it != null) {
            dad[it.dest] = s;
            bWidth[it.dest] = it.weight;
            addFringer(it.dest, bWidth[it.dest]);

            it = it.next;
        }

        while (!heap.empty()) {
            int f = pickNextFringer();
            status[f] = STATUS_INTREE;

            it = g.getEdgesHead(f);
            while (it != null) {
                if (status[it.dest] == STATUS_UNSEEN) {
                    dad[it.dest] = f;
                    bWidth[it.dest] = Math.min(bWidth[f], it.weight);
                    addFringer(it.dest, bWidth[it.dest]);
                } else if (status[it.dest] == STATUS_FRINGER && bWidth[it.dest] < Math.min(bWidth[f], it.weight)) {
                    heap.delete(it.dest);
                    dad[it.dest] = f;
                    bWidth[it.dest] = Math.min(bWidth[f], it.weight);
                    heap.insert(it.dest, bWidth[it.dest]);
                }

                it = it.next;
            }
        }

        buildPath(s, t);
    }

    private void init(Graph g) {
        graph = g;
        int N = g.size;
        status = new int[N];
        bWidth = new int[N];
        dad = new int[N];
        heap = new MaxHeap(N);
        maxBwPath = null;

        for (int v = 0; v < N; v++) {
            status[v] = STATUS_UNSEEN;
            bWidth[v] = 0;
            dad[v] = NO_DAD;
        }
    }

    private void addFringer(int v, int weight) {
        status[v] = STATUS_FRINGER;
        heap.insert(v, weight);
    }

    /**
     * Pick fringer with largest bandwidth
     * 
     * @return
     */
    private int pickNextFringer() {
        int next = heap.maximum();
        heap.delete(next);

        return next;
    }

    private int[] buildPath(int s, int t) {
        if (status[t] == STATUS_UNSEEN) {
            return null;
        }

        int pathSize = 0;
        for (int i = t; i != -1; i = dad[i]) {
            pathSize++;
        }

        maxBw = Integer.MAX_VALUE;
        maxBwPath = new int[pathSize];
        int j = 0;
        for (int i = t; i != -1; i = dad[i]) {
            maxBwPath[pathSize - j - 1] = i;
            if (dad[i] != -1) {
                maxBw = Math.min(maxBw, graph.weight[i][dad[i]]);
            }

            j++;
        }
        return maxBwPath;
    }

    public int getMaxBw() {
        return maxBw;
    }

    public int[] getMaxBwPath() {
        return maxBwPath;
    }
}
