/*
.
 * To change this template file, choose Tools | Templates
.
 */
package test1;

/**
 *
 * @author US
 */
import java.util.Scanner;
import java.util.Arrays;
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(""Hello!World!"");
        String first;
        int[]num;
        num=new int[2];
        Scanner sc = new Scanner(System.in);
        System.out.println(""請輸入字串數目與陣列大小(以逗號分開)："");
        first = sc.next();
        String[]FirstSplit = first.split("","");
        for (int i = 0; i < FirstSplit.length; i++)
            num[i] = Integer.parseInt(FirstSplit[i]);
        String[][] matrix;
        matrix=new String[num[1]][num[1]];
        String str1;
        String str2;
        System.out.println(""請輸入目標國家(以逗號分開)："");
        str1 = sc.next();
        String[]AfterSplit1 = str1.split("","");
        for (int i = 0; i < AfterSplit1.length; i++)
            System.out.println(AfterSplit1[i]);
        
        
        
            for (int i = 0; i < num[1]; i++)
            {   System.out.println(""請輸入第""+(i+1)+""排"");
                str2=sc.next();
                String[]AfterSplit2 = str2.split("","");
                
                for(int j=0;j< num[1];j++)
                {
                    matrix[i][j]=AfterSplit2[j];
                    
            
                }
            }
            
            for (int i = 0; i < num[1]; i++)
            {
                for(int j=0;j< num[1];j++)
                {
                    System.out.println(matrix[i][j]);
                }
            
            }
            //workhere
                int count=0;
                for (int i = 0; i < num[1]; i++)
                {
                back:
                {
                    for(int j=0;j< num[1];j++)
                    {
                        for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[i][j]==AfterSplit1[m]) 
                                break;
                            if(m==AfterSplit1.length-1 && matrix[i][j]!=AfterSplit1[m])
                                break back;
                         } 
                    }
                }
                
                count++;
                }
                
                
                for (int i=0; i < num[1]; i++)
                {
                back:
                {
                    for(int j=0;j< num[1];j++)
                    {
                        for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[j][i]==AfterSplit1[m]) 
                                break;
                            if(m==AfterSplit1.length-1 && matrix[j][i]!=AfterSplit1[m])
                                break back;
                         } 
                    }
                }
                
                count++;
                }
                back:
                {
                for (int i=0; i < num[1]; i++)
                {   
                    for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[i][i]==AfterSplit1[m]) 
                                break;
                            if(m==AfterSplit1.length-1 && matrix[i][i]!=AfterSplit1[m])
                                break back;
                        }
                count++;
                
                }
                }
                
                back:
                {
                for (int i=0; i < num[1]; i++)
                {   
                    for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[i][num[1]-i-1]==AfterSplit1[m]) 
                                break;
                            if(m==AfterSplit1.length-1 && matrix[i][num[1]-i-1]!=AfterSplit1[m])
                                break back;
                        }
                count++;
                
                }
                }
                
                System.out.println(""有""+count+""條線"");
                
            
        }
}    


