
import java.io.BufferedReader;
import java.io.IOException;
/**
 *
 * @author DANNY
 */
public class Bingo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String buf[] = br.readLine().split("","");
            int line = 0;
            int line1 = 0;
            int line2 = 0;
            int line3 = 0;
            int line4 = 0;
            int num = Integer.valueOf(buf[0]);
            int size = Integer.valueOf(buf[1]);
            String[][] data;
            data = new String[size][size];   
            String buf2[] = br.readLine().split("","");
            for(int i=0;i<size;i++){
                String buf3[] = br.readLine().split("","");
                for(int j=0;j<size;j++){
                    data[i][j] = buf3[j];               
                }
            }
            int   numbeango = 2*(size+1);
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    for(int k=0;k<num;k++){
                        if(data[i][j].equals(buf2[k])){
                            data[i][j] = buf2[0];
                        }  
                    }
                }
            }
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(data[i][0].equals(data[i][j])){
                        line1++;
                    }
                    if(data[0][i].equals(data[j][i])){
                        line2++;
                    }
                }
                if(line1== size){
                        line++;
                    }
                if(line2== size){
                        line++;
                    }
                
                
                line1=0;
                line2=0;
            }
            for(int j=0;j<size;j++){
                    if(data[0][0].equals(data[j][j])){
                                line3++;
                            }
                    if(data[0][size-1].equals(data[j][size-1-j])){
                        line4++;
                    }
            }
                if(line3== size){
                        line++;
                    }
                if(line4== size){
                        line++;
                    }
            System.out.printf(""%d"",line);
        }
    }
    
}
