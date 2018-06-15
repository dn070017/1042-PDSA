import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class Clustering  implements Comparable<Clustering> { 
    
    private double x;    // x coordinate
    private double y;    // y coordinate 
    private int n;
    List<Point2D> p = new ArrayList<>();

    public Clustering(){}
    
    public Clustering(double x, double y) {
        this.x = x;
        this.y = y;
        p.add(new Point2D(x,y));
        this.n = 1;        
    }    
    
    public int compareTo(Clustering that) {
        if(this.n > that.n) return 1;
        else if(this.n < that.n) return -1;
        else if(this.x > that.x) return -1;
        else if(this.x < that.x) return 1;
        else if(this.y > that.y) return -1;
        else if(this.y < that.y) return 1;        
        else return 0;
    } 
    
    public Point2D get(int i){
    return p.get(i);
}

    public double x() {
        return x;
    }    

    public double y() {
        return y;
    }
    
    public int n() {
        return n;
    }     
    
    static public Clustering merge(Clustering a,Clustering b){
        Clustering m = new Clustering();
        m.n = a.n+b.n;
        
        double x_temp = 0.0;
        double y_temp = 0.0;
        
        for(int i = 0;i < a.n;i++){
            m.p.add(a.p.get(i));
            x_temp += a.p.get(i).x();
            y_temp += a.p.get(i).y();
        }
        
        for(int i = 0;i < b.n;i++){
            m.p.add(b.p.get(i));
            x_temp += b.p.get(i).x();
            y_temp += b.p.get(i).y();
        }
        
        m.x = x_temp/m.n;
        m.y = y_temp/m.n;        
        return m;
    }
    
    public void print(){
        System.out.printf(""%.2f %.2f \n"",x,y);
    }
    
    public double distanceTo(Clustering that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public void draw() {
        StdDraw.point(x, y);
    }
    
    public void drawTo(Clustering that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    

    public static void main(String[] args) throws Exception{
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            int draw = 1;
            
                String points = br.readLine();
                int n = Integer.parseInt(points);
                List<Clustering> c = new ArrayList<>();
            
                for(int i=0;i < n ; i++){
                    String cor[] = br.readLine().split("" "");
                    c.add(new Clustering(Double.parseDouble(cor[0]), Double.parseDouble(cor[1])));
                }
            
                if(draw == 0){
                    StdDraw.setCanvasSize(800, 800);
                    StdDraw.setXscale(0, 1);
                    StdDraw.setYscale(0, 1);
                    StdDraw.setPenRadius(.01);
            
                    StdDraw.setPenColor(StdDraw.BLUE);
                    for(int i=0;i < c.size() ; i++){
                        c.get(i).draw();
                    }//end of draw==0
                    
                }
            //printall(c);
            
            //System.out.println();
            
                      
            //Clustering a = new Clustering();
            //Clustering b = new Clustering();
            int temp_x=0;
            int temp_y =0;      
            while(c.size()>3){
                double temp =100;                   
                for(int x=0;x<c.size();x++){
                //a=(c.get(x));
                //System.out.println(""-------"");
                //System.out.printf(""x=""+a.x()+"",""+a.y());
                //System.out.print(""\n"");
                    for(int y =0;y<c.size();y++){
       
                        //b=(c.get(y));
                        //System.out.printf(""y=""+b.x()+"",""+b.y());
                        //System.out.print(""\n"");
                        //double dis=a.distanceTo(b);
                        if(x==y)
                        ;
                        else if(temp>c.get(x).distanceTo(c.get(y))){
                            temp=c.get(x).distanceTo(c.get(y));
                            temp_y=y;
                            temp_x=x;
                        }//end of temp
                        //System.out.printf(""Distance=""+c.get(x).distanceTo(c.get(y))+"",SD=""+temp+"",st=""+""(""+temp_x+"",""+temp_y+"")"");
                        //System.out.print(""\n"");           
                    }//end of y         
                }//end of find shortest pair
                Clustering a = new Clustering();               
                //printall(c);
                //System.out.println();                               
                a=Clustering.merge(c.get(temp_y),c.get(temp_x));
                c.add(a);
                //printall(c);
                //System.out.println(c.size());
                //System.out.printf(a.y()+"",""+a.x());
                //System.out.println();              
                c.remove(c.get(temp_y));
                //System.out.println(c.size());
                c.remove(c.get(temp_x));
                //c.add(a);
                //System.out.println(c.size());
                //printall(c);               
            }//end of n>3
            
         int size = c.size();
         Clustering[] a = new Clustering[size];         
         for(int i=0;i<size;i++){             
                  a[i]=c.get(i);
         }
         Arrays.sort(a);
         
         for(int i=size;i>0;i--){
             System.out.print(a[i-1].n()+"" "");
             a[i-1].print();
         }
        // System.out.print(a[1].x());
        /*
        Point2D[] p = new Point2D[size];  
        for(int i=0;i<size;i++){
            for(int j =0;j<a[i].n();j++){
            p[i]=a[i].get();
            System.out.println(a[i].get(j));
            }
            System.out.println();
        }
        */
       Point2D d1;
       Point2D d2;
       double temp=10;
        if(c.size()==1){
        temp= 0.000000;
       System.out.printf(""%.2f"",temp);
        return;
        }
        for (int i=0;i<2;i++){
            for(int j=0; j<a[i].n();j++){
                d1 = a[i].get(j);
                for(int k=0; k<a[i+1].n();k++){
                    d2 = a[i+1].get(k);
                    if(temp>d1.distanceTo(d2))
                        temp=d1.distanceTo(d2);
                    //System.out.println(temp);
                }
             }
            //System.out.println();
        }
        
    
            for(int j=0; j<a[0].n();j++){
                d1 = a[0].get(j);
                for(int k=0; k<a[2].n();k++){
                    d2 = a[2].get(k);
                    if(temp>d1.distanceTo(d2))
                        temp=d1.distanceTo(d2);
                    //System.out.println(temp);
                }
             }
            //System.out.println();
        
        //System.out.println(temp);
 
        
        System.out.printf(""%.2f"",temp);
        /*
         for(int i=0;i<size;i++){
                      double temp=100;
                      int x= a[i].n();
             for(int j =0;j<x;j++){
                 System.out.println(""+"");
                 if(i==j)
                 ;
                 else {
                     System.out.print(a[i].get(i));
                     System.out.println();
                        //temp=a[i].distanceTo(a[j]);
                        //System.out.print(i);
                        //System.out.print(j);
                        //System.out.println();
                 }                           
             
            }
                System.out.println(""_____"");
         }
        */
         //System.out.println(c.size());
        //printall(c);     
         /*
         double temp =100; 
         for(int i=0;i<c.size();i++){
            for(int j =0;j<c.size();j++){
                if(i==j)
                 ;
                else if(temp>c.get(i).distanceTo(c.get(j)))
                       temp=c.get(i).distanceTo(c.get(j));
                    //System.out.println(c.get(i).distanceTo(c.get(j));
                       System.out.println(temp);                                                        
            }
         }
         */
         //System.out.println(temp);
         
         
         
         
        }//end of readfile  
    }// end of main
 
    public  static  void printall(List<Clustering> q  ){
            Iterator<Clustering> iterator = q.iterator();        
            while(iterator.hasNext())
              iterator.next().print();  
    }
    
    public  static  void print2D(List<Point2D> q  ){
            Iterator<Point2D> iterator = q.iterator();        
            while(iterator.hasNext())
              System.out.println(iterator.next().toString());  
    }
    
 
    
}

   
