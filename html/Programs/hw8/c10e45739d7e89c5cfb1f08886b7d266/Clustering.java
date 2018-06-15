import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Clustering implements Comparable<Clustering>{
    
    Point2D pos;
    int weight;
    int num;
    int parent;
    int number;
    
    public Clustering(Point2D a, int b, int c, int d, int e){
        this.pos = a;
        this.weight = b;
        this.num = c;
        this.parent =d;
        this.number = e;
    }
    public int getweight(){
        return this.weight;
    }
    public  int findparent(){
        
       return this.parent; 
    }
    public int compareTo(Clustering that) {
           if(this.weight >that.weight) return 1;
           else if(this.weight <that.weight) return -1;
           else{
               if(this.pos.x()<that.pos.x()) return 1;
               else if(this.pos.x()>that.pos.x()) return -1;
               else{
                   if(this.pos.y()<that.pos.y()) return 1;
                   else if(this.pos.y()>that.pos.y()) return -1;
               }
           }
           return 0;
        }
     private static class Event implements Comparable<Event> {
        private Clustering a,b;
        
        public Event(Clustering a, Clustering b){
           this.a = a;
           this.b = b;
        }
       public double findis(){           
           return this.a.pos.distanceTo(this.b.pos);
       }
        
        public int compareTo(Event that) {
            if(this.findis()<that.findis()) return -1;
            else if(this.findis()>that.findis()) return 1;
                 else    return  0;
        }
        
      
       
   
    }
    public static void main(String[] args) throws Exception {
        try(BufferedReader br =  new BufferedReader(new FileReader(args[0]))){
            
            String N = br.readLine();
            int n = Integer.parseInt(N);
            int t;
            if(n>=3) t = n+n+3;
            else t=n;
            Point2D[] points = new Point2D[t];
            Clustering[] ww = new Clustering[t];
            
           for(int i = 0;i<n;i++){
             String[] postion = br.readLine().split("" "");
             points[i] = new Point2D(Double.parseDouble(postion[0]), Double.parseDouble(postion[1]));
             ww[i] = new Clustering(points[i], 1, 1, i, i);
             
            
            }
             MinPQ<Event> pq = new  MinPQ<>();
             for(int i = 0;i<n-1;i++){
                for(int j = i+1;j<n;j++){
                 pq.insert(new Event(ww[i], ww[j]));
             }
         }
        for(int i = n;i<n+n-3;i++){
            if(n<3) break;
            Event min = pq.delMin();
            while(min.a.num !=1 || min.b.num !=1){
                min = pq.delMin();
            }
            int a,b;
            a = min.a.getweight();
            b = min.b.getweight();
            points[i] = new Point2D((min.a.pos.x()*a+min.b.pos.x()*b)/(a+b), 
            (min.a.pos.y()*a+min.b.pos.y()*b)/(a+b));
            
            min.a.num = 0;
            min.a.parent = i;
            min.b.num = 0;
            min.b.parent = i;                    
            
            ww[i] = new Clustering(points[i], min.a.weight+min.b.weight , 1, i, i);
           for(int j = 0;j<i;j++){
                pq.insert(new Event(ww[i], ww[j]));
            }
        }
        int k;
        if(n>=3) k = 3;
        else k = n;
       Clustering[] ans = new Clustering[k];
        int j = 0;
        if(n>=3){
        for(int i = 0;i<n+n-3;i++){
            if(ww[i].parent == i){
               ans[j] = ww[i]; 
               j++;
               if(j == 3) break;
            }
        }
        }
        else{
            for(int i = 0;i<n;i++){
                ans[j] = ww[i];
                j++;
            }
        }
         Arrays.sort(ans);
       
        if(n>=3){
        for(int i =2;i>=0;i--){
                System.out.print(ans[i].weight);
                System.out.print("" "");
                System.out.print(String.format(""%.2f"", ans[i].pos.x()));
                System.out.print("" "");
                System.out.println( String.format(""%.2f"", ans[i].pos.y()));              
        }
        }
        else{
            for(int i = n-1;i>=0;i--){
                System.out.print(ans[i].weight);
                System.out.print("" "");
                System.out.print(String.format(""%.2f"", ans[i].pos.x()));
                System.out.print("" "");
                System.out.println( String.format(""%.2f"", ans[i].pos.y()));
            }
        }
       MinPQ<Event> ppq =  new  MinPQ<>();
         for(int i = 0;i<n-1;i++){
                for( j = i+1;j<n;j++){
                 ppq.insert(new Event(ww[i], ww[j]));
             }
         }
         if(n>=2){
         Event mmin = ppq.delMin();
         while(true){
              Clustering a,b;
              a = mmin.a;
              b = mmin.b;
             while(a.parent != a.number){
                 a = ww[a.parent];
             }
             while(b.parent != b.number){
                 b = ww[b.parent];
             }
             if(a.number != b.number) break;
             mmin = ppq.delMin();
         }
          System.out.print(String.format(""%.2f"",mmin.findis()));
         }
          else
             System.out.print(String.format(""%.2f"",0.00));
        }    
    }
}

