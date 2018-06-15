
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
            return 1;
        }
        else{
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
}

public class MyConvexHull {
    static public double cross(Point2D o, Point2D a, Point2D b)
{
    return (a.x() - o.x()) * (b.y() - o.y()) - (a.y() - o.y()) * (b.x() - o.x());
}
    static boolean compare_position(Point2D a, Point2D b)
{
    return (a.y() < b.y()) || (a.y() == b.y() && a.x() < b.x());
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
        MyPoint temp;
        temp = mypoint[start];
        mypoint[start] = mypoint[0];
        mypoint[0] = temp;
        Arrays.sort(mypoint, new CompareAngle(mypoint[start]));
        
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
            r[i] = answer[i].index;
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
        int pr[] = ConvexHullVertex(point);
        for(int i = 1 ; i< pr.length ; i++){
                System.out.print(pr[i] + "" "");
        }

    }
}

