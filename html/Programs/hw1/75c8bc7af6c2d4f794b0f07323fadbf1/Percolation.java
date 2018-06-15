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
public class Percolation {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) {
          // TODO code application logic here

          In in = new In(args[0]);

          int num = Integer.parseInt(in.readLine());
//          System.out.println(num);
          ArrayList<Integer> Row = new ArrayList<Integer>();
          ArrayList<Integer> Column = new ArrayList<Integer>();

                    UF uf = new UF(num * num + 2);//   num*num+1  top  node   num*num+2 bottom node
          int[][] OpenSiteStorge = new int[num][num];
           
          
          int i =0;
          String line;
          while ((line = in.readLine()) != null) {
               Row.add(Integer.parseInt(line.split("","")[0]) - 1);
               Column.add(Integer.parseInt(line.split("","")[1]) - 1);
//          for (int i = 0; i < Row.size(); i++) {
//               System.out.print(Row.get(i) + "","" + Column.get(i));
//               System.out.println("""");
//          }

          if(num!=1){
                  
          
          
          
//          for (int i = 0; i < Row.size(); i++) 
//          {                       //Row.get(i) * num + Column.get(i) means ID index    /input coornate
                    if(Row.get(i)<num && Column.get(i)<num)
                    {
//               set open site up
               OpenSiteStorge[Row.get(i)][Column.get(i)] = 1;

//               top row connect to the top node
               if (Row.get(i) == 0) 
               {
                    uf.union(Row.get(i) * num + Column.get(i), num * num);
                    if (OpenSiteStorge[Row.get(i) + 1][Column.get(i)] == 1) 
                    { //connect to second row  if second row with the same column is open site
                         uf.union(Row.get(i) * num + Column.get(i), (Row.get(i) + 1) * num + Column.get(i));
                    }
                         if (Column.get(i) == 0) 
                         {
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) + 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)  * num + Column.get(i)+1);
                              }
                         } 
                         else if (Column.get(i) == num - 1)
                         {
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) - 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i) * num + Column.get(i)-1);
                              }
                         } 
                         else 
                         {
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) + 1] == 1)
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)* num + Column.get(i)+1);
                              }
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) - 1] == 1)
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)* num + Column.get(i)-1);
                              }                              
                         }
           }

//               last row connect to the bottom node
          else if (Row.get (i)   == num - 1)
          {
                    uf.union(Row.get(i) * num + Column.get(i), num * num + 1);
                     if (OpenSiteStorge[Row.get(i) - 1][Column.get(i)] == 1) 
                    { //connect to second row  if second row with the same column is open site
                         uf.union(Row.get(i) * num + Column.get(i), (Row.get(i) - 1) * num + Column.get(i));
                         }  
                         if (Column.get(i) == 0) 
                         {
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) + 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)  * num + Column.get(i)+1);
                              }
                         } 
                         else if (Column.get(i) == num - 1)
                         {
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) - 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i) * num + Column.get(i)-1);
                              }
                         } 
                         else 
                         {
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) + 1] == 1)
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)* num + Column.get(i)+1);
                              }
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) - 1] == 1)
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)* num + Column.get(i)-1);
                              }                              
                         }      
     }
          else if(Row.get (i)  != num - 1 && Row.get (i)   != 0)//middle row 
            {               
               if (Column.get(i) == 0) 
                         {
                              if (OpenSiteStorge[Row.get(i)-1][Column.get(i) ] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), (Row.get(i)-1)  * num + Column.get(i));
                              }
                               if (OpenSiteStorge[Row.get(i)+1][Column.get(i) ] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), (Row.get(i)+1) * num + Column.get(i));
                              }
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) + 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)  * num + Column.get(i)+1);
                              }
                         } 
               else if (Column.get(i) == num-1) 
                         {
                              if (OpenSiteStorge[Row.get(i)-1][Column.get(i) ] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), (Row.get(i)-1)  * num + Column.get(i));
                              }
                               if (OpenSiteStorge[Row.get(i)+1][Column.get(i) ] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), (Row.get(i)+1) * num + Column.get(i));
                              }
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) - 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)  * num + Column.get(i)-1);
                              }
                         } 
               else if (Column.get(i) != num-1 && Column.get(i) != 0) 
                         {
                              if (OpenSiteStorge[Row.get(i)-1][Column.get(i) ] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), (Row.get(i)-1)  * num + Column.get(i));
                              }
                               if (OpenSiteStorge[Row.get(i)+1][Column.get(i) ] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), (Row.get(i)+1) * num + Column.get(i));
                              }
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) - 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)  * num + Column.get(i)-1);
                              }
                              if (OpenSiteStorge[Row.get(i)][Column.get(i) + 1] == 1) 
                              {
                                   uf.union(Row.get(i) * num + Column.get(i), Row.get(i)  * num + Column.get(i)+1);
                              }
                         } 
          }
     
     if(uf.connected(num*num, num*num+1) == true)
     {
//          System.out.println(""output:"");
          System.out.println((Row.get(i)+1)+"",""+(Column.get(i)+1));
          break;
     }
  
     }
//}
          }
          else{
          System.out.println((Row.get(0)+1)+"",""+(Column.get(0)+1));
          }
//for(int i = 0 ; i < num;i++){
//                    System.out.println(OpenSiteStorge[i][0]+"" ""+OpenSiteStorge[i][1]+"" ""+OpenSiteStorge[i][2]);
//          }
//          System.out.println(uf.connected(num*num, num*num+1));
//          System.out.println(uf.connected(0, 3));
//          System.out.println(uf.connected(0, num * num));
//          System.out.println(uf.connected(6, num * num + 1));
//          System.out.println(uf.connected(7, num * num + 1));
//          System.out.println(uf.connected(8, num * num + 1));
          i++;
          }
             if( num!=1 && uf.connected(num*num, num*num+1) == false )
     {           
               System.out.println(""-1"");
     }
     }
}
