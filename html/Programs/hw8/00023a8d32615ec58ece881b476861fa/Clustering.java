import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.MaxPQ;


public class Clustering {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] header = br.readLine().split("","");
            int N = Integer.parseInt(header[0]);
            Point2D[] point=new Point2D[N];
            

            for(int i=0;i<N;i++) 
            {                             
                String[] pointXY = br.readLine().split("" "");
                double pointX=Double.parseDouble(pointXY[0]);
                double pointY=Double.parseDouble(pointXY[1]);
                point[i]=new Point2D(pointX, pointY);            
            }
            
            int numberOfDistance=(N*(N-1))/2;
            double[] distance=new double[numberOfDistance];
            if(N==1)
            {
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[0].x())+"" ""+ String.format(""%.2f"", point[0].y())+"" ""+""0.00"");
            }
            else if(N==2)
            {
                int k=0;
                double smallNumX;
                double bigNumX;
                double smallNumY;
                double bigNumY;
                         
                distance[0]=Math.sqrt(Math.pow(point[0].x()-point[1].x(), 2)+(Math.pow(point[0].y()-point[1].y(), 2)));
                if(point[0].x()>point[1].x())
                {
                    smallNumX=point[1].x();
                    bigNumX=point[0].x();
                }
                else
                {
                    smallNumX=point[0].x();
                    bigNumX=point[1].x();
                }
                if(point[0].y()>point[1].y())
                {
                    smallNumY=point[1].y();
                    bigNumY=point[0].y();
                }
                else
                {
                    smallNumY=point[0].y();
                    bigNumY=point[1].y();
                }
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[0].x())+"" ""+ String.format(""%.2f"", point[0].y())+"" ""+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[1].x())+"" ""+ String.format(""%.2f"", point[1].y())+"" ""+String.format(""%.2f"", distance[0]));
            }
            else if(N==3)
            {
                int k=0;
                for(int i=0;i<N-1;i++)
                {
                    for(int j=i+1;j<N;j++)
                    {
                        distance[k]=Math.sqrt(Math.pow(point[i].x()-point[j].x(), 2)+(Math.pow(point[i].y()-point[j].y(), 2)));
                        k++;
                    }
                }
                MaxPQ<Double> maxPQ=new MaxPQ<Double>();
                for(int i=0;i<k;i++)
                {
                    maxPQ.insert(distance[i]);
                    if(maxPQ.size()>1)
                    {
                        maxPQ.delMax();
                    }
                }
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[0].x())+"" ""+ String.format(""%.2f"", point[0].y())+"" ""+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[1].x())+"" ""+ String.format(""%.2f"", point[1].y())+"" ""+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[2].x())+"" ""+ String.format(""%.2f"", point[2].y())+"" ""+String.format(""%.2f"", maxPQ.max()));
            }
            else            
            {
                int k=0;
                for(int i=0;i<N-1;i++)
                {
                    for(int j=i+1;j<N;j++)
                    {
                        distance[k]=Math.sqrt(Math.pow(point[i].x()-point[j].x(), 2)+(Math.pow(point[i].y()-point[j].y(), 2)));
                        k++;
                    }
                }

                MaxPQ<Double> maxPQ=new MaxPQ<Double>();


                for(int i=0;i<k;i++)
                {
                    maxPQ.insert(distance[i]);
                    if(maxPQ.size()>1)
                    {
                        maxPQ.delMax();
                    }
                }

                int pointNum1=0;
                int pointNum2=0;
                for(int i=0;i<N-1;i++)
                {
                    for(int j=i+1;j<N;j++)
                    {
                        if(maxPQ.max()==Math.sqrt(Math.pow(point[i].x()-point[j].x(), 2)+(Math.pow(point[i].y()-point[j].y(), 2))))
                        {
                            pointNum1=i;
                            pointNum2=j;
                        }                    
                    }
                }




                StdOut.print(point[pointNum1].x()+"" , ""+point[pointNum1].y()+""\n"");
                StdOut.print(point[pointNum2].x()+"" , ""+point[pointNum2].y()+""\n"");

                StdOut.print(maxPQ.max()+""\n"");
                StdOut.print(k+""\n"");

                for(int i=0;i<k;i++)
                {
                    StdOut.print(String.valueOf(distance[i])+""\n"");

                }

                for(int i=0;i<N;i++)
                {
                    StdOut.print(point[i].x()+"" , ""+point[i].y()+""\n"");
                }

            }
            

        }
    }
}

class Cluster 
{
        public ArrayList points;
	public Point centroid;
	public int id;
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.points = new ArrayList();
		this.centroid = null;
	}
 
	public ArrayList getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}
 
	public void setPoints(ArrayList points) {
		this.points = points;
	}
 
	public Point getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}
 
	public int getId() {
		return id;
	}
	
	public void clear() {
		points.clear();
	}     
}

class Point {
 
    private double x = 0;
    private double y = 0;
    private int cluster_number = 0;
 
    public Point(double x, double y)
    {
        this.setX(x);
        this.setY(y);
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getX()  {
        return this.x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setCluster(int n) {
        this.cluster_number = n;
    }
    
    public int getCluster() {
        return this.cluster_number;
    }
    
.
    protected static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
    }
    
    //Creates random point
    protected static Point createRandomPoint(int min, int max) {
    	Random r = new Random();
    	double x = min + (max - min) * r.nextDouble();
    	double y = min + (max - min) * r.nextDouble();
    	return new Point(x,y);
    }
    
    protected static List createRandomPoints(int min, int max, int number) {
    	List points = new ArrayList(number);
//    	for(int i = 0; i &lt; number; i++) {
//    		points.add(createRandomPoint(min,max));
//    	}
    	return points;
    }
    
    public String toString() {
    	return ""(""+x+"",""+y+"")"";
    }
}

