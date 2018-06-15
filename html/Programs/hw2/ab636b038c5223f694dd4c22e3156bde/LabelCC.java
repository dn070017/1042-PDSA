
.
 * To change this template file, choose Tools | Templates
.
 */
//package test1;

/**
 *
 * @author US
 */
import java.io.*; 
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //System.out.println(""Hello!World!"");
       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
         
        String[] first;
        int[]num;
        num=new int[2];
        ///Scanner sc = new Scanner(System.in);
        //System.out.println(""請輸入字串數目與陣列大小(以逗號分開)："");
        int flag=0;
        while(flag==0)
        {flag=1;
        first = br.readLine().split("","");
        if(first.length>2)
            flag=0;
        for (int i = 0; i < first.length; i++)
        {   
            num[i] = Integer.parseInt(first[i]);
            if(num[i]<2)
                flag=0;
        }
        }
        
        String[][] matrix;
        matrix=new String[num[1]][num[1]];
        String[] str1 = null;
        String[] str2 = null;
        //System.out.println(""請輸入目標國家(以逗號分開)："");
         flag = 0;
         while(flag==0)
         {   flag=1;
            str1 = br.readLine().split("","");
            if(str1.length!=num[0])
                flag=0;
            for(int i=0;i<str1.length;i++)
            {    
                for(int j=i+1;j<str1.length;j++)
                {if(str1[i].equals (str1[j]))
                    flag=0;
                }
            }
        }
         
            for (int i = 0; i < num[1]; i++)
            {//System.out.println(""請輸入第""+(i+1)+""排"");
            flag=0;
                while(flag==0)
                {flag=1;
                str2=br.readLine().split("","");
                 if(str2.length!=num[1])
                     flag=0;
                }
                for(int j=0;j< num[1];j++)
                {
                    matrix[i][j]=str2[j];
                }
            }
         
            
            /*for (int i = 0; i < num[1]; i++)
            {
                for(int j=0;j< num[1];j++)
                {
                    System.out.println(matrix[i][j]);
                }
            
            }*/
            
                int count=0;
                for (int i = 0; i < num[1]; i++)
                {    int littlecount=0;
                    //System.out.println(""i=""+i);
                     for(int j=0;j< num[1];j++)
                    {//System.out.println(""j=""+j);
                        for (int m = 0; m < str1.length; m++)
                        {
                            if(matrix[i][j].equals(str1[m])) 
                            {
                            //System.out.println(""yes"");
                            littlecount++;
                            }
                        } 
                    //System.out.println(""j*2=""+j);
                    if(littlecount==num[1])
                        count++;
                    } 
                }
                
                
                for (int i = 0; i < num[1]; i++)
                {   int littlecount=0; 
                    //System.out.println(""i=""+i);
                     for(int j=0;j< num[1];j++)
                    {//System.out.println(""j=""+j);
                        for (int m = 0; m < str1.length; m++)
                        {
                            if(matrix[j][i].equals(str1[m])) 
                            {//System.out.println(""yes"");
                             littlecount++;
                            }
                        } 
                    //System.out.println(""j*2=""+j);
                    if(littlecount==num[1])
                        count++;
                    } 
                }
                int littlecount=0;
                for (int i=0; i < num[1]; i++)
                {   
                    //System.out.println(""i=""+i);
                    for (int m = 0; m < str1.length; m++)
                        {
                            if(matrix[i][i].equals(str1[m]) )
                            {//.out.println(""yes"");
                            littlecount++;
                            }
                        }
                    if(littlecount==num[1])
                        count++;
                }
                
                littlecount=0;
                for (int i=0; i < num[1]; i++)
                {   //System.out.println(""i=""+i);
                    for (int m = 0; m < str1.length; m++)
                        {
                            if(matrix[i][num[1]-i-1].equals(str1[m]) )
                            {//System.out.println(""yes"");  
                            littlecount++;
                            } 
                        }
                    if(littlecount==num[1])
                        count++;
                }
                
                
                System.out.println(count);
                
            
        }
    }
}




