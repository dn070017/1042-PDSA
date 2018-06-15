
import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {
      
    public static void main(String[] args)  throws Exception{
        
        try(BufferedReader br =  new BufferedReader(new FileReader(args[0]))){
                       
            String[] N = br.readLine().split("","");
            String buffer ;
            String data = """";
            int n = Integer.parseInt(N[0]);
            
            int[][] matrix = new int[n+2][n+2];
            
            
             UF uf = new UF(n*n+2);
            int i = 0;
        
           buffer = br.readLine();
           while(buffer!=null){
           data = data.concat(buffer);
           data = data.concat("","");
           i++;
           buffer = br.readLine();
           }
            int q = 0;
           String[] newdata =  data.split("","");    
           
            int[] Data = new int[2*i];
            for(q = 0;q<2*i;q++){
             Data[q] = Integer.parseInt(newdata[q]);
            }
            for(q = 0; q<2*i ; q+=2){
                int a = Data[q];
                int b =Data[q+1];
                matrix[a][b] = 1;
            if(a == 1){
                uf.union(n*n, b-1);
                //System.out.print(uf.find((a-1)*n+b-1));
            }    
            if(a == n){
            uf.union(n*n+1, (a-1)*n+b-1);
            //System.out.print(uf.find((a-1)*n+b-1));
            }
           if(a<n+1 && b<n+1 && matrix[a][b+1] == 1){
           uf.union((a-1)*n+b-1, (a-1)*n+b);
           }  
           if(a<n+1 && b<n+1 && matrix[a][b-1] == 1){
           uf.union((a-1)*n+b-1, (a-1)*n+b-2);
           } 
           if(a<n+1 && b<n+1 && matrix[a+1][b] == 1){
           uf.union((a-1)*n+b-1, a*n+b-1);
           } 
           if(a<n+1 && b<n+1 && matrix[a-1][b] == 1){
           uf.union((a-1)*n+b-1, (a-2)*n+b-1);
           } 
            if(uf.connected(n*n, n*n+1)){
            System.out.print(a);
            System.out.print("","");
            System.out.print(b);
            break;            
            }
            }
            if(!uf.connected(n*n, n*n+1)){
            System.out.print(-1);
            }
            
            
           
            
                
            }
            
                      
    }   
}

