
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
/**
 *
 * @author LAB104
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        int MAX = 50;
        //String str1;
        String [] data = new String[MAX];
        FileReader fr = new FileReader(args[0]);
        BufferedReader bfr = new BufferedReader(fr);
        
        data = bfr.readLine().split("","");
        
        int stringCount = Integer.parseInt(data[0]);
        int num = Integer.parseInt(data[1]);
        
        try
        {
            if((stringCount<2)||(num < 2))
                throw new ArithmeticException();
            
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int [][] Check = new int[num][num];
            String[] str2 = new String[num];
        
            announce = bfr.readLine().split("","");
        
            for(int i = 0; i < num; i++)
            {
                str2 = bfr.readLine().split("","");
                matrix[i]= str2;
            }
            
            for (int i =0; i < num; i++)
            {
                for (int j = 0; j < num; j++)
                {
                    for (int k =0; k < stringCount; k++)
                    {
                        if(matrix[i][j].equals(announce[k]))
                          Check[i][j] += 1;
                    }
                }
            }
            int counter = 0;
            int [] r1 = new int [num];
            int [] c1 = new int [num];
            int [] d1 = new int [2];
            for(int i =0; i < num; i++)
            {
                for (int j = 0; j < num; j++)
                {
                    r1[i] += Check[i][j];
                    c1[i] += Check[j][i];
                    if(i==j)
                    {
                        d1[0]+=Check[i][j];
                        d1[1]+=Check[i][num-1-j];
                    }
                }
            }
            
            for (int i = 0; i < num; i++)
            {
                if(r1[i]==num)
                    counter++;
                if(c1[i]==num)
                    counter++;
                if(i < 2)
                {
                   if(d1[i]==num)
                       counter++;
                }
            }
           
            /**System.out.println(r1[0]);
              *System.out.println(r1[1]);
              *System.out.println(r1[2]);
              *
              *System.out.println(c1[0]);
              *System.out.println(c1[1]);
              *System.out.println(c1[2]);
              *
              *
              *System.out.println(d1[0]);
              *System.out.println(d1[1]); **/
         
            
            System.out.println(counter);
        }
        catch(ArithmeticException e)
        {
            System.out.println(e+"" throwed!"");
        }
       
    }
    
}
