import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

/**
 *
 * @author Po-Lin
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader labelcc_file = new BufferedReader(new FileReader(args[0]))) {

            BufferedReader temp = new BufferedReader(new FileReader(args[0]));
            // read a line and split by ','
            String[] data;
            if (temp.readLine() != null) {

                data = labelcc_file.readLine().split("","");

                int num = Integer.parseInt(data[0]); //num of grid
                int[] targetcoord = new int[2];
                targetcoord[0] = Integer.parseInt(data[1]); //store row of target coord
                targetcoord[1] = Integer.parseInt(data[2]); //store col of target coord

                //System.out.println(""Target :"" + targetcoord[0] + "" "" + targetcoord[1]);
                int[][] grid = new int[num][num];  //create num*num grid
                int[] input_coord = new int[2];
                while (temp.readLine() != null) //add coord to grid
                {
                    data = labelcc_file.readLine().split("","");
                    input_coord[0] = Integer.parseInt(data[0]) - 1;
                    input_coord[1] = Integer.parseInt(data[1]) - 1;
                    grid[input_coord[0]][input_coord[1]] = 1;
                }
                int i, j, k;

                int[][] label = new int[num][num];  //create a array to store label
                int labelnum = 1;
                int tempcheck = 0;
                int newnumcheck = 0;

                class UP {

                    int[][] smalllabel;
                    int[][] biglabel;
                    int[][] checknum;

                    public UP(int a, int b) {
                        smalllabel = new int[a][b];
                        biglabel = new int[a][b];
                        checknum = new int[a][b];
                        for (int i = 0; i < a; i++) {
                            for (int j = 0; j < b; j++) {
                                smalllabel[i][j] = 0;
                                biglabel[i][j] = 0;
                                checknum[i][j] = 0;
                            }
                        }
                    }

                }

                UP storeunionpoint = new UP(num, num);
                int count = 0;

                for (i = 0; i < num; i++) {
                    for (j = 0; j < num; j++) {
                        if (grid[i][j] == 0) {
                            if (j - 1 >= 0 && j - 1 < num - 1) //check left point
                            {
                                if (grid[i][j - 1] == 0) {
                                    label[i][j] = label[i][j - 1];
                                    tempcheck = 1;
                                    newnumcheck = 1;
                                }
                            }

                            if (i - 1 >= 0 && i - 1 < num - 1) //check top point
                            {
                                if (grid[i - 1][j] == 0) {
                                    if (tempcheck == 1) //if left point is also connected
                                    {
                                        if (label[i][j] > label[i - 1][j]) {
                                            storeunionpoint.checknum[i][j] = 1;
                                            storeunionpoint.biglabel[i][j] = label[i][j];
                                            storeunionpoint.smalllabel[i][j] = label[i - 1][j];
                                            label[i][j] = label[i - 1][j];
                                            //System.out.println(storeunionpoint.biglabel[i][j] + "" "" + storeunionpoint.smalllabel[i][j]);
                                            newnumcheck = 1;
                                            count++;
                                        } else if (label[i][j] < label[i - 1][j]) {
                                            storeunionpoint.checknum[i][j] = 1;
                                            storeunionpoint.biglabel[i][j] = label[i - 1][j];
                                            storeunionpoint.smalllabel[i][j] = label[i][j];
                                            //System.out.println(storeunionpoint.biglabel[i][j] + "" "" + storeunionpoint.smalllabel[i][j]);
                                            newnumcheck = 1;
                                            count++;
                                        }
                                    } else {
                                        label[i][j] = label[i - 1][j];
                                        newnumcheck = 1;
                                    }
                                }
                            }
                            if (newnumcheck == 0) {
                                label[i][j] = labelnum;
                                labelnum++;
                            }
                        } else {
                            label[i][j] = 0;
                        }
                        tempcheck = 0;
                        newnumcheck = 0;
                    }
                }

//                for (i = 0; i < num; i++) {  //output first label
//                    for (j = 0; j < num; j++) {
//                        if (j == num - 1) {
//                            if (i == num - 1) {
//                                System.out.println(label[i][j]);
//                                System.out.println("""");
//                            } else {
//                                System.out.println(label[i][j]);
//                            }
//                        } else {
//                            System.out.print(label[i][j] + "" "");
//                        }
//                    }
//                }
                // WeightedQuickUnionUF ufstruct = new WeightedQuickUnionUF((num * num) / 2 + 1);
                //UF ufstruct = new UF((num * num) / 2 + 1);
                //QuickFindUF ufstruct = new QuickFindUF((num * num) / 2 + 1);
                QuickUnionUF ufstruct = new QuickUnionUF((num * num) / 2 + 1);

                for (i = 0; i < num; i++) {
                    for (j = 0; j < num; j++) {
                        if (storeunionpoint.checknum[i][j] == 1) 
                            //if (storeunionpoint.biglabel[i][j] > storeunionpoint.smalllabel[i][j]) {
                                ufstruct.union(storeunionpoint.biglabel[i][j], storeunionpoint.smalllabel[i][j]);
                            //} else {
                              //  ufstruct.union(storeunionpoint.smalllabel[i][j], storeunionpoint.biglabel[i][j]);
                            //}
                        }
                    }
                
                int[] unionarray = new int[(num * num) / 2 + 1];
                for (i = 0; i < unionarray.length; i++) {
                    unionarray[i] = i;
//                    if(i>0)
//                        System.out.println(i+"" = ""+ufstruct.find(i));
                }

//                for (i = 1; i < unionarray.length; i++) {
//                    for (j = i + 1; j < unionarray.length; j++) {
//                        if (ufstruct.connected(i, j) == true) {
//                            if (unionarray[j] > unionarray[i]) {
//                                unionarray[j] = unionarray[i];
//                            } else if (unionarray[j] < unionarray[i]) {
//                                for (k = 1; k <= i; k++) {
//                                    if (unionarray[k] == unionarray[i]) {
//                                        unionarray[k] = unionarray[j];
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                for (k = 1; k < unionarray.length; k++) {
//                    for (i = 0; i < num; i++) {
//                        for (j = 0; j < num; j++) {
//
//                            if (label[i][j] == k && unionarray[k] != k) {
//                                label[i][j] = unionarray[k];
//                            }
//                        }
//                    }
//                }
//                for (i = 0; i < num; i++) {  //output first label
//                    for (j = 0; j < num; j++) {
//                        if (j == num - 1) {
//                            if (i == num - 1) {
//                                System.out.println(label[i][j]);
//                                System.out.println("""");
//                            } else {
//                                System.out.println(label[i][j]);
//                            }
//                        } else {
//                            System.out.print(label[i][j] + "" "");
//                        }
//                    }
//                }
                System.out.println(ufstruct.find(label[targetcoord[0] - 1][targetcoord[1] - 1]));

            }
        }
    }

}

