/*
.
 * To change this template file, choose Tools | Templates
.
 */
package percolation;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintStream;


/**
 *
 * @author chinweihsu
 */



public  class Percolation {
    
    public static class UF {
        private int size;
        private boolean[] points;
        private int[] id;
        private int[] rank;
        
        
        public UF(int N){
            size = N;
            id = new int[N*N];
            points = new boolean[N*N];
            rank = new int[N*N];
            
            for(int i=0; i<N*N; i++){
                id[i] = i;
                points[i] = false;
                rank[i] = 0;
            }
            for(int i=0; i<N; i++){
                id[i] = 0;
                rank[i] = size;
                //id[size*size-1-i] = size*size;
            }
            
        }
        
        public int Root(int p){
            while(p != id[p]){
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
        
        public boolean connected(int p, int q){
            return Root(p) == Root(q);
        }
        
        public void union(int p, int q){
            int i = Root(p);
            int j = Root(q);
            if( i==j) return;
            
            if (rank[i] < rank[j]) id[i] = j;
            else if (rank[i] > rank[j]) id[j] = i;
            else{
                id[j] = i;
                rank[i]++;
            }
            //System.out.printf(""union %d and %d\n"",p,q);
        }
        
        public int getid(int x, int y){
            return y+size * x;
        }
        public void show(){
            for(int i=0;i<size*size;i++)
                System.out.printf(""%d : %b rank = %d union = %d \n"",i,points[i],rank[i],id[i]);
        }
        public void open(int x, int y){
            int p = getid(x,y);
            //System.out.printf(""open %d\n"",p);
            points[p] = true;
            //this.show();
            if (0<y && points[getid(x,y-1)]){
                //left has open
                //System.out.printf(""left has opened\n"");
                this.union(p,id[getid(x,y-1)]);
            }
             if (y<2 && points[getid(x,y+1)]){
                //right has open
                //System.out.printf(""right has opened\n"");
                this.union(p,id[getid(x,y+1)]);
            }
            if(0<x && points[getid(x-1,y)]){
                //top has open
                //System.out.printf(""top has opened\n"");
                this.union(p,id[getid(x-1,y)]);
            }
            if (x<2 && points[getid(x+1,y)]){
                //down has open
                //System.out.printf(""down has opened\n"");
                this.union(p,id[getid(x+1,y)]);
            }
            //this.show();
        }
        
        public boolean perCheck(){
            for(int i=0; i<size; i++){
                if(points[size*size-1-i] && id[size*size-1-i] == 0){
                    return true;
                }
            }
            return false;
        }
    }

    
    
    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data = br.readLine().split("","");
            int gridSize = Integer.parseInt(data[0]);
            UF Grid = new UF(gridSize); 
            boolean check = false;
            int[] result = new int[2];
            while(br.ready()){
                String[] point = br.readLine().split("","");
                //System.out.printf(""input =""+point[0]+"",""+point[1]+""\n"");
                Grid.open(Integer.parseInt(point[0])-1, Integer.parseInt(point[1])-1);
                if(Grid.perCheck()){
                    //System.out.printf(""Grid.perCheck() is true\n"");
                    check = true;
                    result[0] = Integer.parseInt(point[0]);
                    result[1] = Integer.parseInt(point[1]);
                    break;
                }
            }
            if(check){
                System.out.printf(""%d,%d"",result[0],result[1]);
            }else{
                System.out.printf(""-1"");
            }
        }
    }
    
    
}

