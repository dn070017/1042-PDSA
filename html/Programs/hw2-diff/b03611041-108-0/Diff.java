import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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
//            int label[][]=new int[num+2][num+2],
            int ufNum=num*num+(num*num+1)/2+1;
            System.out.printf(""%d\n"",ufNum);
            WeightedQuickUnionUF uf=new WeightedQuickUnionUF(ufNum+1);

            //init
            for(int i=0;i<num+2;i++){
                for (int j=0;j<num+2;j++){
                    if(i==0||i==num+1 || j==0||j==num+1)
                        matrix[i][j]=false;
                    else
                        matrix[i][j]=true;
//                    int current=i*num+j+1;
//                    if(current < ufNum)
//                        label[i][j]= current;
                }
            }
            //read file and build matrix
            while((test=br.readLine())!=null){
                String input[]=test.split("","");
                int row=Integer.parseInt(input[0]);
                int col=Integer.parseInt(input[1]);
                matrix[row][col]=false;
            }

            int labelNum=1;
            for(int i=1;i<num+1;i++){
                for (int j=1;j<num+1;j++){
                    int currentID=(i-1)*num+(j-1)+(num*num+1)/2+1;
                    if(matrix[i][j]==false) continue;
                    else{
                        if(!matrix[i-1][j] && !matrix[i][j-1]){
                            uf.union(labelNum,currentID);
                            labelNum++;
                        }
                        else{
                            if((currentID-1)%num==0)
                                uf.union(currentID-num,currentID);
                            else{
                                if(uf.find(currentID-num)>uf.find(currentID-1))uf.union(currentID-1,currentID);
                                else uf.union(currentID-num,currentID);
                            }
                        }
//                        System.out.printf(""%d\n"",currentID);
                    }
                }
            }

            int temp=(num*num+1)/2+1;
            for(int i=1;i<num+1;i++){
                for (int j=1;j<num+1;j++){
                    if(matrix[i][j]==false)continue;
                    else{
                        int currentID=(i-1)*num+(j-1)+(num*num+1)/2+1;
                        if(currentID%num==0){
                            if(uf.find(currentID-num)!=uf.find(currentID) && uf.find(currentID-num)<temp)uf.union(currentID-num,currentID);
                        }else{
                            if(uf.find(currentID+1)!=uf.find(currentID)&&uf.find(currentID+1)<temp)uf.union(currentID+1,currentID);
                            if(uf.find(currentID-num)!=uf.find(currentID)&&uf.find(currentID-num)<temp)uf.union(currentID-num,currentID);
                        }
                    }
                }
            }

            for(int i=0;i<num+2;i++){
                for (int j=0;j<num+2;j++){
                    System.out.printf(""%d "",matrix[i][j]?1:0);
                }
                System.out.printf(""\n"");
            }
            System.out.printf(""\n"");
            for(int i=0;i<num;i++){
                for (int j=0;j<num;j++){
                    System.out.printf(""%-4d"",uf.find(i*num+j+(num*num+1)/2+1));
                }
                System.out.printf(""\n"");
            }
            System.out.printf(""%d"",uf.find((targetRow-1)*num+(targetCol-1)+(num*num+1)/2+1));
        }
    }
}

