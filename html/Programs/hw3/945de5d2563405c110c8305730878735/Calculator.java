
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class Calculator {
    
    public Double ans(String e) {
        Double Ans = 0.0;
  
        int StrLength = e.length();
        String[] CharArr;
        CharArr = new String[StrLength];
        double[] NumArr;
        NumArr = new double[StrLength];
        String Char;
        int Oper = 0;
        double NU2 = 0;
        double NU1 = 0; 
        int NU1flag = 0;
        int NU2flag = 0;
        int Operflag = 0;
        
        for(int i = 0; i < StrLength; i++){
            Char = Character.toString(e.charAt(i));
            CharArr[i] = Char;
            if(CharArr[i].equals(new String(""("")) || CharArr[i].equals(new String("")"")) || CharArr[i].equals(new String(""+"")) || CharArr[i].equals(new String(""-"")) || CharArr[i].equals(new String(""*""))|| CharArr[i].equals(new String(""/"")) ){
                //NumArr[i] =  Integer.parseInt(CharArr[i]);
            }
            else{
                NumArr[i] =  Integer.parseInt(CharArr[i]);
            }
        }
                
        for(int i = 0; i < StrLength; i++){
            Char = Character.toString(e.charAt(i));
            CharArr[i] = Char;
            NU2flag = 1;
            Operflag = 1;
            NU1 = 0;
            NU2 = 0;
            Ans = 0.0;
            if(Operflag == 1 && CharArr[i].equals(new String("")""))){
                CharArr[i] = ""0"";
                for(int j = i; j >= 0; j--){
                    if(NU2flag == 1 && (CharArr[j].equals(new String(""+"")) || CharArr[j].equals(new String(""-""))|| CharArr[j].equals(new String(""*""))|| CharArr[j].equals(new String(""/"")))){
                        for(int k = 0; k < i-j-1; k++){
                            NU2 = NU2 + NumArr[i-k-1]*Math.pow(10,k);
                            NumArr[i-k-1] = 0;
                            CharArr[i-k-1] = ""0"";
                        }
                        NU1flag = 1;
                        NU2flag = 0;
                        Oper = j;
                    }
                    if(NU1flag == 1 && CharArr[j].equals(new String(""("")) && CharArr[Oper].equals(new String(""+""))){
                        for(int k = 0; k < Oper-j-1; k++){
                            NU1 = NU1 + NumArr[Oper-k-1]*Math.pow(10,k);
                            NumArr[Oper-k-1] = 0;
                            CharArr[Oper-k-1] = ""0"";
                        }
                        Ans = Ans + NU1 + NU2;
                        NumArr[i] = Ans;
                        CharArr[i] = String.valueOf(Ans);
                        
                        CharArr[j] = ""0"";
                        CharArr[Oper] = ""0"";
                        Operflag = 0;
                        break;
                    }
                    if(NU1flag == 1 && CharArr[j].equals(new String(""("")) && CharArr[Oper].equals(new String(""-""))){
                        for(int k = 0; k < Oper-j-1; k++){
                            NU1 = NU1 + NumArr[Oper-k-1]*Math.pow(10,k);
                            NumArr[Oper-k-1] = 0;
                            CharArr[Oper-k-1] = ""0"";
                        }
                        Ans = Ans + NU1 - NU2;
                        NumArr[i] = Ans;
                        CharArr[i] = String.valueOf(Ans);
                        
                        CharArr[j] = ""0"";
                        CharArr[Oper] = ""0"";
                    }
                    if(NU1flag == 1 && CharArr[j].equals(new String(""("")) && CharArr[Oper].equals(new String(""*""))){
                        for(int k = 0; k < Oper-j-1; k++){
                            NU1 = NU1 + NumArr[Oper-k-1]*Math.pow(10,k);
                            NumArr[Oper-k-1] = 0;
                            CharArr[Oper-k-1] = ""0"";
                        }
                        Ans = Ans + NU1 * NU2;
                        NumArr[i] = Ans;
                        CharArr[i] = String.valueOf(Ans);
                        
                        CharArr[j] = ""0"";
                        CharArr[Oper] = ""0"";
                    }
                    if(NU1flag == 1 && CharArr[j].equals(new String(""("")) && CharArr[Oper].equals(new String(""/""))){
                        for(int k = 0; k < Oper-j-1; k++){
                            NU1 = NU1 + NumArr[Oper-k-1]*Math.pow(10,k);
                            NumArr[Oper-k-1] = 0;
                            CharArr[Oper-k-1] = ""0"";
                        }
                        Ans = Ans + NU1 / NU2;
                        NumArr[i] = Ans;
                        CharArr[i] = String.valueOf(Ans);
                        
                        CharArr[j] = ""0"";
                        CharArr[Oper] = ""0"";
                    }
                }
            }
        }        
      return Ans;
    }   

    public static void main(String[] args) {
//        String eq = ""1+(12+5)"";
//        String eq = ""((1+(12*5))-(3*4))"";
        String eq = ""(((1+(12*5))-(3*4))+(4/5))"";
        Calculator cct = new Calculator();
        double a = cct.ans(eq);
        System.out.println(a); 
    }
}

