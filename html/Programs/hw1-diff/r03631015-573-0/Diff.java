import edu.princeton.cs.algs4.UF;
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public static void main(String[] args) throws Exception {
//        read size
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
//            UF basic setting
            UF uf = new UF(num * num + 2);
            for (int i = 1; i <= num; i++) {
                uf.union(0, i);
                uf.union(num * num + 1, num * num + 1 - i);
            }
//            record open/colse coordinate
            boolean[] dataMatrix = new boolean[num * num + 2];
            int position;
            while (br.ready()) {
//                read open coordinate
                String[] Open = br.readLine().split("","");
                position = (Integer.parseInt(Open[0]) - 1) * num + Integer.parseInt(Open[1]);
                dataMatrix[position] = true;
//                start union
//                upside
                if (position - num > 0) {
                    if (dataMatrix[position - num]) {
                        uf.union(position - num, position);
                    }
                }

//                left side
                if (position - 1 > 0) {
                    if (dataMatrix[position - 1]) {
                        uf.union(position - 1, position);
                    }
                }
//                right side
                if (position + 1 < num * num + 1) {
                    if (dataMatrix[position + 1]) {
                        uf.union(position + 1, position);
                    }
                }

//                lower side
                if (position + num < num * num + 1) {
                    if (dataMatrix[position + num]) {
                        uf.union(position + num, position);
                    }
                }
//                connect confirm
                if (uf.connected(0, num * num + 1)) {
                    System.out.printf(""%s,%s\n"", Open[0], Open[1]);
                    break;
                }
                
            }
//            false reaction
            if(uf.connected(0, num*num+1)==false){
                    System.out.println(-1);
                }
        }

    }
}

