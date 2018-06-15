import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {
    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){            
            // read a line and split by ','
            String[] data = br.readLine().split("","");            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);
            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);
            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            // printf in Java (you should comment out or delete this in your final submission)
//            System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            announce = br.readLine().split("","");
            announce[stringCount-1] = announce[stringCount-1].trim();
            //store matrix parameters
            String[] Temp = {"""","""",""""};
            for (int i=0;i<num;i++){
                Temp = br.readLine().split("","");
                for(int j=0;j<num;j++){
                    matrix[i][j] = Temp[j].trim();
                }                
            }
            //count the bingo line
            int BingoNumber = 0;
            //check bingo or not
            int BingoTemp = 0;
            //bingo meet the announce
            boolean BingoCheck = false;
            //check column bingo
            for (int i = 0;i<num;i++){
                for (int j = 0;j<num;j++){
                    for (int k=0;k<stringCount;k++){
                        if ((matrix[i][j]).equals((announce[k].trim()))){
                            BingoCheck = true;
                        }
                    }
                    if (BingoCheck){
                        BingoTemp++;
                        BingoCheck=false;
                    }
                    else{BingoCheck=false;
                    }
                }
                if(BingoTemp==num){BingoNumber +=1 ;BingoTemp = 0;}
                else {BingoTemp = 0;
                }
            }
            //check row bingo            
            for (int i = 0;i<num;i++){
                for (int j = 0;j<num;j++){
                    for (int k=0;k<stringCount;k++){
                        if ((matrix[j][i]).equals((announce[k].trim()))){
                            BingoCheck = true;
                        }
                    }
                    if (BingoCheck){
                        BingoTemp++;
                        BingoCheck=false;
                    }
                    else{BingoCheck=false;
                    }
                }
                if(BingoTemp==num){BingoNumber +=1 ;BingoTemp = 0;}
                else {BingoTemp = 0;
                }
            }
            //check Main diagonal
            for (int i=0;i<num;i++){
                for(int j=0;j<stringCount;j++){
                    if ((matrix[i][i]).equals(announce[j].trim())){
                        BingoCheck = true;
                    }
                }
                if (BingoCheck){
                    BingoTemp++;
                    BingoCheck = false;
                }
                else{BingoCheck = false;
                }
            }
            if(BingoTemp==num){BingoNumber +=1 ;BingoTemp = 0;}
            else {BingoTemp = 0;
            }
            //check Secondary diagonal
            for (int i=0;i<num;i++){
                for(int j=0;j<stringCount;j++){
                    if ((matrix[i][num-i-1]).equals(announce[j].trim())){
                        BingoCheck = true;
                    }
                }
                if (BingoCheck){
                    BingoTemp++;
                    BingoCheck = false;
                }
                else{BingoCheck = false;
                }
            }
            if(BingoTemp==num){BingoNumber +=1 ;BingoTemp = 0;
            }
            else {BingoTemp = 0;
            }
            System.out.printf(""%d\n"",BingoNumber);
        }
    }
}
