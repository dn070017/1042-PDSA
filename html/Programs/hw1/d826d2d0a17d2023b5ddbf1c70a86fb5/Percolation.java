/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.io.FileReader;
import java.io.BufferedReader;
//import java.util.Arrays;

public class Percolation {
    
   public UF uf;
   
   public boolean matrix[];
    public Percolation(int n){
        uf = new UF(n*n+2);
        matrix = new boolean[n*n];
        for(int i=0;i<n;i++){
            uf.union(n*n, i);
            uf.union(n*n+1, n*(n-1)+i);
        }
    }
    public void black(int n,int p, int q){
        matrix[p*n+q] = true;
    }
    
    public void line(int n,int p,int q){
        if(p>0){
            if(matrix[p*n+q] & matrix[(p-1)*n+q]){
                uf.union(p*n+q,(p-1)*n+q);    
            }
        }
        if(p<n-1){
            if(matrix[p*n+q] & matrix[(p+1)*n+q]){
                uf.union(p*n+q,(p+1)*n+q);
            }
        }
        if(q>0){
            if(matrix[p*n+q] & matrix[p*n+(q-1)]){
                uf.union(p*n+q,(p-1)*n+(q-1));
            }
        }
        if(q<n-1){
            if(matrix[p*n+q] & matrix[p*n+(q+1)]){
                uf.union(p*n+q,(p-1)*n+(q+1));
            }
        }
    }
    
    public boolean percolates(int n){
        if(uf.connected(n*n, n*n+1)){
            return true;
        }
        else{
            return false;
        }
        
    }

        
    public static void main(String[] args) throws Exception {
            // read file from args[0] in Java 7 style
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
                String num = br.readLine();
                int N = Integer.parseInt(num);
                //StdOut.println(N); 
                Percolation percolation = new Percolation(N);
                
                int row;
                int column;
                String data ;
                String datacut[];
                while((data = br.readLine()) != null){
                    datacut = data.split("","");
                    row = Integer.parseInt(datacut[0]);
                    column = Integer.parseInt(datacut[1]);
                    //StdOut.println(row+"",""+column);
                    percolation.black(N,row-1, column-1);
                    percolation.line(N,row-1,column-1);
                    if(percolation.percolates(N)){
                        StdOut.println(row+"",""+column);
                        break;
                    }
                }
                if(data == null){
                   StdOut.println(""-1"");
                }
                
            }
        }
             
    }


