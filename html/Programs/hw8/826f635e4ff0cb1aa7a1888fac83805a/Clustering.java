import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Clustering {

private static class Clu implements Comparable<Clu> {
    private int clusize; 
    private Double clux;
    private Double cluy;    
    public Clu(int clusize, Double clux, Double cluy){
        this.clusize = clusize;
        this.clux = clux;
        this.cluy = cluy;    
    }
    public int getclusize(){
        return this.clusize;
    }
    public Double clux(){
        return this.clux;
    }
    public Double cluy(){
        return this.cluy;
    }    
    public int compareTo(Clu that) {   
        if(this.clusize>that.clusize) {return 1;}
        else if(this.clusize<that.clusize) {return -1;}
        else {
            if(this.clux>that.clux){return -1;}
            else  if(this.clux<that.clux){return 1;}
            else{
                if(this.cluy>that.cluy){return -1;}
                else  if(this.cluy<that.cluy){return 1;}
                else{return 0;}
            }}
    } 
}    
private static Double dist(Double[] a, Double[] b){
        Double x = a[0]-b[0];
    Double y = a[1]-b[1];   
    return( Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) );
}


public static int[] root;
private static int find(int p) {
    while (p != root[p])
           p = root[p];
        return p;
    }


    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int N =  Integer.parseInt(br.readLine());
            int N1 = N-1; int N2 = N;
            ST<Double, Integer> st = new ST<Double, Integer>(); 
                int N23=2*N-3;
                if(N23<N)  N23 = N;
                Double[][] xymat = new  Double[N23][2];
                int[][] xyname = new int[N*N][2];
                root =new int[N23];
                int[] size =new int[N23];            
                for (int i = 0; i < (N23); i++) {
                    size[i]=1;
                    root[i]=i;
                }
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split("" "");
                xymat[i][0]=Double.parseDouble(temp[0]);                  
                xymat[i][1]=Double.parseDouble(temp[1]);                        
            }            
            int ccount=1;
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {

                    xyname[ccount][0]=i;
                    xyname[ccount][1]=j;                    
                    Double key = dist(xymat[i],xymat[j]);
                    st.put(key,ccount++);
                }
            }
            while(N2>3){
                N2--;
                int getxy = st.get(  st.min() );
                int delex = xyname[getxy][0];
                int deley = xyname[getxy][1];                
                int sx = size[delex];
                int sy = size[deley];
                xymat[++N1][0] = (sx*xymat[delex][0]+sy*xymat[deley][0])/(sx+sy);
                xymat[N1][1] = (sx*xymat[delex][1]+sy*xymat[deley][1])/(sx+sy);                

                root[find(delex)]=N1;
                root[find(deley)]=N1;
                size[N1]=size[delex]+size[deley];
                st.delete(dist(xymat[delex],xymat[deley]));
                for (int k = 0; k < N1; k++) { 
                    if(find(k)==k){
                        st.delete(dist(xymat[delex],xymat[k]));
                        st.delete(dist(xymat[deley],xymat[k]));
                        xyname[ccount][0]=k;
                        xyname[ccount][1]=N1;
                        st.put(dist(xymat[k],xymat[N1]),ccount++);
                    }
                }             
            }

            Clu[] clu = new Clu[N2]; int z=0;
            for (int k = 0; k < (N1+1); k++) {
                if(root[k]==k){
                    clu[z++] = new Clu(size[k], xymat[k][0], xymat[k][1]); }
            }
            Arrays.sort(clu);
            
            for (int k = N2-1; k > -1; k--) {
                System.out.println(clu[k].clusize+"" ""+ 
                String.format(""%.2f"",clu[k].clux) +"" ""+ String.format(""%.2f"",clu[k].cluy));
            }
            if(N==1){System.out.println(0.00);}
            else{
            MinPQ<String> pq = new MinPQ<String>();
            for (int i = 0; i < (N-1); i++) {
                int cul=find(i);
                for (int j = i+1; j < N; j++) {
                    if(find(j)!=cul){
                        pq.insert(String.format(""%.2f"",dist(xymat[i],xymat[j])));   
                    }
                }
            }            
                System.out.println(pq.min());
            }
        }
    }
}

