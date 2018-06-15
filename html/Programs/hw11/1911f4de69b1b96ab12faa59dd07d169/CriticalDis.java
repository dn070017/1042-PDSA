
import java.util.Arrays;
import java.util.Comparator;

public class CriticalDis {
    public CriticalDis(){}
    public static Point2D[] points;
    private static Digraph di;
    public static class Edges
    {
        private double length;
        private int pointOne;
        
        private int pointTwo;
        
        public Edges(int one, int two)
        {
            pointOne = one;
            pointTwo = two;
            length = points[one].distanceTo(points[two]);
        }
        
        public void set(int one, int two)
        {
            pointOne = one;
            pointTwo = two;
            length = points[one].distanceTo(points[two]);
        }
    }
    public static class  EdgeComparator implements Comparator<Edges> 
     {
        public int compare(Edges o1, Edges o2)
        {
            return 1*Double.compare(o1.length, o2.length);
        }
     }
    
    public static void main(String[] args)  throws Exception{
        In in = new In(args[0]);
        int N = Integer.parseInt(in.readLine());
        points = new Point2D[N];
        Point2D S = new Point2D(1,1);int s = -1;
        Point2D T = new Point2D(0,0);int t = -1;
        for(int i = 0;i < N;i++){
            String[] datas = in.readLine().split("" "");
            double a = Double.parseDouble(datas[0]);
            double b = Double.parseDouble(datas[1]);
            points[i] = new Point2D(a,b);
            if(a+b<S.x()+S.y()){ S = points[i];s = i;}
            else if(a+b>T.x()+T.y()){T = points[i];t = i;}   
        }        
        di = new Digraph(points.length);       
        MinPQ<Edges> edges = new MinPQ<Edges>(N*(N-1)/2,new EdgeComparator());
        //MinPQ<Double> minpq = new MinPQ<Double>();
        for(int i = 0;i < points.length;i++){
            for(int j = i + 1;j < points.length;j++){
                if(points[i].x()<points[j].x() && points[i].y()<points[j].y()){
                    double d = Math.sqrt(Math.pow(points[i].x()-points[j].x(), 2)+Math.pow(points[i].y()-points[j].y(), 2));
                    edges.insert(new Edges(i,j));
                    
                }
                else if(points[i].x()>points[j].x() && points[i].y()>points[j].y()){
                    double d = Math.sqrt(Math.pow(points[i].x()-points[j].x(), 2)+Math.pow(points[i].y()-points[j].y(), 2));
                    edges.insert(new Edges(j,i));
                    
                    
                }
            }           
        }
        while(!edges.isEmpty()){
            Edges E1 = edges.delMin();
              int v1 = E1.pointOne;
              int v2 = E1.pointTwo;
              if(points[v1].x()<points[v2].x())
                  di.addEdge(v1, v2);
              else
                  di.addEdge(v2, v1);          
            DirectedDFS find = new DirectedDFS(di,s);
            if(find.marked(t)){
                System.out.printf(""%1.3f\n"", E1.length);
                break;
            }
        }
        
        //處理距離重複問題
        
        
        
        
    }
    
}

