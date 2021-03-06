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

    
    public Percolation(int N)
    { 
        int spaces= N*N;
.
        this.grid = new boolean[N][N];
             
             for(int i = 0; i < N ; i++)
             {
                 for(int j = 0; j <N;j++)
                 {
                     grid[i][j] = false;
                 }
             }
    
.
        for(int i=0;i<N;i++)
        {
          wuf.union(N*N,i);
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
             int [][]coordinates = new int[N][N];
             String []data;
             while((data = br.readLine().split("",""))==null)
             {
               temp.add(Integer.parseInt(data[0]));
               temp.add(Integer.parseInt(data[1]));               
             }
         
            Integer [] temp2 = temp.toArray(new Integer[temp.size()]);
            int size = N;
            int spaces=size*size-1;
            int space = 0;
            int testedTimes = 1;
           // Percolation perc = new Percolation(size);
            int i = 0; int j = 1;
  
        Percolation perc = new Percolation(size);
        while(testedTimes == temp2.length/2)
        {
            int x=temp2[i];
            int y=temp2[j];

            perc.open(x,y);
            space=x+(y*size);
        if((space+1<spaces)&&(x<size-1))
        {//right 1
          if(perc.isOpen(x+1,y))
          {
            perc.wuf.union(space,space+1);
          }
        }
        if((space-1>0)&&(x>0))
        {//left 1
          if(perc.isOpen(x-1,y))
          {
            perc.wuf.union(space,space-1);
          }
        }
        if((space-size>0)&&(y-1>0))
        {//up 1
          if(perc.isOpen(x,y-1))
          {
            perc.wuf.union(space,space-size);
          }
        }
        if((space+size<spaces)&&(y+1<=size))
        {//down 1
          if((perc.isOpen(x,y+1)))
          {
            perc.wuf.union(space,space+size);
          }
        }
            i+=2;
            j+=2;
            testedTimes++;
            if(perc.percolates()== true )
            {
                System.out.println(temp2[i]+"",""+temp2[j]);
                break;
            }
     }//end while(TestedTimes<tests)
     
     System.out.println(""-1"");
      
    //end while(TestedTimes<tests)
    }           
  }
         
}
     


