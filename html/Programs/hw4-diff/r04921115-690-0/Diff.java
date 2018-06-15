import java.util.*;
import java.lang.*;
import java.util.NoSuchElementException;

public class MyConvexHull {
	public static void main(String[] args) throws Exception{
	    int N = Integer.parseInt(args[0]);
		Point2D[] a = new Point2D[N];
		double min_y = 0;
		double min_x = 0;
		for(int i = 0; i<N; i++){
				double x =StdRandom.uniform();
				double y =StdRandom.uniform();
				if(i==0){
					min_y = y;
				}else{
					if(y < min_y){
						min_y = y;
					}
				}
				//System.out.println(""x"" + x + "" ,""+ ""y"" + y);
				a[i] = new Point2D(x, y);
		}
		for(int i = 0; i<N; i++){
			if(a[i].y()==min_y){
				min_x = a[i].x();
				StdDraw.setPenColor(StdDraw.RED);
			}else{
				StdDraw.setPenColor(StdDraw.BLUE);
			}
			StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
		}
		
		for(int i = 0; i<N; i++){
			
		}
	}
}

