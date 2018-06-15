import java.io.FileReader;
import java.io.BufferedReader;

/**
.
 */

public class Percolation {



    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] data = br.readLine().split("","");

            int dim = Integer.parseInt(data[0]);
            int matrixSize = dim*dim;
            boolean[] opened = new boolean[matrixSize+1];
            int stop =0;

//            System.out.printf(""dimension of matrix: %d x %d\n"", dim, dim);

            WeightedQuickUnionUF ROCK = new WeightedQuickUnionUF(matrixSize+2);
//            System.out.printf(""a.count(): %d\n"", (ROCK.count()));

            // 連結0,1
            ROCK.union(0,1);
//            System.out.printf(""connected? %s\n"", Boolean.toString(ROCK.connected(0,1)));

            // 連結與 0 與上排 ; 連結與 (matrixSize+1) 與下排
            for(int i=1; i<=dim; i++){
//                System.out.println(i);
                ROCK.union(0,i);
//                System.out.println(matrixSize-i+1);
                ROCK.union(matrixSize+1,matrixSize-i+1);
            }

            // 開始一行行讀座標
            while(br!= null) {
                String[] currentCoordinate = br.readLine().split("","");
                int currentBoxID = Integer.parseInt(currentCoordinate[1])+dim*((Integer.parseInt(currentCoordinate[0]))-1);
//                System.out.printf(""currentLine:[%s,%s]; currentBoxID:%d\n"",currentCoordinate[0],currentCoordinate[1],currentBoxID);

                opened[currentBoxID]=true;

                //開始用currentBoxID來union
                if (currentBoxID-dim>=1){
                    if (opened[currentBoxID-dim]){
                        ROCK.union(currentBoxID,currentBoxID-dim);
//                        System.out.print(""上"");
                    }
                }

                if (currentBoxID+dim <= matrixSize){
                   if (opened[currentBoxID+dim]) {
                       ROCK.union(currentBoxID, currentBoxID + dim);
//                       System.out.print(""下"");
                   }
                }

                if (currentBoxID%dim!=0){
                    if (opened[currentBoxID+1]) {
                        ROCK.union(currentBoxID, currentBoxID + 1);
//                        System.out.print(""右"");
                    }
                }

                if (currentBoxID%dim!=1){
                    if (opened[currentBoxID-1]) {
                        ROCK.union(currentBoxID, currentBoxID-1);
//                        System.out.print(""左"");
                    }
                }

                //每次union完都用find檢查是否已滲透
//                System.out.printf(""Percolated? %s\n"", Boolean.toString(ROCK.connected(0,matrixSize+1)));
                if(ROCK.connected(0,matrixSize+1)){
                    System.out.printf(""%s,%s"", currentCoordinate[0],currentCoordinate[1]);
                    break;
                }
            }


          /*String[] ex = new String[1];
           ex[0] = ""input.txt"";
           Bingo.main(ex);*/
        }
    }





}

