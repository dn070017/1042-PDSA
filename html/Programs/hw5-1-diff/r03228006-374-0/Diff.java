import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
//import java.util.StdRandom;

public class MyConvexHull {
    private static int[] id;
    private static int[] sz;    
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        int[]id = new int[a.length];
        for (int i = 0; i<a.length;i++){
            id[i]=i;
        }
        Point2D[] a0=a;
        Arrays.sort(a, Point2D.Y_ORDER);
        Point2D p0 = a[0];
        Arrays.sort(a, p0.POLAR_ORDER);
        Point2D[] cvh= new Point2D[a.length];
        cvh[0]=p0;
        int Ncvh=1;
        int precv=0;//最接近的前一個convex vertex
        for(int i=1;i<(a.length);i++){
            int check=0;
            for(int k=i+1;k<(a.length);k++){
                if(Point2D.ccw(a[precv],a[i],a[k])<=0) {//non-counterclock
                    check++;
                }}
            if(check==0){//大家都是counterclock
                cvh[Ncvh]=a[i];
                Ncvh++;//紀錄vertex數
                precv=i;}//記錄新的最近vertex
        }
        //找出vertex的原始id
        int[] vindex = new int[Ncvh];
        for(int i = 0;i<Ncvh;i++){
            Point2D p1=cvh[i];
            for(int j = 0; j<a0.length;j++){
              if(p1.equals(a0[j])){vindex[i] =j;}//如果找到與原本的a0相符，就記下a0的index
        }}
        //Arrays.sort(vindex);
        return vindex;
    }
    
    
    private double x;    // x coordinate
    private double y;    // y coordinate
    public void draw() {
        StdDraw.point(x, y);
    }
    
    public static void QuickUnionUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for(int i =0; i<N ; i++){
            id[i]=i; 
            sz[i]=1;
        }
    }
    
    private static int root(int i)
    {
        while (i != id[i]) 
            i = id[id[i]];
            return i;
        }
    
    public static void union(int p, int q) {
            //i(p)優先保存
            int i = root(p);
            int j = root(q);
            if(i == j) return;
            id[j] = i; sz[i] += sz[j]; sz[j]=0;
    }
    
   public static void main(String[] args)throws Exception {
        //args[0] is  just for juged system, ""input12.txt"" 
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        // read first line number as vector
            String [] data = br.readLine().split("","");
            double d = Double.parseDouble(data[0]);
            String [] data2 = br.readLine().split("","");
            int N = Integer.parseInt(data2[0]);
            //System.out.println(data);
            //StdDraw.setCanvasSize(100, 100);
            //StdDraw.setXscale(0, 1);
            //StdDraw.setYscale(0, 1);
            
            String temp = null;
            Point2D[] points = new Point2D[N];
            int ii=0;
            while ((temp = br.readLine())!=null) {
                    String[] temp2 = temp.split(""\\s+"");
                    Double t0 = Double.parseDouble(temp2[0]);
                    Double t1 = Double.parseDouble(temp2[1]);
                    points[ii] = new Point2D(t0, t1);
                    //System.out.println(t0);
                    //System.out.println(t1);
                    //points[ii].draw();
                    ii++;
                    }
            //把點連起來
            QuickUnionUF(N);
            //StdDraw.setPenRadius(.001);
            //StdDraw.setPenColor(StdDraw.BLUE);
            for (int i = 0; i < N; i++) {
                for(int j=i;j<N;j++){
                    Point2D pp=points[i];
                    if(pp.distanceTo(points[j])<=d){
                        //pp.drawTo(points[j]);
                        union(i,j);
                    }
                    //StdDraw.show(100);
                }
            }
            //for(int i=0;i<N;i++){
            //System.out.println(id[i]);}
//            for(int i=0;i<N;i++){
//            System.out.println(sz[i]);}
            int lengthv = 0;
            for(int i=0;i<N;i++){
                if(sz[i]>2){
                    int iii=0;
                    Point2D[] cc = new Point2D[sz[i]];//用來放connected components，重複使用
                    //System.out.println(sz[i]);
                    for(int j=0;j<N;j++){
//                        StdDraw.setPenColor(StdDraw.BLUE);
//                        StdDraw.setPenRadius(.01);
                        if(id[i]==id[j]){cc[iii]=points[j] ;
                    //                     points[j].draw();
                                         iii++;}}
                    int [] ccindex = ConvexHullVertex(cc);
                    lengthv += ccindex.length;
//                    for (int k =0;k<ccindex.length;k++){
//                        StdDraw.setPenColor(StdDraw.RED);
//                        StdDraw.setPenRadius(.02);
//                        cc[ccindex[k]].draw();}
                        //System.out.println(ccindex[k]);
                    //System.out.println(ccindex.length);
                }}

            System.out.println(lengthv);
           // 
        //public static void main(String[] args) {

        
//        Point2D[] point = new Point2D[10];
        //Point2D[][] parray = new Point2D()[10][10];
//        for (int i=0;i<10;i++){
//            int x = StdRandom.uniform(-5, 5);
//            int y = StdRandom.uniform(-5, 5);
//            point[i] = new Point2D(x, y);
//            point[i].draw();
//        } 
        //Point2D p = new Point2D(-5, -5);
        //Arrays.sort(point, p.POLAR_ORDER);
//        Arrays.sort(point, Point2D.Y_ORDER);
//        Point2D p1= point[0];
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(.02);
//        p1.draw();


        // draw line segments from p to each point, one at a time, in polar order
//        StdDraw.setPenRadius();
//        StdDraw.setPenColor(StdDraw.BLUE);
//        Arrays.sort(point, p1.POLAR_ORDER);
//        for (int i = 0; i < 10; i++) {
//            p1.drawTo(point[i]);
//            StdDraw.show(100);
//        Point2D.ccw(point[0],point[1],point[2]);

//            

//            

            //System.out.println(root(label[num*(announce[0]-1)+announce[1]-1]));

        }}}

    

