
import java.io.BufferedReader;
import java.io.FileReader;

public class LabelCC {

    public static int[] place;
    public static int[] id, count;
    public static int n, number, x;
    private static int[] parent, child;  // parent[i] = parent of i
    private static int[] change;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private static int rootQ, rootP, amount;
    //amount =rank
    //n= length
    //id= if or not
    //cont=>number where it is

    public static void point(int x, int y) {
        number = (x - 1) * n + y;
    }

    public static void UF(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        parent = new int[N];
        child = new int[N];
        change = new int[N / 4];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            child[i] = 0;
        }
    }

    private void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + (N - 1));
        }
    }

    public static void union(int p, int q) {
        rootP = parent[p];
        rootQ = parent[q];
        if (rootP == rootQ) {
            return;
        }
        // make root of smaller rank point to root of larger rank
        if (rootP < rootQ) {
            parent[rootQ] = rootP;
        } else if (rootP > rootQ) {
            parent[rootP] = rootQ;
        }
//        count--;
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] fund = br.readLine().split("","");
            n = Integer.parseInt(fund[0]);
            place = new int[2];
            place[0] = Integer.parseInt(fund[1]);
            place[1] = Integer.parseInt(fund[2]);
            id = new int[n * n + 2];
            x = 0;
            for (int i = 1; i <= n * n + 1; i++) {
                id[i] = 1;
            }
            count = new int[2];
            String data;
            String[] data1;
            UF(n * n + 2);
            while ((data = br.readLine()) != null) {
                data1 = data.split("","");
                for (int i = 0; i < 2; i++) {
                    count[i] = Integer.parseInt(data1[i]);
                    //define the point
                }
                point(count[0], count[1]);
                id[number] = 0;
                union(0, number);
            }
            amount = 1;
            if (id[1] > 0) {
                parent[1] = amount;
                amount++;
            }
            for (int i = 2; i <= n; i++) {
                if (id[i] > 0) {
                    if (id[i - 1] > 0) {
                        union(i, i - 1);
                    } else {
                        parent[i] = amount;
                        amount++;
                    }
                }
            }
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    point(i, j);
                    if (id[number] > 0) {
                        if (j == 1) {
                            if (id[number - n] > 0) {
                                union(number, number - n);
                            } else {
                                parent[number] = amount;
                                amount++;
                            }
                        } else if (id[number - n] > 0 && id[number - 1] > 0) {
                            if (parent[number - n] < parent[number - 1]) {
                                parent[number] = parent[number - n];
                                child[number] = parent[number - 1];
                                change[x] = parent[number - n];
                                x++;
                                change[x] = parent[number - 1];
                                x++;
                            } else if (parent[number - n] > parent[number - 1]) {
                                parent[number] = parent[number - 1];
                                child[number] = parent[number - n];
                                change[x] = parent[number - 1];
                                x++;
                                change[x] = parent[number - n];
                                x++;
                            } else {
                                if (parent[number - n] <= parent[number - 1]) {
                                    union(number, number - n);
                                } else {
                                    union(number, number - 1);
                                }
                            }
                        } else if (id[number - n] > 0) {
                            union(number, number - n);
                        } else {
                            if (id[number - 1] > 0) {
                                union(number, number - 1);
                            } else {
                                parent[number] = amount;
                                amount++;
                            }
                        }
                    }
                }
            }
            point(place[0], place[1]);
            for (int i = 1; i < x; i = i + 2) {
                if (parent[number] == change[i]) {
                    parent[number] = change[i - 1];
                    break;
                }
            }
            System.out.println(parent[number]);
        }
    }
}

