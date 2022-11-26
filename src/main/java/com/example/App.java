package com.example;

import com.example.graph.Generator;
import com.example.graph.Graph;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Graph 1
        Graph g1 = Generator.sparseGraph(5000, 6);
        g1.summary();

        // Graph 2
        Graph g2 = Generator.denseGraph(5000, 0.2);

        g2.summary();
    }
}
