import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
public class MyConvexHull {
    /**
     * @param args the command line arguments
     */ 
     public static int []ConvexHullVertex (Point2D[] a){
        double start = a[0].y();
        int startpt = 0;
        for (int i = 0; i < a.length; i++) {
            if(Double.compare(start,a[i].y())>0){
            start = a[i].y();
            startpt = i;
            }
        }
        Arrays.sort(a, a[startpt].ATAN2_ORDER);
        //draw for test
        for (int i = 0; i < a.length; i++) {
            StdDraw.point(a[i].x(),a[i].y());
            StdDraw.text(a[i].x(),a[i].y()+0.03,Integer.toString(i));
            StdDraw.line(a[0].x(),a[0].y(),a[i].x(),a[i].y());
        }
        //draw for test
        
        
        
        Stack<Point2D> ans = new Stack<>();
        Stack<Integer> AnsofNum = new Stack<>();

        ans.push(a[0]);
        ans.push(a[1]);
        AnsofNum.push(0);
        AnsofNum.push(1);
        for(int i = 2; i <a.length;i++){
            ans.push(a[i]);
            AnsofNum.push(i);
            while(ans.size()>2){
                Point2D z = ans.pop();
                int Numz = AnsofNum.pop();
                Point2D y = ans.pop();
                int Numy =AnsofNum.pop();
                Point2D x = ans.pop();
                int Numx =AnsofNum.pop();
                if(Point2D.ccw(x, y, z)>=0){
                    ans.push(x);
                    ans.push(y);
                    ans.push(z);
                    AnsofNum.push(Numx);
                    AnsofNum.push(Numy);
                    AnsofNum.push(Numz);
                    break;
                }
                else {
                    ans.push(x);
                    ans.push(z);
                    AnsofNum.push(Numx);
                    AnsofNum.push(Numz);
                } 
            }
            ///draw//
            //(ans.search(1)).x();
            //while(!AnsofNum.isEmpty())
            //{
                //StdDraw.line(ans.search(1).x(),ans.search(2));
                //System.out.println(AnsofNum.pop());       
            //}
        }
        //draw line
        //Point2D[] copyans = new Point2D[ans.size()];               
       // for(int i = 0; i <ans.size();i++){
            //copyans[i] = ans.elementAt(i);
            
            //anstreurn.concat(AnsofNum.pop());
       // }
        /*for(int i = 1; i <ans.size();i++){
            StdDraw.line(copyans[i].x(),copyans[i].y(),copyans[i-1].x(),copyans[i-1].y());
        }*/
        
        
        //System.out.print(AnsofNum);
        int [] ar = new int [AnsofNum.size()] ;
        for(int i = 0; i < AnsofNum.size();i++){
            ar[i] = AnsofNum.elementAt(i);
           //System.out.print(ar[i]);
        }
        //System.out.print(ar);
        //System.out.print(anstreurn);
        //int anstreurn1 = Integer.parseInt(anstreurn); 
        return ar;
    }
}

