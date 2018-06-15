/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
        /*
        for (int i = 0; i < a.length; i++) {
            StdDraw.point(a[i].x(),a[i].y());
            StdDraw.text(a[i].x(),a[i].y()+0.03,Integer.toString(i));
            //StdDraw.line(a[0].x(),a[0].y(),a[i].x(),a[i].y());
        }
        */
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
        }
        //draw line
        /*
        Point2D[] copyans = new Point2D[ans.size()];               
        for(int i = 0; i <ans.size();i++){
            copyans[i] = ans.elementAt(i);
        }
        StdDraw.setPenColor(StdDraw.RED);
        for(int i = 1; i <ans.size();i++){
            StdDraw.line(copyans[i].x(),copyans[i].y(),copyans[i-1].x(),copyans[i-1].y());
        }
        */
        
        //System.out.print(AnsofNum);
        int [] ar = new int [AnsofNum.size()] ;
        for(int i = 0; i < AnsofNum.size();i++){
            ar[i] = AnsofNum.elementAt(++i);
           //System.out.print(ar[i]);
        }
        //System.out.print(ar);
        //System.out.print(anstreurn);
        //int anstreurn1 = Integer.parseInt(anstreurn); 
        return ar;
    }
     
    public static void main(String[] args) throws Exception {
     int result = 0;   
     BufferedReader br = new BufferedReader(new FileReader(args[0]));
     
     String R = br.readLine();//read txt
     double r = Double.parseDouble(R);
     String input = br.readLine();
     int N = Integer.parseInt(input);
     
     Point2D[] a = new Point2D[N];
     String data0 = br.readLine();
     int counta = 0;
       while(data0!=null){
          //set the site block
          String [] data1 = data0.split("" "");
          a[counta] = new Point2D(Double.parseDouble(data1[0]),Double.parseDouble(data1[1]));

              //System.out.printf(""%d\n"",data[x][y]);
          data0 = br.readLine();
          counta=counta+1;
       }
       //StdDraw.setPenColor(StdDraw.BLUE);
       ConvexHullVertex(a);
       //union
       /*UF uf = new UF(N);
       for(int i=0;i<N;i++){
           for(int j=0;j<N;j++){
               if(a[i].distanceTo(a[j])<=r){
                    if (uf.connected(i, j)) continue;
                    uf.union(i, j);
                    //StdOut.println(i + "" "" + j);
                }
            } 
       }
       */
       //find same root and combine to a array
       /*
       for(int i = 0; i < N;i++){
           Point2D[] b = new Point2D [N];
           int countb = 0;
           for(int j = 0; j < N;j++){
            if(i == uf.find(j)){
                b[countb] = a[j];
                countb = countb+1;
                System.out.println(i);
                System.out.println(b[0]);
                System.out.println(b[1]);
                System.out.println(b[2]);
                System.out.println(b[3]);
                System.out.println(b[4]);
                System.out.println(countb);
            }
           }
            */
            /*Point2D[] c = new Point2D [countb];
            for(int k = 0; k < countb;k++){
                c[k] = b[k];
            }
            if((countb)>=3){
                //result = result+ConvexHullVertex(c);
            }
  
       
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(a[0].x(),a[0].y());
        System.out.print(""$$\n"");
        */   
        int [] out = ConvexHullVertex(a);
        System.out.print(out[0]);
        System.out.print(out[1]);
        System.out.print(out[2]);
        System.out.print(out[3]);
        System.out.print(out[4]);
        System.out.print(out[5]);
        
        //System.out.print(ConvexHullVertex(a)[6]);
        }
        
        /*for(int i = 0; i < qq.length;i++){
            System.out.println(qq[i]);
        }*/
         //System.out.println(qq.length);
        //draw
        
        
       
    }
    

