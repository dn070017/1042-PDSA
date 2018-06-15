import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class Percolation {
    private static int[] id;
    private static int[] sz;
.
    public static void QuickUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for(int i =0; i<N ; i++){
            id[i]=i; 
            sz[i]=1;
        }
    }
    
        private static int root(int i)
    {
        while (i != id[i]) 
            i = id[id[i]];
            return i;
        }
        public static boolean connected(int p, int q)
        {
            return root(p) == root(q);
        }
        
        public static void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            if(i == j) return;
            if (sz[i] < sz[j]) {id[i] = j; sz[j] += sz[i];}
            else               {id[j] = i; sz[i] += sz[j];} 
        }
    
    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style args[0]
        //args[0] is  just for juged system, or ""input4.txt"" or ""input5.txt""
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read first line number as vector
            int data = Integer.parseInt(br.readLine());
            //System.out.println(data);
            QuickUnionUF(data*data+2);
            for (int i = 1; i<=data; i++){
                union(0,i);
                union(data*data+1,data*data+1-i);
            }
            int [][] matrix = new int[data][data];
            for (int[] row : matrix){
            Arrays.fill(row, 0);}
            int [] announce = new int[2] ;
            while (true) {
                    String temp = br.readLine();
                    if (temp == null) {
                        System.out.println(""-1"");
                        break;
                    }  
                    String[] temp2 = temp.split("","");
                    //System.out.println(temp2); 
                    //System.out.println(temp2[0]+"",""+temp2[1]);
                    int t0 = Integer.parseInt(temp2[0])-1;
                    int t1 = Integer.parseInt(temp2[1])-1;
                    //System.out.println(t0+"",""+t1);
                    matrix[t0][t1]= 1;
                    int index=t0*data+t1+1;
                    // noted that the border excessive
                    if (t1!=(data-1)){if(matrix[t0][t1+1]==1) union(index,index+1);}
                    if (t1!=0)       {if(matrix[t0][t1-1]==1) union(index,index-1);}
                    if (t0!=(data-1)){if(matrix[t0+1][t1]==1) union(index,index+data);}
                    if (t0!=0)       {if(matrix[t0-1][t1]==1) union(index,index-data);}
                    
                    if (connected(0,data*data+1)) {
                        System.out.println(temp2[0]+"",""+temp2[1]);
                        break;
                    }
            }
            //for (int i = 0; i < data; i++) {
               // for (int j = 0; j < data; j++){
               // System.out.println(matrix[i][j]);  
               // }
            //System.out.println();
            //}
            }
        }
}


