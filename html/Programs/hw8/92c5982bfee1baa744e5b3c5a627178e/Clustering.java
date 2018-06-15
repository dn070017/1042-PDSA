

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
.
 */
public class Clustering{
    private static class cluster implements Comparable<cluster>{
        private Point2D pts;
        int size;

        public cluster(Point2D a){
            pts=a;
            size=1;
        }

        @Override
        public int compareTo(cluster that) {
            if(this.size<that.size)return 1;
            else if(this.size>that.size)return -1;
            else{
                if(this.pts.x()<that.pts.x())return 1;
                else if(this.pts.x()>that.pts.x())return -1;
                else {
                    if(this.pts.y()<that.pts.y())return 1;
                    else if(this.pts.y()>that.pts.y())return -1;
                    else return 0;
                }
            }
        }
    }

    public static cluster add_cluster(cluster a,cluster b){
        cluster c;
        Point2D t=new Point2D((a.pts.x()*a.size+b.pts.x()*b.size)/(a.size+b.size),(a.pts.y()*a.size+b.pts.y()*b.size)/(a.size+b.size));
        c=new cluster(t);
        c.size=a.size+b.size;
        return c;
    }

    private static class pair{
        double distance;
        cluster c1;
        cluster c2;

        public pair(cluster a,cluster b){
            c1=a;
            c2=b;
            distance=a.pts.distanceTo(b.pts);
        }
    }

    public static void main(String[] args)throws Exception{
        try(BufferedReader br= new BufferedReader(new FileReader(args[0]))){

            List<cluster> clusterList = new ArrayList<>();
            List<cluster> clusterListForLast = new ArrayList<>();


            int num=Integer.parseInt(br.readLine());
            cluster c;
            Point2D points;
            for (int i = 0; i < num; i++) {
                String temp[]=br.readLine().split("" "");
                points=new Point2D(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]));
                c=new cluster(points);
                clusterList.add(i,c);
            }

            double min,mostMin=2;
            Point2D p1=new Point2D(0,0);
            Point2D p2=new Point2D(0,0);
            cluster a=new cluster(p1);
            cluster b=new cluster(p2);
            int n= (int) ((num-1)*num*0.5);
            pair p[]=new pair[n];
            MinPQ<Double> d;
            MinPQ<Double> mMin=new MinPQ<>(15);

            cluster newCluster;

            while (num>=3){
                d=new MinPQ<>(num*num);
                for (int i = 0,k=0; i < num; i++) {
                    for (int j = i+1; j < num; j++) {
                        p[k]=new pair(clusterList.get(i),clusterList.get(j));
                        d.insert(p[k].distance);
                        k++;
                    }
                }

                if(num==3){
                    for (int i = 0; i < 3; i++) {
                        clusterList.sort(cluster::compareTo);
                        System.out.println(String.format(""%d %.2f %.2f"",clusterList.get(i).size,clusterList.get(i).pts.x(),clusterList.get(i).pts.y()));
                    }
//                    pair[] pForLast=new pair[15];
//                    for (int i = 0,k=0; i < 6; i++) {
//                        for (int j = i+1; j < 6; j++) {
//                            p[k]=new pair(clusterListForLast.get(i),clusterListForLast.get(j));
//                            mMin.insert(p[k].distance);
//                            k++;
//                        }
//                    }
//                    mostMin=mMin.delMin();
                    System.out.println(String.format(""%.2f"",mostMin));
                }

                min=d.delMin();

                for(int i=0;i<p.length;i++){
                    if(p[i].distance==min){
                        a=p[i].c1;
                        b=p[i].c2;
                        break;
                    }
                }

                newCluster=add_cluster(a,b);
                if(num<=6){
                    clusterListForLast.add(a);
                    clusterListForLast.add(b);
                }

                clusterList.remove(clusterList.indexOf(a));
                clusterList.remove(clusterList.indexOf(b));
                clusterList.add(newCluster);
                num--;


            }
        }
    }
}

