import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 *
 * @author Kyle
 */



public class Clustering {

    private Point2D[] points;
    private Point2D centroid;
    private int size;
    
    
    
    public Clustering(Point2D[] points)
    {
        this.points = points;
        this.size = points.length;
        this.centroid = getCentroid();
    }
    
    public Clustering(Point2D point)
    {
        this.points = new Point2D[1];
        this.points[0] = point;
        this.size = points.length;
        this.centroid = getCentroid();
    }
    
    public Point2D getCentroid()
    {
        double x = 0,y =0;
        for(int i = 0; i < size; i++)
        {
            x += this.points[i].x();
            y += this.points[i].y();
        }
        this.centroid = new Point2D(x/size,y/size);
        return this.centroid;
    }
    
    
    
    
    public static void main(String[] args) throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        
            int n =Integer.parseInt(br.readLine());
            List<Clustering> Clusters = new ArrayList<Clustering>();
            for(String in = br.readLine(); in != null; in = br.readLine()) 
            {
                String[] coordinates = in.split("" "");
                Point2D thePoint = new Point2D(Double.parseDouble(coordinates[0]),Double.parseDouble(coordinates[1]));
                Clusters.add(new Clustering(thePoint));
            }
            MinPQ<Pair> pq = new MinPQ<Pair>();
            
            for(int i = 0; i < n;i++)
            {
                for(int j = i+1;j < n;j++)
                {
                    pq.insert(new Pair(Clusters.get(i),Clusters.get(j)));
                }
            }
            
            int numberC = n;
            Pair nearestP;
            while(true)
            {
                //calculate dist
                do
                {
                    if(pq.isEmpty()) 
                    {
                        System.out.println(String.format(""%d %.2f %.2f"",Clusters.get(0).size,Clusters.get(0).getCentroid().x(),Clusters.get(0).getCentroid().y()));
                        System.out.println(0);
                        return;
                    }
                    nearestP = pq.delMin();
                }while(!Clusters.contains(nearestP.a) || !Clusters.contains(nearestP.b));
                //merge - remove a and b , new c
                int totalSize = nearestP.a.size + nearestP.b.size;
                Point2D[] totalPoints = new Point2D[totalSize];
                System.arraycopy(nearestP.a.points, 0, totalPoints, 0, nearestP.a.points.length);
                System.arraycopy(nearestP.b.points, 0, totalPoints, nearestP.a.points.length, nearestP.b.points.length);
                Clusters.add(new Clustering(totalPoints));
                Clusters.remove(nearestP.a);
                Clusters.remove(nearestP.b);
                numberC--;
                if(numberC == 3) break;
                for(int i = 0; i < Clusters.size()-1;i++)
                {
                    pq.insert(new Pair(Clusters.get(i),Clusters.get(numberC-1)));
                }
            }
            
            Collections.sort(Clusters,new Comparator<Clustering>(){
                public int compare(Clustering c1,Clustering c2){
                    return c2.size - c1.size;
                }
            });
            double smallestD = Double.MAX_VALUE;
            double D = 0.0;
            for(int i = 0;i < 3;i++)
            {
                System.out.println(String.format(""%d %.2f %.2f"",Clusters.get(i).size,Clusters.get(i).getCentroid().x(),Clusters.get(i).getCentroid().y()));
                for(int j = i+1;j < 3;j++)
                {
                    for(int s1 = 0;s1 < Clusters.get(i).size ;s1++)
                    {
                        for(int s2 = 0;s2 < Clusters.get(j).size ;s2++)
                        {
                            D = Clusters.get(i).points[s1].distanceTo(Clusters.get(j).points[s2]);
                            if(D < smallestD)
                            {
                                smallestD = D;
                            }
                        
                        }
                        
                    }
                }
            }
            System.out.println(String.format(""%.2f"",smallestD));
            
        }
    }
    
}

class Pair implements Comparable<Pair>
{
    Clustering a;
    Clustering b;
    double distance;
    
    public Pair(Clustering a,Clustering b)
    {
        this.a = a;
        this.b = b;
        this.distance = getDistance();
    }
    
    public double getDistance()
    {
        return a.getCentroid().distanceTo(b.getCentroid());
    }
    
    public int compareTo(Pair that)
    {
        double d1 = this.distance;
        double d2 = that.distance;
        if(d1 > d2)
        {
            return 1;
        }
        else if(d1 == d2)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}




