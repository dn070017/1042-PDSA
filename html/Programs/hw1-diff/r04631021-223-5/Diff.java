import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author 林康維
 */
public class Percolation {
  private int[] _parent;
  private int[] _rank;


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


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            File file = new File(args[0]);
            try{
            Scanner File_in = new Scanner(file);            
            String num = File_in.nextLine();
            //String num = data ;
            int RankOfData = Integer . parseInt (num) ;
            Percolation uf = new Percolation(RankOfData*RankOfData+2);
            for(int i=1 ; i<=RankOfData ; i++){
                //System.out.println(uf._parent[i]);
                uf.union(i,0) ;
                //System.out.println(uf._parent[i]);
            }
            for(int i=RankOfData*RankOfData ; i>=RankOfData*RankOfData-RankOfData+1 ; i--){
                //System.out.println(uf._parent[i]);
                uf.union(i, RankOfData*RankOfData + 1);
                //System.out.println(uf._parent[i]);
            }
            boolean matrix[] = new boolean [RankOfData*RankOfData+2] ;
            //ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>() ;
            //視情況可刪或不刪,N^2的演算法
            for (int i = 0 ; i < RankOfData*RankOfData+2 ; i++){
                
                matrix[i] = false ;
                
            }
            while(true){
                String White = File_in.nextLine() ;
                String[] mat = White.split("","");
                int Drow = Integer . parseInt( mat[0] ) -1 ;
                int Dcol = Integer . parseInt( mat[1] ) -1 ;
               /*System.out.println(Drow
                        +Dcol);
                System.out.println(matrix[Drow*RankOfData-Dcol+1]);*/
                matrix [Drow*RankOfData+Dcol+1] = true ;
                /*System.out.println(Drow
                        +Dcol);
                System.out.println(matrix[Drow*RankOfData-Dcol+1]);*/
                //System.out.println(uf._parent[Drow*RankOfData+Dcol+1]);
                
                //System.out.println(uf._parent[9]);
                if((Drow!=0)&&(Drow!=RankOfData-1)){
                //上             
                if (matrix[Drow*RankOfData+Dcol-RankOfData+1]){
                    uf.union( Drow*RankOfData+Dcol-RankOfData+1, Drow*RankOfData+Dcol+1 ) ;
                }
                
                //下
                if(matrix[Drow*RankOfData+Dcol+RankOfData+1]){
                    uf.union(Drow*RankOfData+Dcol+RankOfData+1, Drow*RankOfData+Dcol+1);
                }
                
                //左
                if(matrix[Drow*RankOfData+Dcol]){
                    uf.union(Drow*RankOfData+Dcol, Drow*RankOfData+Dcol+1) ;
                  }
                
                //右
                if(matrix[Drow*RankOfData+Dcol+2]){
                    uf.union(Drow*RankOfData+Dcol+2, Drow*RankOfData+Dcol+1) ;
                }
                //System.out.println(uf._parent[Drow*RankOfData+Dcol+1]);
                
                if(uf._parent[0]==uf._parent[RankOfData*RankOfData+1]){
                    
                    System.out.println((Drow+1)
                            +"",""
                            +(Dcol+1)) ;
                    break ;
                }
                }
                else if(Drow == 0){
                    if(matrix[Dcol+RankOfData+1]){
                    uf.union(Dcol+RankOfData+1, Dcol+1);
                    if(uf._parent[0]==uf._parent[RankOfData*RankOfData+1]){
                    
                    System.out.println((Drow+1)
                            +"",""
                            +(Dcol+1)) ;
                    break ;
                    }
                }
                }
                else if(Drow == RankOfData -1){
                    if (matrix[Drow*RankOfData+Dcol-RankOfData+1]){
                    uf.union( Drow*RankOfData+Dcol-RankOfData+1, Drow*RankOfData+Dcol+1 ) ;
                    if(uf._parent[0]==uf._parent[RankOfData*RankOfData+1]){
                    
                    System.out.println((Drow+1)
                            +"",""
                            +(Dcol+1)) ;
                    break ;
                    }
                }
                }
                if(!(File_in.hasNextLine())){
                    System.out.println(-1);
                    break ;
                }
                
            }
            }
            
            catch(IOException e){
            System.out.println(""error!""); 
        }
        // TODO code application logic here
    }
    
}

