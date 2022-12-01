package com.example.utils;

public class Queue {
    private int size;
    private int[] arr;
    private int start;
    private int end;
    private int count;

    public Queue(int N) {
        start = 0;
        end = 0;
        count = 0;
        size = N;
        arr = new int[N];
    }

    public void enqueue(int n) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("queue is full");
        }

        arr[end] = n;
        end = (end + 1) % size;
        count++;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("heap is empty");
        }

        int first = arr[start];
        start = (start + 1) % size;
        count--;
        return first;
    }

}
