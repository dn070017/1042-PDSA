/*
.
 * To change this template file, choose Tools | Templates
.
 */
package labelcc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author YuChing
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            int dimention;
            String[] buffer = line.split("","");
            int target_x=Integer.parseInt(buffer[1]);
            int target_y=Integer.parseInt(buffer[2]);
            dimention = Integer.parseInt(buffer[0]);
            int[][] metrix;
            metrix = new int[dimention][dimention];
            for (int i = 0; i < dimention; i++) {
                for (int j = 0; j < dimention; j++) {
                    metrix[i][j] = 1;
                }
            }
            while ((line = br.readLine()) != null) {
                buffer = line.split("","");
                int x = Integer.parseInt(buffer[0]);
                int y = Integer.parseInt(buffer[1]);
                metrix[x - 1][y - 1] = 0;
            }

//            for(int i=0;i<dimention;i++)
//            {for(int j=0;j<dimention;j++)
//            {System.out.print(metrix[i][j]+"" "");}
//            System.out.println("" "");}
            int count = 0;
            boolean change = false;
            for (int i = 0; i < dimention; i++) {
                for (int j = 0; j < dimention; j++) {
                    if (metrix[i][j] == 1) {
                        if (j > 0 && metrix[i][j - 1] != 0) {
                            metrix[i][j] = metrix[i][j - 1];
                        } else if (i > 0 && metrix[i - 1][j] != 0) {
                            metrix[i][j] = metrix[i - 1][j];
                        } else {
                            count++;
                            metrix[i][j] = count;
                        }
                    }
                }
            }
//for (int i = 0; i < dimention; i++) {
//                for (int j = 0; j < dimention; j++) {
//                    System.out.print(metrix[i][j] + "" "");
//                }
//                System.out.println("" "");
//            }
// System.out.println(metrix[0][5]);
            for (int i = 0; i < dimention; i++) {
                for (int j = 0; j < dimention; j++) {
                                                                        
                    if (metrix[i][j] != 0) {

  
                        if (i > 0 && metrix[i - 1][j] != 0 && metrix[i - 1][j] != metrix[i][j]) {
                            int mi = metrix[i - 1][j] - metrix[i][j];
                            if (mi > 0) {
                                for (int o = 0; o < dimention; o++) {
                                    for (int p = 0; p < dimention; p++) {
                                        if (metrix[o][p] == metrix[i - 1][j]) {
                                            metrix[o][p]=metrix[i][j];
                                        }
                                    }
                                }
                            }
                            if(mi<0){for (int o = 0; o < dimention; o++) {
                                    for (int p = 0; p < dimention; p++) {
                                        if (metrix[o][p] == metrix[i][j]) {
                                            metrix[o][p]=metrix[i-1][j];
                                        }
                                    }
                                }}

                        }
                        
                  
                    }

                }

            }
System.out.print(metrix[target_x-1][target_y-1]);
//            for (int i = 0; i < dimention; i++) {
//                for (int j = 0; j < dimention; j++) {
//                    System.out.print(metrix[i][j] + "" "");
//                }
//                System.out.println("" "");
//            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}

