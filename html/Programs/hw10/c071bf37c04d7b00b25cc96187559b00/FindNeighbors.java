/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LinWeiKuan
 */
//import edu.princeton.cs.algs4.Point2D;
i//mport edu.princeton.cs.algs4.MaxPQ;
//import edu.princeton.cs.algs4.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import edu.princeton.cs.algs4.MaxPQ;
import java.util.Comparator;
.

public class FindNeighbors 
{
    public KdTree tree;
     public FindNeighbors(){}
     public Point2D target;
     public Point2D[] allPoints;

    // TODO
    // please use the Point2D from algs4.jar 
     
     
     
     
     
     
    public void init(Point2D[] points)
    {
        allPoints = points;       
        tree= new KdTree();
        tree.root= new Node(points[0]);
   
         for(int i =1; i<points.length; i++)
         {
             
             tree.add(points[i]);
         }
         
       //  System.out.print(tree.size);

    }
    
    
    
    public class Node
    {
        public double lineDistance;
        public Node left;
        public Node right;
       public Point2D value;
        public double pointDistance;
         public boolean isHorizontal;
        


        public Node(Node left, Node right, Point2D value)
        {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Node(Point2D value)
        {
            this.left = null;
            this.right = null;
            this.value = value;
        }

        public Node(Node node) {
           this.left=node.left;
           this.right=node.right;
           this.value=node.value;
           this.lineDistance=node.lineDistance;
           this.pointDistance = node.pointDistance;
           this.isHorizontal = node.isHorizontal;
        }

        public Node getLeft()
        {
            return(this.left);
        }

        public Node getRight()
        {
            return(this.right);
        }

        public Point2D getValue()
        {
            return(this.value);
        }

        public void setLeft(Node left)
        {
            this.left = left;
        }

        public void setRight(Node right)
        {
            this.right = right;
        }

        public void setValue(Point2D value)
        {
            this.value = value;
        }
        
       
    }
    public class KdTree 
    {
        private int size;
        public Node root;
        
        public KdTree() {}

    /*
     * is the set empty?
     */
    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }
    
    private boolean isLarge( Node x, Point2D pt )
    {
        if(x.isHorizontal)
        {
            return (x.value.x()>pt.x());
        }
        else
        {
            return (x.value.x()>pt.y());
        } 
    }
    

     public void add( Point2D p) 
     {
         Node now =root;
        if (root == null) 
        {
            size++;
          root =  new Node(p);
          root.isHorizontal = true;
        }
        while(true)
        {
            Point2D x;
            if( now.value.equals(p)) 
            {
                return;
            }
            else if(isLarge(now, p))//node is large than p
            {
                if(now.left == null)
                {
                    Node newNode = new Node(null,null,p);
                    newNode.isHorizontal = !now.isHorizontal;
                    now.setLeft(newNode);
                    now = newNode;
                    size++;
                    break;
                }
                else
                {
                    now = now.getLeft();
                }
            }
            else
            {
                if(now.right == null)
                {
                    Node newNode = new Node(null,null,p);
                    newNode.isHorizontal = !now.isHorizontal;
                    now.setRight(newNode);
                    now = newNode;
                    size++;
                    break;
                }
                else now= now.getRight();   
            }
            }
        }
    }
    
private int count =0;
private int number;
public MaxPQ<Point2D> pq ;
public class Point2DComparator implements Comparator<Point2D> 
{
        public int compare(Point2D o1, Point2D o2)
        {
            return 1*Double.compare(o1.distanceTo(target), o2.distanceTo(target));
        }
}


    public boolean needToCheck(Node node)
    {
        if(count<number)
        {
            return true;
        }
        else
        {
            if(node.lineDistance>pq.max().distanceTo(target))
            {
                return false;
            }
            
            else 
                return true;
        }
    }
    
