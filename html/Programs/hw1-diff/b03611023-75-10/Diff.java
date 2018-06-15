
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
           gg = gg.concat("","");
           i++;
           buffer = br.readLine();
           }
           // System.out.println(gg);
           String[] ngg =  gg.split("","");    
           /*for(j = 0;j<i*2;j++){
            System.out.println(ngg[j]);
           }*/
            int[] Data = new int[2*i];
            for(j = 0;j<2*i;j++){
             Data[j] = Integer.parseInt(ngg[j]);
            // System.out.print(Data[j]);
            }
            for(j = 0; j<2*i ; j+=2){
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

