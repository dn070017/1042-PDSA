
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.*;



public class Percolation {

    static int[][] ID;
    static WeightedQuickUnionUF uf;
    static int num;
//    static LinkedList<Point2D> ls;
//    static LinkedList<Point2D> ls2;
    static ArrayList<Integer> list ;
    static ArrayList<Integer> list2 ;
    
    static boolean toptodown() {
//        StdOut.print(list.get());
        for (int j = 0; j < list.size(); j++) {
            for (int k = 0; k < list2.size(); k++) {
                while (uf.connected(ID[0][list.get(j)], ID[num-1][list2.get(k)])) {
                    return true;
                }
            }

        }
        return false;
    }

    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        String[] data = br.readLine().split("","");
        num = Integer.parseInt(data[0]);
        int numcr = Integer.parseInt(data[1]);
//        StdOut.print(num);
//        System.out.print(numcr);
        ID = new int[num][num];

//        初始化matrix  0~num^2-1
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ID[i][j] = num * i + j;
//                StdOut.print(matrix[i][j]);
            }
//            StdOut.print(""\n"");
        }

        uf = new WeightedQuickUnionUF(num * num);
        list = new ArrayList();
        list2 = new ArrayList();
        
        boolean[][] cell = new boolean[num][];
        for (int i = 0; i < num; i++) {
            cell[i] = new boolean[num];
            for (int j = 0; j < num; j++) {
                cell[i][j] = false;
            }
        }

//        ls = new Linkedlist();
        
        for (int i = 0; i < numcr; i++) {
            String[] coor = br.readLine().split("","");
            int x = Integer.parseInt(coor[0]) - 1;
            int y = Integer.parseInt(coor[1]) - 1;
//            StdOut.print(x+"" ""+y);
            cell[x][y] = true;
            if (x - 1 >= 0 && cell[x - 1][y]) {
                uf.union(ID[x][y], ID[x - 1][y]);
            }
            if (x + 1 < num && cell[x + 1][y]) {
                uf.union(ID[x][y], ID[x + 1][y]);
            }
            if (y - 1 >= 0 && cell[x][y - 1]) {
                uf.union(ID[x][y], ID[x][y - 1]);
            }
            if (y + 1 < num && cell[x][y + 1]) {
                uf.union(ID[x][y], ID[x][y + 1]);
            }
            
            
            
//            Point2D[] pp=new Point2D[numcr];
//            pp[i]=new Point2D(x, y);
//            StdOut.print(pp[i].x()+"" ""+pp[i].y());
            if (x==0){
                list.add(y);
               
//                StdOut.print(list.size());
//                StdOut.print(y);
            }
            if (x==(num-1)){
                list2.add(y);
//                StdOut.print(y);
            }
            
            if (toptodown()) {
                StdOut.print((x + 1) + "","" + (y + 1));
                break;
            }
            

        }
//        StdOut.print(list.get(0).x());
//        StdOut.print(ans[0] + "","" + ans[1]);
//        StdOut.print(list2.size());
        
    }
    

}

