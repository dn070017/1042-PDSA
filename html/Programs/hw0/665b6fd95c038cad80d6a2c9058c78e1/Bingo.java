/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.*;
import java.io.FileReader;
//import java.io.BufferedReader;
import java.util.Scanner;
/*import java.lang.String;
 import java.util.Arrays;*/
//import java.util.*;

/**
 *
 * @author 許志鵬
 */
public class Bingo {

    public static void main(String[] args) throws Exception {

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            is = new FileInputStream(args[0]);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            Scanner sn = new Scanner(br);
            String str = """";
            while (sn.hasNextLine()) {
                str = str + sn.nextLine()+"","";
            }

            //System.out.println(str);
            String[] token = str.split("","");
            /*for(int i=0;i<token.length;i++)
            {
                System.out.println(token[i]);
            }*/
            
             int annoucedNum=Integer.parseInt(token[0]);
             int size=Integer.parseInt(token[1]);
             String[] announced=new String[annoucedNum]; //儲存公布國名用
             String[][] countr=new String[size][size];   //賓果板
             int k=annoucedNum+annoucedNum;                                    //以下配置賓果板
             for(int i=0;i<size;i++)
             {
             for(int j=0;j<size;j++)
             {
             countr[i][j]=token[k];
             k++;
             }
             }
             int[][] score=new int[size][size];      //紀錄賓果配對情況
             for(int i=0;i<size;i++)                 //記分板歸零
             {
             for(int j=0;j<size;j++)
             {
             score[i][j]=0;
             }
             }
             for(int i=annoucedNum;i<annoucedNum+annoucedNum;i++)          //開始劃記計分板
             {
             for(int j=0;j<size;j++)
             {
             for(int l=0;l<size;l++)
             {
             if(countr[j][l].equals(token[i]))
             {
             score[j][l]++;
             }
             }
             }
             }
             int lineNum=0;
             int counter=0;//儲存連線數量
             for(int i=0;i<size;i++)
             {
             for(int j=0;j<size;j++)
             {
             counter=counter+score[i][j];
             }
             if(counter==size)
             {
             lineNum++;
             counter=0;
             }
             else
             {
             counter=0;
             }
             }
             for(int i=0;i<size;i++)
             {
             for(int j=0;j<size;j++)
             {
             counter=counter+score[j][i];
             }
             if(counter==size)
             {
             lineNum++;
             counter=0;
             }
             else
             {
             counter=0;
             }
             }
             int counter1=0;
             for(int i=0;i<size;i++)
             {
             counter1=counter1+score[i][i];
             }
             if(counter1==size)
             {
             lineNum++;
             //   counter1=0;
             }
             else
             {
             //  counter1=0;
             }
             int counter2=0;
             for(int i=0;i<size;i++)
             {
             counter2=counter2+score[size-1-i][i];
             }
             if(counter2==size)
             {
             lineNum++;
             // counter2=0;
             }
             else
             {
             //                counter2=0;
             }
             /*
            
             System.out.println(""此文字檔為:"" + str);
             System.out.println(""已公布:"" + row2[0] + ""以及"" + row2[1] + ""共"" + announcedNum + ""個答案"");
             System.out.println(""陣列大小:"" + matrixLen + ""X"" + matrixLen);*/
            System.out.println(lineNum);
            is.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {//Catch exception if any

            System.err.println(""Error: "" + e.getMessage());

        }

    }

}

