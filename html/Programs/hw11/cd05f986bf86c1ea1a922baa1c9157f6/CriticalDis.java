
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
.
 */
public class CriticalDis{
    static class ptCompare implements Comparator<Point2D>{
        public int compare(Point2D a,Point2D b) {
            if (a.x() > b.x()) return 1;
            else return (a.x() < b.x() || (a.x() == b.x() && a.y() > b.y())) ? -1 : 0;
        }
    }

    public static void main(String[] args)throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int vertexNum=Integer.parseInt(br.readLine());
            String[] tmp;
            Point2D source=new Point2D(1,1),target=new Point2D(0,0);
            ArrayList<Point2D> vertexes=new ArrayList<>(vertexNum);
//            Point2D[] vertexes=new Point2D[vertexNum];
            Digraph digraph=new Digraph(vertexNum);
            double glmin=0;

            for (int i=0;i<vertexNum;i++){
                String t=br.readLine();
                tmp=t.split("" "");
                vertexes.add(new Point2D(Double.parseDouble(tmp[0]),Double.parseDouble(tmp[1])));
                if (source.x()+source.y()>=vertexes.get(i).x()+vertexes.get(i).y())source=vertexes.get(i);
                if (target.x()+target.y()<=vertexes.get(i).x()+vertexes.get(i).y())target=vertexes.get(i);
            }
            Collections.sort(vertexes,new ptCompare());

            int nextPt = vertexes.indexOf(source);
//            System.out.println(nextPt);
            for (; nextPt < vertexNum;) {
                double minDis=1.5;
                Point2D minP = null;
                for (int j = nextPt+1; j < vertexNum; j++) {
                    if(vertexes.get(j).y()<=vertexes.get(nextPt).y())continue;
                    double Dis=vertexes.get(nextPt).distanceTo(vertexes.get(j));
                    if(Dis<minDis){
                        minDis=Dis;
                        minP = vertexes.get(j);
                    }
                }
                digraph.addEdge(nextPt,vertexes.indexOf(minP));
//                System.out.println(minDis);
                if(minDis>glmin)glmin=minDis;
                if(minP.equals(target))break;
                nextPt=vertexes.indexOf(minP);
            }
//            System.out.println(source);
//            System.out.println(target);
//            for (Point2D t:vertexes) System.out.println(t);
            System.out.printf(""%1.3f\n"", glmin);
        }
    }
}

