/*
.
 * To change this template file, choose Tools | Templates
.
 */
package bingo;
import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.util.Arrays;
import java.util.*;
/**
 *
 * @author 閮勗?曀?
 */
public class Bingo 
{
    public static void main(String[] args)throws IOException 
    {
        /*try{
            FileInputStream fstream = new FileInputStream(""input.txt"");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
  //Read File Line By Line
            while ((strLine = br.readLine()) != null)  {

                System.out.println (strLine);
            }
            in.close();
        }
        catch (Exception e){//Catch exception if any
   
            System.err.println(""Error: "" + e.getMessage());
  
        }*/
        File Data=new File(""input.txt""); //霈?交?獢?
        int len=(int)Data.length();      //??獢摨?
        FileReader ReadData=new FileReader(Data); 
        char buffer[];
        buffer=new char[len];
        ReadData.read(buffer);
        String str = new String(buffer);
        
        String[] token = str.split(""\n""); //?摮葡
        String[] row1=token[0].split("",""); 
        String[] row2=token[1].split("","");
        String[] row3=token[2].split("","");
        String[] row4=token[3].split("","");
        String[] row5=token[4].split("","");
        int announcedNum = Integer.valueOf(row1[0]);
        int matrixLen = Integer.valueOf(row1[1]);
        int lineCounter=0;

        int [][]matrix=new int[matrixLen][matrixLen];//摰?????摮?憿
        for(int i=0;i<matrixLen;i++)//撠?飛??
        {
            for(int j=0;j<matrixLen;j++)
            {
                matrix[i][j]=0;
            }
        }
        for(int i=0;i<2;i++) //???怨????
        {
            for(int j=0;j<3;j++)
            {
               if(row2[i].equals(row3[j]))
               {
                   matrix[0][j]++;
               }
                    
                
            }
        }
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<3;j++)
            {
               if(row2[i].equals(row4[j]))
               {
                   matrix[1][j]++;
               }
                    
                
            }
        }
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<3;j++)
            {
               if(row2[i].equals(row5[j]))
               {
                   matrix[2][j]++;
               }
                    
                
            }
        }
        if(matrix[0][0]+matrix[0][1]+matrix[0][2]==3)
        {
            lineCounter++;
        }
        if(matrix[1][0]+matrix[1][1]+matrix[1][2]==3)
        {
            lineCounter++;
        }
        if(matrix[2][0]+matrix[2][1]+matrix[2][2]==3)
        {
            lineCounter++;
        }
        if(matrix[0][0]+matrix[1][0]+matrix[2][0]==3)
        {
            lineCounter++;
        }
        if(matrix[0][1]+matrix[1][1]+matrix[2][1]==3)
        {
            lineCounter++;
        }
        if(matrix[0][2]+matrix[1][2]+matrix[2][2]==3)
        {
            lineCounter++;
        }
        if(matrix[0][0]+matrix[1][1]+matrix[2][2]==3)
        {
            lineCounter++;
        }
        if(matrix[2][0]+matrix[1][1]+matrix[0][2]==3)
        {
            lineCounter++;
        }
        System.out.println(""甇斗?摮???""+str);
        System.out.println(""撌脣撣?""+row2[0]+""隞亙?""+row2[1]+""??+announcedNum+""??獢?);
        System.out.println(""???憭批?:""+matrixLen+""X""+matrixLen);
        System.out.println(""蝮賢??+lineCounter+""璇??"");
   
        

 
    }
    
}

