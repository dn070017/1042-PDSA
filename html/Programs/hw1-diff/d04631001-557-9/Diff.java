//import edu.princeton.cs.algs4.UF; // mask
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) { //args[0]
            String[] data = br.readLine().split("","");
            //int stringCount = Integer.parseInt(data[0]);
            int num = Integer.parseInt(data[0]);
            int[][] matrix = new int[num][num];
            //for (int x = 0; x < matrix.length; x++) {
            //    for (int y = 0; y < matrix[0].length; y++) {
            //        System.out.print(matrix[x][y] + "" "");
            //    }
            //    System.out.print(""\n"");
            //}
            String announce = new String();
            UF uf = new UF(num * num + 2);
            String perco = new String();

            for (int i = 0; i < num * num; i++) {
                if ((announce = br.readLine()) == null) {
                    break;
                }
                String[] a = announce.split("","");
                int a_v = Integer.parseInt(a[0]);
                int a_h = Integer.parseInt(a[1]);
                matrix[a_v - 1][a_h - 1] = 1;
                //for (int x = 0; x < matrix.length; x++) {
                //    for (int y = 0; y < matrix[0].length; y++) {
                //        System.out.print(matrix[x][y] + "" "");
                //    }
                //    System.out.print(""\n"");
                //}
                
                if (num > 1) {
                    for (int u = 0; u < num; u++) {
                        if (!uf.connected(u, num * num + 1 - 1)) {
                            uf.union(u, num * num + 1 - 1);
                        }
                        if (!uf.connected((u + (num - 1) * num), num * num + 2 - 1)) {
                            uf.union((u + (num - 1) * num), num * num + 2 - 1);
                        }

                    }
                    if (a_v - 1 > 0) {
                        if (matrix[a_v - 1][a_h - 1] == matrix[a_v - 1 - 1][a_h - 1]) {
                            if (!uf.connected((a_v * num) - num + a_h - 1, (a_v * num) - (num * 2) + a_h - 1)) {
                                uf.union((a_v * num) - num + a_h - 1, (a_v * num) - (num * 2) + a_h - 1);
                                //System.out.println(""up"");
                            }
                        }
                    }
                    if (a_v + 1 <= num) {
                        if (matrix[a_v - 1][a_h - 1] == matrix[a_v - 1 + 1][a_h - 1]) {
                            if (!uf.connected((a_v * num) - num + a_h - 1, (a_v * num) + a_h - 1)) {
                                uf.union((a_v * num) - num + a_h - 1, (a_v * num) + a_h - 1);
                                //System.out.println(""down"");
                            }
                        }
                    }
                    if (a_h - 1 > 0) {
                        if (matrix[a_v - 1][a_h - 1] == matrix[a_v - 1][a_h - 1 - 1]) {
                            if (!uf.connected((a_v * num) - num + a_h - 1, (a_v * num) - num + a_h - 1 - 1)) {
                                uf.union((a_v * num) - num + a_h - 1, (a_v * num) - num + a_h - 1 - 1);
                                //System.out.println(""left"");
                            }
                        }
                    }
                    if (a_h + 1 <= num) {
                        if (matrix[a_v - 1][a_h - 1] == matrix[a_v - 1][a_h - 1 + 1]) {
                            if (!uf.connected((a_v * num) - num + a_h - 1, (a_v * num) - num + a_h)) {
                                uf.union((a_v * num) - num + a_h - 1, (a_v * num) - num + a_h);
                                //System.out.println(""right"");
                            }
                        }
                    }
                    if (uf.connected(num * num + 1 - 1, num * num + 2 - 1)) {
                        perco = announce;
                        System.out.printf(""%s\n"", perco);
                        return;
                    //} else {
                        //    perco = ""-1"";
                        //    System.out.printf(""%s\n"", perco);
                        //}
                    }
                    
                    //uf.find(2) ;
                    if (num == 1) {
                        if (a_v == 1 && a_h == 1) {
                            perco = announce;
                            System.out.printf(""%s\n"", perco);
                            
                        }   
                    }
                }
                //System.out.printf(""%s\n"", uf.find(a_v*num-num+a_h-1));
                // TODO code application logic here
            }
            
            System.out.printf(""%s\n"", ""-1"");
            
            //System.out.printf(""%s\n"", uf.find(9));
            //System.out.printf(""%s\n"", uf.find(10));
        }
    }
}

