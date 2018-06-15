import java.io.FileReader;
import java.io.BufferedReader;
/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author jerry
 */
public class LabelCC {
    protected int[] parent;  // parent[i] = parent of i
    protected byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    protected int count;     // number of components

    
    public LabelCC(int N) {
        if (N < 0) throw new IllegalArgumentException();
        count = N;
        parent = new int[N];
        rank = new byte[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int returnCC(int i){
    return parent[i];
    }
    
     public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }
     
      public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
      public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootQ] = rootP;
    }//changed version for simple union to support 
      
    private void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + (N-1));  
        }
    }
      
      
     public static void main(String[] args)throws Exception {
        try{
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String data = br.readLine();//read in first integer
        String[] data3 = data.split("","");
        int N = Integer.parseInt(data3[0]);
        int targetx = Integer.parseInt(data3[1]);
        int targety = Integer.parseInt(data3[2]);//target X and target y
        
        
        int[][] Statearray = new int[N][N]; // using 0 and 1 to indicate location, 0 = close, 1 = open
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
            Statearray[i][j] = 1;
            }
        }//initialize array with all open

        //imagine both connected state in top and bottom row
        int x,x2,y,y2;
        for(String line = br.readLine(); line != null;line = br.readLine()){
        String[] data2 = line.split("","");
        x = Integer.parseInt(data2[0]);
        y = Integer.parseInt(data2[1]);
        x2 = x-1;
        y2 = y-1;//easy for array use
        Statearray[x2][y2] = 0; // close this site       
        }
        br.close();
        LabelCC result = new LabelCC(64);//initialize artificial groupings
        int[][] Labelarray = new int[N][N];   
        int currentlabel = 1;
        for(int i = 0; i < N ; i ++){
            for (int j = 0; j < N; j++) {
                if (Statearray[i][j] == 0) {
                    continue;
                } else if (checkbound(i - 1, N) && checkbound(j - 1, N) && Statearray[(i - 1)][j] * Statearray[i][(j - 1)] == 1) {
                    int Label1 = Labelarray[(i - 1)][j];
                    int Label2 = Labelarray[i][(j - 1)];
                    if (Label1 > Label2) {
                        Labelarray[i][j] = Label2;
                        result.union(Label1, Label2);
                    } else {
                        Labelarray[i][j] = Label1;
                        result.union(Label2, Label1);
                    }
                } else if (checkbound(i - 1, N) && Statearray[(i - 1)][j] == 1) {
                    Labelarray[i][j] = Labelarray[(i - 1)][j];
                } else if (checkbound(j - 1, N) && Statearray[i][(j - 1)] == 1) {
                    Labelarray[i][j] = Labelarray[i][(j - 1)];
                } else {
                    Labelarray[i][j] = currentlabel;
                    currentlabel++;
                }
            }
        }// the first run

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (Labelarray[i][j] != 0) {
                        int nowlabel = Labelarray[i][j];
                        if (result.returnCC(nowlabel) != result.find(nowlabel)) {
                            int newlabel = result.find(nowlabel);
                            Labelarray[i][j] = newlabel;
                        }
                    }
                }
            }

        System.out.print(Labelarray[(targetx-1)][(targety-1)]);
        }
        
        catch(Exception e){
          System.out.println(-1);  
        }
    }
     
    public static boolean checkbound(int P, int Q) {
        boolean result = true;
        if (P < 0 || P > (Q - 1)) {
            result = false;
        }
        return result;
    }
}

