import java.util.*;

public class Graph {
    // 0 A: B D I           1 3 8
    // 1 B: A C D E         0 2 3 4
    // 2 C: B E F           1 4 5
    // 3 D: A B E G         0 1 4 6
    // 4 E: B C D F G H     1 2 3 5 6 7
    // 5 F: C E H           2 4 7
    // 6 G: D E H I J       3 4 7 8 9
    // 7 H: E F G J         4 5 6 9
    // 8 I: G H J           6 7 9
    // 9 J: G H I           6 7 8

    // A, B, C, D, E, F, G, H, I, J
    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    public static int Char2Index(char c) {
        return c - 'A';
    }

    public static char Index2Char(int i) {
        return (char) (i + 'A');
    }

    public static boolean[] dfsVisited;
    public static boolean[] bfsVisited;

    public static List<Integer>[] BuildGraph() {

        dfsVisited = new boolean[10];
        bfsVisited = new boolean[10];
        List<Integer>[] testGraph = new ArrayList[10];

        for (int i = 0; i < testGraph.length; i++) {
            testGraph[i] = new ArrayList<>();

            // A
            if (i == 0) {
                testGraph[i].add(Char2Index('B')); //B
                testGraph[i].add(Char2Index('D')); //D
                testGraph[i].add(Char2Index('I')); //I
            }
            // B
            else if (i == 1) {
                testGraph[i].add(Char2Index('A')); //A
                testGraph[i].add(Char2Index('C')); //C
                testGraph[i].add(Char2Index('D')); //D
                testGraph[i].add(Char2Index('E')); //E
            }
            // C
            else if (i == 2) {
                testGraph[i].add(Char2Index('B')); //B
                testGraph[i].add(Char2Index('E')); //E
                testGraph[i].add(Char2Index('F')); //F
            }
            // D
            else if (i == 3) {
                testGraph[i].add(Char2Index('A')); //A
                testGraph[i].add(Char2Index('B')); //B
                testGraph[i].add(Char2Index('E')); //E
                testGraph[i].add(Char2Index('G')); //G
            }
            // E
            else if (i == 4) {
                testGraph[i].add(Char2Index('B')); //B
                testGraph[i].add(Char2Index('C')); //C
                testGraph[i].add(Char2Index('D')); //D
                testGraph[i].add(Char2Index('F')); //F
                testGraph[i].add(Char2Index('G')); //G
                testGraph[i].add(Char2Index('H')); //H
            }
            // F
            else if (i == 5) {
                testGraph[i].add(Char2Index('C')); //C
                testGraph[i].add(Char2Index('E')); //E
                testGraph[i].add(Char2Index('H')); //H
            }
            // G
            else if (i == 6) {
                testGraph[i].add(Char2Index('D')); //D
                testGraph[i].add(Char2Index('E')); //E
                testGraph[i].add(Char2Index('H')); //H
                testGraph[i].add(Char2Index('I')); //I
                testGraph[i].add(Char2Index('J')); //J
            }
            // H
            else if (i == 7) {
                testGraph[i].add(Char2Index('E')); //
                testGraph[i].add(Char2Index('F')); //
                testGraph[i].add(Char2Index('G')); //
                testGraph[i].add(Char2Index('J')); //

            }
            // I
            else if (i == 8) {
                testGraph[i].add(Char2Index('G')); //
                testGraph[i].add(Char2Index('H')); //
                testGraph[i].add(Char2Index('J')); //
            }
            // J
            else if (i == 9) {
                testGraph[i].add(Char2Index('G')); //
                testGraph[i].add(Char2Index('H')); //
                testGraph[i].add(Char2Index('I')); //
            }
        }
        return testGraph;
    }


    public static void DFS(int start, List<Integer>[] testGraph) {
        dfsVisited[start] = true;
        DFSTraversal(start, testGraph, dfsVisited);
    }

    public static void DFSTraversal(int start, List<Integer>[] testGraph, boolean[] isVisited) {
        System.out.print(Index2Char(start) + " ");
        for (int i : testGraph[start]) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                DFSTraversal(i, testGraph, isVisited);
            }
        }
    }

    public static void BFS(int start, List<Integer>[] testGraph) {
        bfsVisited[start] = true;
        BFSTraversal(start, testGraph, bfsVisited);
    }

    public static void BFSTraversal(int start, List<Integer>[] testGraph, boolean[] isVisited) {
        System.out.print(Index2Char(start) + " ");
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int i : testGraph[index]) {
                if (!isVisited[i]) {
                    queue.add(i);
                    isVisited[i] = true;
                    System.out.print(Index2Char(i) + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer>[] testGraph = BuildGraph();

        System.out.println("DFS: ");
        DFS(0, testGraph);

        System.out.println("\nBFS: ");
        BFS(0, testGraph);
    }
}
