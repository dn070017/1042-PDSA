import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.ArrayList;


public class Percolation 
{
    WeightedQuickUnionUF wuf;
    int opensites;
    boolean [][]grid;
    public int size;
    int space = 0;
    int coordinates[];
    int Grid[][];
    
    public Percolation(int N)
    { 
        int spaces= N*N;
.
        this.grid = new boolean[N][N];
        Grid = new int[N][N];
        coordinates = new int[spaces+2];
            int count = 0;
             for(int i = 0; i < N ; i++)
             {
                 for(int j = 0; j <N;j++)
                 {
                     Grid[i][j] = count;
                     grid[i][j]= false;
                     count++;
                 }
             }
        for(int i = 0; i < spaces+2;i++)
        {
            coordinates[i] = i;
        }
.
        for(int i=0;i<N+2;i++)
        {
          wuf.union(coordinates[i],coordinates[i]);
        }// end for that connects all top sites
  }// end constructor;
    
    public void open(int i, int j)
    {
        if(grid[i][j]==false)
        {
          this.grid[i][j]=true;
    
        }
  }//end open
  public boolean isOpen(int i, int j)
  {
      return grid[i][j];
  }//end isOpen

  /* 
   * takes to ints as X and Y coordinates and 
   * returns true if that space is full and false
   * if not
   */


  /* 
   * checks if any space on the bottom is full returns true 
   * if there is one false other wise
   */
  public boolean percolates()
  {
    if(wuf.connected(coordinates[size*size], coordinates[size*size+1]) == true)
    {
          return true;
    }
    return false;
  }//end percolates
    
    
    public static void main(String[] args) throws Exception{
        
         try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) 
         {
             String number = br.readLine();
             int N = Integer.parseInt(number);
             ArrayList<Integer>temp = new ArrayList<>();
             String []data;
             String line;
             while((line = br.readLine())!= null)
             {
                data=line.split("","");
               temp.add(Integer.parseInt(data[0]));
               temp.add(Integer.parseInt(data[1]));
               
             }
            
            Integer [] temp2 = temp.toArray(new Integer[temp.size()]);
            for(int i = 0; i < temp.size();i++)
            {
                temp2[i]--;
            }
            int size = N;
            int testedTimes = 1;
           // Percolation perc = new Percolation(size);
            int i = 0; int j = 1;
  
        Percolation perc = new Percolation(size);
           //先upper virtual的相連
            for(int a = 0; a < N;a++)
            {
                perc.wuf.union(perc.coordinates[N*N], perc.coordinates[a]);
            }
            //lower virtual connect
             for(int b = N*(N-1); b < N*N-1;b++)
            {
                perc.wuf.union(perc.coordinates[N*N+1], perc.coordinates[b]);    
            }
        while(testedTimes != temp2.length/2)
        {
            int x=temp2[i];
            int y=temp2[j];
            perc.open(x,y);
//            perc.open(x,y);
//            perc.open(X, Y);
            
         
             if(perc.Grid[x][y]%N!=(N-1))
        {//right 1
          if(perc.isOpen(x+1,y))
          {
            perc.wuf.union(perc.coordinates[perc.Grid[x][y]],perc.coordinates[perc.Grid[x+1][y]]);
          }
        }
        if(perc.Grid[x][y]%N!=0)
        {//left 1
          if(perc.isOpen(x-1,y))
          {
            perc.wuf.union(perc.coordinates[perc.Grid[x][y]],perc.coordinates[perc.Grid[x-1][y]]);
          }
        }
        if(perc.Grid[x][y]>=N)
        {//up 1
          if(perc.isOpen(x,y-1))
          {
             perc.wuf.union(perc.coordinates[perc.Grid[x][y]],perc.coordinates[perc.Grid[x][y-1]]);
          }
        }
        if(perc.Grid[x][y]<N*(N-1))
        {//down 1
          if((perc.isOpen(x,y+1)))
          {
              perc.wuf.union(perc.coordinates[perc.Grid[x][y]],perc.coordinates[perc.Grid[x][y+1]]);
          }
        }
           
          
            
           
            testedTimes++;
            if(perc.percolates()== true )
            {
                System.out.println(i+1+"",""+j);
                break;
            }
             i+=2;
            j+=2;
     }//end while(TestedTimes<tests)
     if(testedTimes == temp2.length/2)
     {System.out.println(""-1"");}
      
    //end while(TestedTimes<tests)
    }           
  }
         
}
