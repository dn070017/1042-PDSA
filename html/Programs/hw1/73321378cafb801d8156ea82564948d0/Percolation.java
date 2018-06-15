/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author 許志鵬
 */
//import edu.princeton.cs.algs4.UF;
import java.io.*;
import java.util.Scanner;
import java.io.FileReader;

public class Percolation {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            InputStream is = new FileInputStream(args[0]);

            InputStreamReader isr = new InputStreamReader(is, ""UTF8"");

            BufferedReader br = new BufferedReader(isr);

            Scanner sn = new Scanner(br);

            StringBuilder buf = new StringBuilder();

            while (sn.hasNext()) {

                buf.append(sn.next()).append(""\n"");

            }

            String str = buf.toString();

            String[] token = str.split(""\n"");

            int size = Integer.parseInt(token[0]); //sieze the matrix size

            int holeNum = token.length - 1;  //Number of holes

            int[] hole = new int[holeNum];

            

            
            for (int i = 0; i < holeNum; i++) //transfer site into flags
            {
                String[] site = token[1 + i].split("","");  //hole[i] are flags

                int row = Integer.parseInt(site[0]);

                int column = Integer.parseInt(site[1]);

                hole[i] = (row - 1) * size + (column - 1);

                //System.out.println(hole[i]);     //hole[i] refers to token[1+i]
            }

            int gridNum = size * size + 2;   // size=3 gridNum=11
            
            int[] brick = new int[gridNum-2];
            
            for (int i = 0; i < holeNum; i++) {
                brick[i] = 0;
            }
            //flag=9: upper
            //flag=10 : buttom

            UF uf;
            uf = new UF(gridNum);    //flag : 0~10

            if(size==1)
            {
                for(int i=0;i<holeNum;i++)
                {
                    if(hole[i]==0)
                    {
                        System.out.print(hole[i] / size + 1 + "","" + (hole[i] % size + 1));
                    }
                }
            }
            
            for (int i = 0; i < holeNum; i++) {
                
                brick[hole[i]]=1;
                
                if (hole[i] == 0) //left up
                {
                    

                    uf.union(hole[i], gridNum - 2);

                    if (brick[hole[i] + 1] == 1) {
                        uf.union(hole[i], hole[i] + 1);
                    }

                    if (brick[hole[i] + size] == 1) {
                        uf.union(hole[i], hole[i] + size);
                    }

                } else if (hole[i] == size - 1) //right up
                {
                    

                    uf.union(hole[i], gridNum - 2);

                    if (brick[hole[i] + size] == 1) {
                        uf.union(hole[i], hole[i] + size);
                    }

                    if (brick[hole[i] - 1] == 1) {
                        uf.union(hole[i], hole[i] - 1);
                    }

                } else if (hole[i] == gridNum - 3) //right down
                {
                    

                    uf.union(hole[i], gridNum - 1);

                    if (brick[hole[i] - 1] == 1) {
                        uf.union(hole[i], hole[i] - 1);
                    }

                    if (brick[hole[i] - size] == 1) {
                        uf.union(hole[i], hole[i] - size);
                    }

                } else if (hole[i] == gridNum - size - 2) //left down
                {
                    

                    uf.union(hole[i], gridNum - 1);

                    if (brick[hole[i] - size] == 1) {
                        uf.union(hole[i], hole[i] - size);
                    }

                    if (brick[hole[i] + 1] == 1) {
                        uf.union(hole[i], hole[i] + 1);
                    }

                } else if (hole[i] / size == 0 && hole[i] != 0 && hole[i] != size - 1) { //upper
                    

                    uf.union(hole[i], gridNum - 2);

                    if (brick[hole[i] + 1] == 1) {
                        uf.union(hole[i], hole[i] + 1);
                    }

                    if (brick[hole[i] - 1] == 1) {
                        uf.union(hole[i], hole[i] - 1);
                    }

                    if (brick[hole[i] + size] == 1) {
                        uf.union(hole[i], hole[i] + size);
                    }

                } else if (hole[i] / size == (size-1) && hole[i] != gridNum - 3 && hole[i] != gridNum - size - 2) {//buttom

                    

                    uf.union(hole[i], gridNum - 1);

                    if (brick[hole[i] + 1] == 1) {
                        uf.union(hole[i], hole[i] + 1);
                    }

                    if (brick[hole[i] - 1] == 1) {
                        uf.union(hole[i], hole[i] - 1);
                    }

                    if (brick[hole[i] - size] == 1) {
                        uf.union(hole[i], hole[i] - size);
                    }

                } else if (hole[i] % size == 0 && hole[i] != 0 && hole[i] != gridNum - size - 2) {//left side

                    

                    if (brick[hole[i] + 1] == 1) {
                        uf.union(hole[i], hole[i] + 1);
                    }

                    if (brick[hole[i] - size] == 1) {
                        uf.union(hole[i], hole[i] - size);
                    }

                    if (brick[hole[i] + size] == 1) {
                        uf.union(hole[i], hole[i] + size);
                    }

                } else if (hole[i] % size == size - 1 && hole[i] != size - 1 && hole[i] != gridNum - 3) {//right side

                    

                    if (brick[hole[i] - 1] == 1) {
                        uf.union(hole[i], hole[i] - 1);
                    }

                    if (brick[hole[i] + size] == 1) {
                        uf.union(hole[i], hole[i] + size);
                    }

                    if (brick[hole[i] - size] == 1) {
                        uf.union(hole[i], hole[i] - size);
                    }

                } else {

                    

                    if (brick[hole[i] + 1] == 1) {
                        uf.union(hole[i], hole[i] + 1);
                    }
                    if (brick[hole[i] - 1] == 1) {
                        uf.union(hole[i], hole[i] - 1);
                    }
                    if (brick[hole[i] + size] == 1) {
                        uf.union(hole[i], hole[i] + size);
                    }
                    if (brick[hole[i] - size] == 1) {
                        uf.union(hole[i], hole[i] - size);
                    }
                }
                if (uf.connected(gridNum - 1, gridNum - 2)) {
                    System.out.println(hole[i] / size + 1 + "","" + (hole[i] % size + 1));
                    break;
                }

            }
            if (uf.connected(gridNum - 1, gridNum - 2) == false) {
                System.out.println(-1);
            }

        } catch (RuntimeException e) {

            throw e;
        }
        // TODO code application logic here
        /*UF uf=new UF(10);
         uf.union(2, 5);
         uf.union(2, 6);
         System.out.println(uf.count());
         System.out.println(uf.connected(5, 6));*/
    }

}

