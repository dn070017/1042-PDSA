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
         tree.root = new Node(points[0]);
         for(int i =1; i<points.length; i++)
         {
             tree.add(points[i]);
         }

    }
    
    
    
    public class Node
    {
        private double lineDistance;
        private Node left;
        private Node right;
        private Point2D value;
        private double pointDistance;
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

        private Node(Node node) {
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
        private Node root;
        
        public KdTree() {}

    /*
     * is the set empty?
     */
    public boolean isEmpty() {
        return root == null;
    }

    /*
     * number of points in the set
     */
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
            
            else if(isLarge(now, p))
            {
                if(now.left == null)
                {
                    Node newNode = new Node(null,null,p);
                    newNode.isHorizontal = !now.isHorizontal;
                    now.setLeft(newNode);
                    now = newNode;
                    size++;
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
                }
                
                else now= now.getRight();
                
            }

            }
        }
    }
private int count = 0;
private int number = 10;

 
MaxPQ<Double> pq = new MaxPQ<Double>(number);
class Point2DComparator implements Comparator<Point2D> 
{
        public int compare(Point2D o1, Point2D o2)
        {
            return Double.compare(o1.distanceTo(target), o2.distanceTo(target));
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
            if(node.lineDistance>pq.max())
            {
                return false;
            }
            
            else 
                return true;
        }
    }
    
    public void toDo(Node node)
    {
        if(count<number)
        {
            pq.insert(node.pointDistance);
        }
        else if(node.pointDistance<pq.max())
        {
            pq.insert(node.pointDistance);
        }

    }


    public void traverse(Node node)
    {   
        Node current = new Node(node);
        
        //base ase
        if(current == null)
        {
            return;
        }
        
        
        //initialize
        else
        {
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
                        traverse(current.right);
                       toDo(current);
                    
                    return;
                }
                
                 else
                {
                    traverse(current.right);//traverse to the nearby points
                    
                    toDo(current);//operation
                    
                    if( needToCheck(current))
                        traverse(current.left);
                       toDo(current);
                    
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
                        traverse(current.right);
                       toDo(current);
                    
                    return;
                }
                
                 else
                {
                    traverse(current.right);//traverse to the nearby points
                    
                    toDo(current);//operation
                    
                    if( needToCheck(current))
                        traverse(current.left);
                       toDo(current);
                    
                    return;
                }
            }
        }
    }
            
            
         /*            
            
            
            
            
            
            
            if(count<number)
            {
                
            }
            if(current.isHorizontal)
            {
                if(point.x()>current.value.x())
                {
                    current.distance = Math.abs(current.value.x()-point.x());
                    current=current.right;
                    traverse(current, point);
                }
               if(point.x()<current.value.x())
                {
                    current.distance = Math.abs(current.value.x()-point.x());
                    current=current.left;
                    traverse(current, point);
                }
            }
        }
        
        
        
        
        if(current.isHorizontal)
            {
                if(point.x()>current.value.x())
                {
                    current.distance = Math.abs(current.value.x()-point.x());
                    current=current.right;
                    traverse(current, point);
                }
               if(point.x()<current.value.x())
                {
                    current.distance = Math.abs(current.value.x()-point.x());
                    current=current.left;
                    traverse(current, point);
                }
            }
            
            
            if(! current.isHorizontal)
            {
                if(point.y()>current.value.y())
                {
                    current.distance = Math.abs(current.value.y()-point.y());
                    current=current.right;
                    traverse(current, point);
                }
               if(point.y()<current.value.y())
                {
                    current.distance = Math.abs(current.value.y()-point.y());
                    current=current.left;
                    traverse(current, point);
                }
            }

    }
    */

    public Point2D[] query(Point2D point, int k)
    {
        
        target = point;
        MaxPQ<Point2D> pq = new MaxPQ<Point2D>(k);
        Point2D[] result = new Point2D[k];
        number = k;
        init(allPoints);
        Node current = tree.root;
        traverse(current);

        /*
        Node current = tree.root;
        while(true)
        {
            if(current.value.equals(point))
            {
                pq.insert(current.value);
               // list.add(current.value);
                current.distance = 0;
                if(current.right != null)               
                {
                    current = current.right;
                }
                else
                {
                    if(current.left == null)
                    {
                        
                    }
                }
                
            }
            
            if(current.isHorizontal)
            {
                if(point.x()>current.value.x())
                {
                    current.distance = Math.abs(current.value.x()-point.x());
                    current=current.right;
                }
               if(point.x()<current.value.x())
                {
                    current.distance = Math.abs(current.value.x()-point.x());
                    current=current.left;
                }
            }
            
            
            if(! current.isHorizontal)
            {
                if(point.y()>current.value.y())
                {
                    current.distance = Math.abs(current.value.y()-point.y());
                    current=current.right;
                }
               if(point.y()<current.value.y())
                {
                    current.distance = Math.abs(current.value.y()-point.y());
                    current=current.left;
                }
            }
        }
        
        
        
        */
        
        return result;
    }

   public static void main(String[] args) 
    {
        

    }
    
}

