/*
.
 * To change this template file, choose Tools | Templates
.
 */
package labelcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steven
 */
public class LabelCC {
    private boolean[][] opened;
    private int[][] label;
    private int size;
    private int num=1;
    //private int[] table;
    private ArrayList<Integer> table=new ArrayList<Integer>();
    //private WeightedQuickUnionUF qf;

    /**
.
     */
    public LabelCC(int N) {
        size = N;
        //qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
        label =new int [size][size];
        table.add(0);
    }

    /**
.
     */
    public void open(int i, int j) {
        opened[i][j] = true;
    }
    public void Label(int y, int x){

        if(x>0 && y>0){
            if (isLabel(y-1,x) || isLabel(y, x-1)){
                label[y][x]=Math.min(label[y][x-1],label[y-1][x] );
                table.set(Math.max(label[y][x-1],label[y-1][x] )-1,Math.min(label[y][x-1],label[y-1][x] ));
            }else{
                num++;
                table.add(num);
                label[y][x]=num;
            }
        }
        if (y==0 && x>0){
            if (isLabel(y,x-1)){
                label[y][x]=label[y][x-1];
            }else{
                num++;
                table.add(num);
                label[y][x]=num;
            }
        }
        if (x==0 && y>0){
            if (isLabel(y-1,x)){
                label[y][x]=label[y-1][x];
            }else{
                num++;
                table.add(num);
                label[y][x]=num;
            }
        }
    }
    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return opened[i][j];
    }
    public boolean isLabel(int y,int x){
        return label[y][x]!=0;
    }
    /**
     * Is site (row i, column j) full?
     */
    public int[] confirm(int[] table){
        boolean b=false;
        for (int i=0;i<table.length;i++){
            if (table[i]==i+1){
                }else{
                    table[i]=table[table[i]-1];
                    b=true;
                }
            }
        if (b){
            table=confirm(table);
        }
        return table;
    }


    private int getQFIndex(int i, int j) {
        return size * (i) + j+1;
    }
    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
        int out;
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data=br.readLine().split("","");
            int N= Integer.parseInt(data[0]);
            int[] target=new int[2];
            target[0]=Integer.parseInt(data[1]);
            target[1]=Integer.parseInt(data[2]);
            target[0]--;
            target[1]--;
            LabelCC matrix=new LabelCC(N);
            String read;
            while((read=br.readLine())!=null){
                String[] xy = read.split("","");
                int x =Integer.parseInt(xy[1]);
                int y =Integer.parseInt(xy[0]);
                x--;
                y--;
                matrix.open(y,x);
                
            }
            System.out.println(matrix.opened[0][0]);
            for (int y=0;y<N;y++){
                for (int x=0;x<N;x++){
                    matrix.Label(y, x);
                }
            }
            
            //matrix.confirm(matrix.table);
            if (matrix.isOpen(target[1],target[0])){
                out=matrix.label[target[1]][target[0]];
                while (out!=matrix.table.get(out-1)){
                    out=matrix.table.get(matrix.table.get(out-1)-1)+1;
                }
            }else{
                out=0;
            }
        }
        System.out.println(out);
    }
    
}
