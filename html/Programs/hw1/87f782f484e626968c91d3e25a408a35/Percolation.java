import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.Math;
import java.util.*;

public class Percolation {
    int mapSize;
    int tailRoot;
    int[][] map;

    public Percolation(int _mapSize) {
        mapSize = _mapSize;
        tailRoot = mapSize*mapSize;
        map = new int[mapSize][mapSize];
        for (int i=0; i<mapSize; ++i) {
            for (int j=0; j<mapSize; ++j) {
                map[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new FileReader(args[0]));
        String mapSizeString = buffer.readLine();

        Percolation percolation = new Percolation(Integer.parseInt(mapSizeString));
        
        String coordLine;
        while ((coordLine = buffer.readLine()) != null) {
            String[] coord = coordLine.split("","");
            int row = Integer.parseInt(coord[0]) - 1;
            int col = Integer.parseInt(coord[1]) - 1;

            if (percolation.checkRunThrough(row, col)) {
                System.out.println(coordLine);
                break;
            }
        }
    }

    public Boolean checkRunThrough(int row, int col) {
        // set self root = self
        map[row][col] = row*mapSize + col;

        // connect with 4 direction
        // U, D, L, R
        if (row > 0 && map[row-1][col] != -1) {
            // connect with UP
            map[row][col] = map[row-1][col] = Math.min(map[row][col], findRoot(map[row-1][col]));
        }
        if (row < mapSize-1 && map[row+1][col] != -1) {
            // connect with DOWN
            map[row][col] = map[row+1][col] = Math.min(map[row][col], findRoot(map[row+1][col]));
        }
        if (col > 0 && map[row][col-1] != -1) {
            // connect with LEFT
            map[row][col] = map[row][col-1] = Math.min(map[row][col], findRoot(map[row][col-1]));
        }
        if (col < mapSize-1 && map[row][col+1] != -1) {
            // connect with RIGHT
            map[row][col] = map[row][col+1] = Math.min(map[row][col], findRoot(map[row][col+1]));
        }

        // check root of tail is first row
        if (row == mapSize-1) {
            tailRoot = findRoot(map[row][col]);
        }

// for (int i=0; i<mapSize; ++i) {
//     for (int j=0; j<mapSize; ++j) {
//         System.out.print(map[i][j] + "" "");
//     }
//     System.out.println();
// }

        int rootOfTail = findRoot(tailRoot);
// System.out.println(""TAILROOT: "" + tailRoot);
        if (rootOfTail < mapSize) {
            return true;
        }
        return false;
    }

    public int findRoot(int index) {
        if (index == mapSize*mapSize) {
            return index;
        }

        Vector<Integer> route = new Vector<>();
        while (map[index/mapSize][index%mapSize] != index) {
            route.add(index);
            index = map[index/mapSize][index%mapSize];
        }

        // post process
        for (int i=0; i<route.size(); ++i) {
            map[index/mapSize][index%mapSize] = index;
        }

        return index;
    }
}

