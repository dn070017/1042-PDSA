mport java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
public class Percolation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
       BufferedReader br = new BufferedReader(new FileReader(args[0]));
       int size = Integer.parseInt(br.readLine());//read txt   
       UF uf = new UF(size*size+2);
       for(int i=1; i<=size; i++){
           uf.union(0,i);
           uf.union(size*size+1,size*size+1-i);
       }
       //for 2>1
       int [][] sitedata =new int [size+2][size+2] ;
       //data0 for null, if not null go
       String data0 = br.readLine();
       // the result
       boolean result = false;
       while( data0 != null){
               String [] data = data0.split("","");
               int x = Integer.parseInt(data[0]);
               int y = Integer.parseInt(data[1]);
               sitedata[x][y]=1;
               //data0 for count, if the next is null, data0 is for while()
               //x is not on the top, (i, j) => (i-1)N+j
               if(x!=1 && sitedata[x-1][y]==1) //2>1 dim
                   uf.union((x-1)*size+y,(x-2)*size+y);
               if(x!=size && sitedata[x+1][y]==1) //2>1 dim
                   uf.union((x-1)*size+y,(x)*size+y);
               if(y!=1 && sitedata[x][y-1]==1) //2>1 dim
                   uf.union((x-1)*size+y,(x-1)*size+y-1);
               if(y!=size && sitedata[x][y+1]==1) //2>1 dim
                   uf.union((x-1)*size+y,(x-1)*size+y+1);     
               
               if(uf.connected(0,size*size+1)){
                    System.out.printf(""%d,%d\n"",x,y);
                    result = true;
                    break;
               }
               data0 = br.readLine();
       }
       if(result != true)
           System.out.printf(""-1\n"");
    }
}

       
       
       
