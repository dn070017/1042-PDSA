import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
.
 *
 * This class implements a clustering algorithm called 'centroid hierarchical clustering algorithm' to
.
 *
 * The clustering procedures are described as follows:
 *
 * Step 0: Treat each point as a cluster;
 * Step 1: Find the nearest pair of clusters (a, b) among the remaining N clusters
 * Step 2: Create a new cluster, c, of which the coordinates are the centroid of all the points it contains after
 *         merging the clusters a and b;
 * Step 3: Delete the two old clusters: a and b;
 * Step 4: N = N - 1;
 * Step 5: Re-calculate the distance of the new cluster, c, to other remaining clusters;
 * Step 6: Go to Step 1 unless N = 3;
 * Step 7: For each point in each cluster, find the nearest point in different cluster. e.g cluster A has 2 points a1,
 *         a2. cluster B has 2 points b1, b2, cluster C has 2 points c1, c2. compare the distance (a1, b1), (a1, b2),
.
 *
 */
public class Clustering {


    private static boolean debug = false;
    private static ArrayList<Point2D> points = new ArrayList<>();


    public static void main(String[] args) throws Exception{

        //timer start
        long startTimeMS = System.currentTimeMillis();
        ArrayList<Cluster> clusters = new ArrayList<>();
        ArrayList<Cluster> clusters2 = new ArrayList<>();
        int N;
//        PriorityQueue<Pair> distances = new PriorityQueue<>();
        MinPQ<Pair> pairsPQ = new MinPQ<>();
        MinPQ<Pair> pairsPQ2 = new MinPQ<>();

        if(debug){
            StdDraw.setCanvasSize(500,500);
            StdDraw.setXscale(0.0,1.0);
            StdDraw.setYscale(0.0,1.0);
            StdDraw.setPenRadius(0.02);
        }




        // read file
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            N = Integer.valueOf(br.readLine());
            String[] line;
//            points = new ArrayList<>(Collections.nCopies(N, new Point2D(0,0)));
            for(int i=0;br.ready();i++){
                line = br.readLine().split("" "");
                points.add(i, new Point2D(Double.valueOf(line[0]),Double.valueOf(line[1])));
                clusters.add(new Cluster(points.get(i)));
                if(debug) StdDraw.point(points.get(i).x(),points.get(i).y());
            }
            if(debug) System.out.println(""*finished file reading\n""+printEverything());
            br.close();
        }

        clusters2=clusters;

        // * Step 1: Find the nearest pair of clusters (a, b) among the remaining N clusters
        //      - calculate all pairs
        for(int i=0;i<N-1;i++){ //0~18
            for(int j=i+1;j<N;j++){ //1~19
                if(debug) System.out.println(""pairing""+i+"",""+j);
                pairsPQ.insert(new Pair(clusters.get(i),clusters.get(j)));
            }
        }


        // * Step 2~5: Merge process
        if(debug) StdDraw.setPenRadius(0.01);
        if(debug) StdDraw.setPenColor(Color.RED);
        while(N!=3){

            // pop with closest pair
            Pair pairToMerge = pairsPQ.delMin();

            //if isValid:
            if(pairToMerge.c1.isValid && pairToMerge.c2.isValid) {

                Cluster merged = new Cluster(pairToMerge.c1, pairToMerge.c2);
                pairToMerge.c1.invalidate();
                pairToMerge.c2.invalidate();
                clusters.add(merged);

                if (debug){
                    StdDraw.point(merged.calCtd().x(),merged.calCtd().y());
                    StdDraw.line(pairToMerge.c1.calCtd().x(),pairToMerge.c1.calCtd().y(), pairToMerge.c2.calCtd().x(), pairToMerge.c2.calCtd().y());
                    System.out.println(""* Merged and invalidated ""
                            + pairToMerge.c1.hashCode() + pairToMerge.c1.calCtd()
                            + pairToMerge.c2.hashCode() + pairToMerge.c2.calCtd()
                            + ""\t->\t"" + merged.hashCode() + merged.calCtd());
                }

                //total validate cluster--
                N--;

                // * Step 5: Re-calculate the distance of the new cluster, c, to other remaining clusters;
                for (Cluster c : clusters) {
                    if (c.isValid && c.hashCode()!=merged.hashCode()) {
                        pairsPQ.insert(new Pair(merged, c));
                    }
                }
            }
        }


        int No = clusters.size();
        Cluster o0 = clusters.get(No-1);
        Cluster o1 = clusters.get(No-2);
        Cluster o2 = clusters.get(No-3);

        double[] weight = {0,0,0};//FIXME
        weight[0] = 1000*((o0.getSize()-o1.getSize())+o0.getSize()-o2.getSize()) - 100*(o0.calCtd().x() + o0.calCtd().y());
        weight[1] = 1000*((o1.getSize()-o0.getSize())+o1.getSize()-o2.getSize()) - 100*(o1.calCtd().x() + o1.calCtd().y());
        weight[2] = 1000*((o2.getSize()-o0.getSize())+o2.getSize()-o1.getSize()) - 100*(o2.calCtd().x() + o2.calCtd().y());


