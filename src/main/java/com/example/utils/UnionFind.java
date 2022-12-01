package com.example.utils;

public class UnionFind {
    private int N;
    private int[] dad;
    private int[] rank;

    public UnionFind(int size) {
        N = size;
        dad = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            dad[i] = -1;
            rank[i] = 0;
        }
    }

    public void union(int r1, int r2) {
        if (rank[r1] < rank[r2])
            dad[r1] = r2;
        else if (rank[r1] > rank[r2])
            dad[r2] = r1;
        else {
            dad[r1] = r2;
            rank[r2]++;
        }
    }

    public int find(int v) {
        int r = v;
        int[] s = new int[N];
        int sSize = 0;

        while (dad[r] != -1) {
            s[sSize++] = r;
            r = dad[r];
        }

        while (sSize > 0) {
            int w = s[--sSize];
            dad[w] = r;
        }

        return r;
    }
}
