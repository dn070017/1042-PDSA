/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            
            String[] row1 = br.readLine().split("","");
            int SizeNum = Integer.parseInt(row1[0]);
            //System.out.println(SizeNum);
            
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(SizeNum*SizeNum+2);
            Point SupperTop = new Point(SizeNum*SizeNum,SizeNum*SizeNum+1,SizeNum*SizeNum+1);
            Point SupperBottom = new Point(SizeNum*SizeNum+1,SizeNum*SizeNum+1,SizeNum*SizeNum+1);
                    
            ArrayList<Point> PointList = new ArrayList<Point>();
            for (int i = 0; i< i+1;i++){
                String[] row;
                try{
                    row = br.readLine().split("","");
                }
                catch(Exception e){
                    break;
                }
                //String[] row = br.readLine().split("","");
                int x = Integer.parseInt(row[0]);
                int y = Integer.parseInt(row[1]);
                
                Point p1 = new Point(i,x,y);
                
                if (x == 1){
                    uf.union(i, SupperTop.ID); 
                }
                if (x == SizeNum){
                    uf.union(i, SupperBottom.ID);
                }
                PointList.add(p1);
                
                int x1 = x-1;
                int x2 = x+1;
                int y1 = y-1;
                int y2 = y+1;
                
                
                for (int j=0;j < PointList.size();j++){
                    Point a = PointList.get(j);
                    int xx = a.XCOOR; 
                    int yy = a.XCOOR;
                    if (xx == x1 & yy == y){
                    uf.union(i, a.ID);
                }
                    if (xx == x2 & yy == y){
                    uf.union(i, a.ID);
                } 
                    if (xx == x & yy == y1){
                    uf.union(i, a.ID);
                } 
                    if (xx == x & yy == y2){
                    uf.union(i, a.ID);
                }
                }
                
                boolean result = uf.connected(SupperTop.ID,SupperBottom.ID);
                //System.out.println(result);
                if (result == true){
                    String out = row[0] + "","" + row[1];
                    System.out.println(out);
                    break;
                    }
                
            }
            boolean result = uf.connected(SupperTop.ID,SupperBottom.ID);
            if (result == false){
                System.out.println(""-1"");
            }
           
        }
    }
    
}

class Point{
    public int XCOOR;
    public int YCOOR;
    public int ID;
    public Point(int id,int x,int y){
        int XCOOR;
        XCOOR=x;
        this.XCOOR=x;
        int YCOOR;
        YCOOR=y;
        this.YCOOR=y;
        int ID;
        ID=id;
        this.ID=id;
        
    
    }
    public int getX(){
        return XCOOR;      
    }
    public int getY(){
        return YCOOR;
    }
    public int getID(){
        return ID;
    }
    
}

