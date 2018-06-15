import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Percolation {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int num = Integer.parseInt(br.readLine());

            ArrayList<String> lines = new ArrayList<String>();

            while (br.ready())
                lines.add(br.readLine());
            br.close();

            int[] row = new int[num*num];
            int[] col = new int[num*num];
            int[] isRowOpen = new int[num];
            int[][] id = new int[num][num];

            for (int i = 0; i < lines.size(); i++) {
                String[] coordinates = lines.get(i).split("","");
                row[i] = Integer.parseInt(coordinates[0]);
                isRowOpen[row[i]-1] = 1;
                col[i] = Integer.parseInt(coordinates[1]);
            }

            int rowCount = 0;
            for (int i = 0; i < num; i++)
                rowCount = rowCount + isRowOpen[i];

            int i = 0;
            if (rowCount == num) {
                boolean isPercolation = false;
                while (!isPercolation) {
                    id[row[i]-1][col[i]-1] = row[i];
                    for (int j = i; j >= 0; j--) {
//                        System.out.println(""old i = "" + i);
//                        System.out.println(""pos = "" + row[j] + "","" + col[j]);
//                        System.out.println(""old id = "" + id[row[j] - 1][col[j] - 1]);
//                        System.out.println(""---------"");
                        if (row[j] != 1 && row[j] != num) {
                            // up
                            int upBlock = id[row[j] - 2][col[j] - 1];
                            // down
                            int downBlock = id[row[j]][col[j] - 1];
                            // left
                            int leftBlock;
                            if (col[j] == 1)
                                leftBlock = 0;
                            else
                                leftBlock = id[row[j] - 1][col[j] - 2];

                            // right
                            int rightBlock;
                            if (col[j] == num)
                                rightBlock = 0;
                            else
                                rightBlock = id[row[j] - 1][col[j]];

                            boolean firstRow = (upBlock == 1 || downBlock == 1 || leftBlock == 1 || rightBlock == 1);
                            boolean lastRow = (upBlock == num || downBlock == num || leftBlock == num || rightBlock == num);

                            if (firstRow) {
                                id[row[j] - 1][col[j] - 1] = 1;
                            } else if (lastRow) {
                                id[row[j] - 1][col[j] - 1] = num;
                            }

                            if (firstRow && lastRow) {
                                j = 0;
                                isPercolation = true;
                                System.out.println(row[i] + "","" + col[i]);
                            }
//                            System.out.println(""u_d_l_r = "" + upBlock + "","" + downBlock + "","" + leftBlock + "","" + rightBlock);
//                            System.out.println(""new id = "" + id[row[j] - 1][col[j] - 1]);
//                            System.out.println(""---------"");
                        }
                    }
                    if (i < lines.size()) {
                        i++;
                    }
                    if (i > lines.size()-1 && !isPercolation) {
                        isPercolation = true;
                        System.out.println(-1);
                    }
                }
            } else {
                System.out.println(-1);
            }
        }
    }
}


