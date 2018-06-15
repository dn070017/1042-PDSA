import java.io.FileReader;
import java.io.BufferedReader;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Calculator cct = new Calculator();
        cct.ans(""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "");
    }
    public Double ans (String e) {
.
//        Calculator cct = new Calculator();
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
//                System.out.print(""hi"");
            }
            else if (data[i].equals("")"")){
                Level--;
                index[i]=Level;
            }
            else{
                index[i]=Level;
            }
//            System.out.print(index[i]);
//            System.out.println(LevelStack.get(i));
            
//            System.out.println(data[i]+i);
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
        int Zero=0;
        for(int k=0;k<MaxCal;k++){
            for(int i=4;i<data.length;i++){
                if(data[i-4].equals(""("") && data[i].equals("")"")){
                    if(data[i-2].equals(""+"")){
                        TempValue = Double.parseDouble(data[i-3])+Double.parseDouble(data[i-1]);
                    }
                    else if(data[i-2].equals(""-"")){
                        TempValue = Double.parseDouble(data[i-3])-Double.parseDouble(data[i-1]);
                    }
                    else if(data[i-2].equals(""*"")){
                        TempValue = Double.parseDouble(data[i-3])*Double.parseDouble(data[i-1]);
                    }
                    else if(data[i-2].equals(""/"")){
                        TempValue = Double.parseDouble(data[i-3])/Double.parseDouble(data[i-1]);
                    }
                    else{
                        System.out.print(""Error !"");
                    }
                    data[i-4] = String.valueOf(TempValue);
    //                System.out.println(data[i-4]);
                    Zero++;
                    for(int j=0;j<4;j++){
                        data[i-3+j]=""0"";
                    }
                }            
            }
            for(int j=0;j<Zero;j++){
                for(int i=4;i<data.length;i++){
                    if (((data[i-4].equals(""0"") && data[i-3].equals(""0"")) && data[i-2].equals(""0"")) && data[i-1].equals(""0"")){
                        data[i-4] = data[i];
                        data[i] = ""0"";
                    }
                }
            }
        }
        
        System.out.print(data[0]);
        
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
//        System.out.print(TempValue);
    return Double.parseDouble(data[0]);
    }
}

