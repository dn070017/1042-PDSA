import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;

public class Tester{
    public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int i = 0;
            double t = Double.parseDouble(br.readLine());
            int n = Integer.parseInt(br.readLine());
            Point2D[] a = new Point2D[n];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String[] data = in.split(" ");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);

                Point2D point = new Point2D(x, y);
                a[i++] = point;
            }

            int[] out = MyConvexHull.ConvexHullVertex(a);
            Arrays.sort(out);
            for(Integer idx: out){
                System.out.printf("%d\n", idx);
            }
        }
    }
}
