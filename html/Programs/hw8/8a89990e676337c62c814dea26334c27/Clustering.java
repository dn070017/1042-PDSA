import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Clustering {
     
    public static int[] findNearest(Point2D pts[]){
        int[] nearest = new int[2];
        double INFINITY = Double.POSITIVE_INFINITY;
        int N=pts.length;
        double[][] d = new double[N][N];
        int[] dmin = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) d[i][j] = INFINITY;
                else d[i][j] = pts[i].distanceTo(pts[j]);
                if (d[i][j] < d[i][dmin[i]]) dmin[i] = j;
            }
        }
        int i1 = 0;
        for (int i = 0; i < N; i++) {if (d[i][dmin[i]] < d[i1][dmin[i1]]) i1 = i;}
        int i2 = dmin[i1];
   
        nearest[0] = i1;
        nearest[1] = i2;
        return nearest;
    }
    public static double clusterdis(Point2D[] c1, Point2D[] c2){
        double output = 0;
        int k =0;
        int m = c1.length;
        int n = c2.length;
        double[] d = new double[m*n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                d[k++] = c1[i].distanceTo(c2[j]);
            }
        }
        Arrays.sort(d);
        return output = d[0];
    }
    public static Point2D[] initial(Point2D pts[],QuickFindUF uf, int k, int size){
        int N = pts.length;
        int j = 0;
        Point2D[] output = new Point2D[size];
        for(int i =0; i < N; i++){
            if(uf.find(i) == k)
                output[j++]=pts[i];
        }
        return output;
    }
    public static Point2D centroid(Point2D pts[],QuickFindUF uf, int[] idx, int size[]){
        double x=0,y=0;
        x = pts[idx[0]].x()*size[0] + pts[idx[1]].x()*size[1];
        y = pts[idx[0]].y()*size[0] + pts[idx[1]].y()*size[1];
        Point2D output;
        output = new Point2D(x/(size[0]+size[1]),y/(size[0]+size[1]));
        return output;
    }
    public static int[] sizemod(int[] size, int i1, int i2){
        int i = 0,j = 0;
        int N = size.length;
        int []output=new int[N-1];
        output[i1] = size[i1] + size[i2];
        while(j < N-1){
            if(i == i1) {output[j++] = size[i1] + size[i2]; i++;}
            else if(i == i2) {i++;}
            else output[j++] = size[i++];
        }
        return output;
    }
    public static int size(QuickFindUF uf, int k, int N){
        int output=0;
        for(int i = 0; i < N; i++){
            if(uf.find(i) == k)
            output += 1;
        }
        return output;
    }
    
    public static Point2D[] del(Point2D pts[], int i1, int i2, Point2D c){
        int i = 0,j = 0;
        int N = pts.length;
        Point2D[] output = new Point2D[N-1];
        while(j < N-1){
            if(i == i1) {output[j++] = c; i++;}
            else if(i == i2) {i++;}
            else output[j++] = pts[i++];
        }
        return output;
    }
    public static Point2D[] deluf(Point2D pts[], int i1, int i2, Point2D c){
        int i = 0,j = 0;
        int N = pts.length;
        Point2D[] output = new Point2D[N];
        while(j < N){
            if(i == i1) {output[j++] = c; i++;}
            else if(i == i2) {output[j++] = null; i++;}
            else output[j++] = pts[i++];
        }
        return output;
    }
    
    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String in = br.readLine();
            int num = Integer.parseInt(in);
            Point2D [] pts = new Point2D[num];
            String[] value;
            double x,y,font=0;
            int a=0,b=0;
            int N = num;
            int[] s = new int[num];
            MinPQ<Double> pq = new MinPQ<Double>();
            QuickFindUF uf = new QuickFindUF (N);
            for(int i = 0; i < num; i++) s[i]=1;

            while((in = br.readLine()) != null){
                value = in.split("" "");
                x = Double.parseDouble(value[0]);
                y = Double.parseDouble(value[1]);
                pts[a++] = new Point2D(x, y);
            }
            if(num == 3)      {System.out.printf(""1 %.2f %.2f\n"", pts[0].x(), pts[0].y());System.out.printf(""1 %.2f %.2f\n"", pts[1].x(), pts[1].y());System.out.printf(""1 %.2f %.2f\n"", pts[2].x(), pts[2].y());int[] idx = findNearest(pts);System.out.printf(""%.2f\n"",pts[idx[0]].distanceTo(pts[idx[1]]));}
            else if(num == 2) {System.out.printf(""1 %.2f %.2f\n"", pts[0].x(), pts[0].y());System.out.printf(""1 %.2f %.2f\n"", pts[1].x(), pts[1].y());System.out.printf(""%.2f\n"",pts[0].distanceTo(pts[1]));}
            else if(num == 1) {System.out.printf(""1 %.2f %.2f\n"", pts[0].x(), pts[0].y());System.out.printf(""0.00\n"");}
            else{
                Point2D [] cluster = pts;
                Point2D [] clus = pts;
                while(N > 3){
                    a = 0; b = 0;
                    int[] idx = findNearest(cluster);               
                    int[] size = new int[2];
                    size[0] =s[idx[0]];
                    size[1] =s[idx[1]];
                    Point2D c = centroid(cluster, uf, idx, size);
                    cluster=del(cluster, idx[0], idx[1], c);
                    clus=deluf(clus, idx[0], idx[1], c);
                    for(int i = 0; i < num; i++){
                        if(clus[i] == null){}
                        else{
                            if(clus[i] == cluster[idx[0]])
                                a = i;
                        }
                    }
                    for(int j = 0; j < num; j++){
                        if(clus[j] == null){}
                        else{
                            if(clus[j] == cluster[idx[1]])
                                b = j;
                        }
                    }
                    uf.union(a, b);
                    s = sizemod(s, idx[0], idx[1]);
                    N = N - 1;
                    font += 0.001;
                    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
                    StdDraw.filledCircle(c.x(), c.y(), font);
                    StdDraw.text(c.x(), c.y(),Integer.toString(N),0.1);
                }
                int[] idx = new int[num];
                int[] ufidx = new int[3];
                int[] size = new int[3];
                for(int i = 0; i < num; i++) idx[i] = uf.find(i);
                Arrays.sort(idx);
                ufidx[0] = idx[0];
                ufidx[2] = idx[num-1];
                for(int i = 0; i < num; i++){
                    if(idx[i] != idx[i+1]){
                        ufidx[1] = idx[i+1];
                        break;
                    } 
                }
                for(int i = 0; i < 3; i++) size[i] = size(uf, uf.find(ufidx[i]), num);
                for(int i = 0; i < 3; i++){
                    for(int j = i; 0 < j; j--){
                        if (size[j] < size[j-1]){
                            int temp = size[j];
                            size[j] = size[j-1];
                            size[j-1] = temp;
                            int temp2 = ufidx[j];
                            ufidx[j] = ufidx[j-1];
                            ufidx[j-1] = temp2;
                        }
                        else break;
                    }
                }
                Point2D[] temp1 = initial(pts, uf, uf.find(ufidx[0]), size[0]);
                Point2D[] temp2 = initial(pts, uf, uf.find(ufidx[1]), size[1]);
                Point2D[] temp3 = initial(pts, uf, uf.find(ufidx[2]), size[2]);
                pq.insert(clusterdis(temp1, temp2));
                pq.insert(clusterdis(temp1, temp3));
                pq.insert(clusterdis(temp2, temp3));

                StdDraw.setPenColor(StdDraw.BOOK_RED);
                for(int i=0; i<num; i++){
//                    System.out.printf(""%.2f"" + "" "" + ""%.2f\n"", pts[i].x(), pts[i].y());
//                    System.out.println(uf.find(i));
                    StdDraw.filledCircle(pts[i].x(), pts[i].y(), 0.008);
                }

                for(int i = 2; 0 <= i; i--)
                    System.out.printf(""%d %.2f %.2f\n"", size[i], cluster[uf.find(ufidx[i])].x(), cluster[uf.find(ufidx[i])].y());
                System.out.printf(""%.2f\n"", pq.delMin());
                }
        }
    }
}

