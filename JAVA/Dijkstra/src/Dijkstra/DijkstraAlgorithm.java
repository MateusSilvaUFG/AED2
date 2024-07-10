package Dijkstra;

import java.io.*;
import java.util.*;

public class DijkstraAlgorithm {
    private static final int NO_PARENT = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lendo a matriz de adjacência do arquivo
        int[][] adjacencyMatrix = readAdjacencyMatrix("AdjacencyMatrix.txt");

        if (adjacencyMatrix == null) {
            System.out.println("Erro ao ler o arquivo.");
            return;
        }

        // Informando o número de nós
        int numVertices = adjacencyMatrix.length;
        System.out.println("Número de nós no grafo: " + numVertices);

        // Recebendo origem e destino do usuário com validação
        int startVertex = -1;
        while (startVertex < 0 || startVertex >= numVertices) {
            System.out.print("Digite o nó de origem (0 a " + (numVertices - 1) + "): ");
            startVertex = scanner.nextInt();
            if (startVertex < 0 || startVertex >= numVertices) {
                System.out.println("Valor inválido. Por favor, insira um valor entre 0 e " + (numVertices - 1));
            }
        }

        int endVertex = -1;
        while (endVertex < 0 || endVertex >= numVertices) {
            System.out.print("Digite o nó de destino (0 a " + (numVertices - 1) + "): ");
            endVertex = scanner.nextInt();
            if (endVertex < 0 || endVertex >= numVertices) {
                System.out.println("Valor inválido. Por favor, insira um valor entre 0 e " + (numVertices - 1));
            }
        }

        // Executando o algoritmo de Dijkstra
        dijkstra(adjacencyMatrix, startVertex, endVertex);
    }

    private static int[][] readAdjacencyMatrix(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<int[]> matrix = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int[] row = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
                matrix.add(row);
            }
            reader.close();

            return matrix.toArray(new int[matrix.size()][]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void dijkstra(int[][] adjacencyMatrix, int startVertex, int endVertex) {
        int nVertices = adjacencyMatrix.length;

        int[] shortestDistances = new int[nVertices];

        boolean[] added = new boolean[nVertices];

        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        shortestDistances[startVertex] = 0;

        int[] parents = new int[nVertices];
        parents[startVertex] = NO_PARENT;

        for (int i = 1; i < nVertices; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            added[nearestVertex] = true;

            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        printSolution(startVertex, shortestDistances, parents, endVertex);
    }

    private static void printSolution(int startVertex, int[] distances, int[] parents, int endVertex) {
        System.out.print("A distância do nó " + startVertex + " ao nó " + endVertex + " é ");
        System.out.println(distances[endVertex]);
        System.out.print("O caminho é: ");
        printPath(endVertex, parents);
        System.out.println();
    }

    private static void printPath(int currentVertex, int[] parents) {
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }
}
