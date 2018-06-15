
//package myconvexhull;

//import edu.princeton.cs.alg4.Point2D;
//import WeightedQuickUnionUF;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author steven
 */
public class MyConvexHull {
    
    
    
    public int CCW(java.awt.geom.Point2D a,java.awt.geom.Point2D b, java.awt.geom.Point2D c){
        double ax=a.getX(),ay=a.getY(),bx=b.getX(),by=b.getY(),cx=c.getX(),cy=c.getY();
        double area=(bx-ax)*(cy-ay)-(by-ay)*(cx-ax);
        if (area>0) return 1;
        else if (area==0) return 0;
        else return -1;
    }
    class point{
        public point(java.awt.geom.Point2D Point){
            this.startPoint=Point;
            }
            private java.awt.geom.Point2D startPoint;
            class polarComparator implements Comparator<pointWithIndex>{
                @Override
                public int compare(pointWithIndex t1, pointWithIndex t2) {
                    double dy1=t1.p.getY()-startPoint.getY();
                    double dy2=t2.p.getY()-startPoint.getY();
                    double dx1=t1.p.getX()-startPoint.getX();
                    double dx2=t2.p.getX()-startPoint.getX();
                    
                    if (dy1==0 && dy2==0){
                        if      (dx1 >= 0 && dx2 < 0) return -1;
                        else if (dx2 >= 0 && dx1 < 0) return +1;
                        else                          return  0;
                    }
                    else if (dy1>=0 && dy2<0) return -1;
                    else if (dy2>=0 && dy1<0) return 1;
                    else 
                    return -CCW(startPoint,t1.p,t2.p);
                    }
            }

        }
        class pointWithIndex{
            private java.awt.geom.Point2D p;
            private int index;
            public pointWithIndex(java.awt.geom.Point2D p,int index){
                this.p=p;
                this.index=index;
            }
        }

        class yPoint2DComparator implements Comparator<pointWithIndex>{
            @Override
            public int compare(pointWithIndex p1, pointWithIndex p2) {
                if (p1.p.getY()>p2.p.getY()) return 1;
                else if (p1.p.getY()<p2.p.getY()) return -1;
                else return 0;
            }
        }
        
    public int[] ConvexHullVertex(Point2D[] a){
        /*java.awt.geom.Point2D[] a = new java.awt.geom.Point2D[alg4_a.length];
        for (int i=0;i<alg4_a.length;i++){
            a[i]=new java.awt.geom.Point2D.Double(alg4_a[i].x(),alg4_a.y()) ;
        }*/
        
        
        boolean[] b=new boolean[a.length];

        //for (double item : y){System.out.println(item);}
        pointWithIndex[] A=new pointWithIndex[a.length];
        for (int i=0;i<a.length;i++){
            A[i]=new pointWithIndex(a[i],i);
        }
        
        Arrays.sort(A, new yPoint2DComparator());
        //for (pointWithIndex item : A){System.out.println(item.p);}
        
        point points = new point(A[0].p);
        
        
        pointWithIndex[] new_A=Arrays.copyOfRange(A,1,A.length);
        Arrays.sort(new_A,  points.new polarComparator());
        System.arraycopy(new_A,0,A,1,new_A.length);
        
        //for (pointWithIndex item : A){System.out.println(item.p);}
        
        Stack<pointWithIndex> st = new Stack();
        Stack<Integer> st2=new Stack();
        st.push(A[0]);
        st.push(A[1]);
        //st.push(A[2]);

        int j=2;
        while (j<a.length){
            //
            //System.out.println(""looping..."");
            pointWithIndex second=st.pop();
            pointWithIndex first=st.pop();
            pointWithIndex third =A[j];

            switch(CCW(first.p,second.p,third.p)){
                case 1:
                    //System.out.println(""case:1"");
                    st.push(first);
                    st.push(second);
                    st.push(third);
                    j++;
                    break;
                case 0:
                    //System.out.println(""case:0"");
                    st.push(first);
                    st.push(second);
                    st.push(third);
                    j++;
                    break;
                case -1:
                    //System.out.println(""case:-1"");
                    st.push(first);
                    break;
            }
        }
        int[] ans=new int[st.size()];
        int i =0;
        while(!st.empty()){
            ans[i]=st.pop().index;
            i++;
        }
        return ans;

    }
    
    public static void main(String[] args) throws Exception {
        /*Point2D.Double p1,p2,p3,p4,p5;
        p1 = new Point2D.Double(0.200,0.1);
        p2 = new Point2D.Double(0.5,0.5);
        p3 = new Point2D.Double(0.5,0.2);
        p4 = new Point2D.Double(0.2,0.5);
        p5 = new Point2D.Double(0.3,0.3);
        Point2D[] p_arr={p1,p2,p3,p4,p5};
        MyConvexHull my=new MyConvexHull() ;
        int[] v_arr=my.ConvexHullVertex(p_arr);*/
        //for (int item : v_arr){System.out.println(item);}
        MyConvexHull myconvexhull=new MyConvexHull();
        float d ;
        int N;
        Point2D[] input;
        WeightedQuickUnionUF qf;
        boolean[] b;
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
               d=Float.parseFloat(br.readLine());
               N=Integer.parseInt(br.readLine());
               input=new Point2D[N];
               qf=new WeightedQuickUnionUF(N);
               b= new boolean[N];
            for (int i=0;i<N;i++){
                String[] temp=br.readLine().split("" "");
                double x = Double.parseDouble(temp[0]);
                double y = Double.parseDouble(temp[1]);
                input[i] = new Point2D.Double(x,y);
                
            }
        }
            for (int i=0;i<N;i++){
                for (int j=0;j<N;j++){
                    if (input[i].distance (input[j])<d)
                        qf.union(i,j);
            }
        }
            int count=0;
            for (int i=0;i<N;i++){
                //System.out.println(""For Looping..."");
                if (b[i]){}
                else{
                    ArrayList<java.awt.geom.Point2D> temp=new ArrayList<java.awt.geom.Point2D>();
                    for (int j=0;j<N;j++){
                        if (qf.connected(i,j)){
                            b[i]=true;
                            b[j]=true;
                            temp.add(input[j]);
                        }
                    }
                    //
                    //for (Point2D item : temp){System.out.println(item);}
                    if (temp.size()>2){
                        java.awt.geom.Point2D[] tempArray=new java.awt.geom.Point2D[temp.size()];
                        count=myconvexhull.ConvexHullVertex(temp.toArray(tempArray)).length+count;
                        //System.out.println(count);
                    }
                }
            }
        System.out.println(count);
    }
}

