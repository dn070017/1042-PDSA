import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
public class CriticalDis {
    
    private static class Edge implements Comparable<Edge> {
        private Point2D v;
        private Point2D w;
        private double d;
        private int idv; private int idw;

        public Edge(Point2D v, Point2D w,int idv,int idw) {
            this.v = v;
            this.w = v;
            this.d = v.distanceTo(w);
            this.idv=idv; this.idw=idw;
        }
        
        public void setid(int idv, int idw){
           this.idv = idv;
           this.idw = idw;
        }
        
        public int compareTo(Edge that) {
          if(this.d > that.d){return 1;}
          if(this.d == that.d){return 0;}
          else {return -1;}
    }}
    
        public static void main(String[] args) throws Exception {
        //""input11.0.txt""  args[0]
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String header = br.readLine();
            int size = Integer.parseInt(header);
            Point2D[] points = new Point2D[size];   
            Digraph graph = new Digraph(size);

            int sid=0; int tid=0;
            double sxy; double txy;//source and target ids
            //inititial
            String[] in0 = br.readLine().split(""\\s+"");
         
            points[0] = new Point2D(Double.parseDouble(in0[0]),Double.parseDouble(in0[1]));
            sxy=points[0].x()+points[0].y();txy=sxy;

            for(int i=1;i<size;i++) {
                String[] in = br.readLine().split(""\\s+"");
                points[i] = new Point2D(Double.parseDouble(in[0]),Double.parseDouble(in[1]));
                double temp=points[i].x()+points[i].y();
                if (temp<sxy){sxy=temp;sid=i;}
                else if (temp>txy){txy=temp;tid=i;}
            }
            //System.out.println(sid);
            //System.out.println(tid);
            MinPQ<Edge> edges = new MinPQ();
            for(int i =0;i<size;i++){//the last one don't be
                for(int j = 0;j<size;j++){
                  if(i!=j){
                  if(points[i].x()<points[j].x()&&points[i].y()<points[j].y()) {
                      Edge ed = new Edge(points[i],points[j],i,j);
                      edges.insert(ed);
                      //graph.addEdge(i,j);}
                }} 
            }}
            while(true){
              
              Edge dd = edges.min();
              graph.addEdge(dd.idv,dd.idw);
              DirectedDFS DFS=new DirectedDFS(graph,sid);
              //System.out.println(dd.d); 
              //System.out.println(dd.idv);
              if (DFS.marked(tid)){
              break;}
              edges.delMin();
            }
            System.out.printf(""%1.3f\n"", edges.min().d);
                      
    }
}}

