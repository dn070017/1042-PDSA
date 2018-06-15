import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int n =  Integer.parseInt(br.readLine());
            Digraph G = new Digraph(n);
            Point2D[] p = new Point2D[n];
            Double minxy = 2.0;
            Double maxxy = 0.0;  
            int s = -1;
            int t = -1;        
            for (int i = 0; i < n; i++){
                String[] data = br.readLine().split("" "");
                Double x = Double.parseDouble(data[0]);
                Double y = Double.parseDouble(data[1]);   
                p[i] = new Point2D(x, y);
                Double xy = x + y;
                if(xy<minxy){s=i;   minxy=xy;}
                if(xy>maxxy){t=i;   maxxy=xy;}
            }
                 
            ST<Double, Integer[]> st = new ST<Double, Integer[]>();
            Double mins = 2.0;
            Double mint = 2.0;               
            for (int i = 0; i < (n-1); i++){                    
                for (int j= i+1; j < n; j++){
                    if(p[i].x()<p[j].x() & p[i].y()<p[j].y()){
                        Double dist = p[i].distanceTo(p[j]);
                        Integer[] ij = new Integer[2];
                        ij[0]=i;  ij[1]=j;  
                        st.put(dist, ij);
                        if(i==s){if(dist<mins){mins=dist;}}
                        if(j==t){if(dist<mint){mint=dist;}}                        
                    } else if(p[i].x()>p[j].x() & p[i].y()>p[j].y()){
                        Double dist = p[i].distanceTo(p[j]);
                        Integer[] ij = new Integer[2];
                        ij[0]=j;  ij[1]=i;                         
                        st.put(dist, ij);  
                        if(j==s){if(dist<mins){mins=dist;}}
                        if(i==t){if(dist<mint){mint=dist;}}                          
                    }
                }
            }
            Double d;
            if(mins>mint){d=mins;}
            else{d=mint;}
            while(st.min()<d){
                Double temp=st.min();
                Integer[] vw = st.get(temp);
                G.addEdge(vw[0],vw[1]);
                st.delete(temp);
            }
            DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);
            while(!dfs.hasPathTo(t)){
                d=st.min();
                Integer[] vw = st.get(d);
                G.addEdge(vw[0],vw[1]);
                st.delete(d);
                dfs = new DepthFirstDirectedPaths(G, s);
            }
            System.out.printf(""%1.3f\n"", d);
        }
    }    
}

