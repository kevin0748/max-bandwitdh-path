package com.example.heap;

public class MaxHeap {
    private int[] H;
    private int[] P;
    private int[] D;
    private int size;
    private int capacity;

    public MaxHeap(int N) {
        H = new int[N];
        P = new int[N];
        D = new int[N];
        capacity = N;
        size = 0;
    }

    public int maximum() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }

        return 0;
    }

    public void insert(int k, int v) {
        if (size == capacity) {
            throw new IndexOutOfBoundsException("heap is full");
        }

        H[size] = k;
        P[k] = size;
        D[k] = v;
        bubbleUp(size);
        size++;
        return;
    }

    /*
     * Delete value in the heap
     */
    public void delete(int v) {
        int idx = P[v];
        swap(idx, size - 1);
        size--;

        bubbleUp(idx);
        bubbleDown(idx);
    }

    public void bubbleUp(int idx) {
        while (idx > 0) {
            int p = (idx + 1) / 2 - 1;
            if (value(idx) > value(p)) {
                swap(idx, p);
            } else {
                break;
            }

            idx = p;
        }
        return;
    }

    public void bubbleDown(int idx) {
        int root = idx;
        while (root < size) {
            int l = 2 * root + 1;
            int r = 2 * root + 2;
            int largestIdx = root;
            if (l < size && value(l) > value(largestIdx)) {
                largestIdx = l;
            }
            if (r < size && value(r) > value(largestIdx)) {
                largestIdx = r;
            }

            if (largestIdx != root) {
                swap(largestIdx, root);
                root = largestIdx;
            } else {
                return;
            }
        }
    }

    private void swap(int a, int b) {
        P[H[a]] = b;
        P[H[b]] = a;

        int t = H[a];
        H[a] = H[b];
        H[b] = t;
    }

    private int value(int idx) {
        return D[H[idx]];
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%d: %d (%d) | \t", i, H[i], P[H[i]]);
        }
        System.out.println("");
    }
}
