
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    private static int id[], count[];
    private static int num, tf = 0, tep;
    private static int number;
    private static final int v = 2;

    public static void union(int p) {
        int quotient;
        int pid = id[p];
        tf = 0;
        tep = p / num;
        quotient = p % num;
        switch (tep) {
            case 0:
                id[p] = 1;
                if (id[p + num] > 0) {
                    id[p + num] = 1;
                }
                break;
            default:
                switch (quotient) {
                    case 1:
                        if (id[p - num] == 1) {
                            id[p] = 1;
                            tf = 1;
                        }
                        if (id[p + 1] == 1) {
                            id[p] = 1;
                            tf = 1;
                        }
                        if (tep >= num - 1) {
                            break;
                        } else {
                            if (id[p + num] == 1) {
                                id[p] = 1;
                                tf = 1;
                            }
                        }
                        break;
                    case 0:
                        if (id[p - num] == 1) {
                            id[p] = 1;
                            tf = 1;
                        }
                        if (id[p - 1] == 1) {
                            id[p] = 1;
                            tf = 1;
                        }
                        if (tep >= num - 1) {
                            break;
                        } else {
                            if (id[p + num] == 1) {
                                id[p] = 1;
                                tf = 1;
                            }
                        }
                        break;
                    default:
                        if (id[p - num] == 1) {
                            id[p] = 1;
                            tf = 1;
                        }
                        if (id[p + 1] == 1) {
                            id[p] = 1;
                            tf = 1;
                        }
                        if (id[p - 1] == 1) {
                            id[p] = 1;
                            tf = 1;
                        }
                        if (tep >= num - 1) {
                            break;
                        } else {
                            if (id[p + num] == 1) {
                                id[p] = 1;
                                tf = 1;
                            }
                        }
                        break;
                }
        }
//左右同步
        if (tf == 1 && quotient < num - 1) {
            switch (tep) {
                case 1:
                    if (id[p + 1] == 2) {
                        id[p + 1] = 1;
                    }
                    break;
                case 0:
                    if (id[p - 1] == 2) {
                        id[p - 1] = 1;
                    }
                    break;
                default:
                    if (id[p + 1] == 2) {
                        id[p + 1] = 1;
                    }
                    if (id[p - 1] == 2) {
                        id[p - 1] = 1;
                    }
                    break;
            }
            if (tep <= num - 2) {
                if (id[p + num] == 2) {
                    id[p + num] = 1;
                }
            }else{
            if(id[p]==1){
            tf=-1;
            return;
            }
            }
        }
        //check connective
        for (int i = 0; i < num; i++) {
            if (id[num * (num - 1) + 1 + i] == 1) {
                tf = -1;
                return;
            }
        }
    }

    public static void point(int x, int y) {
        number = 0;
        number = (x - 1) * num + y;
    }

    public static void main(String[] args) throws Exception {
        String data;
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] sum = br.readLine().split("","");
            num = Integer.parseInt(sum[0]);
            int cube[] = new int[num * num + 2];
            id = cube.clone();
            for (int i = 0; i < cube.length; i++) {
                cube[i] = i;
                id[i] = 0;
            }
            id[0] = 1;
            String[] data1;
            int j = 1;
            count = new int[2];
            number = 0;
            while ((data = br.readLine()) != null) {
                data1 = data.split("","");
                for (int i = 0; i < 2; i++) {
                    count[i] = Integer.parseInt(data1[i]);
                    //define the point
                }
                point(count[0], count[1]);
                id[number] = 2;
                union(number);
                if (tf == -1) {
                    System.out.println(count[0] + "","" + count[1]);
                    break;
                }
            }
            if (data == null) {
                System.out.println(-1);
            }
        }
    }
}

