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
        int num1, num2;
        Scanner sc = new Scanner(System.in);
        System.out.println(""請輸入字串數目與陣列大小(不一定以逗號分開)："");
        num1 = sc.nextInt();
        num2 = sc.nextInt();
        System.out.println(""字串數目為："" + num1 );
        System.out.println(""陣列大小為："" + num2 +""乘"" + num2  );
        
        String[][] matrix;
        matrix=new String[num2][num2];
        String str1;
        String str2;
        System.out.println(""請輸入目標國家(以逗號分開)："");
        str1 = sc.next();
        String[]AfterSplit1 = str1.split("","");
        for (int i = 0; i < AfterSplit1.length; i++)
            System.out.println(AfterSplit1[i]);
        
        
        
            for (int i = 0; i < num2; i++)
            {   System.out.println(""請輸入第""+(i+1)+""排"");
                str2=sc.next();
                String[]AfterSplit2 = str2.split("","");
                
                for(int j=0;j< num2;j++)
                {
                    matrix[i][j]=AfterSplit2[j];
                    
            
                }
            }
            
            for (int i = 0; i < num2; i++)
            {
                for(int j=0;j< num2;j++)
                {
                    System.out.println(matrix[i][j]);
                }
            
            }
            
                int count=0;
                for (int i = 0; i < num2; i++)
                {
                back:
                {
                    for(int j=0;j< num2;j++)
                    {
                        for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[i][j]==AfterSplit1[m]) 
                                continue;
                            if(m==AfterSplit1.length && matrix[i][j]!=AfterSplit1[m])
                                break back;
                         } 
                    }
                }
                
                count++;
                }
                
                
                for (int i=0; i < num2; i++)
                {
                back:
                {
                    for(int j=0;j< num2;j++)
                    {
                        for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[j][i]==AfterSplit1[m]) 
                                continue;
                            if(m==AfterSplit1.length && matrix[j][i]!=AfterSplit1[m])
                                break back;
                         } 
                    }
                }
                
                count++;
                }
                back:
                {
                for (int i=0; i < num2; i++)
                {   
                    for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[i][i]==AfterSplit1[m]) 
                                continue;
                            if(m==AfterSplit1.length && matrix[i][i]!=AfterSplit1[m])
                                break back;
                        }
                count++;
                
                }
                }
                
                back:
                {
                for (int i=0; i < num2; i++)
                {   
                    for (int m = 0; m < AfterSplit1.length; m++)
                        {
                            if(matrix[i][num2-i-1]==AfterSplit1[m]) 
                                continue;
                            if(m==AfterSplit1.length && matrix[i][num2-i-1]!=AfterSplit1[m])
                                break back;
                        }
                count++;
                
                }
                }
                
                System.out.println(""有""+count+""條線"");
                
            
        }
}    

