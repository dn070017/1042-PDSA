import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {
//---------------------------Object from text book----------------------------//    
    private static int[] idd;
    public static void construct(int N,int n){
        idd=new int[N+2*n+2];
        for (int i=0 ; i<N+2*n+2 ; i++)
            idd[i]=i;
        for (int i=1 ; i<=n ; i++){
            union(i,0);
            union(N+2*n+1-i,N+2*n+2-1);
            }
    }
//    private static int root(int i){
//    while(i!=idd[i]){
//        //idd[i]=idd[idd[i]];
//        i=idd[i];
//    }
//    return i;
//    }
    public static boolean connected(int p, int q){
        return idd[p]==idd[q];
    }
    public static void union(int p, int q){
        int pid=idd[p];
        int qid=idd[q];
        for(int i=0 ; i<idd.length ; i++)
            if(idd[i]==pid) idd[i]=qid;
    }
    
 //---------------------------------------------------------------------------//
    public static void check_union(int[][] m,int n,int x_axis,int y_axis){
       int nx=x_axis-1;
       int ny=y_axis-1;
       if(x_axis==1) 
           union(y_axis+n,y_axis);
       if(x_axis==n)
           union(nx*n+ny+n+1,nx*n+ny+2*n+1);
       if(m[x_axis+1][y_axis]==1)
          union(n*(nx+1)+ny+n+1,nx*n+ny+n+1);
       if (m[x_axis-1][y_axis]==1)
          union(n*(nx-1)+ny+n+1,nx*n+ny+n+1);  
       if (m[x_axis][y_axis+1]==1)
          union(nx*n+ny+1+n+1,nx*n+ny+n+1);
       if (m[x_axis][y_axis-1]==1)
           union(nx*n+ny-1+n+1,nx*n+ny+n+1); 
        }
    public static boolean percolation(int N,int n){
    if (idd[0]==idd[N+2*n+1])
        return(true);
    else
        return(false);
    }
    public static void main(String[] args) throws Exception  {
//----------------Read the first line and store the matrix size---------------//
        try(BufferedReader br=new BufferedReader(new FileReader(args[0]))){
        String FirstLine=br.readLine();
        int size=Integer.parseInt(FirstLine);
//----------------Set the matrix and initialized to be zero-------------------//
        int[][] matrix=new int[size+2][size+2];
        for(int i=0 ; i<size+2 ; i++)
            for(int j=0 ; j< size+2 ; j++) 
                matrix[i][j]=0;
//---------------------Initializing the number to id--------------------------//
        int Number=size*size;
        construct(Number,size);
//----------------------------------------------------------------------------//
        String str=null;
        while((str=br.readLine())!=null){
        String[] data=str.split("","");
        int x_coordinate=Integer.parseInt(data[0]);
        int y_coordinate=Integer.parseInt(data[1]);
        matrix[x_coordinate][y_coordinate]=1;
        check_union(matrix,size,x_coordinate,y_coordinate);
        if (percolation(Number,size)==true){
            System.out.println(x_coordinate+"",""+y_coordinate);
            break;
        }
        }
        
        if ((str=br.readLine())==null && percolation(Number,size)==false)
            System.out.println(""-1"");
          
        
        }
        
        }
    }
    

