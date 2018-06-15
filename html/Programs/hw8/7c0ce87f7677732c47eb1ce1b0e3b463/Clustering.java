import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
public class Clustering implements Comparable<Clustering>{

    private final int size,id;
    private final double x,y;
    private boolean state=true;
    public Clustering(double x1, double y1, int id1){
        x=x1;
        y=y1;
        size=1;
        id=id1;
    }
    public Clustering(Clustering a, Clustering b){//丟兩個cluster進來，這個建構子可以直接merge他們
        size=a.getSize()+b.getSize();
        x=(a.x*a.size+b.x*b.size)/size;
        y=(a.y*a.size+b.y*b.size)/size;
        id=a.id;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void off(){
        state=false;
    }
    public double x(){return x;}
    public double y(){return y;}
    public int gerOrder(){
        return id;
    }
    public int getSize(){
        return size;
    }
    public double distanceTo(Clustering that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public int compareTo(Clustering that) {
        if (this.size>that.size) return 1;
        else return -1;
    }
    
    public static void main(String[] args)  throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int N=Integer.parseInt(br.readLine());
            Clustering[] gg=new Clustering[2*N];
            for(int i=0;i<N;i++){
                String[] temp=br.readLine().split("" "");
                gg[i]=new Clustering(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]),i);
            }
            //System.out.print(gg.length);
            //PQ來囉，開存
            MinPQ<cp> baba=new MinPQ();
            for(int i=0;i<N;i++){
                for(int j=i+1;j<N;j++){
                    cp now=new cp(gg[i],gg[j]);
                    baba.insert(now);
                }
            }
            /*從最小的cp開始丟
            將最小cp內的cluster拆出來，將他們
            .off掉
            .由id來union在一起
            .重跑一次new cp = =
            loop~
            */
            WeightedQuickUnionUF label=new WeightedQuickUnionUF(N);
            int mixtime=0;
            while(N-mixtime>3){
                Clustering min[]=new Clustering[2];
                min=baba.delMin().pop();
                if(min[0].state==true&&min[1].state==true){//先確認是不是兩個都沒用過
                    label.union(min[0].id,min[1].id);
                    min[0].off();
                    min[1].off();
                }
                else continue;
                
                gg[N+mixtime]=new Clustering(min[0],min[1]);
                for(int i=0;i<N+mixtime;i++){
                    if(gg[i].state==true){
                        cp now=new cp(gg[i],gg[N+mixtime]);
                        baba.insert(now);
                    }
                }
                mixtime++;
            }
            //輸出三個cluster
            Clustering[] threesmall=new Clustering[3];
            threesmall[0]=gg[N+mixtime-1];
            threesmall[1]=gg[N+mixtime-2];
            threesmall[2]=gg[N+mixtime-3];
            Arrays.sort(threesmall);
            for(int i=2;i>=0;i--){//輸出點
                System.out.print(threesmall[i].size);
                System.out.print("" "");
                System.out.print(String.format(""%.2f"", threesmall[i].x));
                System.out.print("" "");
                System.out.print(String.format(""%.2f\n"", threesmall[i].y));
            }
            //算距離 For each point in each cluster, find the nearest point in different cluster
.
            //compare the distance (a1, b1), (a1, b2), (a1, c1), (a1, c2), (a2, b1), (a2, b2), (a2, c1), (a2, c2), (b1, c1) and (b1, c2). 
.
            
            //id同三小123的id者要討論
            int id1,id2,id3;
            id1=label.find(threesmall[0].id);
            id2=label.find(threesmall[1].id);
            id3=label.find(threesmall[2].id);
            
            //先刪光baba
            baba.delMin();baba.delMin();baba.delMin();
            for(int i=0;i<N;i++){
                if(label.find(gg[i].id)==id1){//1要跟23比
                    for(int j=i+1;j<N;j++){
                        if(label.find(gg[j].id)==id2||label.find(gg[j].id)==id3){
                        cp now=new cp(gg[i],gg[j]);
                        baba.insert(now);
                        }
                }}
                else if(label.find(gg[i].id)==id2){//2要跟3比
                    for(int j=i+1;j<N;j++){
                        if(label.find(gg[j].id)==id3){
                        cp now=new cp(gg[i],gg[j]);
                        baba.insert(now);
                        }
                }}
            }
            System.out.print(String.format(""%.2f"", baba.delMin().dis));
            }
        }
    }



    


class cp implements Comparable<cp>{
    Clustering a;
    Clustering b;
    double dis;
    public cp(Clustering x, Clustering y){
        a=x;
        b=y;
        dis=x.distanceTo(y);
    }
    public Clustering[] pop(){
        Clustering haha[]=new Clustering[2];
        haha[0]=a;
        haha[1]=b;
        return haha;
    }    
    public int compareTo(cp that) {
        if(this.dis>that.dis) return 1;
        else if(this.dis==that.dis) return 1;
        else return -1;
    }

}
