import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
public class MyConvexHull {
    /**
     * @param args the command line arguments
     */ 
     public static int []ConvexHullVertex (Point2D[] a){
        double start = 1;
        int startpt = 0;
        for (int i = 0; i < a.length; i++) {
            if(Double.compare(start,a[i].y())>0){
            start = a[i].y();
            startpt = i;
            }
        }
        Arrays.sort(a, a[startpt].ATAN2_ORDER);
        
        Stack<Point2D> ans = new Stack<Point2D>();
        Stack<Integer> AnsofNum = new Stack<Integer>();

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
                if(x.ccw(x, y, z)>=0){
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
        /*Point2D[] copyans = new Point2D[ans.size()];               
        for(int i = 0; i <ans.size();i++){
            copyans[i] = ans.elementAt(i);
            
            //anstreurn.concat(AnsofNum.pop());
        }
        for(int i = 1; i <ans.size();i++){
            StdDraw.line(copyans[i].x(),copyans[i].y(),copyans[i-1].x(),copyans[i-1].y());
        }*/
        
        
        //System.out.print(AnsofNum);
        int [] ar = new int [AnsofNum.size()] ;
        for(int i = 0; i < AnsofNum.size();i++){
            //String q = Integer.toString(AnsofNum.elementAt(i));
            //anstreurn = anstreurn+q;
            //System.out.print(q);
            ar[i] = AnsofNum.elementAt(i);
           //System.out.print(ar[i]);
        }
        //System.out.print(ar);
        //System.out.print(anstreurn);
        //int anstreurn1 = Integer.parseInt(anstreurn); 
        return ar;
    }
}
