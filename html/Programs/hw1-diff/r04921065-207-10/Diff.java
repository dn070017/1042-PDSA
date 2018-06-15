/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package percolation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;


/**
 *
 * @author 余軒
 */

public class Percolation {

private WeightedQuickUnionUF qf;
private boolean[][] opened;
private int top = 0;
private int bottom;
private int size;

public Percolation(int N) {  //constuctor
        size = N;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }




    /**
     * @param i
     * @param j
     * @param args the command line arguments
     * @throws java.io.IOException
     */


public void open(int i, int j) {     //Object Method
    opened[i-1][j-1]=true;
    if (i == 1) {
            qf.union(getQFIndex(i, j), top);
        }
    if (i == size) {
            qf.union(getQFIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
    }
    
 public boolean isFull(int i, int j) {    //Object Method
        if (0 < i && i <= size && 0 < j && j <= size) {
            return qf.connected(top, getQFIndex(i , j));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }



public boolean isOpen(int i, int j) { //Object Method
        return opened[i - 1][j - 1];
   }

private int getQFIndex(int i, int j) { //Object Method
        return size * (i - 1) + j;
    }


public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        
            String data = br.readLine();    //data不是串列，是單一元素
            int size = Integer.parseInt(data);
        //System.out.println(size);
        boolean[][] opened = new boolean[size][size]; //size = 3; 0~2
        Percolation a = new Percolation(size);  //宣告a為指定某種物件指標
        

      
       while(br.ready()){
                String[] coordinate  =br.readLine().split("","");
                int i = Integer.parseInt(coordinate[0]);
                int j = Integer.parseInt(coordinate[1]);
                //String[] I = new String[i];
               // String[] J = new String[j];
                //System.out.println(i+"",""+j);
                //opened[i-1][j-1]=true;
                a.open(i,j);   
              // open(i,j);  //在static函式中呼叫非static函式
               
                    if (a.percolates()){
                    System.out.println(i+"",""+j);
                    break;
                    }          
                    
                    
                    
                
                }
       if (!a.percolates()){
                System.out.println(""-1"");
           }
            
       // System.out.println(opened[0][2]);
        
        }

     //public boolean isOpen    
    }
     public boolean percolates() {
        return qf.connected(top, bottom);
    }
     
}


