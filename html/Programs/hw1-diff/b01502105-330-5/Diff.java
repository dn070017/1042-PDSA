import java.io.BufferedReader;
import java.io.FileReader;

public class Percolation {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split("" "");
            int num = Integer.parseInt(data[0]);  //matrix size
            int len = data.length;  //inout size
//            System.out.printf(num+""\n"");
            int[][] matrix = new int[num][num];
            int element_num = num*num + 2;
            UF uf = new UF(element_num);
            int[] index = new int[element_num];
            int k;
            for(int i=0; i<element_num; i++){
                index[i]=i;
            }
//            System.out.printf(data[1]+"" ""+data[2]+""\n"");
            for(int j=1;j<=num;j++){
                uf.union(index[0],index[j]);
                uf.union(index[element_num-1-j],index[element_num-1]);
            }
//            System.out.printf(""initial connected = ""+uf.connected(index[0], index[element_num-1])+""\n"");
            loop:{
                for (k = 1; k <= len - 1; k++) {
                    String[] input = data[k].split("","");
                    int row = Integer.parseInt(input[0]) - 1;
                    int column = Integer.parseInt(input[1]) - 1;
                    matrix[row][column] = 1;
//                    System.out.println(matrix[0][0]+"" ""+matrix[0][1]+"" ""+matrix[0][2]);
//                    System.out.println(matrix[1][0]+"" ""+matrix[1][1]+"" ""+matrix[1][2]);
//                    System.out.println(matrix[2][0]+"" ""+matrix[2][1]+"" ""+matrix[2][2]);

                    int open = num*row + column + 1;
                    //down
                    if( row+1<num && matrix[row+1][column] == 1){
                        uf.union(index[open],index[open+num]);
//                        System.out.printf(""union:""+(row+1)+"",""+(column+1)+"" ""+(row+2)+"",""+(column+1)+""\n"");
                    }
                    //up
                    if( row-1>=0 && matrix[row-1][column] == 1){
                        uf.union(index[open],index[open-num]);
//                        System.out.printf(""union:""+(row+1)+"",""+(column+1)+"" ""+row+"",""+(column+1)+""\n"");
                    }
                    //left
                    if( column-1>=0 && matrix[row][column-1] == 1){
                        uf.union(index[open-1],index[open]);
//                        System.out.printf(""union:""+(row+1)+"",""+(column+1)+"" ""+(row+1)+"",""+(column)+""\n"");
                    }
                    //right
                    if( column+1<num && matrix[row][column+1] == 1){
                        uf.union(index[open+1],index[open]);
//                        System.out.printf(""union:""+(row+1)+"",""+(column+1)+"" ""+(row+1)+"",""+(column+2)+""\n"");
                    }
                    //checking connection
                    if( uf.connected(index[0],index[element_num-1])){
//                        System.out.printf(""Done!"" + ""\n"");
                        System.out.printf(input[0]+"",""+input[1]+""\n"");
                        break loop;
                    }
//                    System.out.println("" "");
                }
            }
            if(k>=len){
                System.out.printf(-1+""\n"");
            }
//            System.out.printf(""Done!"" + ""\n"");
        }
    }
}

