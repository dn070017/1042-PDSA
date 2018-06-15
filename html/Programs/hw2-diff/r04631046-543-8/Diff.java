import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {
//        read size and outputelement
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            int[] opElement = new int[2];
            opElement[0] = Integer.parseInt(data[1]);
            opElement[1] = Integer.parseInt(data[2]);
            
//            read close position
            boolean[][] dataMatrix = new boolean[num][num];
            while(br.ready()){
                String[] Open = br.readLine().split("","");
                dataMatrix[Integer.parseInt(Open[0])-1][Integer.parseInt(Open[1])-1] = true;
            }
//            QuickUnionUF setting
            int groupN = (num*num+1)/2;
            int totalN = num*num+groupN+1;
            int opPosition = (opElement[0]-1)*num+opElement[1]+groupN;
            QuickUnionUF uf = new QuickUnionUF(totalN);
            int position ;
            int label = 0;
//            Main
            for(int i=0;i<num;i++){
                for(int j=0;j<num;j++){
                    position = (i*num)+j+groupN+1;
//                    block
                    if(dataMatrix[i][j]==true){
                        uf.union(position, 0);
                    }
//                    first element
                    else if(i==0&&j==0&&dataMatrix[i][j]==false){
                        label++;
                        uf.union(position,label);
                    }
//                    first row
                    else if(i==0&&j!=0&&dataMatrix[i][j]==false){
                        if(dataMatrix[i][j-1]==false){
                            uf.union(position, position-1);
                        }
                        else{
                            label++;
                            uf.union(position, label);
                        }
                        
                    }
//                    first column
                    else if(j==0&&i!=0&&dataMatrix[i][j]==false){
                        if(dataMatrix[i-1][j]==false){
                            uf.union(position, position-num);
                        }
                        else{
                            label++;
                            uf.union(position, label);
                        }
                        
                    }
//                    regulat element
                    else if(dataMatrix[i][j]==false){
//                        -1 yes -num no
                        if(dataMatrix[i][j-1]==false&&dataMatrix[i-1][j]==true){
                            uf.union(position, position-1);
                        }
//                        -1 no -num yes
                        else if(dataMatrix[i][j-1]==true&&dataMatrix[i-1][j]==false){
                            uf.union(position, position-num);
                        }
//                        all yes
                        else if(dataMatrix[i][j-1]==false&&dataMatrix[i-1][j]==false){
                            int left = uf.find(position-1);
                            int up = uf.find(position-num);
                            if(left<up){
                                uf.union(position, position-1);
                            }
                            else if(up<left||up==left){
                                uf.union(position, position-num);
                            }
                        }
//                        all no
                        else{
                            label++;
                            uf.union(position, label);
                        }
                    }
                }
            }
            int outLabel = uf.find(opPosition);
            System.out.printf(""%d"",outLabel);           
        }
    }
}

