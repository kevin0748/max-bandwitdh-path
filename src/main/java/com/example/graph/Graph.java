package com.example.graph;

public class Graph {
    public int[][] adj;
    public int[][] weight;
    public Node[] head;
    public int[] edgeCnt;
    public int size;

    public Graph(int N) {
        size = N;
        adj = new int[N][N];
        weight = new int[N][N];
        edgeCnt = new int[N];
        head = new Node[N];
        for (int i = 0; i < N; i++) {
            head[i] = null;
        }
    }

    public boolean addEdge(int a, int b, int w) {
        if (a == b || adj[a][b] != 0) {
            return false;
        }

        adj[a][b] = adj[b][a] = 1;
        weight[a][b] = weight[b][a] = w;

        Node newNode = new Node(b, w);
        if (head[a] == null) {
            head[a] = newNode;
        } else {
            newNode.next = head[a];
            head[a] = newNode;
        }
        edgeCnt[a]++;

        newNode = new Node(a, w);
        if (head[b] == null) {
            head[b] = newNode;
        } else {
            newNode.next = head[b];
            head[b] = newNode;
        }
        edgeCnt[b]++;

        return true;
    }

    public int getEdgeNum(int v) {
        return edgeCnt[v];
    }

    public Node getEdgesList(int v) {
        return head[v];
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            Node n = head[i];
            if (n != null) {
                System.out.printf("%4d || ", i);
                while (n != null) {
                    System.out.printf("%2d(%2d) ", n.dest, n.weight);
                    n = n.next;
                }
                System.out.println("");
            }
        }
    }

    public void summary() {
        int totalV = 0;
        double avgV = 0.0;

        for (int i = 0; i < size; i++) {
            totalV += edgeCnt[i];
        }
        avgV = totalV / size;

        System.out.printf("Average vertices per node: %f\n", avgV);
    }
}
