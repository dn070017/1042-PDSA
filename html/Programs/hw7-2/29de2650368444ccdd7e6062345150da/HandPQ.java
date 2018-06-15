import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {
       
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            String out = br.readLine();
            String out1 = br.readLine();
            String out2 = br.readLine();
            String out3 = br.readLine();
            String out4 = br.readLine();
            System.out.printf(""%s"",out4);
            
        }
      
    }
    
}

