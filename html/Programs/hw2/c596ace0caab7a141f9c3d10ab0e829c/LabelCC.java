
import java.io.BufferedReader;

public class Bingo {
    
    public static boolean find(String x, String[] anno){
        for (String item : anno) {
            if (x.equals(item))
                return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data = br.readLine().split("","");
            int stringCount = Integer.parseInt(data[0]);
            int num = Integer.parseInt(data[1]);
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];            
            announce = br.readLine().split("","");
            
            for (int i = 0; i < num; i++) {
               String[] temp = br.readLine().split("","");
                for (int j = 0; j < num; j++) {
                    matrix[i][j] = temp[j];                   
                }
            }
            
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if(find(matrix[i][j], announce))
                        matrix[i][j] = ""cross"";
                }
            }
            int lines = 0;
            //check row and colunm
            for (int i = 0; i < num; i++) {
                int temp1 = 0;
                int temp2 = 0;
                for (int j = 0; j < num; j++) {
                    if(matrix[i][j].equals(""cross""))
                        temp1+=1;
                    if(matrix[j][i].equals(""cross""))
                        temp2+=1;
                }
                if(temp1 == num && temp2 == num )
                    lines += 2;
                else if(temp1 == num || temp2 == num)
                    lines += 1;
            }
            //check diagonal
            int temp1 = 0;
            int temp2 = 0;
            for (int i = 0; i < num; i++) {
                if(matrix[i][i].equals(""cross"")){
                    temp1 += 1;
                }
                if(matrix[i][num-1-i].equals(""cross"")){
                    temp2 += 1;
                }
            }
            if(temp1 == num && temp2 == num )
                    lines += 2;
            else if(temp1 == num || temp2 == num)
                    lines += 1;
            
            System.out.printf(""%d"",lines);
        }
    }
}
