/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Stack;
/**
 *
 * @author
 */
public class Calculator {
    public static void main(String[] args) throws Exception {
        Calculator cct = new Calculator();
        System.out.print(cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) ""));
    }
    public Double ans (String e) {
.
        //Calculator cct = new Calculator();
        String[] data = e.split("" "");
        int Level=0;
        int MaxCal = 0;
        int[] index = new int[data.length];
        Stack LevelStack = new Stack();
        for (int i=0;i<data.length;i++){
            LevelStack.push(data[i]);
//            System.out.println(LevelStack.get(i));
            if (data[i].equals(""("")){
                Level++;
                index[i]=Level;
            }
            else if (data[i].equals("")"")){
                Level--;
                index[i]=Level;
            }
            else{
                index[i]=Level;
            }
        }
        // Find maximum 
        MaxCal = index[0];
        for(int i=1;i<index.length;i++){
            if(index[i]>MaxCal){
                MaxCal = index[i];
            }
            else if (index[i]<MaxCal){
                MaxCal = MaxCal;
            }
        }
        String Count ="""";
//        String[] Temp = new String[data.length];
        double TempValue=0.0;
        for(int i=2;i<data.length;i++){
            if(data[i-2].equals(""("") && data[i].equals("")"")){
                
            }
        }
//        for(int j=0;j<MaxCal;j++){
//            for(int i=0;i<index.length;i++){
//                if (index[i]==(MaxCal-j)){
//                    if(data[i].equals("")"")){
//                        data[i]=String.valueOf(TempValue);
//                    }
//                    Count+=data[i]+"" "";
////                    System.out.print(data[i]);                
//                }
//            }
//            String[] Temp = Count.split("" ""); 
//            if(Temp[2].equals(""+"")){
//                for(int i=3;i<Temp.length;i+=2){
//                   TempValue = Double.parseDouble(Temp[i-2])+Double.parseDouble(Temp[i]);
//                }           
//            }
//            else if(Temp[2].equals(""-"")){
//                for(int i=3;i<Temp.length;i+=2){
//                    TempValue = Double.parseDouble(Temp[i-2])-Double.parseDouble(Temp[i]);   
//                }
//            }
//            else if(Temp[2].equals(""*"")){
//                for(int i=3;i<Temp.length;i+=2){
//                   TempValue = Double.parseDouble(Temp[i-2])*Double.parseDouble(Temp[i]);
//                }           
//            }
//            else if(Temp[2].equals(""/"")){
//                for(int i=3;i<Temp.length;i+=2){
//                   TempValue = Double.parseDouble(Temp[i-2])/Double.parseDouble(Temp[i]);
//                }           
//            }
//        }
        System.out.print(TempValue);
        return null;
    }
}

