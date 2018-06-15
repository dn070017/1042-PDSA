import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            StringBuilder everything = new StringBuilder();
            String line;
            everything.append(br.readLine());//first num
            while ((line = br.readLine()) != null) {
                everything.append("" "");
                everything.append(line);
            }
            String[] data = everything.toString().split("" "");
            String[] given = data[0].split("","");
            int num = Integer.parseInt(given[0]);  //matrix size
            int assigned_row = Integer.parseInt(given[1]);
            int assigned_column = Integer.parseInt(given[2]);
            int len = data.length;  //input 
            int label = 0;
//            int k = 0;
//            int[][] p_c = new int[20][2]; 
            UF uf = new UF(num*num);
            
//            System.out.printf(num+"" ""+assigned_row+"" ""+assigned_column+"" ""+len+""\n"");
            int[][] matrix = new int[num][num];
            for(int a=0;a<num;a++){
                Arrays.fill(matrix[a], 1);
            }
            int[][] matrix_label = new int [num][num]; 
            //assign given position
            for(int i=1; i<len; i++){
               String[] block = data[i].split("","");
               int block_row = Integer.parseInt(block[0]);
               int block_column = Integer.parseInt(block[1]);
               matrix[block_row - 1][block_column - 1] = 0;
            }
//            for (int a = 0; a < num; a++) {
//                for (int b = 0; b < num; b++) {
//                    System.out.printf(matrix[a][b] + "" "");
//                }
//                System.out.printf(""\n"");
//            }
//            System.out.printf(""\n"");
           //The first pass
           for(int i=0;i<num;i++){
               for(int j=0;j<num;j++){
                   if(matrix[i][j] == 1){
                        if( i==0 && j==0){ label++; matrix_label[i][j]=label;}
                        //row_1
                        else if(i==0){
                             if(matrix[i][j-1] == 1){
                                matrix_label[i][j]=matrix_label[i][j-1]; 
                             }
                             else{
                                label++;
                                matrix_label[i][j]=label; 
                             }
                        }
                        //column_1
                        else if(j==0){
                            if(matrix[i-1][j] == 1){
                                matrix_label[i][j]=matrix_label[i-1][j];
                             }
                            else {
                                label++;
                                matrix_label[i][j]=label;
                            }
                        }
                        //usual case
                        else{   
                            //up & left are ""1""
                            if (matrix[i][j - 1] == 1 && matrix[i - 1][j] == 1) {
                                //up & left is the same
                                if (matrix_label[i][j - 1] == matrix_label[i - 1][j]) {
                                    matrix_label[i][j] = matrix_label[i][j - 1];
                                } 
                                else if (matrix_label[i][j - 1] < matrix_label[i - 1][j]) {
                                    matrix_label[i][j] = matrix_label[i][j - 1];
//                                    p_c[k][0]=matrix_label[i][j-1]; //parent,small
//                                    p_c[k][1]=matrix_label[i-1][j]; //child,big
//                                    k++;
                                    uf.union(matrix_label[i][j-1],matrix_label[i-1][j]);
                                } 
                                else {  //matrix_label[i][j-1] > matrix_label[i-1][j]
                                    matrix_label[i][j] = matrix_label[i - 1][j];
//                                    p_c[k][0]=matrix_label[i-1][j]; //parent,small
//                                    p_c[k][1]=matrix_label[i][j-1]; //child,big
//                                    k++;
                                    uf.union(matrix_label[i-1][j],matrix_label[i][j-1]);
                                }
                            } //left is ""1""
                            else if (matrix[i][j - 1] == 1) {
                                matrix_label[i][j] = matrix_label[i][j - 1];
                            }
                             //up is ""1""
                             else if(matrix[i-1][j] == 1){
                                 matrix_label[i][j]=matrix_label[i-1][j];
                             }
                             // none
                             else{
                                 label++;
                                 matrix_label[i][j]=label;
                             }
                        }
                   }
               }
           }
//            for (int a = 0; a < num; a++) {
//                for (int b = 0; b < num; b++) {
//                    System.out.printf(matrix_label[a][b] + "" "");
//                }
//                System.out.printf(""\n"");
//            }
//            System.out.printf(""\n"");
//            for (int a = 0; a < k; a++) {
//                System.out.printf(p_c[a][0] + "" <- "" +p_c[a][1] + ""\n"");
//            }
            
//            System.out.printf(uf.find(3)+""\n"");
            //second pass
            for(int i=0;i<num;i++){
                for(int j=0;j<num;j++){
                        matrix_label[i][j] = uf.find(matrix_label[i][j]);
                }
            }
//            System.out.printf(""\n"");
//            for (int a = 0; a < num; a++) {
//                for (int b = 0; b < num; b++) {
//                    System.out.printf(matrix_label[a][b] + "" "");
//                }
//                System.out.printf(""\n"");
//            }
//            System.out.printf(""\n"");
//            System.out.printf(""assigned position:""+assigned_row + "","" + assigned_column + ""\n"");
            System.out.printf(matrix_label[assigned_row - 1][assigned_column - 1]+""\n"");
        }
    }
}

