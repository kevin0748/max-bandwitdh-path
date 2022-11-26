package com.example.graph;

public class Node {
    private static final int UNDEFINED = -1;

    public int dest;
    public int weight;
    public Node next;

    public Node() {
        this(UNDEFINED, 0);
    }

    public Node(int d, int w) {
        dest = d;
        weight = w;
        next = null;
    }
}
