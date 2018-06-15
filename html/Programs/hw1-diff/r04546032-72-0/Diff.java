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
    int [][]  Grid;

    
     public Percolation(int N)
    { 
        int spaces= N*N;
.
        this.grid = new boolean[N][N];
        int count = 0;
        Grid = new int[N][N];
        coordinates = new int[N*N-1]; 
             for(int i = 0; i < N ; i++)
             {
                 for(int j = 0; j <N;j++)
                 {
                     grid[i][j] = false;
                     Grid[i][j] = count;
                     count++;
                 }
             }
    for(int i = 0; i < N*N-1;i++)
    {
        coordinates[i] = i;
    }
.
        for(int i=0;i<N*N;i++)
        {
          wuf.union(i,i);
        }// end for that connects all top sites
  }// end constructor;
    
    public void open(int i, int j)
    {

        if(!(this.grid[i][j]))
        {
          this.grid[i][j]=true;
          opensites+=1;
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
  public boolean isFull(int i, int j)
  {
    if(isOpen(i,j))
    {
      if(wuf.connected((i+j*size),(size*size)))
      { return true;}
    }//end for
    return false;
  }//end isFull

  /* 
   * checks if any space on the bottom is full returns true 
   * if there is one false other wise
   */
  public boolean percolates()
  {
    for(int i=0;i<size;i++)
    {
      if(isFull(i,size-1)) 
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
            int spaces=N*N-1;
            int space = 0;
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
//           //先upper virtual的相連
//            for(int a = 0; a < N;a++)
//            {
//                perc.wuf.union(perc.coordinates[N*N], perc.coordinates[a]);
//            }
//            //lower virtual connect
//             for(int b = N*(N-1); b < N*N-1;b++)
//            {
//                perc.wuf.union(perc.coordinates[N*N+1], perc.coordinates[b]);    
//            }
        while(testedTimes > temp2.length/2)
        {
            int x=temp2[i];
            int y=temp2[j];
//            int X =temp2[i+2];
//            int Y = temp2[j+2];
            perc.open(x,y);
//            perc.open(X, Y);
       
         
            //perc.wuf.union(perc.coordinates[perc.grid[x][y]], perc.coordinates[perc.grid[X][Y]]);
           
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
             perc.wuf.union(perc.coordinates[perc.Grid[x][y]],perc.coordinates[perc.Grid[x][y+1]]);
          }
        }
        if(perc.Grid[x][y]<N*(N-1))
        {//down 1
          if((perc.isOpen(x,y+1)))
          {
              perc.wuf.union(perc.coordinates[perc.Grid[x][y]],perc.coordinates[perc.Grid[x][y-1]]);
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
     


