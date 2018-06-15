import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


//import edu.princeton.cs.algs4.Point2D;
//import edu.princeton.cs.algs4.MaxPQ;
//import edu.princeton.cs.algs4.MinPQ;
//import edu.princeton.cs.algs4.Digraph;
//import edu.princeton.cs.algs4.DirectedDFS;


public class CriticalDis {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
  
            //String nullData;
            String[] data = br.readLine().split("","");            
            int N = Integer.parseInt(data[0]);
            String[] pointXY;
            
            Point2D[] point=new Point2D[N];
            double[] xPlusY=new double[N];
            
            MaxPQ<Double> maxPQ=new MaxPQ<Double>();
            MinPQ<Double> minPQ=new MinPQ<Double>();
            int recordMinIndex=0;
            int recordMaxIndex=0;
            for(int j=0; j<N;j++)
            {
                pointXY = br.readLine().split("" "");
                double pointX=Double.parseDouble(pointXY[0]);
                double pointY=Double.parseDouble(pointXY[1]);
                xPlusY[j] = pointX + pointY;
                point[j]=new Point2D(pointX, pointY);
                maxPQ.insert(xPlusY[j]);
                minPQ.insert(xPlusY[j]);
                if(maxPQ.size()>1)//取得最小
                {
                    maxPQ.delMax();
                }
                if(minPQ.size()>1)//取得最大
                {
                    minPQ.delMin();
                }
                if(xPlusY[j]== maxPQ.max())
                {
                    recordMinIndex = j;
                }
                if(xPlusY[j]== minPQ.min())
                {
                    recordMaxIndex = j;
                }

            }

            double d=0.999;
            do
            {                
                Digraph G=new Digraph(N);

                for(int i=0;i<N-1;i++)
                {
                    for(int j=i+1;j<N;j++)
                    {
                        if(point[i].x() < point[j].x() && point[i].y() < point[j].y() && point[i].distanceTo(point[j]) <= d)
                        {
                            G.addEdge(i, j);                        
                        }
                        else if(point[i].x() > point[j].x() && point[i].y() > point[j].y() && point[i].distanceTo(point[j]) <= d)
                        {
                            G.addEdge(j, i);
                        }
                    }
                }
                DirectedDFS dfs=new DirectedDFS(G,recordMinIndex);
                if(!dfs.marked(recordMaxIndex))
                {    
                    d=d+0.0005;
                    break;
                }
                d-=0.001;
            } while (true);
           

            
//            System.out.printf(maxPQ.max().toString()+""\n""+String.valueOf(point[recordMinIndex].x())+"" ""+String.valueOf(point[recordMinIndex].y())+""\n"");
//            System.out.printf(minPQ.min().toString()+""\n""+String.valueOf(point[recordMaxIndex].x())+"" ""+String.valueOf(point[recordMaxIndex].y())+""\n"");
            
            
            System.out.printf(""%1.3f\n"", d);
            
 
        }
    }
}




    



