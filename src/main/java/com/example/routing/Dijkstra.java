package com.example.routing;

import com.example.graph.Graph;
import com.example.graph.Node;

public class Dijkstra {
    private final int STATUS_UNSEEN = 0;
    private final int STATUS_FRINGER = 1;
    private final int STATUS_INTREE = 2;
    private final int NO_DAD = -1;

    private Graph graph;
    private int[] status;
    private int[] bWidth;
    private int[] dad;
    private int[] fringers;
    private int fringerSize;
    private int[] maxBwPath;
    private int maxBw;

    /*
     * An algorithm for Max-Bandwidth-Path based on a modification of
     * Dijkstra’s algorithm without using a heap structure
     */
    public Dijkstra(Graph g, int s, int t) {
        init(g);

        Node it;
        status[s] = STATUS_INTREE;
        bWidth[s] = Integer.MAX_VALUE;

        it = g.getEdgesList(s);
        while (it != null) {
            addFringer(it.dest);
            dad[it.dest] = s;
            bWidth[it.dest] = it.weight;

            it = it.next;
        }

        while (fringerSize > 0) {
            int f = pickNextFringer();
            status[f] = STATUS_INTREE;

            it = g.getEdgesList(f);
            while (it != null) {
                if (status[it.dest] == STATUS_UNSEEN) {
                    addFringer(it.dest);
                    dad[it.dest] = f;
                    bWidth[it.dest] = Math.min(bWidth[f], it.weight);
                } else if (status[it.dest] == STATUS_FRINGER && bWidth[it.dest] < Math.min(bWidth[f], it.weight)) {
                    dad[it.dest] = f;
                    bWidth[it.dest] = Math.min(bWidth[f], it.weight);
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
        fringers = new int[N];
        fringerSize = 0;
        maxBwPath = null;

        for (int v = 0; v < N; v++) {
            status[v] = STATUS_UNSEEN;
            bWidth[v] = 0;
            dad[v] = NO_DAD;
        }
    }

    private void addFringer(int v) {
        status[v] = STATUS_FRINGER;
        fringers[fringerSize++] = v;
    }

    /**
     * Pick fringer with largest bandwidth
     * 
     * @return
     */
    private int pickNextFringer() {
        if (fringerSize == 0) {
            return -1; // no fringer
        }
        int largestWidthIdx = 0;
        for (int i = 0; i < fringerSize; i++) {
            if (bWidth[fringers[i]] > bWidth[fringers[largestWidthIdx]]) {
                largestWidthIdx = i;
            }
        }

        int next = fringers[largestWidthIdx];
        // pop out the picked one from fringers array
        fringers[largestWidthIdx] = fringers[fringerSize - 1];
        fringerSize--;

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
