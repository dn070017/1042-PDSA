
import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {
        

   
    
    
   
    
    

   
    
    
    public static void main(String[] args)  throws Exception{
        
        try(BufferedReader br =  new BufferedReader(new FileReader(args[0]))){
            
            
            String[] N = br.readLine().split("","");
            // n*n matrix
            String buffer ;
            String gg = """";
            int n = Integer.parseInt(N[0]);
           // System.out.print(n);
            
            int[][] matrix = new int[n+2][n+2];
            
            UF uf = new UF(n*n+2);
            //top id = n*n bottom id = n*n+1
            
            

            int i = 0;
            int j = 0;
           String[] data;
           buffer = br.readLine();
           while(buffer!=null){
           gg = gg.concat(buffer);
           i++;
           buffer = br.readLine();
           }
           
           String ngg =  gg.replaceAll("","", """");        
            //System.out.println(ngg);
            int L = ngg.length();
            int[] Data = new int[L];
            for(j = 0;j<L;j++){
             Data[j] = Integer.parseInt(ngg.substring(j, j+1));
            // System.out.print(Data[j]);
            }
            for(j = 0; j<L ; j+=2){
                int a = Data[j];
                int b =Data[j+1];
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
            
            
           
          
            
            
           /* buffer = br.readLine();
            while (buffer != null) {
                data = buffer.split("","");
                i++;
              for (String printStr : data) {                 
                    System.out.print(printStr);         捲                                                       
                }
                buffer = br.readLine();       
            }*/
        
        
        
           
           
              
              
            
                
            }
            
                      
    }   
}

