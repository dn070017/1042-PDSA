/*
.
 * To change this template file, choose Tools | Templates
.
 */
//import edu.princeton.cs.algs4.*;
import java.util.*;
/**
 *
 * @author user
 */
public class LabelCC {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
          // TODO code application logic here
          In in = new In(args[0]);
          String[] TopRow =in.readLine().split("","");
          
          String[][] matrix = new String[Integer.parseInt(TopRow[0])][Integer.parseInt(TopRow[0])];
//          System.out.println(matrix.length);
//          System.out.println(TopRow[0]+"" ""+TopRow[1]+"" ""+TopRow[2]);
          String line;       
//         ArrayList<Integer> Row = new ArrayList<Integer>();
//          ArrayList<Integer> Column = new ArrayList<Integer>();
            while ((line = in.readLine()) != null) {
//                Row.add(Integer.parseInt(line.split("","")[0]));
//               Column.add(Integer.parseInt(line.split("","")[1]));
               matrix[Integer.parseInt(line.split("","")[0])-1][Integer.parseInt(line.split("","")[1])-1]=""0"";
                }
            
//            for(int  i =0 ; i<matrix.length;i++)
//            {
//                 for(int  j =0 ; j<matrix.length;j++)
//                 {
//                      System.out.print((i+1)+"",""+(j+1)+"" inside ""+matrix[i][j]+""     "");   
//                 }
//                 System.out.println("""");
//            }
            int label=1;
            for(int  i =0 ; i<matrix.length;i++)
            {
                 for(int  j =0 ; j<matrix.length;j++)
                 {
                      if(matrix[i][j]!=""0"") // not block site
                      {
                                   if(i ==0)   //first row
                                   {
                                        if(j==0) // first col
                                        {
                                             matrix[i][j]=Integer.toString(label);
                                        }
                                        else if(matrix[i][j-1]!=""0"")  //left site not block site >> connect
                                        {
                                             matrix[i][j]=matrix[i][j-1];
                                        }
                                        else   
                                        {
                                             matrix[i][j]=Integer.toString(++label);
                                        }
                                   }
                                   else //   another row
                                   {
                                        if(j==0)  //first col
                                        {
                                            if(matrix[i-1][j]!=""0"") // upper row not block site
                                             {
                                                  matrix[i][j]=matrix[i-1][j];
                                             }
                                             else
                                             {
                                                  matrix[i][j]=Integer.toString(++label);
                                             }
                                        }
                                        else // else col
                                        {
                                             if(matrix[i][j-1] != ""0"" && matrix[i-1][j]==""0"") // connext left col
                                             {
                                                  matrix[i][j]=matrix[i][j-1];
                                             }
                                             if(matrix[i-1][j] != ""0"" && matrix[i][j-1]==""0"") // connext upper row
                                             {
                                                  matrix[i][j]=matrix[i-1][j];
                                             }                                            
                                             if(matrix[i][j-1] == ""0"" && matrix[i-1][j]==""0"")
                                             {
                                                  matrix[i][j]=Integer.toString(++label);
                                             }                                             
                                              if(matrix[i][j-1] != ""0"" && matrix[i-1][j]!=""0"")
                                             {
                                                  if(Integer.parseInt(matrix[i][j-1]) < Integer.parseInt(matrix[i-1][j]))
                                                  {
                                                       matrix[i][j]=matrix[i][j-1];
                                                  }
                                                  else
                                                  {
                                                       matrix[i][j]=matrix[i-1][j];
                                                  }
                                             }                                                    
                                        }
                                   }        
                      }
                 }
            }
//            System.out.println(label);
            QuickUnionUF uf = new QuickUnionUF(label+1);
            
           for(int  i =1 ; i<matrix.length;i++)
            {
                 for(int  j =1 ; j<matrix.length;j++)
                 {
                      if(matrix[i][j]!=""0"") // not block site
                      {            
                           if(matrix[i][j-1] != ""0"" && matrix[i-1][j]!=""0"")
                           {
                                if(Integer.parseInt(matrix[i][j-1]) < Integer.parseInt(matrix[i-1][j]))
                                 {
                                       uf.union(uf.find(Integer.parseInt(matrix[i-1][j])),uf.find(Integer.parseInt(matrix[i][j-1])));
                                  }
                                  else
                                   {
                                        uf.union(uf.find(Integer.parseInt(matrix[i][j-1])),uf.find(Integer.parseInt(matrix[i-1][j])));
                                    }               
                           }
                      }
                 }
            }
            
            
            
            
            
            
            
            
//            System.out.println(""check"");
//            
//                        for(int  i =0 ; i<matrix.length;i++)
//            {
//                 for(int  j =0 ; j<matrix.length;j++)
//                 {
////                      System.out.print((i+1)+"",""+(j+1)+"" is ""+matrix[i][j]+""         "");   
//                      System.out.print(matrix[i][j]+""     "");
//                 }
//                                      System.out.println("""");
//            }
            
            
                        System.out.println(uf.find(Integer.parseInt(matrix[Integer.parseInt(TopRow[1])-1][Integer.parseInt(TopRow[2])-1])));
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
     }
}



