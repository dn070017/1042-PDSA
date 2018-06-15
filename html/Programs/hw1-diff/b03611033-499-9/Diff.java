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

public class Percolation {    
   public boolean[] matrix;
   public int[] self;
   public static int count=0;
   public int counted;
    public Percolation(int n){
        matrix = new boolean[n*n];
        self = new int[n*n];
        for(int i=0;i<n;i++){
            self[i]=0;
        }
        for(int i=n;i<n*n;i++){
            self[i]=i;
        }
    }
    public void open(int n,int p,int q){
       matrix[p*n+q] = true;
        count++;
        counted=count;
        line(n,p,q);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(matrix[i*n+j] & counted !=0){
                    line(n,i,j);
                    counted--;
                }
            }
        }
    }
    
    public void line(int n,int p,int q){
         if(q>0){
            if(matrix[p*n+q] & matrix[p*n+(q-1)]){
                root(p*n+q,p*n+(q-1));
            }
        }
        if(p>0){
            if(matrix[p*n+q] & matrix[(p-1)*n+q]){
                root(p*n+q,(p-1)*n+q);
            }
        }
        if(p<n-1){
            if(matrix[p*n+q] & matrix[(p+1)*n+q]){
                root((p+1)*n+q,p*n+q);
            }
        }
        
        if(q<n-1){
            if(matrix[p*n+q] & matrix[p*n+(q+1)]){
                root(p*n+(q+1),p*n+q);
            }
        }
    }
    public void root(int m,int n){
        if(self[m]<self[n]){
            self[n]=self[m];
        }
        else{
            self[m]=self[n];
        }
    }
    
    
    public boolean percolates(int n){
        for(int i=(n-1)*n;i<n*n;i++){
            if(self[i]==0){
                return true;
            }
        }
        return false;
        
    }

        
    public static void main(String[] args) throws Exception {
            // read file from args[0] in Java 7 style
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
                String data = br.readLine();
                int N = Integer.parseInt(data);
                Percolation percolation = new Percolation(N);
                
                int row;
                int column;
                String datacut[];
                while((data = br.readLine()) != null){
                    datacut = data.split("","");
                    row = Integer.parseInt(datacut[0]);
                    column = Integer.parseInt(datacut[1]);
                    percolation.open(N,row-1,column-1);
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


