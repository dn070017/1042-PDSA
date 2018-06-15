import java.io.BufferedReader;
import java.io.FileReader;

/**
.
 */
public class LabelCC {
    public static void main(String args[]) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            int targetRow = Integer.parseInt(data[1]) - 1;  // array index從0開始，所以讀取時將座標減1
            int targetColumn = Integer.parseInt(data[2]) - 1;

            int[][] label = new int[num][num];
            int[][] parents = new int[2][num * num];

            int labelCount = 0;
            int parentsCount = 0;

            // 將label矩陣內值設為-1(因為預設為0)
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    label[i][j] = -1;
                }
            }

            while (br.ready()) {
                String[] temp = br.readLine().split("","");
                int rowBlocked = Integer.parseInt(temp[0]) - 1;
                int columnBlocked = Integer.parseInt(temp[1]) - 1;
                label[rowBlocked][columnBlocked] = 0;
            }
            br.close();

            if (label[0][0] == 0) labelCount++;
            else label[0][0] = ++labelCount;

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
//                    if (i == 0 && j == 0) {
//                        if (label[i][j] == 0) {
//                            labelCount++;
//                        } else {
//                            label[i][j] = ++labelCount;
//                        }
//                    } else if (i == 0) {
                    if (label[i][j] != 0) {
                        if (i == 0) {
//                            if (label[i][j] != 0) {
                            if (label[i][j - 1] == 0) {
                                label[i][j] = ++labelCount;
                            } else {
                                label[i][j] = label[i][j - 1];
                            }
//                            }
                        } else if (j == 0) {
//                            if (label[i][j] != 0) {
                            if (label[i - 1][j] == 0) {
                                label[i][j] = ++labelCount;
                            } else {
                                label[i][j] = label[i - 1][j];
                            }
//                            }
                        } else {
                            int left = label[i][j - 1];
                            int top = label[i - 1][j];
//                            if (label[i][j] != 0) {
                            if (left == 0 && top == 0) {
                                label[i][j] = ++labelCount;
                            } else if (left == 0) {
                                label[i][j] = top;
                            } else if (top == 0) {
                                label[i][j] = left;
                            } else {
                                if (left > top) {
                                    label[i][j] = top;
                                    parents[0][parentsCount] = left;
                                    parents[1][parentsCount] = top;
                                    parentsCount++;
                                } else {
                                    label[i][j] = left;
                                    if (left != top) {
                                        parents[0][parentsCount] = top;
                                        parents[1][parentsCount] = left;
                                        parentsCount++;
                                    }
                                }
                            }
//                            }
                        }
                    }
                }
            }

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {

                    label[i][j] = getRoot(parents, label[i][j], parentsCount);
                }
            }


//            //印出label
//            for (int i = 0; i < num; i++) {
//                for (int j = 0; j < num; j++) {
//                    System.out.print(label[i][j] + ""\t"");
//                }
//                System.out.println("" "");
//            }
//
//            System.out.println(parentsCount);
//
//            //印出parents
//            for (int i = 0; i < parentsCount; i++) {
//                System.out.print(parents[0][i] + ""\t"");
//            }
//            System.out.println("" "");
//            for (int i = 0; i < parentsCount; i++) {
//                System.out.print(parents[1][i] + ""\t"");
//            }
            System.out.println(label[targetRow][targetColumn]);
        }
    }


    public static int getRoot(int[][] parents, int index1, int index2) {
//        boolean okRoot = false;
//        while (!okRoot) {
        for (int i = 0; i < index2; i++) {
            if (index1 == parents[0][i]) {
                index1 = parents[1][i];
            }

        }
//        }
        return index1;
    }
}

