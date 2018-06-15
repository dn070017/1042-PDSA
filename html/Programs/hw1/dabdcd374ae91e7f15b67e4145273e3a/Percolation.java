/**
.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            int num=Integer.parseInt(br.readLine());
            boolean matrix[][]=new boolean [num+2][num+2];
            int id[][]=new int [num][num];
            int inputNum[]=new int[2];
            WeightedQuickUnionUF uf=new WeightedQuickUnionUF(num*num+1);

            //init
            for(int i=0;i<num+2;i++){
                for(int j=0;j<num+2;j++){
                    matrix[i][j]=false;
                }
            }

            String test;
            boolean flag=false;

            while((test=br.readLine())!=null&&flag==false){
                String[] input=test.split("","");
                inputNum[0]=Integer.parseInt(input[0]);
                inputNum[1]=Integer.parseInt(input[1]);
                matrix[inputNum[0]][inputNum[1]]=true;
                int currentId=(inputNum[0]-1)*num+(inputNum[1]-1)+1;

                //check neighbor site is connected
                for(int i=-1;i<2;i++){
                    int j=0;
                    if(i==0 && j==0)continue;
                    else if(matrix[inputNum[0]+i][inputNum[1]+j]==true){
                        if(!uf.connected(currentId,(inputNum[0]-1+i)*num+(inputNum[1]-1+j)+1))
                            uf.union((inputNum[0]-1+i)*num+(inputNum[1]-1+j)+1,currentId);
                    }
                }

                for(int j=-1;j<2;j++){
                    int i=0;
                    if(i==0 && j==0)continue;
                    else if(matrix[inputNum[0]+i][inputNum[1]+j]==true){
                        if(!uf.connected(currentId,(inputNum[0]-1+i)*num+(inputNum[1]-1+j)+1))
                            uf.union((inputNum[0]-1+i)*num+(inputNum[1]-1+j)+1,currentId);
                    }
                }


                for(int i=1;i<=num;i++){
                    for(int j=num*(num-1)+1;j<=num*num;j++){
                        if(uf.connected(i,j)){
                            System.out.printf(""%d,%d"",inputNum[0],inputNum[1]);
                            flag=true;
                            break;
                        }
                    }
                    if(flag==true)break;
                }

//                for(int i=0;i<num*num+1;i++){
//                    System.out.printf(""%s "",uf.find(i));
//                }
//               c
//                System.out.printf(""\n"");

//                    System.out.printf(""%b\n"",percolation.ifNeighbor(current,tempP));
//                    if(matrix[inputNum[0]-1][inputNum[1]-1])

            }
            if(flag==false)
                System.out.printf(""%d"",-1);

        }
    }

//    boolean ifNeighbor(int[] current,int[] neighbor){
//
//        if(((Math.abs(current[0]-neighbor[0]))==0 && Math.abs(current[1]-neighbor[1])==1) || (Math.abs(current[0]-neighbor[0])==1 && Math.abs(current[1]-neighbor[1])==0))
//            return true;
//        else
//            return false;
//    }

}


