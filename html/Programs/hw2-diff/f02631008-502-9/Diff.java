import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author phillip hsiao
 */
public class LabelCC {
    
    private int[] _parent;
    private int[] _rank;
    private int[] _true;

    public int find(int i) {
        int p = _parent[i];
        if (i == p) {
          return i;
        }
        return _parent[i] = find(p);
    }
    public void union(int i, int j) {
        int root1 = find(i);
        int root2 = find(j);
        if (root2 == root1) return;
        if (_rank[root1] > _rank[root2]) {
          _parent[root2] = root1;
        } else if (_rank[root2] > _rank[root1]) {
          _parent[root1] = root2;
        } else {
          _parent[root2] = root1;
          _rank[root1]++;
        }
    }
    public LabelCC(int max) {
        _parent = new int[max];
        _rank = new int[max];
        for (int i = 0; i < max; i++) {
          _parent[i] = i;
        }
    }
    public String toString() {
        return ""<UnionFind\np "" + Arrays.toString(_parent) + ""\nr "" + Arrays.toString(_rank) + ""\n>"";
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File file = new File(args[0]);
        try{
            
            Scanner File_in = new Scanner(file);
            String line_1 = File_in.nextLine();
            String[] Nums_1 = line_1.split("","");
            int ArrNum = Integer.parseInt(Nums_1[0]);
            int AnsRow = Integer.parseInt(Nums_1[1]);
            int AnsCol = Integer.parseInt(Nums_1[2]);
            int AnsInUf = ArrNum*(AnsRow-1)+AnsCol;
            LabelCC uf = new LabelCC(ArrNum*ArrNum +1);
            //System.out.println(AnsInUf);
            
            int[][] IndexMat = new int[ArrNum][ArrNum];
            for (int i = 0; i < ArrNum; i++){
                for (int j = 0; j < ArrNum; j++){
                    IndexMat[i][j] = ArrNum*i+j+1;
                    //IndexMat[i][j] = ArrNum*i+j+1;
                    //System.out.println(IndexMat[i][j]);
                }
            }
            int[][] TrueMat = new int[ArrNum][ArrNum];
            for (int i = 0; i < ArrNum; i++){
                for (int j = 0; j < ArrNum; j++){
                    TrueMat[i][j] = 1;
                    //System.out.println(TrueMat[i][j]);
                }
            }
            
            while (File_in.hasNextLine())
            {
                String line_2 = File_in.nextLine();
                String[] Nums = line_2.split("","");

                int Row = Integer.parseInt(Nums[0]);
                int Col = Integer.parseInt(Nums[1]);
                //System.out.println(Row);
                TrueMat[Row-1][Col-1] = 0;
            }
            int ucount = 1;
            for (int i = 0; i < ArrNum; i++){
                for (int j = 0; j < ArrNum; j++){
                    int NumIndex = ArrNum*(i)+j+1;
                    if(i == 0 && j == 0 && TrueMat[i][j] == 1){
                        IndexMat[i][j] = ucount;
                        //uf.union(ucount,NumIndex);
                    }
                    if(i == 0 && j-1 >= 0 && TrueMat[i][j] == 1 && TrueMat[i][j-1] == 1){
                        IndexMat[i][j] = IndexMat[i][j-1];
                        uf._parent[NumIndex] = uf._parent[NumIndex-1];
                    }
                    if(i == 0 && j-1 >= 0 && TrueMat[i][j] == 1 && TrueMat[i][j-1] == 0){
                        ucount++;
                        IndexMat[i][j] = ucount;
                        uf._parent[NumIndex] = ucount;
                    }
                    if(i > 0 && j == 0 && TrueMat[i][j] == 1 && TrueMat[i-1][j] == 1){
                        IndexMat[i][j] = IndexMat[i-1][j];
                        uf._parent[NumIndex] = uf._parent[NumIndex-ArrNum];
                    }
                    if(i > 0 && j-1 >= 0 && TrueMat[i][j] == 1 && TrueMat[i-1][j] == 1){
                        IndexMat[i][j] = IndexMat[i-1][j];
                        uf._parent[NumIndex] = uf._parent[NumIndex-ArrNum];
                    }
                    if(i > 0 && j-1 >= 0 && TrueMat[i][j] == 1 && TrueMat[i][j-1] == 1){
                        IndexMat[i][j] = IndexMat[i][j-1];
                        uf._parent[NumIndex] = uf._parent[NumIndex-1];
                    }
                    if(i > 0 && j == 0 && TrueMat[i][j] == 1 && TrueMat[i-1][j] == 0){
                        ucount++;
                        IndexMat[i][j] = ucount;
                        uf._parent[NumIndex] = ucount;
                    }
                    if(i > 0 && j-1 >= 0 && TrueMat[i][j] == 1 && TrueMat[i][j-1] == 0 && TrueMat[i-1][j] == 0){
                        ucount++;
                        IndexMat[i][j] = ucount;
                        uf._parent[NumIndex] = ucount;
                    }
                    if(i > 0 && j-1 >= 0 && TrueMat[i][j] == 1 && TrueMat[i][j-1] == 1 && TrueMat[i-1][j] == 1){
                        if(IndexMat[i][j-1] > IndexMat[i-1][j]){
                            IndexMat[i][j] = IndexMat[i-1][j];
                            uf._parent[NumIndex] = uf._parent[NumIndex-ArrNum];
                            int ChangeNum = IndexMat[i][j-1];
                            for (int k = 0; k < ArrNum*ArrNum+1; k++){
                                if(uf._parent[k] == ChangeNum){
                                    uf._parent[k] = uf._parent[NumIndex-ArrNum];
                                }
                            }
                        }
                        if(IndexMat[i-1][j] > IndexMat[i][j-1]){
                            IndexMat[i][j] = IndexMat[i][j-1];
                            uf._parent[NumIndex] = uf._parent[NumIndex-1];
                            int ChangeNum = IndexMat[i-1][j];
                            for (int k = 0; k < ArrNum*ArrNum+1; k++){
                                if(uf._parent[k] == ChangeNum){
                                    uf._parent[k] = uf._parent[NumIndex-1];
                                }
                            }
                        }
                    }
                    if(TrueMat[i][j] == 0){
                        IndexMat[i][j] = 0;
                        uf._parent[NumIndex] = 0;
                        uf.union(0,NumIndex);
                    }
                }
            }
            System.out.println(uf._parent[AnsInUf]);
        }
        catch(IOException e){
            System.out.println(""error!""); 
        }
        // TODO code application logic here
    }
}
