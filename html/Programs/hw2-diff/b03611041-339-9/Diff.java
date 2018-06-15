import java.io.BufferedReader;
import java.io.FileReader;

/**
.
 */
public class LabelCC {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String test,FirstLine[]=br.readLine().split("","");
            int num=Integer.parseInt(FirstLine[0]);
            int targetRow=Integer.parseInt(FirstLine[1]);
            int targetCol=Integer.parseInt(FirstLine[2]);
            boolean matrix[][]=new boolean[num+2][num+2];
            int label[][]=new int[num+2][num+2];
            WeightedQuickUnionUF uf=new WeightedQuickUnionUF(num*num/2+1);


            //init
            for(int i=0;i<num+2;i++){
                for (int j=0;j<num+2;j++){
                    if(i==0||i==num+1 || j==0||j==num+1)
                        matrix[i][j]=false;
                    else
                        matrix[i][j]=true;
                }
            }
            //read file and build matrix
            while((test=br.readLine())!=null) {
                String input[] = test.split("","");
                int row = Integer.parseInt(input[0]);
                int col = Integer.parseInt(input[1]);
                matrix[row][col] = false;
            }
//
//            for(int i=0;i<num+2;i++){
//                for(int j=0;j<num+2;j++){
//                    System.out.printf(""%3d"",matrix[i][j]?1:0);
//                }
//                System.out.printf(""\n"");
//            }
//
//            System.out.printf(""\n"");

            int labelNum=1;
            for(int i=1;i<num+1;i++){
                for(int j=1;j<num+1;j++){
                    if(matrix[i][j]==false)continue;
                    else{
                        if(matrix[i-1][j]==false && matrix[i][j-1]==false){
                            label[i][j]=labelNum;
                            labelNum++;
                        }else{
                            if((label[i-1][j]>label[i][j-1] || label[i-1][j]==0) && label[i][j-1]!=0){
                                label[i][j]=label[i][j-1];
                            }else{
                                label[i][j]=label[i-1][j];
                            }
                        }
                    }
                }
            }

//            for(int i=0;i<num+2;i++){
//                for(int j=0;j<num+2;j++){
//                    {
//                        System.out.printf(""%3d"", uf.find(label[i][j]));
//                    }
//                }
//                System.out.printf(""\n"");
//            }
//            System.out.printf(""\n"");

            for(int i=1;i<num+1;i++){
                for(int j=1;j<num+1;j++){
                    if(matrix[i][j]==false || label[i][j - 1] == 0 || label[i - 1][j] == 0)continue;
                    else {
                        if (label[i][j - 1] > label[i - 1][j]) {
                            uf.union(uf.find(label[i - 1][j]),label[i][j-1]);
                        } else {
                            uf.union( uf.find(label[i][j-1]),label[i -1 ][j]);
                        }
                    }
//                    for(int m=0;m<num+2;m++){
//                        for(int n=0;n<num+2;n++){
//                            {
//                                System.out.printf(""%3d"", uf.find(label[m][n]));
//                            }
//                        }
//                        System.out.printf(""\n"");
//                    }
//                    System.out.printf(""\n"");

                }
            }
//
//            for(int i=0;i<num+2;i++){
//                for(int j=0;j<num+2;j++){
//                    {
//                        System.out.printf(""%3d"", uf.find(label[i][j]));
//                    }
//                }
//                System.out.printf(""\n"");
//            }
            System.out.printf(""%d"", uf.find(label[targetRow][targetCol]));

        }
    }
}

