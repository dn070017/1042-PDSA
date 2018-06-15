import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.List;
/**
 *
 * @author CHIN LUNG
 */
public class MyConvexHull {
    protected static double[][]vertices;
    protected static double MD;
    protected static Point2D[] AllPoint;
    protected static Point2D[] GroupPoint ;
    protected static Point2D[][] GroupPoints ;
    protected static int [][]Coordinate;
    protected static WeightedQuickUnionUF wuf;
        public static int[] ConvexHullVertex(Point2D[] a) {          
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
            double max = 1;int index = 0;double [] angle = new double[a.length];
            int[] indices; List<Integer> temp = new ArrayList<Integer>();
            Stack<Point2D> order = new Stack<Point2D>();
            for(int i = 0; i < a.length ;i++) // find the lowest y
            {
                if(a[i].y() < max)
                {
                    max = a[i].y(); index = i ; 
                }
            }
            for(int j = 0; j < a.length;j++)
            {
                angle[j] = a[j].theta();
            }
     
          order.push(a[0]);
        for(int k = 0; k < a.length;k++)  
        {
           if(Point2D.ccw(order.peek(),a[k+1], a[k+2]) == -1) 
           {
               order.push(a[k]);
           }
           else if(Point2D.ccw(order.peek(),a[k+1], a[k+2]) != -1)
           {
             
           }
        }
           indices = new int[temp.size()];
         
            return null;
    }

    public static void main(String[] args)throws Exception {
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            MD = Double.parseDouble(br.readLine().trim()) ;
            int Points = Integer.parseInt(br.readLine().trim());
            vertices = new double[Points][2]; AllPoint = new Point2D[Points];int [] parent = new int [Points];
            int [] copyParent = new int [Points];
            // 1. read in the file containing N 2-dimentional points
            for(int i = 0 ; i < Points;i++)
            {
                String[] data= br.readLine().split("" "");
                vertices[i][0] = Double.parseDouble(data[0]) ;
                vertices[i][1] = Double.parseDouble(data[1]) ;
                Point2D p2 = new Point2D(vertices[i][0],vertices[i][1]) ;
                //StdDraw.circle(p2.x(), p2.y(), 0.01);
                AllPoint[i] = p2;
            }
            // 2. create an edge for each pair of points with a distance <= d
            Coordinate = new int[Points][Points];
            wuf = new WeightedQuickUnionUF(Points);
            for(int i = 0; i < AllPoint.length;i++)
            {
                for(int j = i+1 ; j < AllPoint.length;j++)
                {
                    if(AllPoint[i].distanceTo(AllPoint[j]) <= MD)
                    {
                        Coordinate[i][j] = 1;
                        wuf.union(i, j);
                    }
                }
            } 
        // 3. find connected components (CCs) with a size >= 3
            int mark = 0;  ;int []count = new int[Points];//共有幾個cc是大於3個ㄉ 
            int Count = 0; int index = 0;
            for(int i = 0; i < Points;i++)
            {
                parent[i] = wuf.find(i);
                copyParent[i] = parent[i];
            }
          GroupPoint = new Point2D[Points];
            for(int i = 0; i < parent.length;i++)
            {  
               for(int j = i; j < copyParent.length;j++)
                {
                    if(parent[i] == copyParent[j])
                    {
                        copyParent[j] = -1;
                        GroupPoint[j]=AllPoint[j];
                        count[i]++;
                    }

                }
                
            }

        for(int i = 0; i < count.length;i++)
        {
            if(count[i]>=3)
            {             
                Count++;
            }
        }
        GroupPoints = new Point2D[Count][];
        for(int i = 0; i < count.length;i++)
        {
            if(count[i]>=3)
            {             
                GroupPoints[mark] = new Point2D[count[i]];
                for(int k = index; k < index+count[i];k++)
                {
                    GroupPoints[mark][k-index] = AllPoint[k];                  
                }
               mark++;  index = count[i];
            }
        }
        // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
            int total = 0;
            for(int i = 0; i < Count;i++)
            {
                total+=ConvexHullVertex(GroupPoints[i]).length;
            }
        // 5. count the number of points in N serving as a convex hull vertex, print it
            System.out.println(total);
            }                               
    }
}

