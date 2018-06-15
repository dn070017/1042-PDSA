import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {
    public static boolean check(int[][] site, int i, int j) {
        int N = site.length;
        if (i < 0 || i >= N) return false;        // invalid row
        if (j < 0 || j >= N) return false;        // invalid column
        if (site[i][j] == -1) return false;       // not an open site
        return true;
    }
    public static void main(String[] args) throws Exception {
     // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            String line;
            String[] broken_line;
            int i = 0; int j = 0; int x = 0; int y = 0; int count = 1;
            int[][] site = new int[num][num];
            QuickFindUF  uf = new QuickFindUF (num*num+1);
//            uf.union(0, 5);
//            System.out.printf(""%d\n"",uf.find(2));

            while((line = br.readLine()) != null){
                broken_line = line.split("","");
                i = Integer.parseInt(broken_line[0])-1;
                j = Integer.parseInt(broken_line[1])-1;
                site[i][j] = -1;
            }
            
            for (i = 0; i < num; i++) {
                for (j = 0; j < num; j++) {
                    if (site[i][j] >= 0) {
                        if (check(site,i,j-1) && check(site,i-1,j)) {
                            if(site[i-1][j]<site[i][j-1]){site[i][j] = site[i-1][j];uf.union((i-1)*num+j+1, i*num+j+1);uf.union((i-1)*num+j+1, i*num+j);}
                            else{site[i][j] = site[i][j-1];uf.union(i*num+j, i*num+j+1);uf.union(i*num+j, (i-1)*num+j+1);}
                        }
                        else 
                            if (check(site,i,j-1)){site[i][j] = site[i][j-1];uf.union(i*num+j, i*num+j+1);}
                            else if(check(site,i-1,j)){site[i][j] = site[i-1][j];uf.union((i-1)*num+j+1, i*num+j+1);}
                            else {site[i][j] = count; count++;}   
                    }
                    else
                        uf.union(0, i*num+j+1);
                }
            }
            for (i = 0; i < num; i++) {
                for (j = 0; j < num; j++) {
                    if (uf.find(i*num+j+1) > 0) {
                        x = (uf.find(i*num+j+1)-1)/num;
                        y = (uf.find(i*num+j+1)-1)%num;
                        site[i][j] = site[x][y];
                    }
                }
            }
//            for (i = 0; i < num; i++) {
//                for (j = 0; j < num; j++) {
//                    System.out.printf(""%d "",site[i][j]);
//                    }
//                System.out.printf(""\n"");
//            }
            System.out.printf(""%d\n"",site[Integer.parseInt(data[1])-1][Integer.parseInt(data[2])-1]);
        }
    }
}

