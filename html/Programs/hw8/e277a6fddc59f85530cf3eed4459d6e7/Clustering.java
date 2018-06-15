/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author chenchen
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Clustering {

    private static class Pqcompare implements Comparable<Pqcompare> {

        Pqcompare() {
.
        }

        private Integer b;
        private Integer a;
        private Double distance;

        public Pqcompare(Integer a, Integer b, Double distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;

        }

        @Override
        public int compareTo(Pqcompare that) {
            if (this.distance < that.distance) {
                return -1;
            } else if (this.distance > that.distance) {
                return 1;
            } else {
                return 0;
            }
        }


    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int Num = Integer.parseInt(br.readLine());
            int[] R = new int[Num*2];
            int[] Ori_num = new int[Num*2];
            Point2D[] points = new Point2D[Num*2];
            Point2D[] original_points = new Point2D[Num*2];
            int[][] min2point = new int[1][2];
            MinPQ<Pqcompare> pq = new MinPQ<Pqcompare>();
            MinPQ<Pqcompare> tryy = new MinPQ<Pqcompare>();
            QuickFindUF uf = new QuickFindUF(Num*2);
            for (int i = 0; i < Num; i++) {
                String[] header = br.readLine().split("" "");
                R[i] = i;
                R[i+Num] = i+Num;
                Ori_num[i] = 1;
                Ori_num[i+Num] = 1;
                points[i] = new Point2D(Double.parseDouble(header[0]), Double.parseDouble(header[1]));
                original_points[i] = new Point2D(Double.parseDouble(header[0]), Double.parseDouble(header[1]));

            }
          
//try other methodfor (int j = i + 1; i < Num; i++) {===============================================
            

            for (int i = 0; i < Num-1 ; i++) {
                for (int j = i + 1; j < Num; j++) {
                    double arr = points[i].distanceTo(points[j]);
                    Pqcompare keke = new Pqcompare(i, j, arr);
                    tryy.insert(keke);
                }
            }
int ch = 0;
            while (uf.count() != 6) {
                Pqcompare aa = tryy.delMin();
                if (Ori_num[aa.a] != 0) {
                    if (Ori_num[aa.b] != 0) {
                        R[uf.find(aa.a)] = 2*Num + 1;
                        R[aa.a] = 2*Num + 1;  
                        
                        
//                        it means aa.a become a children
                        Ori_num[aa.a] = 0;
                        Ori_num[aa.b] = 0;

                        int root_now_b = uf.find(aa.b);
                        int root_now_a = uf.find(aa.a);
                        int cum_count_a = 0;
                        int cum_count_b = 0;
                        for (int i = 0; i < Num; i++) {
                            if (uf.find(i) == root_now_a) {
                                cum_count_a = cum_count_a + 1;
                            }
                            if (uf.find(i) == root_now_b) {
                                cum_count_b = cum_count_b + 1;
                            }
                        }

                        uf.union(aa.a, aa.b);
  
                        double x_merged = (points[aa.a].x() * cum_count_a + points[aa.b].x() * cum_count_b) / (cum_count_a + cum_count_b);
                        double y_merged = (points[aa.a].y() * cum_count_a + points[aa.b].y() * cum_count_b) / (cum_count_a + cum_count_b);
                        points[Num + ch] = new Point2D(x_merged, y_merged);
                       uf.union(Num+ch, aa.b);
  
                       
                        for (int i = 0; i < Num+ch; i++) {
                            if(Ori_num[i]!=0){
                            double newdis = points[Num + ch].distanceTo(points[i]);
                            Pqcompare haha = new Pqcompare(Num + ch, i, newdis);
                            tryy.insert(haha);
                            }
                        }
                        
                         
                
                        ch = ch+1;
                    }
                }
                  if (uf.count() == 6) {
                    break;
                }
            }


// keep here=============================================
            int[] root_record = new int[3];
            int[] root_size = new int[3];
            root_size[0] = 0;
            root_size[1] = 0;
            root_size[2] = 0;
            int initial = 0;
          

            for (int i = 0; i < Num; i++) {
                if (R[i] != 2*Num + 1) {
                    root_record[initial] = R[i];
                    initial = initial + 1;
                }
            }

            for (int i = 0; i < Num; i++) {
                if (uf.find(i) == root_record[0]) {
                    root_size[0] = root_size[0] + 1;
                }
                if (uf.find(i) == root_record[1]) {
                    root_size[1] = root_size[1] + 1;
                }
                if (uf.find(i) == root_record[2]) {
                    root_size[2] = root_size[2] + 1;
                }
            }
            
            Point2D[] points_ans = new Point2D[3];
           
           for (int i = Num+0.5*ch; i<Num+ch; i ++){
                int ra = uf.find(i);
                if (ra ==root_record[0]){
                    points_ans[0] = points[i];
                }
                if (ra ==root_record[1]){
                    points_ans[1] = points[i];
                }
                if (ra ==root_record[2]){
                    points_ans[2] = points[i];
                }
            }

           for (int i = 0; i<2; i ++){
               for (int j = 2; j>0; j --){
               if( root_size[j] >root_size[j-1]) {
                   int t = root_size[j];
                   root_size[j] = root_size[j-1];
                   root_size[j-1] = t;
                   Point2D y = points_ans[j];
                   points_ans[j] = points_ans[j-1];
                   points_ans[j-1] = y;
               }
           }
           }
 
            System.out.println(Integer.toString(root_size[0]) + "" "" + String.format(""%.2f"", points_ans[0].x()) + "" "" + String.format(""%.2f"", points_ans[0].y()));
            System.out.println(Integer.toString(root_size[1]) + "" "" + String.format(""%.2f"", points_ans[1].x()) + "" "" + String.format(""%.2f"", points_ans[1].y()));
            System.out.println(Integer.toString(root_size[2]) + "" "" + String.format(""%.2f"", points_ans[2].x()) + "" "" + String.format(""%.2f"", points_ans[2].y()));

            
                        for (int i = 0; i < Num-1 ; i++) {
                for (int j = i + 1; j < Num; j++) {
                    double arr = points[i].distanceTo(points[j]);
                    Pqcompare keke = new Pqcompare(i, j, arr);
                    pq.insert(keke);
                }
            }
            int mm = 0;
            while(mm == 0){
                Pqcompare aa = pq.delMin();
                if(uf.find(aa.a)!=uf.find(aa.b)){
                   System.out.println(String.format(""%.2f"", aa.distance) );   
                   break;
                }
            }

        }
    }
}

