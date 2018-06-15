import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception{
                try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){                    
            
            String data = br.readLine();
            
            // store the first integer in variable stringCount (number of announced strings)
            int dim = Integer.parseInt(data);
            String[] open_str;
            int[][] matrix = new int[dim][dim];
            UF uf = new UF(dim*dim+2);
            //unuion the top to uf[dimension*dimension]
            for(int i = 0; i < dim; i++){
                uf.union(i, dim*dim);
            }            
            //unuion the buttom to uf[dimension*dimension+1]
            for(int i = 0; i < dim; i++){
                uf.union(dim*(dim-1) + i, dim*dim+1);
            }      
            int x,y;
            
            boolean flag = false;

            while( br.ready() ){
                open_str = br.readLine().split("","");
                x = Integer.parseInt(open_str[0])-1;
                y = Integer.parseInt(open_str[1])-1;
                
                matrix[x][y] = 1;     
                
                //up
                if((x-1 >= 0) && (matrix[x-1][y]==1))
                   uf.union(dim*x + y, dim*(x-1) + y); 
                
                //down
                if((x+1 < dim) && (matrix[x+1][y]==1))
                   uf.union(dim*x + y, dim*(x+1) + y);
                
                //left
                if((y-1 >= 0) && (matrix[x][y-1]==1))
                   uf.union(dim*x + y, dim*x + y-1);
                
                //up
                if((y+1 < dim) && (matrix[x][y+1]==1))
                   uf.union(dim*x + y, dim*x + y+1); 
                
                if(uf.connected(dim*dim,dim*dim+1)){
                    System.out.printf(""%d,%d"",x+1,y+1);
                    flag = true;
                    break;
                }
                
            }
            if (flag == false){
                System.out.printf(""%d"",-1);
            }
                }            
            
    }
    
    
    public static void print(int dim,int matrix[][]){
                for(int i =0; i < dim; i++){

                for(int j=0; j < dim; j++){
                    System.out.printf(""%d"",matrix[i][j]);
                    System.out.printf("","");
                }
                
                System.out.println("""");
            }    
    }
    
    
}

