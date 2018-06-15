import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception{
                try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){                    
            
            String data = br.readLine();
            
            // store the first integer in variable stringCount (number of announced strings)
            int dimension = Integer.parseInt(data);
            String[] open_str;
            int[][] matrix = new int[dimension][dimension];
            UF uf = new UF(dimension*dimension+2);
            //unuion the top to uf[dimension*dimension]
            for(int i = 0; i < dimension; i++){
                uf.union(i, dimension*dimension);
            }            
            //unuion the buttom to uf[dimension*dimension+1]
            for(int i = 0; i < dimension; i++){
                uf.union(dimension*(dimension-1) + i, dimension*dimension+1);
            }      
            
            int x,y;

            while( br.ready() ){
                open_str = br.readLine().split("","");
                x = Integer.parseInt(open_str[0])-1;
                y = Integer.parseInt(open_str[1])-1;
                
                matrix[x][y] = 1;     
                
                //up
                if((x-1 >= 0) && (matrix[x-1][y]==1))
                   uf.union(dimension*x + y, dimension*(x-1) + y); 
                
                //down
                if((x+1 < dimension) && (matrix[x+1][y]==1))
                   uf.union(dimension*x + y, dimension*(x+1) + y);
                
                //left
                if((y-1 >= 0) && (matrix[x][y-1]==1))
                   uf.union(dimension*x + y, dimension*x + y-1);
                
                //up
                if((y+1 < dimension) && (matrix[x][y+1]==1))
                   uf.union(dimension*x + y, dimension*x + y+1); 
                
                if(uf.connected(dimension*dimension,dimension*dimension+1)){
                    System.out.printf(""%d,%d"",x+1,y+1);
                    break;
                }
                
            }
          
            //print(dimension,matrix);
            
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

