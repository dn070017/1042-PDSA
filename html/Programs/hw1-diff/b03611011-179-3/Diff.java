
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    private static int id[], count[];
    private static int num, recount, tf = 0, tep;
    private static int number[] = new int[1];

    private static int v = 2;

    public static void union(int p) {
        int quotient;
        int pid = id[p];
        tf = 0;
        if (p < num || p > num * (num - 1)) {
            id[p] = 1;
            if (p < num && id[p + num] != 0) {
                id[p + num] = id[p];
            }
            if (p > num * (num - 1)) {
                id[p] = 2;
                if (id[p - num] > 0) {
                    id[p] = id[p - num];
                    if (id[p - num] == 1) {
                        id[p] = 1;
                    }
                }
            }
        } else {
            quotient = p % num;
            switch (quotient) {
                case 1: {
                    if (id[p - num] > 0) {
                        if (id[p - num] == 1) {
                            id[p] = 1;
                        } else {
                            id[p - num] = id[p];
                        }
                        tf = 1;
                    }
                    if (id[p + 1] > 0) {
                        if (id[p + 1] == 1) {
                            id[p] = 1;
                        } else {
                            id[p + 1] = id[p];
                        }
                    }
                    if (id[p + num] > 0) {
                        if (id[p + num] == 1) {
                            id[p] = 1;
                        } else {
                            id[p + num] = id[p];
                        }
                    }
                    break;
                }
                case 0:
                    if (id[p - num] > 0) {
                        if (id[p - num] == 1) {
                            id[p] = 1;
                        } else {
                            id[p - num] = id[p];
                        }
                        tf = 1;
                    }
                    if (id[p - 1] > 0) {
                        if (id[p - 1] == 1) {
                            id[p] = 1;
                        } else {
                            id[p - 1] = id[p];
                        }
                    }
                    if (id[p + num] > 0) {
                        if (id[p + num] == 1) {
                            id[p] = 1;
                        } else {
                            id[p + num] = id[p];
                        }
                    }
                    break;
                default:
                    if (id[p - num] > 0) {
                        if (id[p - num] == 1) {
                            id[p] = 1;
                        } else {
                            id[p - num] = id[p];
                        }
                        tf = 1;
                    }
                    if (id[p + 1] > 0) {
                        if (id[p + 1] == 1) {
                            id[p] = 1;
                        } else {
                            id[p + 1] = id[p];
                        }
                    }
                    if (id[p - 1] > 0) {
                        if (id[p - 1] == 1) {
                            id[p] = 1;
                        } else {
                            id[p - 1] = id[p];
                        }
                    }
                    if (id[p + num] > 0) {
                        if (id[p + num] == 1) {
                            id[p] = 1;
                        } else {
                            id[p + num] = id[p];
                        }
                    }
            }
        }
//同步上下
        if (tf == 1) {
            for (int k = num + 1; k < id.length - 1; k++) {
                if (id[k] > 0 && id[k - num] > 1) {
                    id[k] = id[p - num];
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
        number[0] = 0;
        number[0] = (x - 1) * num + y;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] data = br.readLine().split("","");
            num = Integer.parseInt(data[0]);
            int cube[] = new int[num * num + 2];
            id = cube.clone();
            tep = 2;
            for (int i = 0; i < cube.length; i++) {
                cube[i] = i;
                id[i] = 0;
                tep = 0;
            }
            id[0] = 1;
            int j = 1;
            recount = 0;
            count = new int[2];
            number[0] = 0;
            search:
            {
                while (data != null) {
                    data = br.readLine().split("","");
                    for (int i = 0; i < 2; i++) {
                        count[i] = Integer.parseInt(data[i]);
                        //define the point
                    }
                    point(count[0], count[1]);
                    id[number[0]] = 2;
                    union(number[0]);
                    if (tf == -1) {
                        System.out.println(count[0] + "","" + count[1]);
                        break search;
                    }
                    j++;
                }
                br.close();
                System.err.println(-1);
            }
        }
    }
}

