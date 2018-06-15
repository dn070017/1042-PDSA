
import java.util.Vector;
import java.io.FileReader;
import java.io.BufferedReader;
//import edu.princeton.cs.algs4.UF;
//import edu.princeton.cs.algs4.StdIn;

public class Percolation {
    
public  static int  xyToN(int x,int y,int size)
 {
     int index = 0;
     
     index = size *y+x;
     
     
     return index;
 }
    
 public static void connect(int x, int xCor, int yCor, boolean[][] check,UF uf )
 {

     if (xCor != 0)
     {
         if(check[xCor-1][yCor])
         {
              int neighbor =  xyToN(xCor-1, yCor, x);
              int center= xyToN(xCor,yCor, x);
              if (!uf.connected(neighbor, center))
              {
              uf.union(neighbor, center);
              connect(x,xCor-1, yCor,check,uf);
              }
        }
     }
     
     if (xCor !=x-1)
     {
         
          if(check[xCor+1][yCor])
         {
              int neighbor =  xyToN(xCor+1, yCor, x);
              int center= xyToN(xCor,yCor, x);
               if (!uf.connected(neighbor, center))
               {
              uf.union(neighbor, center);
              connect(x,xCor+1, yCor,check,uf);
               }
        }
     }
     
     if(yCor != 0)
     {
            if(check[xCor][yCor-1])
         {
              int neighbor =  xyToN(xCor, yCor-1, x);
              int center= xyToN(xCor,yCor, x);
               if (!uf.connected(neighbor, center))
               {
              uf.union(neighbor, center);
              connect(x,xCor, yCor-1,check,uf);
               }
        }
     }
            
     if (yCor != x-1)
     {
               if(check[xCor][yCor+1])
         {
              int neighbor =  xyToN(xCor, yCor+1, x);
              int center= xyToN(xCor,yCor, x);
               if (!uf.connected(neighbor, center))
               {
              uf.union(neighbor, center);
              connect(x,xCor, yCor+1,check,uf);
               }
        }
     }
    
 }
  


    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int x = Integer.parseInt(br.readLine());
              
              String line;
            
              
              //table to record the union(the root)
             int size = x*x+2;
            UF table= new UF (size);
            for ( int i =0; i<x; i++)
            {
                table.union(i, x*x);
                table.union(x*x-i-1, x*x+1);
            }

            //openTable to memorize the index has been opened
            boolean openTable[][] =new boolean[x][x];

            while ((line = br.readLine()) != null)
            {
                           String[] data = line.split("","");
                        //  int [] coordinate = {0.0};
                          int xCor =Integer.parseInt(data[0])-1;
                           int yCor= Integer.parseInt(data[1])-1;
                           openTable[xCor][yCor] = true;
                           connect(x, xCor, yCor, openTable, table );
                           
                           if (table. connected(x*x, x*x+1))
                           {
                                System.out.print(xCor+1+"","" +(yCor+1)+""\n"");
                                break;
                           }
            }

            
 
            
            


        }
    }
}

