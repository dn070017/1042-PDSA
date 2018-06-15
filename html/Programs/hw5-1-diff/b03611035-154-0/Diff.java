import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MyConvexHull {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(0.00187);
        
        double d=Double.parseDouble(br.readLine());
        int N=Integer.parseInt(br.readLine());
        
        Point2D[] a=new Point2D[N];
        for(int i=0;i<N;i++){
            String readd[]=br.readLine().split("" "");
            a[i]=new Point2D(Double.parseDouble(readd[0]),Double.parseDouble(readd[1]));
        }
        
        ConvexHullVertex(a);
        }
    }
    
     public static int[] ConvexHullVertex(Point2D[] a) {

        Point2D[] b=new Point2D[a.length];
        Point2D ymin=a[0];
        for(int i=0;i<a.length;i++){//弄一個b，不然a會變成錯誤的形狀
            b[i]=new Point2D(a[i].x(),a[i].y());
            if(b[i].compareTo(ymin)==-1) ymin=b[i];//找到y最小的點
        }
        Arrays.sort(b,ymin.atan2Order());//把b變成atan的形狀
        for (int i = 0; i < a.length-1; i++) {
           //b[i].drawTo(b[i+1]);
          // StdDraw.show(187);
        }
        
        Stack <Point2D>vertex=new Stack();//vertex stack
       // int count=2;//有幾個vertex，前兩點一定是
        vertex.push(b[0]);
        vertex.push(b[1]);
        int count=2;
      
        for(int i=2;i<b.length;i++){
            Point2D x=vertex.pop();
            Point2D y=vertex.pop();
            while(Point2D.ccw(y,x,b[i])!=1){
                x=y;
                y=vertex.pop();
                count--;
            }
            vertex.push(y);
            vertex.push(x);
            vertex.push(b[i]);
            count++;
        }
        
        int index[]=new int [b.length];
        for(int i=0;i<count;i++){
            Point2D temp=vertex.pop();
            for(int j=0;j<b.length;j++){
                if(temp==b[j]){
                    index[i]=j;
                    System.out.println(index[i]);
                }
            }
        }
        Arrays.sort(index);
        for(int i=0;i<index.length;i++){
            System.out.println(index[i]);
        }
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        
        return index;
    }

}

