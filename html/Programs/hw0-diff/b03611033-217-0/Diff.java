/*
.
 * To change this template file, choose Tools | Templates
.
 */
package bingo;

import java.io.FileReader;
import java.io.BufferedReader;
//import java.util.Arrays;

/**
 *
 * @author user
 */
public class Bingo {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */    
    public static void main(String[] args) throws Exception 
    {
            // read file from args[0] in Java 7 style
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);
            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            //String str = null;
            // if((str = br.readLine()) != null)
            
            int [] strcount = new int[stringCount];
            int [] strnum = new int[num];
            
            data = br.readLine().split("","");
            for (int i=0;i<stringCount;i++)
            {
                announce[i]=data[i];
                strcount[i]=i;
                //System.out.println(announce[i]);
            }
            
            int[][] aftercom = new int[num][num];
            for(int j=0;j<num;j++)
            {
                strnum[j]=j;
                data = br.readLine().split("","");
                for(int k=0;k<num;k++)
                {
                    matrix[j][k]=data[k];
                    for(int i:strcount)
                    {
                        if(matrix[j][k].equals(announce[i]))
                        {
                            aftercom[j][k]=1;
                            break;
                        }
                    }
                }
            }
            int bingoline=0;
            for(int j:strnum)
            {
                for(int k:strnum)
                {
                    if(aftercom[j][k]!=1)
                    {
                        break;
                    }
                    if(k==num-1)
                    {
                        bingoline++;
                    }
                }  
            }
            for(int j:strnum)
            {
                for(int k:strnum)
                {
                    if(aftercom[k][j]!=1)
                    {
                        break;
                    }
                    if(k==num-1)
                    {
                        bingoline++;
                    }
                }
            }
            
            for(int j:strnum)
            {
                if(aftercom[j][j]!=1)
                {
                    break;
                }
                if(j==num-1)
                {
                    bingoline++;
                }
            }
            for(int j:strnum)
            {
                if(aftercom[j][num-1-j]!=1)
                {
                    break;
                }
                if(j==num-1)
                {
                    bingoline++;
                }
            }
            System.out.println(bingoline);
        }
    }
}


