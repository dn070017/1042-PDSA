import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Clustering {

private static class Clu implements Comparable<Clu> {
    public int clusize;
    public int node;
    public int minid;
    public Double mindis;    
    public Double clux;
    public Double cluy;    
    public Clu(int clusize, Double clux, Double cluy){
        this.clusize = clusize;
        this.clux = clux;
        this.cluy = cluy;
        this.node = 1;
    }

    public Double disto(Clu that) {
        Double dx=this.clux-that.clux;
        Double dy=this.cluy-that.cluy;
        return( Math.sqrt( dx*dx+dy*dy) );       
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

public static ST<Double, Integer> sti = new ST<Double, Integer>(); 
public static Clu merge(Clu a , Clu b){
    int news = a.clusize+b.clusize;
    Double newx = (a.clux*(a.clusize+0.0)+b.clux*(b.clusize+0.0))/news;
    Double newy = (a.cluy*(a.clusize+0.0)+b.cluy*(b.clusize+0.0))/news;
    Clu n = new Clu(news,newx,newy);
    a.node=0;
    b.node=0;
    Double temp=sti.min();
    while(sti.min()==temp){   sti.delete(sti.min());}  
    return(n);
}

public static int[] root;
private static int find(int p) {
    int p1 = p;
    while (p != root[p])
        p = root[p];
        root[p1]=root[p];
        return p;
    }


    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int N =  Integer.parseInt(br.readLine());
            int N1 = N-1; int N2 = N;
            int N23=2*N-3;
            if(N23<N)  N23 = N;
            Clu[] clu = new Clu[N23];
            root =new int[N23];
            for (int i = 0; i < (N23); i++) {
                root[i]=i;
            }
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split("" "");
                clu[i] = new Clu(1, Double.parseDouble(temp[0]), Double.parseDouble(temp[1])); 
            }
            if(N>1){
            for (int i = 0; i < N; i++) {
                ST<Double, Integer> st = new ST<Double, Integer>();     
                for (int j = 0; j < N; j++) {
                    if(i==j){ continue;}   
                    Double key = clu[i].disto(clu[j]);
                    st.put(key,j);                    
                }
                clu[i].mindis=st.min();
                clu[i].minid=st.get(clu[i].mindis);
                sti.put(clu[i].mindis,i); 
            }}
            while(N2>3){
                N2--;
                int di = sti.get( sti.min());
                int dj = clu[di].minid;
   
                clu[++N1]=merge(clu[di], clu[dj]);
                root[find(di)]=N1;
                root[find(dj)]=N1;
                ST<Double, Integer> st = new ST<Double, Integer>();   
                for (int k = 0; k < N1; k++) { 
                    if(clu[k].node==0){continue;}
                    Double newd=clu[N1].disto(clu[k]);
                    st.put(newd, k);
                    if(clu[k].minid==di | clu[k].minid==dj){
                        sti.delete( clu[k].disto( clu[ clu[k].minid ] ) );
                        ST<Double, Integer> sth = new ST<Double, Integer>(); 
                        for (int h = 0; h < N1; h++) {
                            if(h == k){continue;}
                            if(clu[h].node==0){continue;}
                            Double key=clu[k].disto(clu[h]);
                            sth.put(key, h);                    
                        }
                        clu[k].mindis=sth.min();
                        clu[k].minid=sth.get(sth.min());
                        sti.put(clu[k].mindis, k);
                    }                      
                    if(clu[k].mindis>newd){
                        sti.delete(clu[k].mindis);
                        clu[k].mindis=newd;
                        clu[k].minid=N1;
                        sti.put(newd, k);
                    }             
                }
            clu[N1].mindis=st.min();
            clu[N1].minid=st.get(st.min());
            sti.put(clu[N1].mindis,N1);

            }
        int N3 = 3;
        if(N<3){ N3=N;}
        Clu[] clu1 = new Clu[N3];
        int cc = 0;
        for (int k = 0; k < (N1+1); k++) {
            if(clu[k].node==1){
                clu1[cc++]=clu[k];
            }
        } 
        Arrays.sort(clu1);    
        for (int k = N2-1; k > -1; k--) {
                System.out.println(clu1[k].clusize+"" ""+ 
                String.format(""%.2f"",clu1[k].clux) +"" ""+ String.format(""%.2f"",clu1[k].cluy));
            }
            if(N==1){System.out.println(0.00);}
            else{
            MinPQ<String> pq = new MinPQ<String>();
            for (int i = 0; i < (N-1); i++) {
                int findi=find(i);
                for (int j = i+1; j < N; j++) {
                    if(find(j)!=findi){
                        pq.insert(String.format(""%.2f"",clu[i].disto(clu[j])));   
                    }
                }
            }            
                System.out.println(pq.min());
            }
        }
    }
}

