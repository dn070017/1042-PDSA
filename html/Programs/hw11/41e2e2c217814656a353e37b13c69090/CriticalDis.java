
import java.util.Arrays;


public class CriticalDis {
    public CriticalDis(){}
    
    double d = 1;
    public void Array(Point2D[] Points,double linodistance,Point2D prepointance,Point2D T,Point2D S){
        for(int i = 0;i < Points.length;i++){
            if(prepointance==S)linodistance = 0;
            double tempd = Math.sqrt(Math.pow(Points[i].x()-prepointance.x(),2)+Math.pow(Points[i].y()-prepointance.y(),2));
                if(tempd > d)continue;
                //System.out.println(""prepointance = ""+prepointance.x()+""  Points[i] = ""+Points[i].x());
                if(tempd > linodistance) linodistance = tempd;
               // System.out.println(""tempd = ""+tempd+""  linodistance = ""+linodistance);                
                if(Points[i]==T && linodistance<d){d = linodistance;}
                //System.out.println(""distance = ""+d);
                Point2D[] NextPoints = new Point2D[Points.length];
                int j = 0;
                for(int k = 0;k < Points.length;k++){
                    if(Points[k].x()>Points[i].x() && Points[k].y()>Points[i].y()){
                        NextPoints[j] = Points[k];
                        j++;
                    }
                }
                NextPoints = Arrays.copyOf(NextPoints, j);
                Array(NextPoints,linodistance,Points[i],T,S);
        }
        
        
    }
    
    public static void main(String[] args)  throws Exception{
        In in = new In(args[0]);
        int N = Integer.parseInt(in.readLine());
        if(N<2){System.out.println(""0"");return;}
        Point2D[] A = new Point2D[N];
        Point2D S = new Point2D(1,1);
        Point2D T = new Point2D(0,0);
        for(int i = 0;i < N;i++){
            String[] datas = in.readLine().split("" "");
            double a = Double.parseDouble(datas[0]);
            double b = Double.parseDouble(datas[1]);
            A[i] = new Point2D(a,b);
            if(a+b<S.x()+S.y()) S = A[i];
            if(a+b>T.x()+T.y()) T = A[i];            
        }
        
        double linod = 0;
        Point2D[] NextPoints = new Point2D[A.length];
        int j = 0;
        for(int i = 0;i < A.length;i++){
            if(A[i].x()>S.x() && A[i].y()>S.y()){
                NextPoints[j] = A[i];
                j++;
            }
        }
        NextPoints = Arrays.copyOf(NextPoints, j);
        CriticalDis cd = new CriticalDis();
        cd.Array(NextPoints, linod, S, T,S);
        
        System.out.printf(""%1.3f\n"", cd.d);
    }
    
}