        if(weight[0]>weight[1] && weight[0]>weight[2]){
            System.out.println(String.format(""%d %.2f %.2f"" ,o0.getSize(), o0.calCtd().x() , o0.calCtd().y() ));
            if(weight[1] > weight [2]) {
                System.out.println(String.format(""%d %.2f %.2f"" ,o1.getSize(), o1.calCtd().x() , o1.calCtd().y() ));
                System.out.println(String.format(""%d %.2f %.2f"" ,o2.getSize(), o2.calCtd().x() , o2.calCtd().y() ));
            }else {
                System.out.println(String.format(""%d %.2f %.2f"" ,o2.getSize(), o2.calCtd().x() , o2.calCtd().y() ));
                System.out.println(String.format(""%d %.2f %.2f"" ,o1.getSize(), o1.calCtd().x() , o1.calCtd().y() ));
            }

        }else if(weight[1]>weight[0] && weight[1]>weight[2]){
            System.out.println(String.format(""%d %.2f %.2f"" ,o1.getSize(), o1.calCtd().x() , o1.calCtd().y() ));
            if(weight[0] > weight [2]) {
                System.out.println(String.format(""%d %.2f %.2f"" ,o0.getSize(), o0.calCtd().x() , o0.calCtd().y() ));
                System.out.println(String.format(""%d %.2f %.2f"" ,o2.getSize(), o2.calCtd().x() , o2.calCtd().y() ));
            }else {
                System.out.println(String.format(""%d %.2f %.2f"" ,o2.getSize(), o2.calCtd().x() , o2.calCtd().y() ));
                System.out.println(String.format(""%d %.2f %.2f"" ,o0.getSize(), o0.calCtd().x() , o0.calCtd().y() ));
            }
        }else {
            System.out.println(String.format(""%d %.2f %.2f"" ,o2.getSize(), o2.calCtd().x() , o2.calCtd().y() ));
            if(weight[0] > weight [1]) {
                System.out.println(String.format(""%d %.2f %.2f"" ,o0.getSize(), o0.calCtd().x() , o0.calCtd().y() ));
                System.out.println(String.format(""%d %.2f %.2f"" ,o1.getSize(), o1.calCtd().x() , o1.calCtd().y() ));
            }else {
                System.out.println(String.format(""%d %.2f %.2f"" ,o1.getSize(), o1.calCtd().x() , o1.calCtd().y() ));
                System.out.println(String.format(""%d %.2f %.2f"" ,o0.getSize(), o0.calCtd().x() , o0.calCtd().y() ));
            }
        }

        Pair tempPair;
        for(int i0 = 0; i0<o0.getSize(); i0++){
            for(int i1 = 0; i1<o1.getSize(); i1++) {
                tempPair=new Pair(new Cluster(o0.cps.get(i0)),new Cluster(o1.cps.get(i1)));
                pairsPQ2.insert(tempPair);
            }
        }
        for(int i1 = 0; i1<o1.getSize(); i1++){
            for(int i2 = 0; i2<o2.getSize(); i2++) {
                tempPair=new Pair(new Cluster(o1.cps.get(i1)),new Cluster(o2.cps.get(i2)));
                pairsPQ2.insert(tempPair);
            }
        }
        for(int i2 = 0; i2<o2.getSize(); i2++){
            for(int i0 = 0; i0<o0.getSize(); i0++) {
                tempPair=new Pair(new Cluster(o2.cps.get(i2)),new Cluster(o0.cps.get(i0)));
                pairsPQ2.insert(tempPair);
            }
        }

//        System.out.println(pairsPQ2.min().getDistance());
        System.out.println(String.format(""%.2f"" ,pairsPQ2.min().getDistance()));


        //timer end
        long totalms = System.currentTimeMillis()-startTimeMS;
        int sec = (int) (totalms / 1000) % 60 ;
        int min = (int) ((totalms / (1000*60)) % 60);
        int hr   = (int) ((totalms / (1000*60*60)) % 24);
        if (debug) System.out.printf(""Program Finished. Runtime: %d ms (%dhr %dmin %dsec)\n"",hr,min,sec,totalms);
    }








    //TODO check APIs reasonability
    private static class Cluster{

        //constructor I
        Cluster(Point2D firstPoint){
            cps = new ArrayList<>();
            cps.add(firstPoint);
            ctd = firstPoint;
            isValid = true;
        }

        //constructor II
        Cluster(Cluster c1, Cluster c2){
            cps = new ArrayList<>();
            cps.addAll(c1.cps);
            cps.addAll(c2.cps);
            ctd = calCtd();
            isValid = true;
            c1.invalidate();
            c2.invalidate();
        }

        //MEMBER : points within cluster
        ArrayList<Point2D> cps;

        //MEMBER : centroid of the points
        private Point2D ctd;

        //MEMBER :
        boolean isValid;

        //METHOD :
        int getSize() {return this.cps.size();}

        //METHOD :
        Point2D calCtd(){
            double sumX=0,sumY=0;
            for(Point2D p:this.cps){
                sumX += p.x();
                sumY += p.y();
            }
            return new Point2D(sumX/getSize(),sumY/getSize());
        }

        //METHOD : invalidate the cluster instead of delete it
        void invalidate(){ isValid = false; }

        boolean isValid() {return isValid;}

        //METHOD : merge two cluster
        boolean absorb(Cluster c1){
            cps.addAll(c1.cps);
            ctd = calCtd();
            c1.invalidate();
            return true;
        }
    }


    private static class Pair implements Comparable<Pair>{

        Cluster c1;

        Cluster c2;

        Pair(Cluster c1,Cluster c2){
            this.c1=c1;
            this.c2=c2;
        }

        double getDistance() { return this.c1.calCtd().distanceTo(this.c2.calCtd()); }

//        Cluster getC1(){ return c1; }

//        Cluster getC2(){ return c2; }

        @Override
        public int compareTo(Pair that) {
            if(this.getDistance()>that.getDistance()) return 1;
            else if (this.getDistance() < that.getDistance()) return -1;
            else return 0;
        }
    }


    private static String printEverything(){
        for(Point2D p:points) System.out.printf(""(%f,%f)"",p.x(),p.y());
        return ""done printEverything"";
    }

}

