import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
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
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[0].x())+"" ""+ String.format(""%.2f"", point[0].y()));
                StdOut.print(""\n""+""0.00"");
            }
            else if(N==2)
            {
                int k=0;                
                distance[0]=Math.sqrt(Math.pow(point[0].x()-point[1].x(), 2)+(Math.pow(point[0].y()-point[1].y(), 2)));
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[0].x())+"" ""+ String.format(""%.2f"", point[0].y())+"" ""+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[1].x())+"" ""+ String.format(""%.2f"", point[1].y()));
                StdOut.print(""\n""+String.format(""%.2f"", distance[0]));
            }
            else if(N==3)
            {
                int k=0;
                double[] x=new double[3];
                double[] y=new double[3];
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
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[0].x())+"" ""+ String.format(""%.2f"", point[0].y())+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[1].x())+"" ""+ String.format(""%.2f"", point[1].y())+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[2].x())+"" ""+ String.format(""%.2f"", point[2].y()));
                StdOut.print(""\n""+String.format(""%.2f"", maxPQ.max())); 
            }
            else            
            {
                int k=0;
                
                Cluster cluster=new Cluster(point[N]);
                for(int i=0; i<N;i++)
                {
                    cluster.points.add(point[i]);
                }
                int a=0;
                a=cluster.getSize();
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
                
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", cluster.getCentroid().x())+"" ""+ String.format(""%.2f"", point[0].y())+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[1].x())+"" ""+ String.format(""%.2f"", point[1].y())+""\n"");
                StdOut.print(""1""+"" ""+ String.format(""%.2f"", point[2].x())+"" ""+ String.format(""%.2f"", point[2].y()));
                StdOut.print(""\n""+String.format(""%.2f"", maxPQ.max())); 

                
            }
 
        }
    }
}

class Cluster 
{
        public ArrayList<Point2D> points;
	public Pair centroid;
	public int id;
	private int cluster_number = 0;
        private int cluster_numberY = 0;
        
	//Creates a new Cluster
	public Cluster(Point2D point) {
            this.id = id;
            this.points = new ArrayList<Point2D>();
            points.add(point);
            this.centroid = null;
	}
 
	public int getSize() {            
            return points.size();
	}
	
	public void setPoints(ArrayList points) {
		this.points = points;
                
	}
 
        public Point2D getCentroid()
        {
            Point2D[] centroid=new Point2D[1];
            double centroidX=0;
            double centroidY=0;
            for(int i=0;i<cluster_number;i++)
            {
                centroidX+=points.get(i).x();
                centroidY+=points.get(i).y();
            }
            for(int i=0;i<cluster_numberY;i++)
            {
                centroidX+=points.get(i).x();
                centroidY+=points.get(i).y();
            }
            centroidX=centroidX/(cluster_number+cluster_numberY);
            centroidY=centroidY/(cluster_number+cluster_numberY);
            centroid[0]=new Point2D(centroidX, centroidY);
            return centroid[0];
        }
	
 
	public void setCentroid(Pair centroid) {
		this.centroid = centroid;
	}
	
}

class Pair {
 
    private Cluster x;
    private Cluster y;
    private int cluster_number = 0;
    private int cluster_numberY = 0;
    private int N;
    private int k;
 
    public Pair(Cluster x, Cluster y,int N,int k)
    {
        this.x=x;
        this.y=y;
        this.cluster_number=x.getSize();
        this.cluster_numberY=y.getSize();
        this.N=N;
        this.k=k;
    }
    
    public double Distence()
    {
        double dis=0;
        //dis=Math.sqrt(Math.pow(x.getCentroid().x()-y.points.get(i).x();, 2)+(Math.pow(x.points.get(i).y()-y.points.get(i).y(), 2)));
        
        return 0;
    }
    

}

