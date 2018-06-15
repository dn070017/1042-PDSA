
package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author steven
 */
public class Calculator {

    public Calculator(){

    
}
    public Double ans(String e){
        String[] strings=e.split("" "");
        return calculate(strings);
    }
    public Double calculate(String[] strings){
                
                int indexF=-1;
                int indexB=4;

                for (int i=0;i<strings.length;i++){
                    
                    if (strings[i].equals(""(""))
                        indexF=i;
                    if (strings[i].equals("")"")){
                        indexB=i;           
                        break;
                    }
                }
                
                // multiplication and devision first
                for (int j=indexF+2;j<indexB-1;j=j+2){
                    double a=0;
                   // System.out.println(j);
                    if (isMD(strings[j])>2){
                        if (isMD(strings[j])==3){
                            //System.out.println(""*"");
                            //System.out.println(strings.length);
                            a=Double.parseDouble(strings[j-1])* Double.parseDouble(strings[j+1]);}
                        if (isMD(strings[j])==4){
                            //System.out.println(""/"");
                            //System.out.println(strings.length);
                            a=Double.parseDouble(strings[j-1])/ Double.parseDouble(strings[j+1]);}
                        
                            
                        
                        if (j==2){
                            //System.out.println(""strings==1"");
                            return a;}
                        else{
                            newArray(strings,a,j-2,5);
                            return calculate(strings);
                        }
                    }    
                }
                // Then addition and subtraction
                for (int j=indexF+2;j<indexB-1;j=j+2){
                    double a=0;
                    if (isAS(strings[j])==1){
                           // System.out.println(""+"");
                            //System.out.println(strings.length);
                            a=Double.parseDouble(strings[j-1])+ Double.parseDouble(strings[j+1]);
                    }else if (isAS(strings[j])==2){
                            //System.out.println(""-"");
                            //System.out.println(strings.length);
                            a=Double.parseDouble(strings[j-1])- Double.parseDouble(strings[j+1]);
                    }
                       
                        
                            
                        if (j==2){
                            //System.out.println(""strings==1"");
                            return a;
                        }
                            
                        else{
                            newArray(strings,a,j-2,5);
                            return calculate(strings);
                        }
                            
                 }
                return -1.0;
    }
    public void newArray(String[] sArray,double append,int del,int len){
        sArray[del]=String.valueOf(append);
        System.arraycopy(sArray,del+len,sArray,del+1,sArray.length-len-del);
    }
    public int isAS(String s){
        int type=0;
        if (s.equals(""+""))
            type =1;
        else if (s.equals(""-""))
            type=2;
        return type;
    }
    public int isMD(String s){
        int type=0;
        if (s.equals(""*""))
            type =3;
        else if (s.equals(""/""))
            type=4;
        return type;
    }


    public static void main(String[] args) {
        String e=""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        Calculator cct=new Calculator();
        System.out.println(cct.ans(e));
    }
    
}

