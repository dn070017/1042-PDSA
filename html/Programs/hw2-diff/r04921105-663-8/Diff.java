import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC{
    public static void main(String[] args) throws Exception{
                try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){                    
            
            String[] data = br.readLine().split("","");
            int dim = Integer.parseInt(data[0]);//得到維度
            int targetx = Integer.parseInt(data[1])-1;//目標 X座標
            int targety = Integer.parseInt(data[2])-1;//目標     Y座標
            // System.out.print (dim);
             //System.out.print (""\n"");
             
            String[] open_str;
            
            int[][] matrix = new int [dim][dim];//拿來看blocked sites分布的matrix
            int[][] lmatrix = new int [dim][dim];//拿來貼label的matrix
            
            //創立一個矩陣裡面的數字=1，代表全部無blocked sites
             for (int i = 0 ;i<matrix.length;i++){
              for(int j=0;j<matrix[0].length;j++){
              matrix[i][j]=1;    
                }
            }
            //print剛剛創立的矩陣
           /* for (int[] matrix3 : matrix) {
                for (int j = 0; j<matrix[0].length; j++) {
                    System.out.print(matrix3[j]);
                }
                System.out.print(""\n"");
            }
             
             System.out.print(""\n"");
             */
             int x,y;
         //將有blocked sites的座標表示出    
          while( br.ready() ){
                open_str = br.readLine().split("","");
                x = Integer.parseInt(open_str[0])-1;
                y = Integer.parseInt(open_str[1])-1; 
               matrix[x][y] = 0;        
          }
             //print剛剛有blocked sites的矩陣
           /* for (int[] matrix3 : matrix) {
                for (int j = 0; j<matrix[0].length; j++) {
                    System.out.print(matrix3[j]);
                }
                System.out.print(""\n"");
            }
             System.out.print(""\n"");*/
//以上完成讀檔 以下開始判斷答案////以上完成讀檔 以下開始判斷答案////以上完成讀檔 以下開始判斷答案////以上完成讀檔 以下開始判斷答案//
            int label = 1;
           
            for(int i=0;i<dim;i++){
                for(int j =0;j<dim;j++){
                    if(matrix[i][j]==0)
                        continue;
                    if(i != 0 && matrix[i-1][j] != 0)//判斷上面的site是否為blocked site
                        matrix[i][j] = matrix[i-1][j];
                    else if(j != 0 && matrix[i][j-1] != 0)//判斷左邊的site是否為blocked site
                        matrix[i][j] = matrix[i][j-1];
                    else {
                        matrix[i][j] = label;
                        label++;//label編號增加
                    }
                 }
            }   
            //print pass 1 result
            //for (int[] matrix1 : matrix) {
                //for (int j = 0; j<matrix[0].length; j++) {
                   // System.out.print(matrix1[j]);
               // }
                //System.out.print(""\n"");
            //}
            
            //連起label
            int len = 0;
            int[][] parent = new int[dim*dim][2];
            
            for(int i=0;i<dim;i++){
                for(int j=0;j<dim;j++){
                     if (matrix[i][j] == 0)
                        continue;
                      if (j!=0&&(matrix[i][j-1]!=0) && (matrix[i][j-1] != matrix[i][j])){
                          if(matrix[i][j] > matrix[i][j-1]){
                              parent[len][1] = matrix[i][j];
                              parent[len][0] = matrix[i][j-1];
                          }
                          else {
                              parent[len][0] = matrix[i][j];
                            parent[len][1] = matrix[i][j-1];
                          }
                       
                        }
                }
            }
            
           
          //  for (int i=0; i < len; i++)
           // System.out.printf(""(%d,%d)\n"",parent[i][0],parent[i][1]);
            
            for(int X = 0; X < len; X++){
                for(int i = 0;i < dim; i++){
                    for(int j = 0;j < dim; j++){
                        if(matrix[i][j] == parent[X][1]){
                            matrix[i][j] = parent[X][0];
                            
                        }    
                    }
                }
            
            }    
            
            
            
            System.out.print(matrix[targetx][targety]);
            
        
            
                   
                        
         }       
    
    }
}