    public void toDo(Node node)
    {
        //System.out.print(count+""\n"");
        if(count<number)
        {
            pq.insert(node.value);
            count++;
        }
        else if(node.pointDistance<pq.max().distanceTo(target))
        {
            pq.delMax();
            pq.insert(node.value);
            count++;
        }
    }

 static int shit =0;
    public void traverse(Node node)
    {   
        if(node == null)
        {
            return;
        }
        else
        {   
            shit++;
            Node current =(node);
            current.pointDistance = current.value.distanceTo(target);
            
            if(current.isHorizontal)
            {
                current.lineDistance=Math.abs(current.value.x()-target.x());
            }
            else
            {
                current.lineDistance=Math.abs(current.value.y()-target.y());
            }
            if(current.isHorizontal)
            {
                if(current.value.x()>target.x())
                {
                    traverse(current.left);//traverse to the nearby points
                    
                    toDo(current);//operation
                    
                    if( needToCheck(current))
                    {
                        traverse(current.right);
                    }
                    
                    return;
                }
                 else
                {
                    traverse(current.right);//traverse to the nearby points
                    
                    toDo(current);//operation
                    
                    if( needToCheck(current))
                    {
                        traverse(current.left);
                    }
                    
                    return;
                }
            }
            else
            {
                if(current.value.y()>target.y())
                {
                    traverse(current.left);//traverse to the nearby points
                    
                    toDo(current);//operation
                    
                    if( needToCheck(current))
                    {
                        traverse(current.right);
                    }
                    
                    return;
                }
                 else
                {
                    traverse(current.right);//traverse to the nearby points
                    
                    toDo(current);//operation
                    
                    if( needToCheck(current))
                    {
                        traverse(current.left);
                    }
                    
                    return;
                }
            }
        }
        
    }
            

    public Point2D[] query(Point2D point, int k)
    {

        Point2D[] result = new Point2D[k];

                
        FindNeighbors neighbors = new FindNeighbors();
        neighbors.number=k;
        neighbors.pq=new MaxPQ<>(neighbors.number,neighbors.new Point2DComparator());
       // neighbors.init(array);
         neighbors.target = new Point2D (point.x(), point.y() );
        neighbors.traverse(neighbors.tree.root);
        int size = neighbors.pq.size(); 
        for(int i = number-1; i>=0;i++)
        {
            result[i]= neighbors.pq.delMax();
           // Point2D temp = neighbors.pq.delMax();
            //System.out.print(temp+""\t""+temp.distanceTo(neighbors.target)+""\n"");
        }
      
                 
        
        
        return result;
                
    }
    
    
    public void printNode(Node node)
    {
        if(node!= null)
        {
            printNode(node.left); 
            System.out.print(node.value+""\n"");
             printNode(node.right);
        }
        else 
            return;
    }

   public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        String line;
        ArrayList<Point2D> points = new ArrayList<Point2D>();
        int counter = 0;
        BufferedReader br = new BufferedReader(new FileReader(args[0])); 
         while((line = br.readLine())!=null )
         {

            double x=Double.parseDouble(line.split("" "")[0]);
            double y=Double.parseDouble(line.split("" "")[1]);
            counter++;
           points.add(new Point2D(x,y));
         }
         
       Point2D[] array = new Point2D[counter];
       for(int i =0; i< points.size();i++)
       {
           array[i] = points.get(i);
       }
        FindNeighbors neighbors = new FindNeighbors();
        neighbors.number=3;
        neighbors.pq=new MaxPQ<>(neighbors.number,neighbors.new Point2DComparator());
        neighbors.init(array);
         neighbors.target = new Point2D (0.531440, 0.616918 );
        neighbors.traverse(neighbors.tree.root);
        int size = neighbors.pq.size(); 
        for(int i = 0; i<size;i++)
        {
            Point2D temp = neighbors.pq.delMax();
            System.out.print(temp+""\t""+temp.distanceTo(neighbors.target)+""\n"");
        }
      
                 
    }
}

