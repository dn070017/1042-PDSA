
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Sophia
 */
class CompareAngle implements Comparator{
    MyPoint start;
    CompareAngle(MyPoint start){this.start = start;}
    @Override
    public int compare(Object o1, Object o2) {
        MyPoint p1=(MyPoint) o1;
        MyPoint p2=(MyPoint) o2;
        if(MyConvexHull.compare_angle(start.p, p1.p, p2.p)){
            System.out.print(p1.index);
            System.out.print(""change whith"");
            System.out.println(p2.index);
            return 1;
        }
        else{
            System.out.print(p1.index);
            System.out.print(""didn't change whith"");
            System.out.println(p2.index);
            return -1;
        } 
    }
}

 class MyPoint implements Comparator{
    public Point2D p;
    public int index;
    
    
    
    MyPoint(){}
    
    MyPoint(int index, Point2D p){this.index = index; this.p = p;}

    @Override
    public int compare(Object o1, Object o2) {
.
    }
    public int compareto(MyPoint o1) {
        if(this.index > o1.index){
            return 1;
        }else{
            return -1;
        }
    }
    public final Comparator<MyPoint> ATAN2_ORDER = new Atan2Order();
    private double angleTo(MyPoint that) {
        double dx = that.p.x() - this.p.x();
        double dy = that.p.y() - this.p.y();
        return Math.atan2(dy, dx);
    }
    
    private class Atan2Order implements Comparator<MyPoint> {
        public int compare(MyPoint q1, MyPoint q2) {
            double angle1 = angleTo(q1);
            double angle2 = angleTo(q2);
            if      (angle1 < angle2) return -1;
            else if (angle1 > angle2) return +1;
            else                      return  0;
        }
    }
    
}

public class MyConvexHull {
    static public double cross(Point2D o, Point2D a, Point2D b)
{   // >0
    return (a.x() - o.x()) * (b.y() - o.y()) - (a.y() - o.y()) * (b.x() - o.x());
}
    static boolean compare_position(Point2D a, Point2D b)
{
    return (a.y() < b.y()) || (a.y() == b.y() && a.x() > b.x());
}
    static boolean compare_angle(Point2D o,Point2D a, Point2D b)
{
    int c = (int) cross(o, a, b);
    return (c > 0) || (c == 0 && o.distanceTo(a) < o.distanceTo(b));
}


    
    public static int[] ConvexHullVertex(Point2D[] a){
        Stack<Integer> s = new Stack<Integer>();
        int start = 0;
        MyPoint mypoint[] = new MyPoint[a.length];
        for(int i = 0; i < a.length; i++){
            mypoint[i] = new MyPoint (i , a[i]);
            if(compare_position(a[i],a[start])){
                start = i;
            }     
        }
        //System.out.print(start);
        MyPoint temp;
        temp = mypoint[start];
        mypoint[start] = mypoint[0];
        mypoint[0] = temp;
        //System.out.print(mypoint[0].index);
        //Point2D[] b = Arrays.copyOf(a,a.length);
        Arrays.sort(mypoint, mypoint[0].ATAN2_ORDER);
        /*
        for(int i = 0 ; i < mypoint.length; i++){
                System.out.print(mypoint[i].index);
        }
        */
        int m = 0;
        int k = 0;
        MyPoint answer[] = new MyPoint[a.length];
        
        answer[0] = mypoint[0];
        for (int i=0; i <= mypoint.length; i++) {
            k = i;
            if(k == mypoint.length){k = 0;}
            while (m >= 2 && cross(answer[m-2].p, answer[m-1].p, mypoint[k].p) < 0){m--;}
            //System.out.print(m);
            answer[m++] = mypoint[k];
        }    
         m--;
        int r[] = new int[m];
        for(int i = 0; i<m ;i++){
            //System.out.print(answer[i].index);
            r[i] = answer[i].index;
        }
        Arrays.sort(r);
        for(int i = 0; i<m ;i++){
            //System.out.println(r[i]);
        }

        return r;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        StringBuffer sb = new StringBuffer();
        String strNum = new String();
        String buffer[];
        double d = Double.parseDouble(strNum = br.readLine());
        double x;
        double y;
        int N = Integer.valueOf(strNum = br.readLine());
        Point2D point[] = new Point2D[N];
        for(int i = 0; i < N; i++){
            strNum = br.readLine();
            buffer = strNum.split("" "");
            x = Double.parseDouble(buffer[0]);
            y = Double.parseDouble(buffer[1]);
            point[i] = new Point2D(x, y);    
        }
        //int pr[] = ConvexHullVertex(point);
        QuickUnionUF uf = new QuickUnionUF(N);
        //for(int i = 0 ; i< pr.length ; i++){
                //System.out.print(pr[i]+ "" "");
                //System.out.print(point[pr[i]] + "" "");
                
        //}
        
        for(int i = 0; i<N ;i++){
            for(int j = 0 ; j<N;j++){
                if(point[i].distanceTo(point[j]) <= d){
                    uf.union(i, j);
                }            
            }
        }
        //System.out.print(uf.count());
        Stack<Integer>[] st;
        st = new Stack [N];
        for(int i = 0; i<N ; i++){
            st[i] = new Stack<Integer>();
        }
        for(int i = 0; i<N ; i++){
            st[uf.find(i)].push(i);
        }
        int howmany;
        int sum = 0;
        Point2D out[];
        for(int i = 0; i<st.length;i++){
            if(!st[i].isEmpty()){
                howmany = st[i].size();
                //System.out.print(howmany);
                if(howmany > 3){
                    out = new Point2D[howmany];
                    for(int j = 0 ; j< howmany ; j++){
                        out[j] = point[st[i].pop()];
                        //System.out.print(out[j].x());
                        //System.out.println(out[j].y());
                    }
                    
                    sum = sum + ConvexHullVertex(out).length;
                    //System.out.print(sum);
                }else if(howmany == 3){
                    sum = sum+3;
                }
            }
        }
        System.out.print(sum);
    }
}

