import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
public class FindNeighbors {
public FindNeighbors(){}
public class Node{
    private Node left;
    private Node right;
    private int xy;
    private Point2D point;
    public Node(Node left, Node right, int xy, Point2D point ){
        this.left = left;
        this.right = right;
        this.xy = xy;
        this.point = point;        
    }
    public Node getLeft(){        return(this.left);    }
    public Node getRight(){        return(this.right);    }
    public int getxy(){        return(this.xy);    }
    public Point2D getPoint(){        return(this.point);    }    
    public void setLeft(Node left){        this.left = left;    }
    public void setRight(Node right){        this.right = right;    }
    public void setxy(int xy){        this.xy = xy;    }
    public void setpoint(Point2D point){        this.point = point;    }    
}    
    public Node root;
    public ST<Double,Point2D> pq = new ST<Double,Point2D>();
public void sp(Point2D[] a, int xy, int lr , Node f){
    int l = a.length;
    int mid = l / 2;
    Node ch;
    if(l > 1){
        if(xy==0){
            Arrays.sort(a,Point2D.X_ORDER);
            ch = new Node(null,null,xy,a[mid]);
            if(lr==0){
                f.left=ch;
            }   else{f.right=ch;}
            xy=1;
        } else{
            Arrays.sort(a,Point2D.Y_ORDER);
            ch = new Node(null,null,xy,a[mid]);
            if(lr==0){
                f.left=ch;
            }   else{f.right=ch;}            
            xy=0;
        }
        Point2D[] b = new Point2D[mid];
        Point2D[] c = new Point2D[l-mid];        
        for (int i = 0; i < mid; i++){            b[i]=a[i];        }
        for (int i = mid; i < l; i++){            c[i-mid]=a[i];        }        
        sp(b,xy,0,ch);
        sp(c,xy,1,ch);
    }
}
    
    public Node init(Point2D[] points){
        int l = points.length;
        int mid = l/2;
        Arrays.sort(points,Point2D.X_ORDER);
        root = new Node(null,null,0,points[mid]);
        Point2D[] b = new Point2D[mid];
        Point2D[] c = new Point2D[l-mid];        
        for (int i = 0; i < mid; i++){            b[i]=points[i];        }
        for (int i = mid; i < l; i++){            c[i-mid]=points[i];        }        
        sp(b,1,0,root);
        sp(c,1,1,root);
        return root;
    }
    public Stack<Node> s = new Stack<Node>();
    public void find(Node temp,Point2D que,int k){
        s.push(temp);
        Double value;
        pq.put(temp.point.distanceTo(que), temp.point);

        if(pq.size()>k){pq.delete(pq.max());}
        if(temp.xy==0){
             value = que.x()-temp.point.x();
        } else{  value = que.y()-temp.point.y();    }
        Double va=value;
        if(va<0){va=va*(-1);}
        if(pq.size()==k & pq.max()<va){
        } else{
        if( value > 0 & temp.right!=null){
            find(temp.right, que,k);
        }   else if( value < 0 & temp.left!=null){
            find(temp.left, que,k);
        }
    }}
    public void find2(Node temp,Point2D que, int k){
        Double value;
        if(temp.xy==0){
             value = que.x()-temp.point.x();
        } else{  value = que.y()-temp.point.y();    }
        if( value > 0 & temp.left!=null){
            find(temp.left, que,k);
        }   else if( value < 0 & temp.right!=null){
            find(temp.right, que,k);
        }        
    }
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        Node temp = root;
        find(temp,point,k);
        while(pq.size()<k){
           Node spop = s.pop();
           find2(spop,point,k);
        }
        while(pq.size()>k){
             s.pop();
        }
        while(s.size()>0){
            Node spop=s.pop();
            find2(spop,point,k);
        }
        for(int i=k-1;i>-1;i--){
        result[i]=pq.get(pq.max());
        pq.delete(pq.max());
        }
        return result;
        
     // the points should be sorted accordingly to their distances to the query, from small to large
    }
    
}




