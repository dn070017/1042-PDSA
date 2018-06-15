import java.io.BufferedReader;
import java.io.FileReader;

public class LabelCC {
//---------------------------API from QuickFindUF data structure--------------//    
private static int[] id;    // id[i] = component identifier of i
private static int count;   // number of components
private static int counter;
public static void QuickFindUF(int N) {
        counter=0;
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
 public static int count() {
        return count;
    }
 public static int find(int p) {
        validate(p);
        return id[p];
    }

    // validate that p is a valid index
private static void validate(int p) {
        int N = id.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + (N-1));
        }
    }
public static boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }
public static void union(int p, int q) {
        validate(p);
        validate(q);
        int pID = id[p];   // needed for correctness
        int qID = id[q];   // to reduce the number of array accesses

        // p and q are already in the same component
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }
//----------------------------------------------------------------------------//
public static void setzero (int row, int column ,int size){
    id[(row-1)*size+column-1]=0;
}
public static void first_pass (int[][] m,int row, int column,int size){
if (m[row][column]==1){
if (m[row-1][column]==0 && m[row][column-1]==0){
    counter++;
    id[(row-1)*size+column-1]=counter;
}
else if(m[row-1][column]==1 &&m[row][column-1]==0)
    union((row-1)*size+column-1,(row-2)*size+column-1);    
else if(m[row][column-1]==1 &&m[row-1][column]==0)
    union((row-1)*size+column-1,(row-1)*size+column-2);
else if(m[row-1][column]==1 && m[row][column-1]==1 ){
        if ((row-2)*size+column-1<(row-1)*size+column-2)
          union((row-1)*size+column-1,(row-2)*size+column-1);
        else
          union((row-1)*size+column-1,(row-1)*size+column-2);
        }
}
}
public static void second_pass(int[][] m,int row,int column,int size){
  if (m[row][column]==1 && m[row-1][column]==1 && m[row][column-1]==1){  
      if (id[(row-2)*size+column-1]<id[(row-1)*size+column-2]){
          union((row-1)*size+column-2,(row-2)*size+column-1);
          union((row-1)*size+column-1,(row-2)*size+column-1);     
      }
      else{
          union((row-2)*size+column-1,(row-1)*size+column-2);
          union((row-1)*size+column-1,(row-1)*size+column-2);
      }
  }
  }
public static void printt(int row, int column, int size){

    System.out.println(id[(row-1)*size+column-1]);
}


    
    public static void main(String[] args) throws Exception{
       try(BufferedReader br=new BufferedReader(new FileReader(args[0]))){
          String[] FirstLine=br.readLine().split("","");
          int size=Integer.parseInt(FirstLine[0]);
          int assignrow=Integer.parseInt(FirstLine[1]);
          int assigncolumn=Integer.parseInt(FirstLine[2]);
       //   System.out.println(size+"",""+assignrow+"",""+assigncolumn);
//-----------------initializing a new matrix and assign to 1------------------//
          int[][] matrix=new int[size+2][size+2];
          for(int i=0 ; i<size+2 ; i++)
            for(int j=0 ; j< size+2 ; j++) 
                matrix[i][j]=1;
          QuickFindUF(size*size);
//--------------read another data and block the site data assigned------------//
          String str=null;
          while((str=br.readLine())!=null){
          String[] data=str.split("","");
          int x_coordinate=Integer.parseInt(data[0]);
          int y_coordinate=Integer.parseInt(data[1]);
          matrix[x_coordinate][y_coordinate]=0;
          setzero(x_coordinate,y_coordinate,size);
          }
//-------------------Set the boundary of matrix[][] with 0--------------------//
          for (int i=0 ; i<size+2 ; i++){
              matrix[i][0]=0;
              matrix[0][i]=0;
              matrix[size+1][i]=0;
              matrix[i][size+1]=0;
             }
//          for (int i=0 ; i<size+2 ; i++){
//              for (int j=0 ; j<size+2 ; j++)
//           System.out.print(matrix[i][j]+""\t"");
//           System.out.println("""");
//           }
        for (int i=1 ; i<size+1 ; i++)
            for (int j=1 ; j<size+1 ; j++)
            first_pass(matrix,i,j,size);
         for (int i=1 ; i<size+1 ; i++)
            for (int j=1 ; j<size+1 ; j++)
            second_pass(matrix,i,j,size);                  
             
              printt(assignrow,assigncolumn,size);
            
          
       }
    }
}
