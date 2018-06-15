import java.util.Vector;
import java.io.FileReader;
import java.io.BufferedReader;
public class LabelCC 
{   
    static  int count =0;
    static int size;
     static  Vector <Integer>root =new Vector<>();
     public static void main(String[] args) throws Exception 
    {
         try (BufferedReader br = new BufferedReader(new FileReader(args[0])))
         {
            String[] line = br.readLine().split("","");
           size= Integer.parseInt(line[0]);
            int x=Integer.parseInt(line[1]);
             int y=Integer.parseInt(line[2]);   
             boolean[][] check=new boolean[size+2][size+2];
             for (int i=1; i<size+1; i++)
             {
                for(int j=1; j<size+1; j++)
                {
                    check[i][j]=true;
                }
             }
             int [][] table = new int [size][size];
             String Line;
             root.add(count);
             while((Line = br.readLine())!=null )
             {
                 String[] data = Line.split("","");
                 int xCor = Integer.parseInt(data[0]);
                  int yCor = Integer.parseInt(data[1]);
                  check[xCor][yCor] =false;
             }
             for (int i= 1; i<size+1; i++)
             {
                 for (int j=1; j<size+1; j++)
                 {
                   if(check[i][j])
                     {
                     if(!check[i-1][j] )
                     {
                         if(! check[i][j-1])
                         {
                             count++;
                             table[i-1][j-1] =count;
                             root.add(count);
                             //   System.out.print(""(""+i +"",""+ j+"")\n\n"");
                         }
                         else 
                         {
                             table[i-1][j-1]=table[i-1][j-2];
                         }
                     }
                 
                     else
                     {
                         if(!check[i][j-1])
                         {
                             table[i-1][j-1]=table[i-2][j-1];
                         }
                         
                         else
                         {
                             int above = table[i-1][j-2];
                             int left = table[i-2][j-1];
                             if (above>=left)
                             {
                                 table[i-1][j-1]= left;
                                root.set(above, left);
                             }
                             else
                             {
                                 table[i-1][j-1] = above;
                                 root.set(left,above);
                             }
                         }
                     }
                     }
                   else
                       table[i-1][j-1]=0;
                 }     
                 }
              int label = table[x-1][y-1];
            //  int answer;
              while(true)
              {
                 
                  int temp=root.get(label);
                  if(temp == label)
                  {
                      break;
                  }
                  else
                      label=temp;
              }
             //answer = root.get(label);
              System.out.print(label);
             }
         }
     }
                          

