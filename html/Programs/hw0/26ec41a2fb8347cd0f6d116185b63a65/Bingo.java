import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    @SuppressWarnings(""empty-statement"")
    public static void main(String[] args) throws Exception {
 // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            //讀要殺的國家(第二行)存入killcountry
            String[] killcountry;
            killcountry = br.readLine().split("","");
            //要殺的國家數(字元)存成整數
            int stringCount = Integer.parseInt(data[0]);
            //幾階的國家(字元)存成整數      
            int num = Integer.parseInt(data[1]);
            
            //做出存賓果的二維陣列(ccr為currentcountry的縮寫)
            String[][] ccr=new String[num][num];
            int i;
            for (i=0; i<num;i++){
            ccr[i] = br.readLine().split("","");
            }

            //死去的國家都存0
            for (i=0; i<num;i++){
                for(int j=0;j<num;j++){
                    for(int k=0;k<stringCount;k++){                        
                         if(ccr[i][j].equals(killcountry[k]))
                             ccr[i][j]=""0"";
                         }                 
                  }
            }
            
            //開始檢查，設計數器
            int count;
            count = 0;
            
            //檢查橫排        
            for(i=0;i<num;i++){ 
                    for(int j=0;j<num;j++){
                        if(!ccr[i][j].equals(""0"")){
                                break;
                            }
                        else if(j==num-1)
                            count++;
                     }
                }            
            
            //檢查直排            
            for(int j=0;j<num;j++){
                for(i=0;i<num;i++){
                    if(!ccr[i][j].equals(""0"")){
                        break;
                    }
                    else if(i==num-1)
                        count++;
                }                               
             }
            
             //檢查對角線 1
              for(i=0;i<num;i++){
                  if(!ccr[i][i].equals(""0"")){
                      break;
                  }
                  else if(i==num-1)
                      count++;                  
              }
              
              //檢查對角線 2
              for(i=num-1;i>0;i--){
                  if(!ccr[i][i].equals(""0"")){
                      break;
                  }
                  else if(i==0)
                      count++;                  
              }
              
              System.out.println(count);
        }          
    }                      
}
