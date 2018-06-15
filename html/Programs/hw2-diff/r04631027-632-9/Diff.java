import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author phillip hsiao
 */
public class LabelCC {
    
    private int[] _parent;
    private int[] _rank;
    private int[] _true;

    public int find(int i) {
        int p = _parent[i];
        if (i == p) {
          return i;
        }
        return _parent[i] = find(p);
    }
    public void union(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);
        if (root2 == root1) return;
        if (_rank[root1] > _rank[root2]) {
          _parent[root2] = root1;
        } else if (_rank[root2] > _rank[root1]) {
          _parent[root1] = root2;
        } else {
          _parent[root2] = root1;
          _rank[root1]++;
        }
    }
    public LabelCC(int max) {
        _parent = new int[max];
        _rank = new int[max];
        for (int i = 0; i < max; i++) {
          _parent[i] = i;
        }
    }
    public String toString() {
        return ""<UnionFind\np "" + Arrays.toString(_parent) + ""\nr "" + Arrays.toString(_rank) + ""\n>"";
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File file = new File(args[0]);
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
 //System.out.println("""");
            for (int i = 0; i < dimention; i++) {
                for (int j = 0; j < dimention; j++) {
                                                                        
                    if (metrix[i][j] != 0) {

  
                        if (i > 0 && metrix[i - 1][j] != 0 && metrix[i - 1][j] != metrix[i][j]) {
                           
                            int mi = metrix[i - 1][j] - metrix[i][j];
                            int big = metrix[i - 1][j];
                            int small = metrix[i][j];
                            if (mi > 0) {
                              
                                for (int o = 0; o < dimention; o++) {
                                    for (int p = 0; p < dimention; p++) {
                                        if (metrix[o][p] == big) {
                                            metrix[o][p]=small;
                                           
                                        }
                                    }
                                }
                            }
                            if(mi < 0){
                                for (int o = 0; o < dimention; o++) {
                                    for (int p = 0; p < dimention; p++) {
                                        if (metrix[o][p] == small) {
                                            metrix[o][p]=big;
                                           
                                        }
                                    }
                                }
                            }

                        }
                        
                  
                    }

                }
//               
            }

//            for (int i = 0; i < dimention; i++) {
//                for (int j = 0; j < dimention; j++) {
//                    System.out.print(metrix[i][j] + "" "");
//                }
//                System.out.println("" "");
//            }
            System.out.print(metrix[target_x-1][target_y-1]);
        } catch (IOException e) {
            System.out.println(e);
        }
        // TODO code application logic here
    }
}
