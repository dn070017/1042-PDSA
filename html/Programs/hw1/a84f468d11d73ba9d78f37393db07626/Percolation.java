import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.Math;
import java.util.ArrayList;

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

        Boolean found = false;
        String coordLine;
        while ((coordLine = buffer.readLine()) != null) {
            String[] coord = coordLine.split("","");
            int row = Integer.parseInt(coord[0]) - 1;
            int col = Integer.parseInt(coord[1]) - 1;

            if (percolation.checkRunThrough(row, col)) {
                System.out.println(coordLine);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(""-1"");
        }
    }

    public Boolean checkRunThrough(int row, int col) {
        ArrayList<Integer> minCandidate = new ArrayList<>();
        if (row > 0 && map[row-1][col] != -1) { // check UP
            minCandidate.add(findRoot(map[row-1][col]));
        }
        if (row < mapSize-1 && map[row+1][col] != -1) { // check DOWN
            minCandidate.add(findRoot(map[row+1][col]));
        }
        if (col > 0 && map[row][col-1] != -1) { // check LEFT
            minCandidate.add(findRoot(map[row][col-1]));
        }
        if (col < mapSize-1 && map[row][col+1] != -1) { // check RIGHT
            minCandidate.add(findRoot(map[row][col+1]));
        }

        int min = row*mapSize + col;
        for (int i=0; i<minCandidate.size(); ++i) {
            if (minCandidate.get(i) < min) {
                min = minCandidate.get(i);
            }
        }

        for (int i=0; i<minCandidate.size(); ++i) {
            int index = minCandidate.get(i);
            map[index/mapSize][index%mapSize] = min;
        }

        int oldRoot = findRoot(map[row][col]);
        map[row][col] = map[oldRoot/mapSize][oldRoot%mapSize] = min;

        if (row == mapSize-1) { // check root of tail if last row
            tailRoot = Math.min(findRoot(tailRoot), findRoot(map[row][col]));
        }

        int rootOfTail = findRoot(tailRoot);
        if (rootOfTail < mapSize) {
            return true;
        }
        return false;
    }

    public int findRoot(int index) {
        if (index == mapSize*mapSize) {
            return index;
        }

        ArrayList<Integer> route = new ArrayList<>();
        while (map[index/mapSize][index%mapSize] != index) {
            route.add(index);
            index = map[index/mapSize][index%mapSize];
        }

        // post process
        for (int i=0; i<route.size(); ++i) {
            int _index = route.get(i);
            map[_index/mapSize][_index%mapSize] = index;
        }

        return index;
    }
}

