import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author USER
 */
public class LabelCC {
//--------------------------API QuickUnionUF----------------------------------//
     private static int[] parent;  // parent[i] = parent of i
    private  static int count;     // number of components
    private  static int counter;
    public static void constr(int N) {
        counter=0;
        parent = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }
 public static int count() {
        return count;
    }
   public static int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }
   private static void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + (N-1));  
        }
    }
    public static boolean connected(int p, int q) {
        return find(p) == find(q);
    }
      public static void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ; 
        count--;
    }
//------------------------Self define function--------------------------------//
      public static void first_pass(int[][] m ,int row, int column, int size){
          if (m[row][column]!=0){
           if (m[row-1][column]==0 && m[row][column-1]==0){
             counter++;
             m[row][column]=counter;
           }
           else if(m[row-1][column]!=0 && m[row][column-1]==0){
             m[row][column]=m[row-1][column];
             union((row-1)*size+column-1,(row-2)*size+column-1);
           }
           else if(m[row-1][column]==0 && m[row][column-1]!=0){
             m[row][column]=m[row][column-1];
             union((row-1)*size+column-1,(row-1)*size+column-2);
           }
           else if (m[row-1][column]!=0 && m[row][column-1]!=0){
               if (m[row-1][column]<m[row][column-1]){
                   m[row][column]=m[row-1][column];
                   union((row-1)*size+column-1,(row-2)*size+column-1);
                   union((row-1)*size+column-2,(row-2)*size+column-1);
               }
               else{
                   m[row][column]=m[row][column-1];
                   union((row-1)*size+column-1,(row-1)*size+column-2);
                   union((row-2)*size+column-1,(row-1)*size+column-2);
               }
           }
          }
      }
      public static void second_pass (int[][] m,int row, int column, int size){
          int temp=find((row-1)*size+column-1);
          if (m[row][column]!=m[(temp/size)+1][(temp%size)+1])
          {
                if (m[(temp/size)+1][(temp%size)+1]<m[row][column]){
                m[row][column]=m[(temp/size)+1][(temp%size)+1];
                union((row-1)*size+column-1,temp);
                }
                
           }
      }
  public static void main(String[] args) throws Exception{
       try(BufferedReader br=new BufferedReader(new FileReader(args[0]))){
          String[] FirstLine=br.readLine().split("","");
          int size=Integer.parseInt(FirstLine[0]);
          int assignrow=Integer.parseInt(FirstLine[1]);
          int assigncolumn=Integer.parseInt(FirstLine[2]);
//-----------------initializing a new matrix and assign to 1------------------//
          int[][] matrix=new int[size+2][size+2];
          for(int i=0 ; i<size+2 ; i++)
            for(int j=0 ; j< size+2 ; j++) 
                matrix[i][j]=1;
          constr(size*size);
//--------------read another data and block the site data assigned------------//
          String str=null;
          while((str=br.readLine())!=null){
          String[] data=str.split("","");
          int x_coordinate=Integer.parseInt(data[0]);
          int y_coordinate=Integer.parseInt(data[1]);
          matrix[x_coordinate][y_coordinate]=0;
         
          }
//-------------------Set the boundary of matrix[][] with 0--------------------//
          for (int i=0 ; i<size+2 ; i++){
              matrix[i][0]=0;
              matrix[0][i]=0;
              matrix[size+1][i]=0;
              matrix[i][size+1]=0;
             }
//--------------------------First pass operation------------------------------//
          for(int i=1 ; i<size+1 ;i++)
              for (int j=1 ; j<size+1 ; j++)
              first_pass(matrix,i,j,size);
//---------------------------Second pass operation----------------------------//         
          for(int i=1 ; i<size+1 ;i++){
              for (int j=1 ; j<size+1 ; j++){
              if (matrix[i][j]!=0)
                  second_pass(matrix,i,j,size);
              }
          }
//          for (int i=0 ; i<size+2 ; i++){
//              for (int j=0 ; j<size+2 ; j++){
//                  System.out.print(matrix[i][j]+""\t"");
//              }
//                  System.out.println("""");
//          }
              System.out.println(matrix[assignrow][assigncolumn]);
       }
  }
}
  
