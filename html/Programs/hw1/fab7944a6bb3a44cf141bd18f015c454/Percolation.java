
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author S410
 */
public class Percolation {


    public static void main(String[] args)throws Exception {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        
        String[] data = br.readLine().split("","");
        int num = Integer.parseInt(data[0]);
        int numcr = Integer.parseInt(data[1]);
//        StdOut.print(num);
//        System.out.print(numcr);
        int[][] ID =new int[num][num];
        
//        初始化matrix  0~num^2-1
        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++){
                ID[i][j]=num*i+j;
//                StdOut.print(matrix[i][j]);
            }
//            StdOut.print(""\n"");
        }
        
//        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(num*num);
        int ans =0;
        for(int i=0;i<numcr;i++){
            String[] coor = br.readLine().split("","");
            int x = Integer.parseInt(coor[0]);
            int y = Integer.parseInt(coor[1]);
            ID[x-1][y-1]=-1;
            ans =i+1;
            if(y<=num-1&&ID[x-1][y]==-1)
                break;
            if(x<=num-1&&ID[x][y-1]==-1)
                break;
            if((y-2)>=0&&ID[x-1][y-2]==-1)
                break;
            if((x-2)>=0&&ID[x-2][y-1]==-1)
                break;
        }
        
        StdOut.print(ans);
    }
    
}

