import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class FindNeighbors {

    Node root;
    MaxPQ<Nearest> nearests;
    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        List<Point2D> _pointsList = Arrays.asList(points);
        this.root = CreateTreeRecursive(_pointsList,0);
    }
    
    public Node CreateTreeRecursive(List<Point2D>  pointSet,int depth)
    {
        if(pointSet.isEmpty()) return null;
        depth = depth % 2;
        if(depth == 0)
        {
            Collections.sort(pointSet, Point2D.X_ORDER);
        }
        else
        {
            Collections.sort(pointSet ,Point2D.Y_ORDER);
        }
        int mid = pointSet.size() / 2;
        Node root = new Node(CreateTreeRecursive(pointSet.subList(0, mid),depth+1),CreateTreeRecursive(pointSet.subList(mid+1, pointSet.size()),depth+1), pointSet.get(mid));
        return root;
    }

    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point, int k){
        nearests = new MaxPQ<Nearest>();
        K_Nearest(root,point,k,0);
        Point2D[] result = new Point2D[nearests.size()];
        for(int i = nearests.size()-1;i >= 0;i--)
        {
            result[i] = nearests.delMax().thepoint;
        }
        return result;
    }
    public void K_Nearest(Node root,Point2D point,int k,int depth){
        if(root.getValue() != null)
        {
            nearests.insert(new Nearest(root.getValue(),point));
            if(nearests.size() > k)
            {
                nearests.delMax();
            }
            depth = depth %2;
            if(depth == 0)
            {
                if(point.x() < root.getValue().x())
                {
                    double dis = root.getValue().x() - point.x();
                    if(root.getLeft() != null )
                    K_Nearest(root.getLeft(),point,k,depth+1);
                    if(root.getRight() == null || ( nearests.size() == k  && dis > nearests.max().getDistance()))
                    {
                        
                    }
                    else
                    {    
                        K_Nearest(root.getRight(),point,k,depth+1);
                    }
                }
                else
                {
                    double dis = - root.getValue().x() + point.x();
                    if(root.getRight() != null)
                    K_Nearest(root.getRight(),point,k,depth+1);
                    if(root.getLeft() == null || ( nearests.size() == k  && dis > nearests.max().getDistance()))
                    {
                        
                    }
                    else
                    {    
                        K_Nearest(root.getLeft(),point,k,depth+1);
                    }
                }
            }
            else
            {
                if(point.y() < root.getValue().y())
                {
                    double dis = root.getValue().y() - point.y();
                    if(root.getLeft() != null)
                    K_Nearest(root.getLeft(),point,k,depth+1);
                     if(root.getRight() == null || ( nearests.size() == k  && dis > nearests.max().getDistance()))
                    {
                        
                    }
                    else
                    {    
                        K_Nearest(root.getRight(),point,k,depth+1);
                    }
                }
                else
                {
                    double dis = - root.getValue().y() + point.y();
                    if(root.getRight() != null)
                    K_Nearest(root.getRight(),point,k,depth+1);
                     if(root.getLeft() == null || ( nearests.size() == k  && dis > nearests.max().getDistance()))
                    {
                        
                    }
                    else
                    {    
                        K_Nearest(root.getLeft(),point,k,depth+1);
                    }
                }
            }
        }
    }
    /*public static void main(String[] args) throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String stream = br.readLine();
            List<Point2D> _pointsList = new ArrayList();
            while(stream != null)
            {
                String[] coordinates = stream.split("" "");
                _pointsList.add(new Point2D(Double.valueOf(coordinates[0]),Double.valueOf(coordinates[1])));
                stream = br.readLine();
            }
            Point2D[] _pointsArray = new Point2D[_pointsList.size()]; 
            _pointsList.toArray(_pointsArray);
            FindNeighbors fb = new FindNeighbors();
            fb.init(_pointsArray);
            Point2D[] result = fb.query(new Point2D(0.1354339200, 0.7019446863), 3);
            for(int i = 0; i < result.length;i++)
            {
                System.out.println(String.format(""%f %f"", result[i].x(),result[i].y()));
            }
        }
    }*/

}

class Node{
    private Node left;
    private Node right;
    private Point2D point;

    public Node(Node left, Node right, Point2D point){
        this.left = left;
        this.right = right;
        this.point = point;
    }

    public Node getLeft(){
        return(this.left);
    }

    public Node getRight(){
        return(this.right);
    }

    public Point2D getValue(){
        return(this.point);
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public void setValue(Point2D point){
        this.point = point;
    }

}

class Nearest implements Comparable<Nearest>
{
    Point2D thepoint;
    Point2D querypoint;
    double distance;
    
    Nearest(Point2D thepoint,Point2D querypoint)
    {
        this.thepoint = thepoint;
        this.querypoint = querypoint;
    }
    
    public double getDistance()
    {
        return this.thepoint.distanceTo(this.querypoint);
    }
    
    public int compareTo(Nearest that)
    {
        double d1 = this.getDistance();
        double d2 = that.getDistance();
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
