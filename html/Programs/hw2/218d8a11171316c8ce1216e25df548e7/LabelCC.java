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
            
            int position = 0;
            int k=0;
            int pre=0;
            int aft=0;
            int[] r_matrix = new int[size];
            for(int i = 1; i<= size; i++){
                for(int j=1; j<=size; j++){
                    position = j + (i-1)*10;
                    if(checkmatrix[i][j]==true){
                        r_matrix[position-1]=0;
                    }
                    else{
                        if(checkmatrix[i][j-1]==true&&checkmatrix[i-1][j]==true){
                            k++;
                            r_matrix[position]=k;
                        }
                        //left
                        else if(checkmatrix[i][j-1]==false&&checkmatrix[i-1][j]==true){
                            r_matrix[position-1]=r_matrix[position-2];
                        }
                        //up
                        else if(checkmatrix[i][j-1]==true&&checkmatrix[i-1][j]==false){
                            r_matrix[position-1]=r_matrix[position-1-size];
                        }
                        else{
                            if(r_matrix[position-2]<r_matrix[position-1-size]){
                                r_matrix[position-1]=r_matrix[position-2];
                                pre=r_matrix[position-1-size];
                                aft=r_matrix[position-2];
                                for(int m=0;m<position;m++){
                                    if(r_matrix[m]==pre){
                                        r_matrix[m]=aft;
                                    }
                                }
                            }
                            else if(r_matrix[position-2]>r_matrix[position-1-size]){
                                r_matrix[position-1]=r_matrix[position-1-size];
                                pre=r_matrix[position-2];
                                aft=r_matrix[position-1-size];
                                for(int m=0;m<position;m++){
                                    if(r_matrix[m]==pre){
                                        r_matrix[m]=aft;
                                    }
                                }
                            }
                            else if(r_matrix[position-2]==r_matrix[position-1-size]){
                                r_matrix[position-1]=r_matrix[position-2];

                            }
                        }
                    }
                }
            }
            
//            int NumOfgroup = (size*size+1)/2;
//            int NumOfmatrix = size * size;
//            int NumOfuf = NumOfgroup + NumOfmatrix + 1;
//            QuickUnionUF uf = new QuickUnionUF(NumOfuf);
//            int groupid = 0;
//
//            for(int i = 1; i <= size ; i++){
//                for(int j = 1 ; j <= size ; j++){
//                    if(checkmatrix[i][j]==true){
//                        continue;
//                    }
//                    if(checkmatrix[i][j]==false){
//                        if(checkmatrix[i][j-1]==true&&checkmatrix[i-1][j]==true){
//                            groupid++;
//                            int matrixnum = ( i-1 )*size + j;
//                            uf.union(NumOfgroup + matrixnum, groupid);
//                        }
//                        else if(checkmatrix[i][j-1]==false&&checkmatrix[i-1][j]==false){
//                            int matrixnum = ( i-1 )*size + j;
//                            if(uf.find(NumOfgroup + matrixnum-1)>=uf.find(NumOfgroup + matrixnum-size)){
//                                uf.union(NumOfgroup + matrixnum, NumOfgroup + matrixnum - size);
//                                uf.union(NumOfgroup + matrixnum - 1,NumOfgroup + matrixnum - size);
//                            }
//                            else{
//                                uf.union(NumOfgroup + matrixnum - size,NumOfgroup + matrixnum);
//                                uf.union(NumOfgroup +matrixnum,NumOfgroup + matrixnum - 1);
//                            }
//                        }
//                        else if(checkmatrix[i][j-1]==true&&checkmatrix[i-1][j]==false){
//                            int matrixnum = ( i-1 )*size + j;
//                            uf.union(NumOfgroup + matrixnum, NumOfgroup + matrixnum - size);
//                        }
//                        else if(checkmatrix[i][j-1]==false&&checkmatrix[i-1][j]==true){
//                            int matrixnum = ( i-1 )*size + j;
//                            uf.union(NumOfgroup + matrixnum, NumOfgroup + matrixnum - 1);
//                        }
//                    }
//                }
//            }
            System.out.printf(""%d"",r_matrix[goal]);

         
        }
    }
}

