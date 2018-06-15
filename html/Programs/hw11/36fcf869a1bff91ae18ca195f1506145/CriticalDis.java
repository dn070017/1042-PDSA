
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
        
    private static Digraph graph ;
    private static DirectedDFS dfs;
    private static BreadthFirstPaths bfs;
    protected static Point2D[] AllPoint;
    protected static double[][]vertices;
    protected static int Totalpoints = 0;
    
       public static void main(String[] args)throws Exception {
           int start = 0;int end = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {  //to amend when upload
            Totalpoints = Integer.parseInt(br.readLine().trim());
            //number of vertices
            graph = new Digraph(Totalpoints);

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
            
            
            // create all path

            boolean flag = false;
            double d = 0.00001;
            while(flag == false)
            {

              graph = new Digraph(Totalpoints);

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
              dfs = new DirectedDFS(graph,start);
              flag = dfs.marked(end);

              d+=0.00001;
            }

            
                   
            System.out.printf(""%1.3f\n"", d);
            
    }  
}

