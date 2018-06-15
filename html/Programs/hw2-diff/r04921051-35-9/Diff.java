import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception{
                try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){                    
            
            String[] data = br.readLine().split("","");
            
            
            // store the first integer in variable stringCount (number of announced strings)
            
            int dimension = Integer.parseInt(data[0]);
            int input_x = Integer.parseInt(data[1])-1;
            int input_y = Integer.parseInt(data[2])-1;
            
            
            String[] open_str;
            int[][] matrix = new int[dimension][dimension];
            
            for (int i=0; i < dimension; i++){
                for(int j = 0; j < dimension; j++){
                 matrix[i][j] = -1;   
                }
            }
                   
            
            int x,y;
            

            while( br.ready() ){
                open_str = br.readLine().split("","");
                x = Integer.parseInt(open_str[0])-1;
                y = Integer.parseInt(open_str[1])-1;
                matrix[x][y] = 0;                
                }
            
            
            int counter = 1;
            
            for(int i = 0; i < dimension; i++){
                for(int j = 0; j < dimension; j++){
                    if(matrix[i][j] == 0)
                        continue;
                    if(i != 0 && matrix[i-1][j] != 0){
                        matrix[i][j] = matrix[i-1][j];     
                    }
                    else if(j != 0 && matrix[i][j-1] != 0)
                        matrix[i][j] = matrix[i][j-1];
                    else {
                        matrix[i][j] = counter;
                        counter++;
                    }
                        
                }
            }

            
            int len = 0;
            int[][] tree = new int[dimension*dimension][2];
            
    for (int k = 0; k < 5;k++){
            for(int i=0; i < dimension; i++){
                for(int j=1;j < dimension; j++){
                    if (matrix[i][j] == 0)
                        continue;
                    if ((matrix[i][j-1]!=0) && (matrix[i][j-1] != matrix[i][j])){
                        if(matrix[i][j] > matrix[i][j-1]){
                            tree[len][1] = matrix[i][j];
                            tree[len][0] = matrix[i][j-1];}
                        else{
                            tree[len][0] = matrix[i][j];
                            tree[len][1] = matrix[i][j-1];}
                        len++;
                    }
                }
            }
            
            //System.out.println(dimension);
            //for (int i=0; i < len; i++)
            //System.out.printf(""(%d,%d)\n"",tree[i][0],tree[i][1]);
            
            
            
            for(int temp = 0; temp < len; temp++){
            for(int i = 0;i < dimension; i++){
                for(int j = 0;j < dimension; j++){
                if(matrix[i][j] == tree[temp][1]){
                    matrix[i][j] = tree[temp][0];
                            
                }    
                }
            }
            }
            
                
    }    
            //print(dimension, matrix);
            
            
            System.out.println(matrix[input_x][input_y]);
            
            }            
            
    }
    
    
    public static void print(int dimension,int matrix[][]){
                for(int i =0; i < dimension; i++){

                for(int j=0; j < dimension; j++){
                    System.out.printf(""%d"",matrix[i][j]);
                    System.out.printf("","");
                }
                
                System.out.println("""");
            }    
    }
    
    
}

