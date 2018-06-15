
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author wayne17
 */
public class Calculator {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        
          
    }
    public Double ans (String e) {
.
//        System.out.printf(e);
        String[] input = e.split("" "");
        int size = input.length;
//        System.out.printf(e.replace(""("", """").replace("")"","""").replaceAll("" "",""""));
        String[] num = e.replace(""("", """").replace("")"","""").replaceAll("" "","""").split(""\\D+"");
        String[] operator = e.replace(""("", """").replace("")"","""").replaceFirst(""\\d+"","""").replaceAll("" "","""").split(""\\d+"");
        boolean[] num_used = new boolean[num.length];
        int[] layer = new int[operator.length];
        boolean[] op_used = new boolean[operator.length];
        int layer_num = 0;
        int position = 0;
        int Max_layer = 0;
        String[] remove_num = e.replaceAll(""\\d+"","""").split("" "");
        for (String unit:remove_num){
            if (unit.equals(""("")){
                layer_num++;
                if (Max_layer < layer_num){
                    Max_layer = layer_num;
                }
            }
            else if (unit.equals("")"")){
                layer_num--;
            }
            else if (unit.equals(""*"") || unit.equals(""/"") || unit.equals(""+"") || unit.equals(""-"")) {
                layer[position] = layer_num;
//                System.out.print(layer[position]);
                position++;
            }
//            System.out.printf(""[%s,%d]"",unit,layer_num);
        }
//        System.out.print(operator.length);
        
        double tmp =0;
        //from max layer to min layer
        for (int lay = Max_layer; lay>=0;lay--){
            // x,/
            for (int op =0; op <operator.length;op++ ){
                if (layer[op] == lay && op_used[op]==false){
                    if (operator[op].equals(""*"") || operator[op].equals(""/"")){
                        for (int search=op+1;search<num.length;search++){
                            if(num_used[search] ==false){
                                if(operator[op].equals(""*"") ){
                                    num[search] = String.valueOf(Double.parseDouble(num[op])*Double.parseDouble(num[search]));
                                    num_used[op]=true;
                                    break;
                                }
                                else if (operator[op].equals(""/"")){
                                    num[search] = String.valueOf(Double.parseDouble(num[op])/Double.parseDouble(num[search]));
                                    num_used[op]=true;
                                    break;
                                }
                            }
                        }
                        op_used[op]=true;
                    }
                }
            }
            //+,-
            for (int op =0; op <operator.length;op++ ){
                if (layer[op] == lay && op_used[op]==false){
                    if (operator[op].equals(""+"") || operator[op].equals(""-"")){
                        for (int search=op+1;search<num.length;search++){
                            if(num_used[search] ==false){
                                if(operator[op].equals(""+"") ){
                                    num[search] = String.valueOf(Double.parseDouble(num[op])+Double.parseDouble(num[search]));
                                    num_used[op]=true;
                                    break;
                                }
                                else if (operator[op].equals(""-"")){
                                    num[search] = String.valueOf(Double.parseDouble(num[op])-Double.parseDouble(num[search]));
                                    num_used[op]=true;
                                    break;
                                }
                            }
                        }
                        op_used[op]=true;
                    }
                }
            }
        }
//        System.out.printf(num[num.length-1]);
//        System.out.print(num.length);
//        for (int i:layer){
//        System.out.printf(""%d,"",Integer.parseInt(i));
//            System.out.printf(""%d,"",i);
//        }
        return Double.parseDouble(num[num.length-1]);
    }

}


back return to list
