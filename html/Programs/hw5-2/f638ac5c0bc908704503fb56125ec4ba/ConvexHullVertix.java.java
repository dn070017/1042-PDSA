
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Jayden
 */
public class MyConvexHull {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {//讀檔
            double distance=Double.valueOf( br.readLine());//第一個數字是距離
            int num=Integer.valueOf(br.readLine());//第二個數字是點的數量
            Point2D[] pp = new Point2D[num];
            String[] loc=new String[2];//紀錄位置的陣列
            for(int i=0;i<num;i++){
                loc=br.readLine().split("" "");
                pp[i] = new Point2D(Double.valueOf(loc[0]), Double.valueOf(loc[1]));
            }
            UF uf=new UF(num);
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(Math.sqrt(Math.pow(pp[i].x()-pp[j].x(),2)+Math.pow(pp[i].y()-pp[j].y(), 2))<=distance){
                        uf.union(i, j);//計算每兩個點之間的長度，並且將距離內的點連接起來
                    }
                }
            }
            int convexHullVertexPointCounting=0;
            boolean[] conectedOrNot=new boolean[num];//紀錄該點是否被用過，false代表還沒使用過
            Stack<Point2D> cc=new Stack<Point2D>();
            for(int i=0;i<num;i++){
                cc.clear();//清空stack好用來記錄下一個cc
                if(conectedOrNot[i]==false){
                    cc.push(pp[i]);
                }
                conectedOrNot[i]=true;//i點已經使用
                for(int j=i+1;j<num;j++){
                    if(conectedOrNot[j]==false && uf.connected(i, j)){
                        conectedOrNot[j]=true;//j點已經使用
                        cc.push(pp[j]);                        
                    }
                }
                if(cc.size()>=3){//當這個cc夠大才需要計算他的convexhull
                    Point2D[] subCC=new Point2D[cc.size()];                                      
                    cc.copyInto(subCC);
                    convexHullVertexPointCounting+=ConvexHullVertex(subCC).length;
                }
            }
            System.out.print(convexHullVertexPointCounting);
        }
    }

    public static int[] ConvexHullVertex(Point2D[] a) {
        Double[] order = new Double[a.length];//用來排序角度大小
        double minPoint = 100;
        int minP = 0;
        for (int i = 0; i < a.length; i++) {
            order[i] = a[i].y();
            if (minPoint > order[i]) {//找到y最小的數
                minPoint = order[i];
                minP = i;
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (i != minP) {
                order[i] = (a[i].x() - a[minP].x()) / (a[i].y() - a[minP].y());
            }//cot用來表示與原點的角度大小
        }
        order[minP] = Double.MAX_VALUE;//將原點的值設成最大
        Double[] cc = Arrays.copyOf(order, order.length);//cc陣列用來儲存原本的order型式，用在下面兩個for迴圈找到rank
        Arrays.sort(order, Collections.reverseOrder());//倒過來排(由大到小)
        int[] rank = new int[order.length];//rank就是依照角度排序好的點
        for (int i = 0; i < order.length; i++) {
            for (int j = 0; j < order.length; j++) {
                if (order[i] == cc[j]) {
                    rank[i] = j;
                }
            }
        }

        Stack<Point2D> checkConvexHull = new Stack<Point2D>();
        checkConvexHull.push(a[rank[0]]);
        checkConvexHull.push(a[rank[1]]);
        int count = 1;
        int ccwChecked = ccw(checkConvexHull.elementAt(checkConvexHull.size() - 2), checkConvexHull.lastElement(), a[rank[count + 1]]);
        while (count + 1 != order.length || ccwChecked != 1) {
            if (ccwChecked != 1) {//如果不是CCW，就丟掉一個
                checkConvexHull.pop();
                if (checkConvexHull.size() == 1) {//當stack裡面只有一個點的時候，加入一個點，並讓count++好讓下次有三個點可以計算
                    checkConvexHull.push(a[rank[count + 1]]);
                    count++;
                }
            } else if (ccwChecked == 1) {//如果是CCW
                count++;
                checkConvexHull.push(a[rank[count]]);
                if (count == order.length - 1) {
                    break;
                }
            }
            ccwChecked = ccw(checkConvexHull.elementAt(checkConvexHull.size() - 2), checkConvexHull.lastElement(), a[rank[count + 1]]);
        }
        int[] returnArray = new int[checkConvexHull.size()];
        for (int i = checkConvexHull.size() - 1; i >= 0; i--) {
            Point2D QQ = checkConvexHull.pop();
            for (int j = 0; j < a.length; j++) {
                if (QQ.equals(a[j])) {
                    returnArray[i] = j;
                }
            }
        }
        return returnArray;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {//CCW
        double area2 = (b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x());
        if (area2 < 0) {//順時鐘
            return -1;
        } else if (area2 > 0) {//逆時鐘
            return +1;
        } else {//直線
            return 0;
        }
    }
}

