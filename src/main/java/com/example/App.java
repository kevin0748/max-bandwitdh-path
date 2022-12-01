package com.example;

import java.util.Random;

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
        Random rand = new Random();

        // Graph 1
        Graph g1 = Generator.sparseGraph(5000, 3);
        // g1.print();
        System.out.println(">>>Generate Graph 1");
        g1.summary();

        int i = 0;
        while (i < 5) {
            int start = rand.nextInt(5000), target = rand.nextInt(5000);
            long startTime, endTime;

            // 1
            startTime = System.currentTimeMillis();
            Dijkstra route1 = new Dijkstra(g1, start, target);
            int[] maxBwPath = route1.getMaxBwPath();
            endTime = System.currentTimeMillis();
            if (maxBwPath == null) {
                continue;
            }
            System.out.println("==========================");
            System.out.printf("start: %d, target: %d\n\n", start, target);
            System.out.println("[Dijkstra]");
            printResult(maxBwPath, route1.getMaxBw(), endTime - startTime);

            // 2
            startTime = System.currentTimeMillis();
            DijkstraWithHeap route2 = new DijkstraWithHeap(g1, start, target);
            maxBwPath = route2.getMaxBwPath();
            endTime = System.currentTimeMillis();
            System.out.println("[Dijkstra with Heap]");
            printResult(maxBwPath, route2.getMaxBw(), endTime - startTime);

            // 3
            startTime = System.currentTimeMillis();
            Kruskal route3 = new Kruskal(g1, start, target);
            maxBwPath = route3.getMaxBwPath();
            endTime = System.currentTimeMillis();
            System.out.println("[Kruksal]");
            printResult(maxBwPath, route3.getMaxBw(), endTime - startTime);

            i++;
        }

        // Graph 2
        Graph g2 = Generator.denseGraph(5000, 0.2);
        System.out.println(">>>Generate Graph 2");
        g2.summary();

        i = 0;
        while (i < 5) {
            int start = rand.nextInt(5000), target = rand.nextInt(5000);
            long startTime, endTime;

            // 1
            startTime = System.currentTimeMillis();
            Dijkstra route1 = new Dijkstra(g2, start, target);
            int[] maxBwPath = route1.getMaxBwPath();
            endTime = System.currentTimeMillis();
            if (maxBwPath == null) {
                continue;
            }
            System.out.println("==========================");
            System.out.printf("start: %d, target: %d\n\n", start, target);
            System.out.println("[Dijkstra]");
            printResult(maxBwPath, route1.getMaxBw(), endTime - startTime);

            // 2
            startTime = System.currentTimeMillis();
            DijkstraWithHeap route2 = new DijkstraWithHeap(g2, start, target);
            maxBwPath = route2.getMaxBwPath();
            endTime = System.currentTimeMillis();
            System.out.println("[Dijkstra with Heap]");
            printResult(maxBwPath, route2.getMaxBw(), endTime - startTime);

            // 3
            startTime = System.currentTimeMillis();
            Kruskal route3 = new Kruskal(g2, start, target);
            maxBwPath = route3.getMaxBwPath();
            endTime = System.currentTimeMillis();
            System.out.println("[Kruksal]");
            printResult(maxBwPath, route3.getMaxBw(), endTime - startTime);

            i++;
        }

    }

    public static void printResult(int[] path, int maxBw, long elapse) {
        if (path == null) {
            System.out.println("No max bw path found");
        } else {
            System.out.printf("Max BW: %d \n", maxBw);
            System.out.printf("Max BW Path: ");
            for (int i = 0; i < path.length; i++) {
                System.out.printf("%2d ", path[i]);
            }
        }

        System.out.printf("\nElapse: %d ms\n\n", elapse);
    }
}
