
//import static java.awt.geom.Point2D.distance;
import java.util.*;

class CV implements Comparable<CV> {
    public super_point[] p1 = new super_point[1];
    public super_point[] p2 = new super_point[1];
    public double cc;
    public CV(super_point p1, super_point p2, double cc) {
        this.p1[0] = p1;
        this.p2[0] = p2;
        this.cc = cc;
    }
    public super_point[] getp1() {
        return this.p1;
    }
    public super_point[] getp2() {
        return this.p2;
    }
    public int compareTo(CV that) {
        if (this.cc > that.cc) {
            return 1;
        }
        if (this.cc < that.cc) {
            return -1;
        }
        return 0;
    }
}

class super_point implements Comparable<super_point> {
    public Point2D[] pp = new Point2D[1];
    public int fat;
    public super_point(Point2D pp, int fat) {
        this.pp[0] = pp;
        this.fat = fat;
    }
    public Point2D[] getpp() {
        return this.pp;
    }
    public int fat() {
        return this.fat;
    }
    public int compareTo(super_point that) {
        if(this.fat>that.fat)
            return 1;
        if(this.fat<that.fat)
            return -1;
        return 0;
    }
}
public class Clustering {

    public static super_point[] shortist(super_point[] oh,ArrayList<Integer> op,Point2D[] re) {
        int len = oh.length;
        MinPQ<CV> distence = new MinPQ();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                double dd = oh[i].pp[0].distanceTo(oh[j].pp[0]);
                CV a = new CV(oh[i], oh[j], dd);
                distence.insert(a);
            }
        }
  
        CV result = distence.delMin();
        super_point[] output = new super_point[len - 1];
        double x_1 = result.p1[0].pp[0].x();
        double x_2 = result.p2[0].pp[0].x();
        double y_1 = result.p1[0].pp[0].y();
        double y_2 = result.p2[0].pp[0].y();
        int fat_1 = result.p1[0].fat;
        int fat_2 = result.p2[0].fat;
        double new_x = ((x_1 * fat_1) + (x_2 * fat_2)) / (fat_1 + fat_2);
        double new_y = ((y_1 * fat_1) + (y_2 * fat_2)) / (fat_1 + fat_2);
        Point2D newones = new Point2D(new_x, new_y);
        output[0] = new super_point(newones, fat_1 + fat_2);
        Point2D[] newone = new Point2D[len];
        int output_count = 1;
        int uu =0;
       
        for(int i=0;i<re.length;i++)
        {
             if (result.p1[0].pp[0].equals(re[i])|result.p2[0].pp[0].equals(re[i])) {
                 re[i] = newones;
                op.add(uu, i);
                uu++;
              //   System.out.println("" i"" +i);
            } 
        
        }
        
        for (int i = 0; i < len; i++) {
            if ((oh[i].pp[0].equals(result.p1[0].pp[0])) | (oh[i].pp[0].equals(result.p2[0].pp[0]))) {
                
            } else {
                output[output_count] = new super_point(oh[i].pp[0], oh[i].fat);
                output_count++;
            }
        }
        return output;
    }

    public static void main(String[] args) {

        In in = new In(args[0]);
        int N = Integer.valueOf(in.readLine());
        Point2D[] cluster = new Point2D[N];
        int N_3 = N;
        int input_count = 0;
        String line;
        super_point[] sup_clu = new super_point[N];
         Point2D[] clean_clu = new Point2D[N];
        while ((line = in.readLine()) != null) {
            cluster[input_count] = new Point2D(Double.valueOf(line.split("" "")[0]), Double.valueOf(line.split("" "")[1]));
             clean_clu[input_count] = new Point2D(Double.valueOf(line.split("" "")[0]), Double.valueOf(line.split("" "")[1]));
            sup_clu[input_count] = new super_point(cluster[input_count], 1);
            input_count++;
        }
       
       
        UF uf = new UF(N);
        int[] aaa=new int[2];
   
       
        while (N_3 > 3) {
            ArrayList<Integer> storge = new ArrayList<Integer>();
           sup_clu = shortist(sup_clu,storge,cluster);  
        
             for (int i = 0; i < storge.size()-1; i++) {
                 for(int j=i;j<storge.size();j++)
                 {  
                     uf.union(storge.get(i), storge.get(j));              
                 }
        }
            N_3--;
        }     
        MinPQ distence = new MinPQ();
        Merge.sort(sup_clu);
        if(N>3){
        for (int i = sup_clu.length-1; i >=0 ; i--) {
          
          double c=sup_clu[i].pp[0].x() ;
          double d=sup_clu[i].pp[0].y() ;
           String first =   String.format(""%.2f"", c);
           String sec =   String.format(""%.2f"", d);         
           System.out.println(sup_clu[i].fat+"" ""+first+"" ""+sec); 
        }           
        }else{for (int i = 0; i<sup_clu.length ; i++) {
          
          double c=sup_clu[i].pp[0].x() ;
          double d=sup_clu[i].pp[0].y() ;
           String first =   String.format(""%.2f"", c);
           String sec =   String.format(""%.2f"", d);         
           System.out.println(sup_clu[i].fat+"" ""+first+"" ""+sec); }}
     //   System.out.println(""sss"");
        
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
            
                if(uf.find(i)!=uf.find(j))
                {                     
                double dd = clean_clu[i].distanceTo(clean_clu[j]);        
                distence.insert(dd);
                }
            }
        }
    
        double i = (double) distence.delMin();
       String first =   String.format(""%.2f"", i);
//String e = r.substring(0, r.indexOf(""."") + 3);
        System.out.println(first);

    }
}
