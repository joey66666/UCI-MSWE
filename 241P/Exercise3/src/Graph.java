import javax.print.DocFlavor;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Graph {


    // Convert from adjacency matrix to adjacent list
    // Time Complexity: O(n^2)
    public static List<Integer>[] AMatrix2AList(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) return null;
        List[] aList = new ArrayList[map.length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    if (aList[i] == null) {
                        aList[i] = new ArrayList();
                    }
                    aList[i].add(j);
                }
            }
        }
        return aList;
    }

    // Convert from adjacency list to incidence matrix
    // Time Complexity: O(2 * m + n)
    // m: num of edges, n: num of vertexes
    public static int[][] AList2IMatrix(List[] aList) {
        if (aList == null || aList.length == 0) return null;

        int sum = 0;
        for (int i = 0; i < aList.length; i++) {
            if (aList[i] != null) {
                sum += aList[i].size();
            }
        }

        int edgeNum = sum / 2;
        int vertexNum = aList.length;

        int[][] iMatrix = new int[vertexNum][edgeNum];
        Set<String> s = new HashSet();
//        HashMap<Integer, Integer> hm = new HashMap<>();
        int edge = 0;
        for (int i = 0; i < aList.length; i++) {
            if (aList[i] == null) continue;
            for (int j = 0; j < aList[i].size(); j++) {
                if (aList[i].get(j) != null) {
                    int l = (int) aList[i].get(j);
                    String edgeDes = String.valueOf(l) + String.valueOf(i);
                    if (!s.contains(edgeDes)) {
                        iMatrix[i][edge] = 1;
                        iMatrix[l][edge] = 1;
                        edge += 1;
                        s.add(edgeDes);
                        s.add(String.valueOf(i) + String.valueOf(l));
                    }
                }
            }
        }
        return iMatrix;
    }

    // Convert from incidence matrix to adjacency list
    // Time Complexity: O(m * n)
    // m: num of edges, n: num of vertexes
    public static List[] IMatrix2AList(int[][] iMatrix) {
        if (iMatrix == null || iMatrix.length == 0 || iMatrix[0].length == 0) return null;

        int vertexNum = iMatrix.length;
        int edgeSize = iMatrix[0].length;

        List[] aList = new List[vertexNum];
        List listItem = new ArrayList();

        for (int i = 0; i < edgeSize; i++) {
            int[] mark = new int[2];
            int markIndex = 0;
            for (int j = 0; j < vertexNum; j++) {
                if (iMatrix[j][i] == 1) {
                    if (aList[j] == null) aList[j] = new ArrayList();
                    mark[markIndex] = j;
                    markIndex += 1;
                }
            }
            aList[mark[0]].add(mark[1]);
            aList[mark[1]].add(mark[0]);
        }
        return aList;
    }


    public static void main(String[] args) {
        // Test Data
        // 0 — 5 — 6 — 4
        // | \
        // 2 — 3
        // |
        // 1
        int[][] map = new int[][]{
                {0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 0},};


        System.out.println("Adjacency Matrix");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }


        System.out.println("\nAdjacency List");
        List[] aList = AMatrix2AList(map);
        for (int i = 0; i < aList.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < aList[i].size(); j++) {
                System.out.print(aList[i].get(j));
                System.out.print(", ");
            }
            System.out.println();
        }


        System.out.println("\nIncidence Matrix");
        int[][] iMatrix = AList2IMatrix(aList);
        for (int i = 0; i < iMatrix.length; i++) {
            for (int j = 0; j < iMatrix[0].length; j++) {
                System.out.print(iMatrix[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }

        System.out.println("\nAdjacency List");
        List[] bList = IMatrix2AList(iMatrix);
        for (int i = 0; i < bList.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < bList[i].size(); j++) {
                System.out.print(bList[i].get(j));
                System.out.print(", ");
            }
            System.out.println();
        }
    }
}
