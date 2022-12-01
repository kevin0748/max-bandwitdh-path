package com.example.routing;

import com.example.graph.Edge;
import com.example.graph.Graph;
import com.example.graph.Node;
import com.example.utils.Queue;
import com.example.utils.UnionFind;
import com.example.utils.heap.MaxHeap;

public class Kruskal {
    private int N;
    private Graph mst;
    private int[] maxBwPath;
    private int maxBw;
    private MaxHeap heap;

    public Kruskal(Graph g, int s, int t) {
        mst = buildMST(g, s, t);
        buildPath(s, t);
    }

    public Graph buildMST(Graph g, int s, int t) {
        Edge[] list = g.getAllEdges();

        initHeap(list);

        N = g.size;
        Graph mst = new Graph(N);
        UnionFind uf = new UnionFind(N);

        int i = 0;
        // at most N-1 edges
        while (i < N - 1 && !heap.empty()) {
            int top = heap.maximum();
            Edge e = list[top];
            heap.delete(top);

            int ra = uf.find(e.va);
            int rb = uf.find(e.vb);
            if (ra != rb) {
                uf.union(ra, rb);
                mst.addEdge(e.va, e.vb, e.weight);
                i++;
            }
        }

        return mst;
    }

    public void initHeap(Edge[] input) {
        if (input == null) {
            return;
        }

        heap = new MaxHeap(input.length);
        for (int i = 0; i < input.length; i++) {
            heap.insert(i, input[i].weight);
        }
    }

    private int[] buildPath(int s, int t) {
        // mst.print();

        boolean[] visited = new boolean[N];
        int[] dad = new int[N];
        for (int i = 0; i < N; i++) {
            dad[i] = -1;
            visited[i] = false;
        }
        Queue q = new Queue(N);

        q.enqueue(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int v = q.dequeue();
            Node it = mst.getEdgesHead(v);
            while (it != null) {
                if (!visited[it.dest]) { // only pass node once
                    dad[it.dest] = v;
                    q.enqueue(it.dest);
                    visited[it.dest] = true;
                }
                it = it.next;
            }
        }

        if (dad[t] == -1) {
            return null;
        }

        int pathSize = 0;
        int v = t;
        while (v != -1) {
            pathSize++;
            v = dad[v];
        }

        maxBwPath = new int[pathSize];
        maxBw = Integer.MAX_VALUE;
        int j = 0;
        for (int i = t; i != -1; i = dad[i]) {
            maxBwPath[pathSize - j - 1] = i;
            if (dad[i] != -1) {
                maxBw = Math.min(maxBw, mst.weight[i][dad[i]]);
            }

            j++;
        }

        return maxBwPath;
    }

    public int[] getMaxBwPath() {
        return maxBwPath;
    }

    public int getMaxBw() {
        return maxBw;
    }
}
