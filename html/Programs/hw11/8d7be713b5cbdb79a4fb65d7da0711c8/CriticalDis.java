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
        public static void main(String[] args) {
        In in = new In(args[0]);
        String[] s = in.readAllLines();
        int N = Integer.parseInt(s[0]);
        //StdOut.println(N);
        double T_max = 0;
        int T = 0;
        double S_min = 2;
        int S = 0;
        Point2D[] vert = new Point2D[N];//store all
        for(int i = 0; i<N;i++){
            String[] inData = s[i+1].split("" "");
            Double x = Double.parseDouble(inData[0]);
            Double y = Double.parseDouble(inData[1]);
            vert[i] = new Point2D(x,y);
            if(x+y<=S_min) {
                S_min = x+y;
                S = i;
            }
            if(x+y>=T_max) {
                T_max = x+y;
                T = i;
            }
        }
        //StdOut.println(""T is ""+T);
        //StdOut.println(""S is ""+S);
        //store all the distance
        MinPQ<Double> pq = new MinPQ<Double>();//store the distance, this is for searching the min distance
        for(int i=0; i<N;i++){
            for(int j = i+1;j<N;j++){
                if(vert[i].x()<=vert[j].x() && vert[j].y()<=vert[j].y()){
                    // in this condition, add edge
                    pq.insert(vert[i].distanceTo(vert[j]));
                    //StdOut.println(vert[i].distanceSquaredTo(vert[j]));
                }
            }
        }
        double smallest_distance = 0;
        while(!pq.isEmpty()){
            Graph  G = new Graph (N);
            smallest_distance = pq.delMin();
            //add the edge
                /*for(int i=0; i<N;i++){
                    for(int j = i+1;j<N;j++){
                        if(vert[i].x()<=vert[j].x() && vert[j].y()<=vert[j].y()){
                            // in this condition, add edge
                            if(vert[i].distanceTo(vert[j]) <=smallest_distance){
                                G.addEdge(i, j);
                                //smaller than the dist, then adding the edge
                            }
                    }
                }
            }*/
            CC cc = new CC(G);
            if(cc.connected(S, T) == true) break;
        }

        System.out.printf(""%1.3f\n"", smallest_distance);
        //StdOut.println(smallest_distance);
        //StdOut.println(x[1]);
        //Graph G = new Graph(in);
        //StdOut.println(G);
    }
}

