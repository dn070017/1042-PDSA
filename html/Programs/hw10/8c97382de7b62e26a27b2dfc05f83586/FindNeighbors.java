/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LinWeiKuan
 */
/*
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
*/
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
        if(count<=number)
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
         //   count++;
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
           // (node);
            node.pointDistance = node.value.distanceTo(target);

            if(node.isHorizontal)
            {
                node.lineDistance=Math.abs(node.value.x()-target.x());
            }
            else
            {
                node.lineDistance=Math.abs(node.value.y()-target.y());
            }

            if(node.isHorizontal)
            {
                if(node.value.x()>target.x())
                {
                    traverse(node.left);//traverse to the nearby points
                    
                    toDo(node);//operation
                    
                    if( needToCheck(node))
                    {
                        traverse(node.right);
                    }
                    
                    return;
                }
                 else
                {
                    traverse(node.right);//traverse to the nearby points
                    
                    toDo(node);//operation
                    
                    if( needToCheck(node))
                    {
                        traverse(node.left);
                    }
                    
                    return;
                }
            }
            else
            {
                if(node.value.y()>target.y())
                {
                    traverse(node.left);//traverse to the nearby points
                    
                    toDo(node);//operation
                    
                    if( needToCheck(node))
                    {
                        traverse(node.right);
                    }
                    
                    return;
                }
                 else
                {
                    traverse(node.right);//traverse to the nearby points
                    
                    toDo(node);//operation
                    
                    if( needToCheck(node))
                    {
                        traverse(node.left);
                        
                    }
                    
                    return;
                }
            }
        }
        
    }
            

    public Point2D[] query(Point2D point, int k)
    {

        Point2D[] result = new Point2D[k];

        this.number=k;
        this.count = 0;
       this.pq=new MaxPQ(this.number,this.new Point2DComparator());
       this.target = new Point2D (point.x(), point.y() );
       this.traverse(this.tree.root);
        int size = this.pq.size(); 
        for(int i = number-1; i>=0;i--)
        {
            result[i]= this.pq.delMax();
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

   public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(""in.txt""))){
            Stack<Point2D> shell = new Stack<Point2D>();
            for(String in = br.readLine(); in != null; in = br.readLine()) {
              String[] p = in.split(""\\s+"");
              shell.push(new Point2D(Double.parseDouble(p[0]),Double.parseDouble(p[1])));
            }
            Point2D[] pp = new Point2D[shell.size()];
            for(int i =pp.length-1; i>-1; i--){
              pp[i]=shell.pop();
            }
            FindNeighbors e = new FindNeighbors();
            e.init(pp);  
            Point2D q1=new Point2D(0.3877173327383, 0.7075952067296792);
            Point2D q2=new Point2D(0.8795693399,0.8849481938);
            Point2D[] re1 =e.query(q1,8);
            
           Point2D[] re2 =e.query(q2,8);
            System.out.println(""result of query1:"");
            for(int i=0;i<8;i++)
            {
              System.out.println(re1[i].x()+"" ""+re1[i].y());
            }
            
         //   System.out.println(q1.distanceTo(new Point2D(0.32847307129408443, 0.6903622540706313)));
           // System.out.println(""\n""+q1.distanceTo(new Point2D(0.3443748307083425, 0.7018484032983843)));

       //    Point2D[] re2 =e.query(q2,5);
        }    
    }
}

