
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
//import edu.princeton.cs.algs4.UF;
import java.util.Arrays;

public class LabelCC {

    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

    public LabelCC(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        count = N;
        parent = new int[N];
        rank = new byte[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        parent[rootQ] = rootP;
        rank[rootP]++;
    }

    private void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException(""index "" + p + "" is not between 0 and "" + (N - 1));
        }
    }

    public static void main(String[] args) throws Exception {

        //Read input and pre-proccess all attribute
        BufferedReader br = null;
        String sCurrentLine;
        List<String[]> info = new ArrayList<String[]>();
        try {
            br = new BufferedReader(new FileReader(args[0]));
            while ((sCurrentLine = br.readLine()) != null) {
                String[] Line = sCurrentLine.split("","");
                info.add(sCurrentLine.split("",""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int size = Integer.valueOf(info.get(0)[0]);
        int[] target = {Integer.valueOf(info.get(0)[1]), Integer.valueOf(info.get(0)[2])};
        int[][] image = new int[size][size];

        //initiallize image
        for (int i = 0; i < image.length; i++) {
            Arrays.fill(image[i], 1);
        }
        for (int i = 1; i < info.size(); i++) {
            image[Integer.valueOf(info.get(i)[0]) - 1][Integer.valueOf(info.get(i)[1]) - 1] = 0;
        }

        //initiallize uf object
        LabelCC uf = new LabelCC(size * size);
//        for (int i = 0; i < image.length; i++) {
//            for (int j = 0; j < image[0].length; j++) {
//                System.out.print(image[i][j] + "" "");
//            }
//            System.out.println(' ');
//        }
        int label = 1;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j] > 0) {
                    try {
                        if (image[i][j - 1] > 0 && image[i - 1][j] > 0) {
                            if (image[i][j - 1] < image[i - 1][j]) {
                                image[i][j] = image[i][j - 1];
                                uf.union(size * i + j - 1, size * i + j);
                                uf.union(size * i + j - 1, size * (i - 1) + j);
                            } else {
                                image[i][j] = image[i - 1][j];
                                uf.union(size * (i - 1) + j, size * i + j);
                                uf.union(size * (i - 1) + j, size * i + j - 1);
                            }
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (image[i][j - 1] > 0) {
                            image[i][j] = image[i][j - 1];
                            uf.union(size * i + j - 1, size * i + j);
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (image[i - 1][j] > 0) {
                            image[i][j] = image[i - 1][j];
                            uf.union(size * (i - 1) + j, size * i + j);
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    image[i][j] = label;
                    label++;
                }
            }
        }

        int ii = uf.find(size * (target[0] - 1) + target[1] - 1) / size;
        int jj = uf.find(size * (target[0] - 1) + target[1] - 1) % size;
        System.out.println(image[ii][jj]);
//        for (int i = 0; i < image.length; i++) {
//            for (int j = 0; j < image[0].length; j++) {
//                ii = uf.find(size * i + j) / size;
//                jj = uf.find(size * i + j) % size;
//                System.out.print(image[ii][jj] + ""   "");
//            }
//            System.out.println(' ');
//        }
    }
}

