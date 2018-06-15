
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author CHIN LUNG
 */
public class CriticalDis {
        
    private static Graph graph ;
    private static DepthFirstPaths dfs;
    protected static Point2D[] AllPoint;
    protected static double[][]vertices;
    protected static int Totalpoints = 0;
    
       public static void main(String[] args)throws Exception {
           int start = 0;int end = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {  //to amend when upload
            Totalpoints = Integer.parseInt(br.readLine().trim());
            //number of vertices
            graph = new Graph(Totalpoints);

            vertices = new double[Totalpoints][2]; AllPoint = new Point2D[Totalpoints];
            // 1. read in the file containing N 2-dimentional points
            double min = 10;double max = 0;
            for(int i = 0 ; i < Totalpoints;i++)
            {
                String[] data= br.readLine().split("" "");
                vertices[i][0] = Double.parseDouble(data[0]) ;
                vertices[i][1] = Double.parseDouble(data[1]) ;
                Point2D p2 = new Point2D(vertices[i][0],vertices[i][1]) ;
                //find the start and target
                 
                if( (p2.x()+p2.y()) < min)
                {
                    min = (p2.x()+ p2.y());
                    start = i;
                }
                 
                if( (p2.x()+p2.y()) > max)
                {
                    max = p2.x() + p2.y();
                    end = i;
                }                               
                //StdDraw.circle(p2.x(), p2.y(), 0.01);
                AllPoint[i] = p2;
            }    
        }
            
            dfs = new DepthFirstPaths(graph,start);
            // create all path
            double d = 0;
            List<Double> distance = new ArrayList<Double>();
            for(int i = 0; i < AllPoint.length ;i++)
            {
                for(int j = 0; j < AllPoint.length ;j++)
                {
                    if((AllPoint[i].x()  < AllPoint[j].x()) && (AllPoint[i].y() < AllPoint[j].y()))
                    {
                        //graph.addEdge(i, j);
                        distance.add(AllPoint[i].distanceTo(AllPoint[j]));
                    }
                }               
            }
            double dis [] = new double[distance.size()];
            for(int a = 0; a < distance.size();a++)
            {
                dis[a] = distance.get(a);
            }
            Arrays.sort(dis);
            int count = 0;
            double last = 0;
            boolean flag = false;
            while(flag == false)
            {
              graph = new Graph(Totalpoints);
              d = dis[++count];  
              for(int i = 0; i < AllPoint.length ;i++)
              {
                 for(int j = 0; j < AllPoint.length ;j++)
                 {
                     if((AllPoint[i].x()  < AllPoint[j].x()) && (AllPoint[i].y() < AllPoint[j].y()) &&AllPoint[i].distanceTo(AllPoint[j]) <d)
                     {
                         graph.addEdge(i, j);
                     }
                 }               
              }
              dfs = new DepthFirstPaths(graph,start);
              flag = dfs.hasPathTo(end);

          
            }
//            List<Integer> sources = new ArrayList<Integer>();
//
//             for (int x : bfs.pathTo(end)) 
//             {
//                 sources.add(x);
//             }
//             double d = 0;
//            for(int a = 0; a < sources.size()-1;a++)
//            {
//                d+=AllPoint[sources.get(a)].distanceTo(AllPoint[sources.get(a+1)]);
//            }
            
                   
            System.out.printf(""%1.3f\n"", dis[count-1]);
    }  
}

