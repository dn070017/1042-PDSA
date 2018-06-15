
import java.io.BufferedReader;
import java.io.FileReader;

public class CriticalDis {
    private double d;
    private double value;

    public static void main(String[] args)throws Exception {
        // TODO code application logic here
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            int DotNumber = Integer.parseInt(br.readLine());
            
            Point2D[] dot =new Point2D[DotNumber];
            int i=0;
            while(true){
                String header = br.readLine();
                if (header == null) {break;}
                String[] place = header.split("" "");
                double x = Double.parseDouble(place[0]);
                double y = Double.parseDouble(place[1]);
                
                dot[i] = new Point2D(x,y);
                
                
//                StdDraw.filledCircle(dot[i].x(), dot[i].y(),0.01);
//                StdDraw.text(dot[i].x(), dot[i].y()+0.03,String.valueOf(i+1));
                i++;
                //Clustering cluster = new Clustering(x,y);
                }
            double distance=0.357;
            System.out.printf(""%1.3f\n"", distance);
            
            
    }
    
}
}
