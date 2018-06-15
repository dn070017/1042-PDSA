//package clustering;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author steven
 */
public class Clustering {
    private static int N;
    private static event[][] result;
    private static cluster[] input;
    private static cluster[] inputCopy;
    private static WeightedQuickUnionUF qf;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        //cluster[] input;
        //int N;
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            N=Integer.parseInt(br.readLine());
            input=new cluster[N];
            inputCopy=new cluster[N];
            qf = new WeightedQuickUnionUF(N);
            String read;
            
            for(int i=0;i<N;i++){
                read=br.readLine();
                String[] xy= read.split("" "");
                double x=Double.parseDouble(xy[0]);
                double y=Double.parseDouble(xy[1]);
                //minPQ.add(new Point2D.Double(x,y));
                input[i]=new cluster(new Point2D.Double(x,y),i);
            }

        }
        System.arraycopy(input,0,inputCopy,0,input.length);
        result = new event[N][N];
        PriorityQueue<event> eventPQ=new PriorityQueue();
        for (int i=0; i<N; i++){
            //PriorityQueue<event> singleClusterPQ=new PriorityQueue();
            for (int j=i+1; j<N; j++){
                if (i!=j){
                    event e=new event(input[i],input[j]);
                    result[i][j]=e;
                    /*singleClusterPQ.add(e);
                    if (singleClusterPQ.size()>1)
                        singleClusterPQ.poll();*/

                    eventPQ.add(e);

                    
                }
            }
        }
        for (int u=0;u<N-3;u++){
            event prior=eventPQ.poll();
            eventPQ.clear();
            cluster newCluster=prior.src[0].merge(prior.src[1]);
            qf.union(prior.src[0].n,prior.src[1].n);
            input[newCluster.n]=newCluster;
            input[prior.src[1].n]=null;
            recalculate(newCluster);
            clear(prior.src[1]);
            for (int i=0;i<N;i++){
                for (int j=i+1;j<N;j++){
                    if (result[i][j]!=null){
                        
                        
                            eventPQ.add(result[i][j]);
                            

                    }
                }
            }
        }
        cluster[] output;
        int r;
        if (N>=3)
            r=3;
        else 
            r=2;
        output=new cluster[r];
        int count=0;
        for (int i=0;i<N;i++){
            if (input[i]!=null){
                output[count]=input[i];
                count++;
            }
        }
        Arrays.sort(output);
        PriorityQueue<event> nearestPQ=new PriorityQueue();
        /*for (int i=0;i<r;i++){
            for (int j=i+1;j<r;j++){
                for (int u:output[i].ns){
                    for (int v:output[j].ns){
                        event e=new event(inputCopy[u],inputCopy[v]);
                        if (nearestPQ.size()>0){
                            nearestPQ.add(e);
                            nearestPQ.poll();
                        }else
                            nearestPQ.add(e);
                    }
                }
            }
        }*/
        
        for (int i=0;i<r;i++){
            for (int j=i+1;j<r;j++){
                for (int u=0;u<N;u++){
                    for (int v=0;v<N;v++){
                        event e = null;
                        if (!qf.connected(u, v) && u!=v){
                            e=new event(inputCopy[u],inputCopy[v]);

                            nearestPQ.add(e);}
                    }
                }
            }
        }
        
        
        
        

        for (cluster out:output)
            System.out.println(Integer.toString(out.N)+"" ""+String.format(""%.2f"", out.coor.getX())+"" ""+String.format(""%.2f"", out.coor.getY()));
        System.out.println(String.format(""%.2f"",Math.sqrt(nearestPQ.poll().distance)));

    }
    public static void clear(cluster none){
        for (int i=0;i<N;i++){
            if (none.n<i && result[none.n][i]!=null)
                result[none.n][i]=null;

            else if (none.n>i && result[i][none.n]!=null)
                result[i][none.n]=null;
        }
    
    }
    
    public static void recalculate(cluster c){
        for (int i=0;i<N;i++){
            if (c.n<i && result[c.n][i]!=null && input[i]!=null)
                result[c.n][i]=new event(input[c.n],input[i]);

            else if (c.n>i && result[i][c.n]!=null && input[i]!=null)
                result[i][c.n]=new event(input[i],input[c.n]);
        }
    }
}

class event implements Comparable<event>{
    public int N;
    public cluster[] src=new cluster[2];
    public double distance;
    
    public event(cluster A,cluster B){
    if (A.n>B.n){
        this.src[0]=A;
        this.src[1]=B;
    }else{
        this.src[0]=B;
        this.src[1]=A;
    }
    this.N=A.N+B.N;
    this.distance=A.dis(B);
    }

    @Override
    public int compareTo(event t) {
        if (this.distance>t.distance) return 1;
        else if (this.distance==t.distance) return 0;
        else return -1;
    }
}



class cluster implements Comparable<cluster>{
    public int n;
    public Point2D coor;
    public int N;
    //public ArrayList<Integer> ns=new ArrayList();
    //public ArrayList<event> events;
    public cluster(Point2D.Double coor,int n){
        this.coor=coor;
        this.N=1;
        this.n=n;
        //this.ns.add(n);
    }
    public cluster(cluster A,cluster B){
        this.coor=new Point2D.Double((A.coor.getX()*A.N+B.coor.getX()*B.N)/(A.N+B.N),(A.coor.getY()*A.N+B.coor.getY()*B.N)/(A.N+B.N));
        this.N=A.N+B.N;
        this.n=A.n;
        //A.ns.addAll(B.ns);
        //this.ns=A.ns;
        
    }
    public double dis(cluster that){
        return this.coor.distanceSq(that.coor);
    }
    public cluster merge(cluster B){
        cluster C=new cluster(this,B);
        return C;
    }

    @Override
    public int compareTo(cluster t) {
        if (this.N>t.N) return -1;
        else if (this.N==t.N) {
            if (this.coor.getX()>t.coor.getX())
                return 1;
            else if (this.coor.getX()>t.coor.getX()){
                if (this.coor.getY()>t.coor.getY())
                    return 1;
                else if (this.coor.getY()==t.coor.getY())
                    return 0;
                else 
                    return -1;
            }
                
            else return -1;
        }
        else return 1;
    }
}
