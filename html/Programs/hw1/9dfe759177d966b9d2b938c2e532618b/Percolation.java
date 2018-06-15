/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package percolation;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author steven
 */
public class Percolation {
    private boolean[][] opened;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qf;

    /**
.
     */
    public Percolation(int N) {
        size = N;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    /**
.
     */
    public void open(int i, int j) {
        opened[i - 1][j - 1] = true;
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

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return opened[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (0 < i && i <= size && 0 < j && j <= size) {
            return qf.connected(top, getQFIndex(i , j));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    private int getQFIndex(int i, int j) {
        return size * (i - 1) + j;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String n= br.readLine();
            int N=Integer.parseInt(n);
            
            Percolation matrix=new Percolation(N);
            String position;
            while((position=br.readLine())!=null){
                String[] xy=position.split("","");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                matrix.open(x,y);
                if (matrix.percolates()){
                    System.out.println(position);
                    break;
                }
            }
        System.out.println(-1);
        }
    }
    
}

