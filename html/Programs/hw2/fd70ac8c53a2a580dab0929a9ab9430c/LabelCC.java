/*
.
 * To change this template file, choose Tools | Templates
.
 */
//package labelcc;


//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import labelCC.QuickFindUF;
import labelCC.QuickFindUF;




// class QuickFindUF {
//    public  int[] id;    // id[i] = component identifier of i
//    private int count;   // number of components
//
//   
//    public QuickFindUF(int N) {
//        count = N;
//        id = new int[N];
//        for (int i = 0; i < N; i++)
//            id[i] = i;
//    }
//
//  
//   
//    
//    
//    
//    public int count() {
//        return count;
//    }
//  
// 
//    public int find(int p) {
//        validate(p);
//        return id[p];
//    }
//
//  
//    private void validate(int p) {
//        int N = id.length;
//        if (p < 0 || p >= N) {
//            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + (N-1));
//        }
//    }
//
//    
//    public boolean connected(int p, int q) {
//        validate(p);
//        validate(q);
//        return id[p] == id[q];
//    }
//  
//   
//    public void union(int p, int q) {
//        validate(p);
//        validate(q);
//        int pID = id[p];   // needed for correctness
//        int qID = id[q];   // to reduce the number of array accesses
//
//        // p and q are already in the same component
//        if (pID == qID) return;
//
//        for (int i = 0; i < id.length; i++)
//            if (id[i] == pID) id[i] = qID;  // p(小樹) 全部變 q(大樹) 的child
//        count--;
//    }
//
//  
//
//}
public class LabelCC {
    private boolean[][] closed;
    private int[][] label;
    private int size;
    private int num;
    private int[] table;
    private static QuickFindUF qf ;
    
    /**
.
     */
    public LabelCC(int N) {
        size = N;
        //qf = new WeightedQuickUnionUF(size * size + 2);
        closed = new boolean[size][size];
        label =new int[size][size];
        table = new int[size*size];
        qf = new QuickFindUF(size * size);
        num = 0;
        
        
    }
 
    /**
.
     */
    public void close(int i, int j) {
        closed[i-1][j-1] = true;
    }
    public void Label(int i, int j){
        int temp = getQFIndex(i,j);
        if(i==0 && j==0){
            if(!isClose(i,j)){
                num++;
                table[temp]=num;
                label[i][j]=num;
            }
        }
        if(i>0 && j>0){
            if (isLabel(i-1,j) && isLabel(i, j-1)){
                label[i][j]=Math.min(label[i][j-1],label[i-1][j] );
                table[temp]=label[i][j];
            }else if(isLabel(i-1,j)){
                label[i][j]=label[i-1][j];
                table[temp]=label[i][j];
            }else if(isLabel(i,j-1)){
                label[i][j]=label[i][j-1];
                table[temp]=label[i][j];
            }else{
                num++;
                table[temp]=num;
                label[i][j]=num;
            }
        }
        if (i==0 && j>0){
            if (isLabel(i,j-1)){
                label[i][j]=label[i][j-1];
                table[temp]=label[i][j];
            }else{
                num++;
                label[i][j]=num;
                table[temp]=label[i][j];
            }
        }
        if (j==0 && i>0){
            if (isLabel(i-1,j)){
                label[i][j]=label[i-1][j];
                table[temp]=label[i][j];
            }else{
                num++;
                label[i][j]=num;
                table[temp]=label[i][j];
            }
        }
    }
    /**
     * Is site (row i, column j) open?
     */
    private int getQFIndex(int i, int j) { //Object Method
        return size * i + j;
    }
    
    public boolean isClose(int i, int j) {
        return closed[i][j];
    }
    public boolean isLabel(int i,int j){
        return label[i][j]!=0;
    }
    
    public void change(int i,int j){
        int temp = size*i+j;
        qf.id[temp] = table[temp];
    }
 
    public void un(int i,int j){
        //qf.id[i*size+j] = table[i*size+j];
        if(i==0 && j==0){
            return;
        }
        if(i>0 && j>0){
            if (isLabel(i-1,j) && isLabel(i, j-1)){
                //int temp =Math.min(label[i][j-1],label[i-1][j] );
                if (label[i][j-1]>=label[i-1][j]){
                qf.union(getQFIndex(i,j),getQFIndex(i-1,j));
               }else{qf.union(getQFIndex(i,j),getQFIndex(i,j-1));
            }
            }
            
            else if(isLabel(i-1,j)){
                qf.union(getQFIndex(i,j),getQFIndex(i-1,j));
            }else if(isLabel(i,j-1)){
                qf.union(getQFIndex(i,j),getQFIndex(i,j-1));
            }else{
                return;
            }
                
           }
        if (i==0 && j>0){
            if (isLabel(i,j-1)){
                qf.union(getQFIndex(i,j),getQFIndex(i,j-1));
            }
        }
        
        if (j==0 && i>0){
            if (isLabel(i-1,j)){
                qf.union(getQFIndex(i,j),getQFIndex(i-1,j));
            }
        }
    }
    
    
    
    
    public static void main(String[] args)throws Exception {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data=br.readLine().split("","");
            int N = Integer.parseInt(data[0]);
//            target[1]=Integer.parseInt(data[2]);
//            target[0]=Integer.parseInt(data[1]);
            int a1 = Integer.parseInt(data[1]);  // target1
            int a2 = Integer.parseInt(data[2]);  // target2
            a1--;
            a2--;
            //System.out.println(num);
            LabelCC matrix = new LabelCC(N);
            
            String read;
            while((read=br.readLine())!=null){
                if (read.isEmpty()){}else{
                String[] xy = read.split("","");
                int x =Integer.parseInt(xy[0]);
                int y =Integer.parseInt(xy[1]);

                matrix.close(x,y);
                }
            }

            for (int i=0;i<N;i++){
                for (int j=0;j<N;j++){
                    if (!matrix.isClose(i,j)){
                        matrix.Label(i, j);
                    }
                }
            }
            
//            for (int i = 0; i < N*N; ++i) {
//                table[i] = 0;  //陣列 2D->1D
//            }
            
            for (int i=0;i<N;i++){
                for (int j=0;j<N;j++){
                   matrix.change(i, j);
                    }
                }
             //System.out.println(qf.id[0]);
            
            for (int i=0;i<N;i++){
                for (int j=0;j<N;j++){
                    if (!matrix.isClose(i,j)){
                        matrix.un(i, j);
                    }
                }
            }

            int goal = N * a1 + a2;
           // System.out.println(a1);
           // System.out.println(a2);
            
            if(!matrix.isClose(a1,a2)){
            System.out.println(qf.id[goal]);
            }
            else{
             System.out.println(""0"");
            }
            
            
            
           
  
    } 
        }
        }
