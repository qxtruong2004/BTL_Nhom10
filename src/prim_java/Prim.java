
package prim_java;

import java.util.Arrays;

public class Prim {
    private static final int INF = Integer.MAX_VALUE;
    
    private static int[][] createGraph(int V, int[][] edges) {
        int[][] graph = new int[V][V];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            graph[u][v] = weight;
            graph[v][u] = weight; // Đồ thị vô hướng
        }
        return graph;
    }

    // Hàm tìm đỉnh có trọng số nhỏ nhất mà chưa nằm trong MST
    private static int findMinKey(int[] key, boolean[] inMST, int V) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Hàm in cây khung nhỏ nhất (MST)
    private static void printMST(int[] parent, int[][] graph, int V) {
        System.out.println("Cay khung nho nhat (MST):");
        System.out.println("Canh \tTrong so");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    // Hàm thực hiện thuật toán Prim để tìm MST
    public static void primMST(int[][] graph, int V) {
        int[] key = new int[V];    
        int[] parent = new int[V]; 
        boolean[] inMST = new boolean[V]; 

        // Bước khởi tạo
        Arrays.fill(key, INF); 
        Arrays.fill(inMST, false); 
        key[0] = 0; 
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = findMinKey(key, inMST, V);
            inMST[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        printMST(parent, graph, V);
    }

    // Hàm chính để chạy chương trình
    public static void main(String[] args) {
        int V = 1;

    int[][] edges = {
    };

        int[][] graph = createGraph(V, edges);

        primMST(graph, V);
    }
}

