import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Math;

public class LabelCC {
    int mapSize;
    int[][] map;
    int bound;
    ArrayList<Integer> roots;

    public LabelCC(int _mapSize) {
        mapSize = _mapSize;
        map = new int[mapSize][mapSize];
        bound = mapSize * mapSize;
        roots = new ArrayList<>();
    }

    public void block(int row, int col) {
        map[row][col] = bound;
    }

    public void construct() {
        int indexCounter = 0;
        for (int i=0; i<mapSize; ++i) {
            for (int j=0; j<mapSize; ++j) {
                if (map[i][j] == bound) {
                    continue;
                }

                int up = (i==0) ? bound : map[i-1][j];
                int left = (j==0) ? bound : map[i][j-1];

                map[i][j] = Math.min(Math.min(up, left), indexCounter);
                if (map[i][j] == indexCounter) {
                    roots.add(indexCounter);
                    indexCounter++;
                }

                if (Math.max(up, left) != bound && up != left) {
                    roots.set(Math.max(up, left), Math.min(up, left));
                }
            }
        }
    }

    public int findRoot(int row, int col) {
        if (map[row][col] == bound) {
            return 0;
        }

        int root = roots.get(map[row][col]);
        while (root != roots.get(root)) {
            root = roots.get(root);
        }

        return root + 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new FileReader(args[0]));
        String[] paramsStrings = buffer.readLine().split("","");

        LabelCC labelCC = new LabelCC(Integer.parseInt(paramsStrings[0]));

        String coordLine;
        while ((coordLine = buffer.readLine()) != null) {
            String[] coord = coordLine.split("","");
            labelCC.block(Integer.parseInt(coord[0]) - 1, Integer.parseInt(coord[1]) - 1);
        }

        labelCC.construct();

        System.out.println(labelCC.findRoot(Integer.parseInt(paramsStrings[1]) - 1,
                                            Integer.parseInt(paramsStrings[2]) - 1));
    }
}
