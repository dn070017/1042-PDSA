//import algs4.jar.UF;
import java.io.FileReader;
import java.io.BufferedReader;
//記得刪掉++

public class Percolation {
    @SuppressWarnings(""empty-statement"")
    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(""input.dat""))){
         //讀檔，要幾階>order，今天不split，ASCII的陰謀-48
         int order=br.read()-48;
         //int z=br.read();
         //建立UF物件，名為gg
         UFF gg=new UFF(order*order);
               
         //MARK
         br.mark(1234);
         
        //數有幾個座標要開通
         int count=0;
         while(br.read()!=-1){
            if(br.read()==10||br.read()==13) {
                count++;
            }
         }
         
         //reset
         br.reset();
         //不知道為什麼reset後會往前跑一行
         br.readLine();
         
         //做字串陣列來存要開通的格子
         String ayaya[][]=new String[count][count];
         for(int i=0;i<count;i++) ayaya[i]=br.readLine().split("","");
         
         //mark用，UFF物件裡存父子關係，opcl裡存是否開通
        
         int opcl[]=new int[order*order];
         for(int i=0; i<order*order; i++) opcl[i]=i;
         
             
         //先將頂排全體相連+開通，底排亦然
         for(int i=0;i<order;i++){
             gg.union(i,0);
             gg.union(order*order-1-i,order*order-1);
             //opcl[i]=0; opcl[order*order-1-i]=0;
         }
         
         //開始開通
         int x,y,id,idu,idd,idr,idl;
         for(int i=0;i<count;i++){
             x=Integer.parseInt(ayaya[i][0]);
             y=Integer.parseInt(ayaya[i][1]);
             //化二維坐標為一維坐標
             id=order*(y-1)+x-1;
             //開通
             opcl[id]=0;
             //檢查四周格子是否為0，有就union
             //先檢查是四周還是三周還是上下排
             //先排除上下排
             if(y!=1&&y!=order){
                 //四周
                 if(x!=1||x!=order){
                     if(opcl[id+1]==0) gg.union(id,id+1);
                     if(opcl[id-1]==0) gg.union(id, id-1);
                     if(opcl[id+order]==0) gg.union(id, id+order);
                     if(opcl[id-order]==0) gg.union(id, id-order);
                 }
                 //左排
                 else if(x==1){
                     if(opcl[id+1]==0) gg.union(id,id+1);
                     if(opcl[id+order]==0) gg.union(id, id+order);
                     if(opcl[id-order]==0) gg.union(id, id-order);
                 }
                 //右排
                 else if(x==order){
                     if(opcl[id-1]==0) gg.union(id, id-1);
                     if(opcl[id+order]==0) gg.union(id, id+order);
                     if(opcl[id-order]==0) gg.union(id, id-order);
                 }
                 }
             
             
             
             //上下列恰好連起來了嗎?若有輸出當下座標，若無且i又走到盡頭輸出-1
             if(gg.connected(0,order*order-1)){
                System.out.printf(""%d,%d"",y,x);
                break;
             }else if(i==count-1)System.out.print(-1);
         } 
        }
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

private static class UFF {
    private int[] id;     // id[i] = parent of i
    private byte[] rank;  // rank[i] = rank of subtree rooted at i (cannot be more than 31)
    private int count;    // number of components

    /**
     * Initializes an empty union-find data structure with <tt>N</tt>
     * isolated components <tt>0</tt> through <tt>N-1</tt>
     * @throws java.lang.IllegalArgumentException if <tt>N &lt; 0</tt>
     * @param N the number of sites
     */
    public UFF(int N) {
        if (N < 0) throw new IllegalArgumentException();
        count = N;
        id = new int[N];
        rank = new byte[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            rank[i] = 0;
        }
    }

    /**
.
     * @param p the integer representing one object
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws java.lang.IndexOutOfBoundsException unless <tt>0 &le; p &lt; N</tt>
     */
    public int find(int p) {
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        while (p != id[p]) {
            id[p] = id[id[p]];    // path compression by halving
            p = id[p];
        }
        return p;
    }

    /**
.
     * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
     */
    public int count() {
        return count;
    }
  
    /**
     * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return true if the two sites <tt>p</tt> and <tt>q</tt> are in the same component; false otherwise
     * @throws java.lang.IndexOutOfBoundsException unless
     *      both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

  
    /**
     * Merges the component containing site <tt>p</tt> with the 
.
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws java.lang.IndexOutOfBoundsException unless
     *      both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[i] < rank[j]) id[i] = j;
        else if (rank[i] > rank[j]) id[j] = i;
        else {
            id[j] = i;
            rank[i]++;
        }
        count--;
    }
   
    /**
     * Reads in a an integer <tt>N</tt> and a sequence of pairs of integers
     * (between <tt>0</tt> and <tt>N-1</tt>) from standard input, where each integer
     * in the pair represents some site;
     * if the sites are in different components, merge the two components
.
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UFF uf = new UFF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + "" "" + q);
        }
        StdOut.println(uf.count() + "" components"");
    }*/
}}
