
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author philip
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File file = new File(args[0]);
        try{
            Scanner File_in = new Scanner(file);
            String line_1 = File_in.nextLine();
            String[] Nums = line_1.split("","");
            
            int AnsNum = Integer.parseInt(Nums[0]);
            int ArrNum = Integer.parseInt(Nums[1]);
            int LineIndex=0;
            int[][] Mat = new int[ArrNum][ArrNum];
            int sum = 0;
            int bingoline = 0;
            
            String line_2 = File_in.nextLine();
            String[] Ans = line_2.split("","");
            
            while (File_in.hasNextLine())
            {
                
                String line_3 = File_in.nextLine();
                String[] mypart = line_3.split("","");
                
                for (int i = 0; i < ArrNum; i++) {
                    //System.out.println(part_C1[i]);
                    for(int j = 0; j < AnsNum; j++){
                        
                        if(mypart[i].equalsIgnoreCase(Ans[j])){
                            //System.out.println(""1"");
                            Mat[LineIndex][i] = 1;
                        }
                        
                    }
                }
                
                

                /*for (int i = 0; i < ArrNum; i++){
                    for (int j = 0; j < LineIndex; j++){
                        sum = sum + Mat[LineIndex][i];
                        if(sum == ArrNum){
                            System.out.println(""ininder"");
                        }
                    }
                }*/
                
         
                /*for (int i = 0; i < ArrNum; i++) {
                    //System.out.println(Mat[LineIndex][i]);
                }*/
                
                LineIndex++;
            }
            
            
            for (int i = 0; i < ArrNum; i++){
                sum = 0;
                for (int j = 0; j < ArrNum; j++){
                    sum = sum + Mat[j][i];
                    if(sum == ArrNum){
                        //System.out.println(""Bingo"");
                        bingoline = bingoline+1;
                    }
                }
            }
            
            for (int i = 0; i < ArrNum; i++){
                sum = 0;
                for (int j = 0; j < ArrNum; j++){
                    sum = sum + Mat[i][j];
                    if(sum == ArrNum){
                        //System.out.println(""Bingo1"");
                        bingoline = bingoline+1;
                    }
                }
            }
            
            for (int i = 0; i < 1; i++){
                sum = 0;
                for (int j = 0; j < ArrNum; j++){
                    sum = sum + Mat[i+j][i+j];
                    if(sum == ArrNum){
                        //System.out.println(""Bingo2"");
                        bingoline = bingoline+1;
                    }
                }
            }
            for (int i = ArrNum-1; i > ArrNum-2; i--){
                sum = 0;
                for (int j = 0; j < ArrNum; j++){
                    sum = sum + Mat[i-j][j];
                    if(sum == ArrNum){
                        //System.out.println(""Bingo3"");
                        bingoline = bingoline+1;
                    }
                }
            }
            System.out.println(bingoline);
            
        }
        catch(IOException e){
            System.out.println(""error!""); 
        }
        // TODO code application logic here
    }
    
}

