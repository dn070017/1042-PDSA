import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;

/**
 *
 * @author Jayden
 */
public class CriticalDis {
    public static void main(String[] args) throws Exception{//讀檔
        try(BufferedReader br=new BufferedReader(new FileReader(args[0]))){
            int count=Integer.valueOf(br.readLine());
            Point2D[] point=new Point2D[count];//存所有的點
            String[] a=new String[2];//讀檔用的
            double x,y;
            double max=Double.MIN_VALUE;//用來比較出所有點的x+y最大值
            double min=Double.MAX_VALUE;//用來比較出所有點的x+y最小值
            int s=0,t=0;//s=start,t=target
            for(int i=0;i<count;i++){//讀取所有點
                a=br.readLine().split("" "");
                x=Double.valueOf(a[0]);
                y=Double.valueOf(a[1]);
                point[i]=new Point2D(x,y);
                if(x+y>max){//找出終點
                    max=x+y;
                    t=i;
                }
                if(x+y<min) {//找出起點
                    min=x+y;
                    s=i;
                }
            }            
            MinPQ<Distance> pq=new MinPQ();
            for(int i=0;i<count;i++){//計算所有可能點的距離
                for(int j=0;j<count;j++){
                    if(point[i].x()<point[j].x()&&point[i].y()<point[j].y()){
                        pq.insert(new Distance(i,j,point[i].distanceTo(point[j])));
                    }
                }
            }
            Digraph D=new Digraph(count);//這是用來建構各點間連線的class
            DirectedDFS DD;//這是用來檢驗某點透過directed first是否可以找到另一點
            Distance minSoFar;//紀錄目前最小的距離是哪一個
            for(int i=0;i<pq.size();i++){//依序從最小距離開始找，直到s可以連到t就停
                minSoFar=pq.delMin();
                D.addEdge(minSoFar.getFrom(),minSoFar.getTo());
                DD=new DirectedDFS(D,D.adj(s));
                if(DD.marked(t)){//如果目前這個class是可以從s連到t就可以輸出了
                    System.out.printf(""%1.3f\n"", minSoFar.getDistance());
                    break;
                }
            }
        }
    }
}

class Distance implements Comparable<Distance>{//inner class，將每一段距離都設為一個class
    private int from;//起始點的編號
    private int to;//到達點的編號
    private double dis;//距離
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

