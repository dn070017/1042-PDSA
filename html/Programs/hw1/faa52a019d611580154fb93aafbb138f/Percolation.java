
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    private static int id[], tep[], count[];
    private static int num, recount, tf = 0;
    private static int number[] = new int[1];

    private static int v = 1;

    public static void union(int p) {
        int quotient;
        int pid = id[p];
        if (p < num || p > num * (num - 1)) {
            id[p] = 1;
            if (p < num && id[p + num] != 0) {
                id[p + num] = id[p];
            }
            if (p >= num * (num)) {
                id[p] = id.length;
            }
        } else {
            quotient = p % num;
            switch (quotient) {
                case 1: {
                    if (id[p - num] > 0) {
                        id[p] = id[p - num];
                        tf = 1;
                    }
                    if (id[p + 1] > 0) {
                        id[p] = id[p + 1];
                        tep[recount] = id[p];
                    }
                    if (id[p + num] > 0) {
                        id[p] = id[p + num];
                        tep[recount] = id[p];
                    }
                    if (id[p - num] <= 0 && id[p + 1] <= 0 && id[p + num] <= 0) {
                        v++;
                        id[p] = v;
                    }
                    break;
                }
                case 0:
                    if (id[p - num] > 0) {
                        id[p] = id[p - num];
                        tf = 1;
                    }
                    if (id[p - 1] > 0) {
                        id[p] = id[p - 1];
                        tep[recount] = id[p];
                    }
                    if (id[p + num] > 0) {
                        id[p + num] = id[p];
                        tep[recount] = id[p];
                    }
                    if (id[p - num] <= 0 && id[p - 1] <= 0 && id[p + num] <= 0) {
                        v++;
                        id[p] = v;
                    }
                    break;
                default:
                    if (id[p - num] > 0) {
                        id[p] = id[p - num];
                        tf = 1;
                    }
                    if (id[p + 1] > 0) {
                        id[p] = id[p + 1];
                        tep[recount] = id[p];
                    }
                    if (id[p - 1] > 0) {
                        id[p] = id[p - 1];
                        tep[recount] = id[p];
                    }
                    if (id[p + num] > 0) {
                        id[p + num] = id[p];
                        tep[recount] = id[p];
                    }
                    if (id[p - num] <= 0 && id[p + 1] <= 0 && id[p + num] <= 0 && id[p - 1] <= 0) {
                        v++;
                        id[p] = v;
                    }
            }
        }
        for (int i = 0; i < num; i++) {
            if (id[2 * num + 1 + i] == 1) {
                tf = -1;
                return;
            }
        }

        if (tf == 1) {
            for (int i = 0; i < 2; i++) {
                for (int k = 1; k < id.length - 1; k++) {
                    if (id[k] == tep[recount]) {
                        id[k] = id[p - num];
                    }
                }
            }
        }
    }

    public int getnumber() {
        return number[0];
    }

    public static void point(int x, int y) {
        number[0] = 0;
        number[0] = (x - 1) * num + y;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            num = Integer.parseInt(data[0]);
            int cube[] = new int[num * num + 2];
            id = cube.clone();
            tep = cube.clone();
            for (int i = 0; i < cube.length; i++) {
                cube[i] = i;
                id[i] = 0;
                tep[i] = 0;
            }
            id[0] = 1;
            int j = 1;
            recount = 0;
            count = new int[2];
            number[0] = 0;
            while (j >= 0) {
                String[] data1 = br.readLine().split("","");
                for (int i = 0; i < 2; i++) {
                    count[i] = Integer.parseInt(data1[i]);
                    //define the point
                }
                point(count[0], count[1]);
                union(number[0]);
                if (tf == -1) {
                    System.out.println(count[0] + "","" + count[1]);
                    break;
                }
                j++;
            }

        }
    }
}

