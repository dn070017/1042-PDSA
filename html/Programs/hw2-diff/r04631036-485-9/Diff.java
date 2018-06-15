
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Arthur
 */
public class LabelCC {

    public int size;
    public int[] labelmap;
    public QuickUnionUF qf;

    /**
     * @param args the command line arguments
     */
    LabelCC(int sizee, QuickUnionUF qff) {
        size = sizee;
        qf = qff;
        labelmap = new int[size * size];
    }

    public void setlabel(int i, int value) {
        labelmap[i] = value;
    }

    public int getindex(int i, int j) {
        return size * (i - 1) + j - 1;
    }

    public void checkrange(int i, int j) {
        if (i <= 0 || j <= 0 || i > size || j > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getlabel(int i) {
        return labelmap[i];
    }

    public static void main(String[] args) throws Exception {
        int row = 0;
        int col = 0;

        int[][] block;
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            int size = Integer.parseInt(data[0]);
            int[] target = new int[2];
            target[0] = Integer.parseInt(data[1]);
            target[1] = Integer.parseInt(data[2]);
            QuickUnionUF qf = new QuickUnionUF(size * size);
            LabelCC cc = new LabelCC(size, qf);
            block = new int[size][size];
            if(size==1)
            {    System.out.printf(""0"");
                return;
            }
            //initial labelmap
            for (int i = 0; i < size * size; i++) {
                cc.setlabel(i, 1);
            }
            //initial block
            for (int i = 0; i < size; i++) {
                //System.out.printf(""\n"");
                for (int j = 0; j < size; j++) {
                    block[i][j] = 1;
                    //System.out.printf(""%d "", block[i][j]);
                }
            }
            //System.out.printf(""\n"");
            String str;
            //System.out.printf(""%d\n%d\n%d"",size ,target[0],target[1]);
            while ((str = br.readLine()) != null) {
                data = str.split("","");
                row = Integer.parseInt(data[0]);
                col = Integer.parseInt(data[1]);
                block[row - 1][col - 1] = 0;
                cc.setlabel(cc.getindex(row, col), 0);
                // System.out.printf(""%d %d\n"", row, col);

            }

            //step0
            for (int i = 0; i < size; i++) {
                //System.out.printf(""\n"");
                for (int j = 0; j < size; j++) {
                    //  System.out.printf(""%d "", block[i][j]);
                }
            }

            //System.out.printf(""\n"");
            //System.out.printf(""\n"");
            int label = 1;
            /*
             for (int j = 0; j < size; j++) {
             if (j == 0) {
             block[0][j] = label;
             } else if (j > 0 && block[0][j - 1] == 0) {
             label++;
             block[0][j] = label;
             } else if (j > 0 && block[0][j - 1] > 0 && block[0][j] != 0) {
             block[0][j] = block[0][j - 1];
             }

             System.out.printf(""%d "", block[0][j]);
             }*/
            //step1
            for (int i = 0; i < size; i++) {
                //System.out.printf(""\n"");
                for (int j = 0; j < size; j++) {
                    
                    if (block[i][j] != 0) {
                        if (i == 0) {
                            if (j == 0) {
                                block[i][j] = label;
                            } else if (j > 0 && block[i][j - 1] == 0&& j!=1) {
                                
                                label++;
                                block[i][j] = label;
                            } else if (j > 0 && block[i][j - 1] > 0 && block[i][j] != 0) {
                                block[i][j] = block[i][j - 1];
                            }
                            else if(j ==1 && block[i][j - 1] == 0){
                                block[i][j] = label;
                            }
                        } else if (i > 0) {
                            if (j == 0 && block[i - 1][j] != 0) {
//                            System.out.printf(""me"");
                                block[i][j] = block[i - 1][j];
                            } else if (j == 0 && block[i - 1][j] == 0&&j!=1) {
                                label++;
                                block[i][j] = label;
                            } else if (j > 0 && block[i - 1][j] != 0 && block[i][j - 1] != 0) {//up left!=0 find parent
                                block[i][j] = Math.min(block[i - 1][j], block[i][j - 1]);
                                //qf.union(block[i - 1][j], block[i][j - 1]);
                                if (block[i][j - 1] > block[i - 1][j]) {
                                    qf.union(block[i][j - 1], block[i - 1][j]);
                                } else if (block[i][j - 1] < block[i - 1][j]) {
                                    qf.union(block[i - 1][j], block[i][j - 1]);
                                }
                            } else if (j > 0 && block[i][j - 1] == 0 && block[i - 1][j] != 0) {//left=0 up!=0
                                block[i][j] = block[i - 1][j];
                            } else if (j > 0 && block[i - 1][j] == 0 && block[i][j - 1] != 0) {//up=0 left!=0
                                block[i][j] = block[i][j - 1];
                            } else if (j > 0 && block[i - 1][j] == 0 && block[i][j - 1] == 0) {//up left=0
                                label++;
                                block[i][j] = label;
                            }

                        }
                    }
                    //System.out.printf(""%d "", block[i][j]);
                }

            }

            //System.out.printf(""\n\n"");
            //step2
            for (int i = 0; i < size; i++) {
                //System.out.printf(""\n"");
                for (int j = 0; j < size; j++) {
                    if (qf.find(block[i][j]) < block[i][j]) {
                        block[i][j] = qf.find(block[i][j]);
                    }
                    //else if(qf.find(block[i][j])>block[i][j])
                    //block[i][j]=
                    //System.out.printf(""%d "", block[i][j]);
                }
            }
            for (int i = 0; i < size; i++) {
                //System.out.printf(""\n"");

                for (int j = 0; j < size; j++) {
                    if (qf.find(block[i][j]) < block[i][j]) {
                        //block[i][j] = qf.find(block[i][j]);
                    }
                    //else if(qf.find(block[i][j])>block[i][j])
                    //block[i][j]=
                    //System.out.printf(""%d "", block[i][j]);
                }
            }

            //System.out.printf(""\nroot(1)= %d\n"", qf.find(1));
            //System.out.printf(""\nroot(2)= %d\n"", qf.find(2));
            System.out.printf(""%d"", block[target[0] - 1][target[1] - 1]);
            System.out.printf(""\n%d"", block[0][1]);
            
        } catch (Exception e) {
//    System.err.println(""-1"");
            System.out.printf(""0"");
            
        }
    }
}

