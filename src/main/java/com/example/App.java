package com.example;

import com.example.graph.Generator;
import com.example.graph.Graph;
import com.example.routing.Dijkstra;
import com.example.routing.DijkstraWithHeap;
import com.example.routing.Kruskal;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        int start = 0, target = 5;

        // Graph 1
        Graph g1 = Generator.sparseGraph(5000, 3);
        // g1.print();
        g1.summary();

        Dijkstra route1 = new Dijkstra(g1, start, target);
        printResult(route1.getMaxBwPath(), route1.getMaxBw());

        DijkstraWithHeap route2 = new DijkstraWithHeap(g1, start, target);
        printResult(route2.getMaxBwPath(), route2.getMaxBw());

        Kruskal route3 = new Kruskal(g1, start, target);
        printResult(route3.getMaxBwPath(), route3.getMaxBw());

        // Graph 2
        Graph g2 = Generator.denseGraph(5000, 0.2);
        g2.summary();

        route1 = new Dijkstra(g2, start, target);
        printResult(route1.getMaxBwPath(), route1.getMaxBw());

        route2 = new DijkstraWithHeap(g2, start, target);
        printResult(route2.getMaxBwPath(), route2.getMaxBw());

        route3 = new Kruskal(g2, start, target);
        printResult(route3.getMaxBwPath(), route3.getMaxBw());
    }

    public static void printResult(int[] path, int maxBw) {
        if (path == null) {
            System.out.println("No max bw path found");
        } else {
            System.out.printf("Max BW: %d \n", maxBw);
            System.out.printf("Max BW Path: ");
            for (int i = 0; i < path.length; i++) {
                System.out.printf("%2d ", path[i]);
            }
        }

        System.out.printf("\n\n");
    }
}
