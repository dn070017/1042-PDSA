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
 * @author 許志鵬
 */
public class Bingo {

    public static void main(String[] args) throws IOException {
        try {
            File Data = new File(""input.txt""); //讀入檔案
            int len = (int) Data.length();      //抓檔案長度
            FileReader ReadData = new FileReader(Data);
            char buffer[];
            buffer = new char[len];
            ReadData.read(buffer);
            String str = new String(buffer);
            
            String[] token = str.split(""\n""); //分割字串
            int tokenLen=token.length;
            String matr="""";
            for(int i=0;i<tokenLen;i++)
            {
                matr=matr+token[i]+"","";
                
            }
            String[] ans=matr.split("","");
            
            int annoucedNum=Integer.valueOf(ans[0]);
            int size=Integer.valueOf(ans[1]);
            String[] announced=new String[annoucedNum]; //儲存公布國名用
            String[][] countr=new String[size][size];   //賓果板
            int k=2+annoucedNum;                                    //以下配置賓果板
            for(int i=0;i<size;i++)
            {
                for(int j=0;j<size;j++)
                {
                    countr[i][j]=ans[k];
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
                        if(countr[j][l].equals(ans[i]))
                        {
                            score[j][l]++;
                        }
                    }
                }
            }
            int lineNum=0;                 //儲存連線數量
            if(score[0][0]+score[0][1]+score[0][2]==3)
            {
                lineNum++;
            }
            if(score[1][0]+score[1][1]+score[1][2]==3)
            {
                lineNum++;
            }
            if(score[2][0]+score[2][1]+score[2][2]==3)
            {
                lineNum++;
            }
            if(score[0][0]+score[1][0]+score[2][0]==3)
            {
                lineNum++;
            }
            if(score[0][1]+score[1][1]+score[2][1]==3)
            {
                lineNum++;
            }
            if(score[0][2]+score[1][2]+score[2][2]==3)
            {
                lineNum++;
            }
            if(score[0][0]+score[1][1]+score[2][2]==3)
            {
                lineNum++;
            }
            if(score[0][2]+score[1][1]+score[2][0]==3)
            {
                lineNum++;
            }
            /*
            
            System.out.println(""此文字檔為:"" + str);
            System.out.println(""已公布:"" + row2[0] + ""以及"" + row2[1] + ""共"" + announcedNum + ""個答案"");
            System.out.println(""陣列大小:"" + matrixLen + ""X"" + matrixLen);*/
            System.out.println(""總共有"" + lineNum + ""條連線"");
        }
        catch (Exception e) {//Catch exception if any

            System.err.println(""Error: "" + e.getMessage());

        }
        
    }

}

