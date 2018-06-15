
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import static java.util.Arrays.sort;
import java.util.Iterator;


public class MyConvexHull {
    
    public static int[] ConvexHullVertex(Point2D[] point){
        
        Stack<Point2D> hull = new Stack<Point2D>();
        int N = point.length;
        Point2D origin[] = new Point2D[N];
        System.arraycopy(point, 0, origin, 0, N);
        
        sort(point, Point2D.Y_ORDER);
        sort(point, point[0].POLAR_ORDER);
        
        hull.push(point[0]);
        hull.push(point[1]); 
        
        for(int i=2;i<N;i++){
            Point2D top = hull.pop();
            while ( (Point2D.ccw(hull.peek(),top,point[i])) <= 0 ){
                top = hull.pop();
            }
            hull.push(top);
            hull.push(point[i]);
        }
        
        int[] index = new int[hull.size()];
        int a=0;
        
        Iterator<Point2D> j = hull.iterator();
        while (j.hasNext()){
            Point2D P = j.next();
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.MAGENTA);
//            P.draw();
            for(int i=0;i<N;i++){
                if(origin[i].equals(P)){
                    index[a]=i;
                    a++;
//                    System.out.printf(i+"" "");
                }
            }
        }
//        System.out.printf(""\n"");
        Arrays.sort(index);
        return index;
    }
       
    public static void main(String[] args) throws Exception {
        
        Stopwatch stopwatch = new Stopwatch();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            StringBuilder everything = new StringBuilder();
            String line;
            everything.append(br.readLine()); //first num
            while ((line = br.readLine()) != null) {
                everything.append("","");       //split elements by "",""
                everything.append(line);
            }
            String[] data = everything.toString().split("","");
            double distance = Double.parseDouble(data[0]);
            int N = Integer.parseInt(data[1]);
            Point2D[] point = new Point2D[N];
            QuickFindUF uf = new QuickFindUF(N);
            int vertice_num = 0;
//            System.out.printf(""distance = "" + distance + ""\n"");
//            System.out.printf(""num = "" + N + ""\n"");
//            StdDraw.setCanvasSize(600, 600);
//            StdDraw.setPenRadius(0.01);
//            StdDraw.setPenColor(StdDraw.BLACK);
            
            for(int i=0;i<N;i++){
                String[] given = data[i+2].split("" "");
                point[i] = new Point2D(Double.parseDouble(given[0]), Double.parseDouble(given[1]));
//                point[i].draw();
//                StdDraw.text(Double.parseDouble(given[0]), Double.parseDouble(given[1])-0.02, point[i].toString());
            }
            
//            sort(point, Point2D.Y_ORDER);
//            StdDraw.setPenColor(StdDraw.BLUE);
//            for(int i=0;i<N;i++){
//                StdDraw.text(point[i].x(), point[i].y()-0.05, Integer.toString(i));
//            }
//            StdDraw.setPenRadius(0.001);
//            StdDraw.setPenColor(StdDraw.GRAY);
            for(int i=0;i<N;i++){
                for(int j=i+1;j<N;j++){
                    if( (point[i].distanceTo(point[j]))<= distance){
                        uf.union(j, i);
//                        point[i].drawTo(point[j]);
                    }
                }
            }
            int[] origin_root = new int[N];
            int[] root = new int[N];
            for(int i=0;i<N;i++){
                origin_root[i] = uf.find(i);
            }
            System.arraycopy(origin_root, 0, root, 0, N);
//            System.out.printf(Arrays.toString(root)+""\n"");
            Arrays.sort(root);
//            System.out.printf(Arrays.toString(root)+""\n"");
            //identify the group_num and their elements
            int[][] temp = new int[N][2];//[root][element_number]
            int count=0;
            int group_num = 0;
            for(int i=0;i<N-1;i++){
                if(root[i]==root[i+1]){
                    count++;
                    if(count==2){ temp[group_num][0]=root[i]; group_num++; }
                    if(count>=2){ temp[group_num-1][1]=count+1; }
                }
                else{count=0;}    
            }       
            int[][] group_index = new int[group_num][2];
            for(int i=0;i<group_num;i++) {
                group_index[i][0]=temp[i][0];
                group_index[i][1]=temp[i][1];
//                System.out.printf(group_index[i][0]+"" ""+group_index[i][1]+""\n"");
            }
            
            for(int i=0;i<group_num;i++){
                Point2D[] input_point = new Point2D[group_index[i][1]];
                int flag = 0;
                for(int j=0;j<N;j++){
                    if(origin_root[j] == group_index[i][0]){
                        input_point[flag] = point[j];
//                        System.out.printf(input_point[flag].toString() + "" "");
                        flag++;
                    }
                }
//                System.out.printf(""\n"");
                int[] index = ConvexHullVertex(input_point);
                vertice_num += index.length;
            }
//            System.out.printf(""ConvexHullVertex_num = "");
            System.out.print(vertice_num + ""\n"");
        }
//        double time = stopwatch.elapsedTime();
//        System.out.printf(""run time = "" + time + ""(s)\n"");
    }
    
}

