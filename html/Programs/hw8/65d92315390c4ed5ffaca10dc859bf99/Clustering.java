import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
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
        else {return 0;}
    } 
}    
private static Double dist(Point2D a, Point2D b){
    Double x = a.x()-b.x();
    Double y = a.y()-b.y();    
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
            Point2D[] p = new Point2D[2*N-3];
            ST<Double, Point2D> st = new ST<Double, Point2D>();
            root =new int[2*N-3];
            int[] size =new int[2*N-3];            
            for (int i = 0; i < (2*N-3); i++) {
                size[i]=1;
                root[i]=i;
            }           
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split("" "");
                p[i]= new Point2D(Double.parseDouble(temp[0]) , Double.parseDouble(temp[1])  );
            }            
       
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    Double key = dist(p[i],p[j]);
                    Point2D pij = new Point2D(i,j);
                    st.put(key,pij);
                }
            }
            while(--N2>2){
                Point2D pij = st.get(  st.min() );
                int delei = (int)pij.x();
                int delej = (int)pij.y();  
              
                Double sumx = 0.0;
                Double sumy = 0.0;
                Double s=0.0;
                for (int k = 0; k < N; k++) {
                    if(find(k)==delei || find(k)==delej){
                        sumx=sumx + p[k].x(); sumy=sumy + p[k].y();
                        s++;
                    }
                }
                p[++N1]= new Point2D(sumx/s,sumy/s);
                
                root[find(delei)]=N1;
                root[find(delej)]=N1;
                size[N1]=size[delei]+size[delej];
                st.delete(dist(p[delei],p[delej]));
                for (int k = 0; k < N1; k++) { 
                    if(find(k)==k){
                        st.delete(dist(p[delei],p[k]));
                        st.delete(dist(p[delej],p[k]));                        
                        Point2D pkN1 = new Point2D(k,N1);                    
                        st.put(dist(p[k],p[N1]),pkN1);
                    }
                }             
            }

            Clu[] clu = new Clu[3]; int z=0;
            for (int k = 0; k < (N1+1); k++) {
                if(root[k]==k){ clu[z] = new Clu(size[k], p[k].x(), p[k].y());  z++;}
            }
            Arrays.sort(clu);
            
            for (int k = 2; k > -1; k--) {
                System.out.println(clu[k].clusize+"" ""+ 
                String.format(""%.2f"",clu[k].clux) +"" ""+ String.format(""%.2f"",clu[k].cluy));
            }
            MinPQ<String> pq = new MinPQ<String>();
            for (int i = 0; i < (N-1); i++) {
                int cul=find(i);
                for (int j = i+1; j < N; j++) {
                    if(find(j)!=cul){
                        pq.insert(String.format(""%.2f"",dist(p[i],p[j])));   
                    }
                }
            }            
           System.out.println(pq.min());
        }
    }
}

