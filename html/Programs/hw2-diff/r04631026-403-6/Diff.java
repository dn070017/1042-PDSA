import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {
    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] Initial = br.readLine().split("","");
            //Set the matrix size
            int Num = Integer.parseInt(Initial[0]);
            //Target point
            int[][] matrix = new int[Num][Num];
            int AreaNum = 1;
            for (int i=0;i<Num;i++){
                for (int j=0;j<Num;j++){
                    matrix[i][j] = 1;
                }
            }
            while (br.ready()){
                String[] Position = br.readLine().split("","");
                matrix[Integer.parseInt(Position[0])-1][Integer.parseInt(Position[1])-1]=0;
            }
            //Set first row
            for (int i=0;i<Num;i++){
                if (matrix[0][i]==0){
                    AreaNum++;
//                    continue;
                }
                else {
                    matrix[0][i]= AreaNum;
                }
            }
            boolean CheckStat = false;
            for (int i=1;i<Num;i++){
                for (int j=0;j<Num;j++){
                    if (matrix[i][j]==0){continue;}
                    //Set first column
                    if (j==0){
                        if (matrix[i-1][j]!=0){
                            matrix[i][j] = matrix[i-1][j];
                            CheckStat = false;
                        }
                        else{
                            AreaNum++;
                            matrix[i][j]=AreaNum;
                            CheckStat = true;
                        }
                    }
                    else{
                        //Check left
                        if (matrix[i][j-1]==0 && matrix[i-1][j]==0){
                            AreaNum++;
                            matrix[i][j]=AreaNum;
                        }
                        else if(matrix[i][j-1]!=0 && matrix[i-1][j]!=0){
                            //Top is smaller
                            if (matrix[i][j-1]>matrix[i-1][j]){
                                matrix[i][j]=matrix[i-1][j];
                            }
                            //Left is smaller
                            else{
                                matrix[i][j]=matrix[i][j-1];
                            }
                        }
                        else if(matrix[i][j-1]==0 && matrix[i-1][j]!=0){
                            matrix[i][j]=matrix[i-1][j];                           
                        }
                        else if(matrix[i][j-1]!=0 && matrix[i-1][j]==0){
                            matrix[i][j]=matrix[i][j-1];
                        }
                        else{
                            if (CheckStat){
                                AreaNum++;
                                matrix[i][j]=AreaNum;
                                CheckStat = true;
                            }
                            else{
                                matrix[i][j]=AreaNum;
                            }
                        }
                    }
                }
            }           
            //Print all value in matrix
            for (int test=0;test<4;test++){
                for (int i=1;i<Num;i++){
                    for (int j=1;j<Num;j++){
                        if (matrix[i-1][j]!=0 && matrix[i][j]!=0){                        
                            if (matrix[i-1][j]!=matrix[i][j] && matrix[i-1][j]>matrix[i][j]){
                                for (int a=0;a<Num;a++){
                                    for (int b=0;b<Num;b++){
                                        if (matrix[a][b]==matrix[i-1][j]){
                                            matrix[a][b]=matrix[i][j];
                                        }                                   
                                    }
                                }
                            }
                        }
                        if (matrix[i][j-1]!=0 && matrix[i][j]!=0){
                            if (matrix[i][j-1]!=matrix[i][j] && matrix[i][j-1]>matrix[i][j]){
                                for (int a=0;a<Num;a++){
                                    for (int b=0;b<Num;b++){
                                        if (matrix[a][b]==matrix[i][j-1]){
                                            matrix[a][b]=matrix[i][j];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(matrix[Integer.parseInt(Initial[1])-1][Integer.parseInt(Initial[2])-1]);
        }        
    }
}
