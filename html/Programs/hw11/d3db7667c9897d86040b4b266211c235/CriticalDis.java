import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;

/**
 *
 * @author Jayden
 */
public class CriticalDis {
    public static void main(String[] args) throws Exception{
        try(BufferedReader br=new BufferedReader(new FileReader(args[0]))){
            int count=Integer.valueOf(br.readLine());
            Point2D[] point=new Point2D[count];
            String[] a=new String[2];
            double x,y;
            double max=Double.MIN_VALUE;
            double min=Double.MAX_VALUE;
            int s=0,t=0;
            for(int i=0;i<count;i++){
                a=br.readLine().split("" "");
                x=Double.valueOf(a[0]);
                y=Double.valueOf(a[1]);
                point[i]=new Point2D(x,y);
                if(x+y>max){
                    max=x+y;
                    t=i;
                }
                if(x+y<min) {
                    min=x+y;
                    s=i;
                }
            }            
            MinPQ<Distance> pq=new MinPQ();
            for(int i=0;i<count;i++){
                for(int j=0;j<count;j++){
                    if(point[i].x()<point[j].x()&&point[i].y()<point[j].y()){
                        pq.insert(new Distance(i,j,point[i].distanceTo(point[j])));
                    }
                }
            }
            Digraph D=new Digraph(count);
            DirectedDFS DD;
            Distance minSoFar;
            for(int i=0;i<pq.size();i++){
                minSoFar=pq.delMin();
                D.addEdge(minSoFar.getFrom(),minSoFar.getTo());
                DD=new DirectedDFS(D,s);
                if(DD.marked(t)){
                    System.out.printf(""%1.3f\n"", minSoFar.getDistance());
                    break;
                }
            }
        }
    }
}

class Distance implements Comparable<Distance>{
    private int from;
    private int to;
    private double dis;
    public Distance(int f,int t,double d){
        from=f;
        to=t;
        dis=d;
    }
    
    public int compareTo(Distance that){
        if(this.dis>that.dis) return 1;
        else if(this.dis<that.dis) return -1;
        return 0;
    }
    
    public int getFrom(){
        return from;
    }
    
    public int getTo(){
        return to;
    }
    
    public double getDistance(){
        return dis;
    }
         
}

