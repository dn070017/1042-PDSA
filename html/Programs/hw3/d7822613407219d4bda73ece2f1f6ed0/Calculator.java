public class Calculator {
    
    public Double ans(String e) {
        
        Double Ans = 0.0;
        String[] Ele = e.split("" "");
        int StrLength = Ele.length;
        double NU1 = 0.0;
        double NU2 = 0.0;
        int Operflag = 0;
        int Operator = 0;
        int Oper = 0;
        int Nxtflag = 0;
        //System.out.println(StrLength);
        //System.out.println(Ele[4]);
        
        for(int i = 0; i < StrLength; i++){
            NU1 = 0.0;
            NU2 = 0.0;
            Operflag = 0;
            Nxtflag = 0;
            //System.out.println(Ans);
            if(Ele[i].equals(new String("")""))){
                if(Ele[i-1].equals(new String(""0""))){
                    NU2 = Ans;
                    //System.out.println(NU2);
                }
                else{
                    NU2 = Double.parseDouble(Ele[i-1]);
                    //System.out.println(NU2);
                }
                Ele[i] = ""0"";
                Ele[i-1] = ""0"";
                for(int j = i; j >= 0; j--){
                    if(Nxtflag == 0 && Operflag == 0 && (Ele[j].equals(new String(""+"")) || Ele[j].equals(new String(""-""))  || Ele[j].equals(new String(""*""))  || Ele[j].equals(new String(""/"")))){
                        Oper = j;
                        Operflag = 1;
                    }
                    if(Operflag == 1 && Ele[j].equals(new String(""(""))){
                        NU1 = Double.parseDouble(Ele[Oper-1]);
                        Ele[j] = ""0"";
                        Ele[Oper-1] = ""0"";
                        if(Ele[Oper].equals(new String(""+""))){
                            Ans = (NU1 + NU2);
                            Ele[Oper] = ""0"";
                            Ele[i] = String.valueOf(Ans);
                            Operflag = 0;
                            Nxtflag = 1;
                        } 
                        if(Ele[Oper].equals(new String(""-""))){
                            Ans = (NU1 - NU2);
                            Ele[Oper] = ""0"";
                            Ele[i] = String.valueOf(Ans);
                            Operflag = 0;
                            Nxtflag = 1;
                        }
                        if(Ele[Oper].equals(new String(""*""))){
                            Ans = (NU1 * NU2);
                            Ele[Oper] = ""0"";
                            Ele[i] = String.valueOf(Ans);
                            Operflag = 0;
                            Nxtflag = 1;
                        } 
                        if(Ele[Oper].equals(new String(""/""))){
                            Ans = (NU1 / NU2);
                            Ele[Oper] = ""0"";
                            Ele[i] = String.valueOf(Ans);
                            Operflag = 0;
                            Nxtflag = 1;
                        } 
                    }
                }
            }
        }
        
        //System.out.println(NU1); 
        //System.out.println(NU2); 
      return Ans;
    }   

    public static void main(String[] args) {
//        String eq = ""( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) )"";
//        String eq = ""( 1 + ( 12 * 5 ) )"";
//        String eq = ""( 12 + 15.28 )"";
//        String eq = ""(19.25*15.2215)"";
//        String eq = ""((1+(12*5))-(3*4))"";
//        String eq = ""(((1+(12*5))-(3*4))+(4/5))"";
//        String eq = ""(((1+(12*5))-(3*4))+(4/5))""; 
        String eq = ""( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        Calculator cct = new Calculator();
        double a = cct.ans(eq);
        System.out.println(a); 
    }
}

