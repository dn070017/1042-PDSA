import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class Clustering {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) { // change ""ex.txt"" to args[0]
            int N = Integer.parseInt(br.readLine());
            double points[][] = new double[N][2];
            ArrayList<double[]> list = new ArrayList();
            QuickFindUF QFUF = new QuickFindUF(N);
            
            for (int i = 0; i < N; i++){
                String temp[] = br.readLine().split("" "");
                double input[] = new double[4];
                input[0] = Double.parseDouble(temp[0]);
                input[1] = Double.parseDouble(temp[1]);
                
                points[i][0] = input[0];
                points[i][1] = input[1];
                
                input[2] = 1;
                input[3] = i;
                list.add(input);
            }
            while (list.size()>3){
                int list_size = list.size();
                int point_link = list_size * (list_size - 1)/2;
                Integer num[] = new Integer[2];
                ST<Double, String> link_table = new ST<>();
                
                for (int i = 0; i < list_size; i ++){
                    for (int j = i + 1; j < list_size; j++){
                        num[0] = i;
                        num[1] = j;
                        double[] point_a = list.get(i);
                        double[] point_b = list.get(j);
                        double dist = Math.sqrt(Math.pow(point_a[0] - point_b[0], 2) + Math.pow(point_a[1] - point_b[1], 2));
                                                
                        String a_id = Integer.toString(i);
                        String a_id_lab = String.valueOf(point_a[2]);
                        String b_id = Integer.toString(j);
                        String b_id_lab = String.valueOf(point_b[2]);
                        
                        String lab = a_id + "","" + b_id + "","" + a_id_lab + "","" + b_id_lab;
                        link_table.put(dist, lab);  
                    }
                }
                Double min_link = link_table.min();
                String min_point[] = link_table.get(min_link).split("","");
                
                int a_id_int = Integer.parseInt(min_point[0]);
                double a_lab_dou = Double.parseDouble(min_point[2]);
                
                int b_id_int = Integer.parseInt(min_point[1]);
                double b_lab_dou = Double.parseDouble(min_point[3]);
                
                double[] a_min_dou = list.get(a_id_int);
                double[] b_min_dou = list.get(b_id_int);
                
                double cent_x = a_min_dou[0] + ((b_min_dou[0] - a_min_dou[0]) * (b_lab_dou / (a_lab_dou + b_lab_dou)));
                double cent_y = a_min_dou[1] + ((b_min_dou[1] - a_min_dou[1]) * (b_lab_dou / (a_lab_dou + b_lab_dou)));
                                
                double clus_num = 0;
                int clust_a_num = 0;
                int clust_b_num = 0;
                
                if (a_lab_dou == 1 && b_lab_dou == 1){
                    if (a_min_dou[3] > b_min_dou[3]){
                        clust_a_num = (int) a_min_dou[3];
                        clust_b_num = (int) b_min_dou[3];
                        QFUF.union(clust_b_num, clust_a_num);
                        clus_num = a_min_dou[3];
                    } else {
                        clust_a_num = (int) a_min_dou[3];
                        clust_b_num = (int) b_min_dou[3];
                        QFUF.union(clust_a_num, clust_b_num);
                        clus_num = b_min_dou[3];
                    }
                } else if (a_lab_dou != 1 && b_lab_dou == 1){
                    clust_a_num = (int) a_min_dou[3];
                    clust_b_num = (int) b_min_dou[3];
                    QFUF.union(clust_b_num, clust_a_num);
                    clus_num = a_min_dou[3];
                } else if (a_lab_dou == 1 && b_lab_dou != 1){
                    clust_a_num = (int) a_min_dou[3];
                    clust_b_num = (int) b_min_dou[3];
                    QFUF.union(clust_a_num, clust_b_num);
                    clus_num = a_min_dou[3];
                } else {
                    if (a_min_dou[3] > b_min_dou[3]){
                        clust_a_num = (int) a_min_dou[3];
                        clust_b_num = (int) b_min_dou[3];
                        QFUF.union(clust_b_num, clust_a_num);
                        clus_num = a_min_dou[3];
                    } else {
                        clust_a_num = (int) a_min_dou[3];
                        clust_b_num = (int) b_min_dou[3];
                        QFUF.union(clust_a_num, clust_b_num);
                        clus_num = b_min_dou[3];
                    }
                }
                link_table.delete(min_link);
                if (a_id_int > b_id_int){
                    list.remove(a_id_int);
                    list.remove(b_id_int);
                } else {
                    list.remove(a_id_int);
                    list.remove(b_id_int - 1);
                }
                double[] curr_list = {cent_x, cent_y, a_lab_dou + b_lab_dou, clus_num};
                list.add(curr_list);
            }
            double clus_dist = 0;
            double min_dist = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    if (!QFUF.connected(i, j)) {
                        min_dist = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                        if (clus_dist == 0) {
                            clus_dist = min_dist;
                        } else if (min_dist <= clus_dist) {
                            clus_dist = min_dist;
                        }
                    }
                }
            }           
            double[] cen_clus1 = list.get(0);
            double[] cen_clus2 = list.get(1);
            double[] cen_clus3 = list.get(2);
            double[] clus_size = {cen_clus1[2], cen_clus2[2], cen_clus3[2]};
            Arrays.sort(clus_size);
            
            if (clus_size[0] == 1 && clus_size[1] == 1 && clus_size[2] == 1) {
                System.out.printf(""%.0f %.2f %.2f"", cen_clus1[2], cen_clus1[0], cen_clus1[1]);
                System.out.printf(""\n%.0f %.2f %.2f"", cen_clus2[2], cen_clus2[0], cen_clus2[1]);
                System.out.printf(""\n%.0f %.2f %.2f"", cen_clus3[2], cen_clus3[0], cen_clus3[1]);
            } else {
                for (int i = 2; i >= 0; i--) {
                    if (clus_size[i] == cen_clus1[2]) {
                        System.out.printf(""%.0f %.2f %.2f"", cen_clus1[2], cen_clus1[0], cen_clus1[1]);
                    } else if (clus_size[i] == cen_clus2[2]) {
                        System.out.printf(""\n%.0f %.2f %.2f"", cen_clus2[2], cen_clus2[0], cen_clus2[1]);
                    } else if (clus_size[i] == cen_clus3[2]) {
                        System.out.printf(""\n%.0f %.2f %.2f"", cen_clus3[2], cen_clus3[0], cen_clus3[1]);
                    }
                }
            }
            System.out.printf(""\n%.2f \n"", clus_dist);            
        }
    }
}

