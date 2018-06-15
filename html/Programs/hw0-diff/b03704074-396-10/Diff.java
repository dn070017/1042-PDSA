/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package test1;

/**
 *
 * @author US
 */
import java.io.FileReader;
import java.io.BufferedReader;
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //System.out.println(""Hello!World!"");
       try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           
        String[] data = br.readLine().split("","");
       
        int stringCount = Integer.parseInt(data[0]);//有幾個目標國家
        int num = Integer.parseInt(data[1]);//幾乘幾
        
        String[] announce = new String[stringCount];//列出目標國家
        String[][] matrix = new String[num][num];//列出陣列
        String[] convert = new String[num];
        announce=br.readLine().split("","");
        
        
        for(int i=0;i<num;i++)
        {convert=br.readLine().split("","");
            for(int j=0;j< num;j++)
                    matrix[i][j]=convert[j];
        }
         
                int count=0;
                for (int i = 0; i < num; i++)
                {    int littlecount=0;
                    //System.out.println(""i=""+i);
                     for(int j=0;j< num;j++)
                    {//System.out.println(""j=""+j);
                        for (int m = 0; m < stringCount; m++)
                        {
                            if(matrix[i][j].equals(announce[m])) 
                                {
                            littlecount++;
                            break;
                            }
                        }
                    if(littlecount==num)
                        count++;
                    } 
                }
                
                
                for (int i = 0; i < num; i++)
                {   int littlecount=0;
                     for(int j=0;j< num;j++)
                    {
                        for (int m = 0; m < stringCount; m++)
                        {
                            if(matrix[j][i].equals(announce[m]))
                             {
                            littlecount++;
                            break;
                            }
                        } 
                    if(littlecount==num)
                        count++;
                    } 
                }
                int littlecount=0;
                for (int i=0; i < num; i++)
                { 
                    for (int m = 0; m < stringCount; m++)
                        {
                            if(matrix[i][i].equals(announce[m]) )
                            {
                            littlecount++;
                            break;
                            }
                        }
                    if(littlecount==num)
                        count++;
                }
                
                littlecount=0;
                for (int i=0; i < num; i++)
                {   
                    for (int m = 0; m < stringCount; m++)
                        {
                            if(matrix[i][num-i-1].equals(announce[m]) )
                            {
                            littlecount++;
                            break;
                            }
                            
                        }
                    if(littlecount==num)
                        count++;
                }
                System.out.println(count);
                
            
        }
    }
    }





