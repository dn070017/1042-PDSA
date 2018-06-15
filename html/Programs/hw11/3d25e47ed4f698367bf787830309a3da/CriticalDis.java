import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 *
 * @author Kyle
 */
public class CriticalDis {

    public static final Comparator<Point2D> Total_ORDER = new TotalOrder();
    private static class TotalOrder implements Comparator<Point2D> {
        public int compare(Point2D p1, Point2D p2) {
            double v1 =0.0, v2 =0.0;
            v1 = p1.x() + p1.y();
            v2 = p2.x() + p2.y();
            if(v1 > v2)
            {
                return 1;
            }
            else if(v1 < v2)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    
    }
    
    private Digraph ConstructDigraph(List<Point2D> _pointsList,double d)
    {
        int n = _pointsList.size();
        Digraph dg = new Digraph(n);
        for(int i = 0;i < n-1;i++)
            {
                for(int j = i;j < n;j++)
                {
                    Point2D v = _pointsList.get(i);
                    Point2D w = _pointsList.get(j);
                    if(w.x() > v.x() && w.y() > v.y())
                    {
                        if(v.distanceTo(w) <= d)
                        {
                            dg.addEdge(i, j);
                        }
                    }
                }
            }
        return dg;
        
    }
    
    private boolean DFS_Check(Digraph dg,double d)
    {
        int t = dg.V();
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(dg,0);
        return dfs.hasPathTo(t-1);
    }
    
    
    
    public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int n = Integer.valueOf(br.readLine());
            
            String stream = br.readLine();
            List<Point2D> _pointsList = new ArrayList();
            while(stream != null)
            {
                String[] coordinates = stream.split("" "");
                _pointsList.add(new Point2D(Double.valueOf(coordinates[0]),Double.valueOf(coordinates[1])));
                stream = br.readLine();
            }
            Collections.sort(_pointsList ,Total_ORDER);
            Point2D source = _pointsList.get(0);
            //System.out.printf(""%f  %f"", source.x(),source.y());
            double d = 0.0;
            CriticalDis cd = new CriticalDis();
            do
            {
                d = d +0.001;
            }while(!cd.DFS_Check(cd.ConstructDigraph(_pointsList,d),d));
            
            System.out.printf(""%1.3f\n"", d-0.001);
        }
    }
    
}

