/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class FindNeighbors {
    public Node root;
    private MaxPQ<Pair> MPQ;
    
    public void init(Point2D[] points){
        root = new Node(points[0],null,null,1);
        for (int i=1;i<points.length;i++){
            put(points[i]);
        }
        return;
    }
    
    public Point2D[] query(Point2D point, int k){
        MPQ = new MaxPQ<Pair>(k);
        root = search(point,root,k);
        Point2D[] result = new Point2D[k];
        for (int i=0;i<k;i++){
            Point2D p = MPQ.delMax().neighbor;
            result[k-1-i]=p;
        }
        return result;
    // the points should be sorted accordingly to their distances to the query, from small to large
    }
////////////////////////////////////////////////////////////////////////////////   
    private class Node{
        private Point2D loc;
        private Node left;
        private Node right;
        private Integer dir;
        public Node(Point2D p, Node l, Node r, Integer d){
            this.loc=p;
            this.left=l;
            this.right=r;
            this.dir=d;
            
        }
    }  
////////////////////////////////////////////////////////////////////////////////
    private static class Pair implements Comparable<Pair>{
        public Point2D neighbor;
        public double dis;
        public Pair(Point2D p, double dis){
            this.neighbor = p;
            this.dis = dis;
        }
        public int compareTo(Pair that){
            if (this.dis > that.dis){return 1;}
            else if (this.dis < that.dis){return -1;}
            else{return 0;}
        }
        
        public Point2D getNeighbor(){
            return this.neighbor;
        }
        public double getDis(){
            return this.dis;
        } 
    }

////////////////////////////////////////////////////////////////////////////////

    public void put(Point2D LOC){
        root = put(root,LOC,1);
    }
    private Node put(Node n,Point2D point,Integer dir){
        
        if (n==null) {
            return new Node(point,null,null,dir);
        }
        Integer cmp;
        if (dir==1){
            cmp=compare(point.x(),n.loc.x());
            dir=0;
        }
        else {
            cmp=compare(point.y(),n.loc.y());
            dir=1;
        }
        
        if (cmp <= 0){
            n.left=put(n.left, point, dir);
        }
        else if (cmp > 0){
            n.right=put(n.right, point, dir);
        }
        return n;
    }
    
    private Integer compare(double a,double b){
        if (a<b){return -1;}
        else if (a>b){ return 1;}
        else{return 0;}
    }
    
    public Node search(Point2D point, Node n, Integer k){
        if (n==null) return null;
        
        Integer cmp;
        if (MPQ.size()==k){
            Pair MaxPair = MPQ.max();
            double distance;
            if (n.dir == 1){
                cmp = compare(point.x(),n.loc.x());
                if (cmp > 0){distance = point.x()-n.loc.x();}
                else {distance = n.loc.x()-point.x();}
            }
            else{
                cmp = compare(point.y(),n.loc.y());
                if (cmp > 0){distance = point.y()-n.loc.y();}
                else {distance = n.loc.y()-point.y();}       
            }
            if (distance > MaxPair.dis){return n;}
            else {
                distance = point.distanceTo(n.loc);
                if (distance < MaxPair.dis){
                    MPQ.delMax();
                    MPQ.insert(new Pair(n.loc,distance));
                }
                if (cmp > 0){
                    n.right=search(point,n.right,k);
                    n.left=search(point,n.left,k);
                }
                else{
                    n.left=search(point,n.left,k);
                    n.right=search(point,n.right,k);
                }
            }
        }

        else{
            Pair pair = new Pair(n.loc,point.distanceTo(n.loc));
            MPQ.insert(pair);
            if (n.dir==1){
                cmp = compare(point.x(),n.loc.x());
            }
            else{
                cmp = compare(point.y(),n.loc.y());
            }
            if (cmp > 0){
                n.right=search(point,n.right,k);
                n.left=search(point,n.left,k);
            }
            else{
                n.left=search(point,n.left,k);
                n.right=search(point,n.right,k);
            }
        }
        return n;
    }
////////////////////////////////////////////////////////////////////////////////
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        ArrayList<Point2D> PA = new ArrayList<Point2D>();
        Point2D Target;
        Integer k;
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String temp_target = br.readLine();
            String[] temp_target_list=temp_target.split("" "");
            Target = new Point2D(Double.parseDouble(temp_target_list[0]),Double.parseDouble(temp_target_list[1]));
            
            String nei = br.readLine();
            //String[] temp_nei=nei.split("" "");
            k = Integer.parseInt(nei);
            
            String temp;
            while((temp = br.readLine())!=null){
                String[] split_temp=temp.split("" "");
                Point2D p = new Point2D(Double.parseDouble(split_temp[0]),Double.parseDouble(split_temp[1]));
                PA.add(p);
            }
        }
        
        Point2D[] PList = PA.toArray(new Point2D[PA.size()]);
        
        FindNeighbors find = new FindNeighbors();
        find.init(PList);
        /*
        Node root = find.root;
        Node L1 = root.right;
        Node L1L = L1.right.right;
        System.out.println(L1L.loc.x());
        System.out.println(L1L.loc.y());
        */     
        
        
        Point2D[] result = find.query(Target,k);
        
        for (int i=0;i<k;i++){
            System.out.println(result[i].x());
            System.out.println(result[i].y());
            System.out.println(""stop"");
        }
                
                
    }
    
}

