import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author philip
 */

public class Percolation {
    
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
    public Percolation(int max) {
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
            int ArrNum = Integer.parseInt(line_1);
            int[] TrueArr;
            int Flag = 1;
            
            int[][] IndexMat = new int[ArrNum][ArrNum];
            for (int i = 0; i < ArrNum; i++){
                for (int j = 0; j < ArrNum; j++){
                    IndexMat[i][j] = ArrNum*i+j+1;
                    //System.out.println(IndexMat[i][j]);
                }
                
            }
            
            int[][] TrueMat = new int[ArrNum][ArrNum];
            for (int i = 0; i < ArrNum; i++){
                for (int j = 0; j < ArrNum; j++){
                    TrueMat[i][j] = 0;
                    //System.out.println(TrueMat[i][j]);
                }
                
            }
            
            Percolation uf = new Percolation(ArrNum*ArrNum +1);
            for (int i = 1; i < ArrNum+1; i++){
                uf.union(0,i);
                //System.out.println(uf);
            }
            for (int i = ArrNum*ArrNum-1; i > ArrNum*(ArrNum-1); i--){
                uf.union(ArrNum*ArrNum,i);
                //System.out.println(uf);
            }
            
            //System.out.println(uf);
            
            while (File_in.hasNextLine() && Flag ==1 )
            {
                String line_2 = File_in.nextLine();
                String[] Nums = line_2.split("","");

                int Row = Integer.parseInt(Nums[0]);
                int Col = Integer.parseInt(Nums[1]);
                
                int NumIndex = ArrNum*(Row-1)+Col;
                
                TrueMat[Row-1][Col-1] = 1;
                
                
                if(Row-2 >= 0 && TrueMat[Row-1][Col-1] == TrueMat[Row-2][Col-1]){
                    uf.union(NumIndex,NumIndex-ArrNum);
                    //System.out.println(uf);
                }
                if(Row+1 <= ArrNum && TrueMat[Row-1][Col-1] == TrueMat[Row][Col-1]){
                    uf.union(NumIndex,NumIndex+ArrNum);
                    //System.out.println(uf);
                }
                if(Col-2 >= 0 && TrueMat[Row-1][Col-1] == TrueMat[Row-1][Col-2]){
                    uf.union(NumIndex,NumIndex-1);
                    //System.out.println(uf);
                }
                if(Col+1 <= ArrNum && TrueMat[Row-1][Col-1] == TrueMat[Row-1][Col]){
                    uf.union(NumIndex,NumIndex+1);
                    //System.out.println(uf);
                }
                //System.out.println(uf);
                /*if(uf._rank[6] == 2){
                        System.out.println(line_2);
                        break;
                }*/
                for (int i = 0; i < ArrNum*ArrNum+1; i++){
                    if(uf._parent[0] == ArrNum*ArrNum){
                        System.out.println(line_2);
                        Flag = 0;
                        break;
                    }
                }
                if(!File_in.hasNextLine()){
                    System.out.println(-1);
                }
            }
            
            
            /*for (int i = 0; i < ArrNum*ArrNum+1; i++) {
                    //System.out.println(TrueArr[i]);
            }
            for (int i = 0; i < ArrNum; i++){
                for (int j = 0; j < ArrNum; j++){
                    //Mat[i][j] = 0;
                    //System.out.println(TrueMat[i][j]);
                }
                
            }*/
        }
        catch(IOException e){
            System.out.println(""error!""); 
        }
        // TODO code application logic here
    }
    
}

