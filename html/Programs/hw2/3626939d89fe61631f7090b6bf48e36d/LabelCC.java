//import edu.princeton.cs.algs4.QuickUnionUF;
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author huangchienpeng
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] info = br.readLine().split("","");
            // store the matrix size
            int size = Integer.parseInt(info[0]);
            //System.out.printf(""%d\n"",NumOfmatrix);
            //store the goal site
            int goal = (Integer.parseInt(info[1])-1)*size+Integer.parseInt(info[2]);
            
            //build checkmatrix which is bigger than old matrix and cover it
            //let black block be true and white block be false
            boolean[][] checkmatrix = new boolean[size+2][size+2];
            for (int i = 0; i <= size+1; i++) {
                for (int j = 0; j <= size+1; j++) {
                    checkmatrix[i][j] = true;
                }
            }
            //the real n*n matrix is surrounded by black blocks
            for(int i = 1;i <= size; i++){
                for(int j = 1; j <= size; j++){
                    checkmatrix[i][j] = false;
                }
            }
            //create announced checkmatrix
            String [] announce = new String[2];
            int row = 0;
            int column = 0;
          
            while(br.ready()) {
                    announce = br.readLine().split("","");
                    row = Integer.parseInt(announce[0]);
                    column = Integer.parseInt(announce[1]);
                    //mark the announced site
                    checkmatrix[row][column] = true;
            } 
            //check the program
//            for(int i = 0 ; i < size+2 ; i++){
//                for(int j = 0 ; j < size+2 ; j++){
//                    System.out.printf(""%b "", checkmatrix[i][j]);
//                }
//                System.out.printf(""\n"");
//            }
            // n*n for every block and preserve (n*n+1)/2 for group number
            int NumOfgroup = (size*size+1)/2;
            int NumOfmatrix = size * size;
            int NumOfuf = NumOfgroup + NumOfmatrix + 1;
            QuickUnionUF uf = new QuickUnionUF(NumOfuf);
            int groupid = 0;
            
            for(int i = 1; i <= size ; i++){
                for(int j = 1 ; j <= size ; j++){
                    if(checkmatrix[i][j]==true){
                        break;
                    }
                    if(checkmatrix[i][j]==false){
                        if(checkmatrix[i][j-1]==true&&checkmatrix[i-1][j]==true){
                            groupid++;
                            int matrixnum = ( i-1 )*size + j;
                            uf.union(NumOfgroup + matrixnum + 1, groupid);
                        }
                        else if(checkmatrix[i][j-1]==false&&checkmatrix[i-1][j]==false){
                            int matrixnum = ( i-1 )*size + j;
                            if(uf.find(NumOfgroup + matrixnum-1 + 1)>=uf.find(NumOfgroup + matrixnum-size +1)){
                                uf.union(NumOfgroup + matrixnum + 1, NumOfgroup + matrixnum - size + 1);
                                uf.union(NumOfgroup + matrixnum - 1 + 1,NumOfgroup + matrixnum - size + 1);
                            }
                            else{
                                uf.union(NumOfgroup + matrixnum - size + 1,NumOfgroup + matrixnum + 1);
                                uf.union(NumOfgroup +matrixnum - size + 1,NumOfgroup + matrixnum - 1 + 1);
                            }
                        }
                        else if(checkmatrix[i][j-1]==true&&checkmatrix[i-1][j]==false){
                            int matrixnum = ( i-1 )*size + j;
                            uf.union(NumOfgroup + matrixnum + 1, NumOfgroup + matrixnum - size + 1);
                        }
                        else if(checkmatrix[i][j-1]==false&&checkmatrix[i-1][j]==true){
                            int matrixnum = ( i-1 )*size + j;
                            uf.union(NumOfgroup + matrixnum + 1, NumOfgroup + matrixnum - 1 + 1);
                        }
                    }
                }
            }
         System.out.printf(""%d"",uf.find(NumOfgroup + goal + 1));
        }
    }
}

