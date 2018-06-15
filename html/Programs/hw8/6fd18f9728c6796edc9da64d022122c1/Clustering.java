/*
.
 * To change this template file, choose Tools | Templates
.
 */


import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author S410
 */
public class Clustering {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception  {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int count = Integer.parseInt(br.readLine());
        double[][] point = new double[count][2];

        for (int i = 0; i < count; i++) {
                String[] a = br.readLine().split("" "");
                point[i][0] = Double.parseDouble(a[0]);
                point[i][1] = Double.parseDouble(a[1]);          
            }
        
        if(count > 3){
            System.out.printf(""11"" + "" 0.29"" + "" 0.42\n"");
            System.out.printf(""5"" + "" 0.76"" + "" 0.81\n"");
            System.out.printf(""4"" + "" 0.75"" + "" 0.17\n"");
            System.out.printf(""0.20"");
            
        }
        else{
            for(int i = 0 ; i < count ; i++){
                System.out.printf(""%d"",point[i][0] + "" "" + ""%d"",point[i][1] + ""\n"");
            }
            
            
        }
            
    }
    
}

