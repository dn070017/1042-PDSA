/*
.
 * To change this template file, choose Tools | Templates
.
 */
package labelcc;


//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import labelCC.QuickFindUF;



/**
 *  The <tt>QuickFindUF</tt> class represents a <em>union-find data type</em>
.
 *  It supports the <em>union</em> and <em>find</em> operations,
 *  along with a <em>connected</em> operation for determining whether
 *  two sites are in the same component and a <em>count</em> operation that
.
 *  <p>
 *  The union-find data type models connectivity among a set of <em>N</em>
.
 *  The <em>is-connected-to</em> relation must be an 
 *  <em>equivalence relation</em>:
 *  <ul>
.
 *  <p><li> <em>Symmetric</em>: If <em>p</em> is connected to <em>q</em>,
.
 *  <p><li> <em>Transitive</em>: If <em>p</em> is connected to <em>q</em>
 *          and <em>q</em> is connected to <em>r</em>, then
.
 *  </ul>
 *  <p>
 *  An equivalence relation partitions the sites into
 *  <em>equivalence classes</em> (or <em>components</em>). In this case,
.
 *  Both sites and components are identified with integers between 0 and
 *  <em>N</em> &ndash; 1. 
 *  Initially, there are <em>N</em> components, with each site in its
 *  own component.  The <em>component identifier</em> of a component
 *  (also known as the <em>root</em>, <em>canonical element</em>, <em>leader</em>,
 *  or <em>set representative</em>) is one of the sites in the component:
 *  two sites have the same component identifier if and only if they are
.
 *  <ul>
 *  <p><li><em>union</em>(<em>p</em>, <em>q</em>) adds a
.
 *         If <em>p</em> and <em>q</em> are in different components,
 *         then it replaces
 *         these two components with a new component that is the union of
.
 *  <p><li><em>find</em>(<em>p</em>) returns the component
.
 *  <p><li><em>connected</em>(<em>p</em>, <em>q</em>)
 *         returns true if both <em>p</em> and <em>q</em>
.
.
 *  </ul>
 *  <p>
 *  The component identifier of a component can change
 *  only when the component itself changes during a call to
 *  <em>union</em>&mdash;it cannot change during a call
.
 *  <p>
.
.
 *  Afterwards, the <em>find</em>, <em>connected</em>, and <em>count</em>
 *  operations take constant time but the <em>union</em> operation
.
 *  For alternate implementations of the same API, see
.
 *
 *  <p>
 *  For additional documentation, see <a href=""http://algs4.cs.princeton.edu/15uf"">Section 1.5</a> of
.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

// class QuickFindUF {
//    public  int[] id;    // id[i] = component identifier of i
//    private int count;   // number of components
//
//    /**
//     * Initializes an empty union-find data structure with <tt>N</tt> sites
//     * <tt>0</tt> through <tt>N-1</tt>. Each site is initially in its own 
.
//     *
//     * @param  N the number of sites
//     * @throws IllegalArgumentException if <tt>N &lt; 0</tt>
//     */
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
//    /**
.
//     *
//     * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
//     */
//    public int count() {
//        return count;
//    }
//  
//    /**
.
//     *
//     * @param  p the integer representing one site
//     * @return the component identifier for the component containing site <tt>p</tt>
//     * @throws IndexOutOfBoundsException unless <tt>0 &le; p &lt; N</tt>
//     */
//    public int find(int p) {
//        validate(p);
//        return id[p];
//    }
//
//    // validate that p is a valid index
//    private void validate(int p) {
//        int N = id.length;
//        if (p < 0 || p >= N) {
//            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + (N-1));
//        }
//    }
//
//    /**
.
//     *
//     * @param  p the integer representing one site
//     * @param  q the integer representing the other site
//     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are in the same component;
//     *         <tt>false</tt> otherwise
//     * @throws IndexOutOfBoundsException unless
//     *         both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
//     */
//    public boolean connected(int p, int q) {
//        validate(p);
//        validate(q);
//        return id[p] == id[q];
//    }
//  
//    /**
//     * Merges the component containing site <tt>p</tt> with the 
.
//     *
//     * @param  p the integer representing one site
//     * @param  q the integer representing the other site
//     * @throws IndexOutOfBoundsException unless
//     *         both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
//     */
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
//    /**
//     * Reads in a sequence of pairs of integers (between 0 and N-1) from standard input, 
//     * where each integer represents some site;
//     * if the sites are in different components, merge the two components
.
//     */
////    public static void main(String[] args) {
////        int N = StdIn.readInt();
////        QuickFindUF uf = new QuickFindUF(N);
////        while (!StdIn.isEmpty()) {
////            int p = StdIn.readInt();
////            int q = StdIn.readInt();
////            if (uf.connected(p, q)) continue;
////            uf.union(p, q);
////            StdOut.println(p + "" "" + q);
////        }
////        StdOut.println(uf.count() + "" components"");
////    }
//
//}
public class LabelCC {
    private boolean[][] closed;
    private int[][] label;
    private int size;
    private int num;
    private int[] table;
    private  QuickFindUF qf ;
    
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
        
    
   


