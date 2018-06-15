
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author hung-wei
 */
public class Percolation {
    private static int [] states;
    private static int side;
    private static UF cellStorage;
// create N-by-N grid, with all sites blocked
    public static void percolation(int N){
        side=N;
        //+2 are for additional top and bottom cells
        cellStorage=new UF(N*N+2);      
        states=new int[N*N+2];
        for(int index=0;index<N*N;index++){
            states[index]=0;
        }
        states[N*N]=1;
        states[N*N+1]=1;            
    }
   // open site (row i, column j) if it is not already  
    public static void open(int i, int j){ 
        checkRange(i,j);
        if(isOpen(i,j))return;       
        int cell=getCellIndex(i,j);
        states[cell]=1;
        //if not top row
        if(i!=1 && isOpen(i-1,j)){
            cellStorage.union(getCellIndex(i-1,j),cell);
        }else if(i==1){
            //connect to virtual top cell
            cellStorage.union(cell,side*side);
        }
        //if not bottom row
        if(i!=side && isOpen(i+1,j)){       
            cellStorage.union(getCellIndex(i+1,j),cell);
        }else if (i==side){
            //connect to virtual bottom cell
            cellStorage.union(cell,side*side+1);
        }
        //if not left border
        if(j!=1 && isOpen(i,j-1)){
            cellStorage.union(getCellIndex(i,j-1),cell);
        }
        //if not right border
        if(j!=side && isOpen(i,j+1)){
            cellStorage.union(getCellIndex(i,j+1),cell);
        }
    }
     
    private static void checkRange(int i, int j){
        if (i<=0||j<=0||i>side||j>side)throw new IndexOutOfBoundsException();
    }
    
    // is site (row i, column j) open?
    public static boolean isOpen(int i, int j){
        checkRange(i,j);
        return states[getCellIndex(i,j)]==1;
    }   
  
    // does the system percolate?
    public static boolean percolates(){
        return cellStorage.connected(side*side,side*side+1);
    }           
     
    private static int getCellIndex(int row, int column){
        return (side*(row-1))+column-1;
    }
     
     
    public static void main(String[] args) throws Exception {

        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0])))
        {
            String data = br.readLine();
            int N = Integer.parseInt(data);
            //initialization
            percolation(N);
            String[] datatemp = new String[2];
            while ((datatemp = br.readLine().split("","")).length != 1){
                int i = Integer.parseInt(datatemp[0]);
                int j = Integer.parseInt(datatemp[1]);
                open(i,j);
                if (percolates()){
                    System.out.println(i + "","" + j);
                    break;
                }
            }
            if (!percolates())
                System.out.println(-1);
            

        }
            
    }
}

