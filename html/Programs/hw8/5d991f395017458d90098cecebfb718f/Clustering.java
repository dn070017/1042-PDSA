
import java.io.BufferedReader;
import java.io.FileReader;

public class Clustering {

    private static class Cluster implements Comparable<Cluster> {

        private int size;
        private double x, y;
        private int index;
        private int head;
        public int valid = 1;

        public Cluster(double x, double y, int size, int index, int head) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.index = index;
            this.head = head;
        }

        public double centriodx() {
            return this.x;
        }

        public double centriody() {
            return this.y;
        }

        public void addSize(int a) {
            this.size = this.size + a;
        }

        public int getSize() {
            return this.size;
        }

        public int getIndex() {
            return this.index;
        }

        public int getHead() {
            return this.head;
        }
        
        public int isValid(){
            return valid;
        }

        public void merge(Cluster that) {
            this.x = (this.x * this.size + that.x * that.size) / (this.size + that.size);
            this.y = (this.y * this.size + that.y * that.size) / (this.size + that.size);
            this.size = this.size + that.size;
        }

        public int compareTo(Cluster that) {
            if (this.size < that.size) {
                return -1;
            }
            if (this.size > that.size) {
                return 1;
            }
            if (this.centriodx() < that.centriodx()) {
                return -1;
            }
            if (this.centriodx() > that.centriodx()) {
                return 1;
            }
            if (this.centriody() < that.centriody()) {
                return -1;
            }
            if (this.centriody() > that.centriody()) {
                return 1;
            }
            return 0;
        }

    }

    private static class Pair implements Comparable<Pair> {

        private Cluster a, b;
        private int[] index = new int[2];

        public Pair(Cluster a, Cluster b, int x, int y) {
            this.a = a;
            this.b = b;
            index[0] = x;
            index[1] = y;

        }

        public double getDistance() {
            double dx = this.a.centriodx() - this.b.centriodx();
            double dy = this.a.centriody() - this.b.centriody();
            return Math.sqrt(dx * dx + dy * dy);
        }

        public int getIndex1() {
            return index[0];
        }

        public int getIndex2() {
            return index[1];
        }

        public Cluster getClusterA() {
            return this.a;
        }

        public Cluster getClusterB() {
            return this.b;
        }

        public int compareTo(Pair that) {
            if (this.getDistance() > that.getDistance()) {
                return 1;
            }
            if (this.getDistance() < that.getDistance()) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            int number = Integer.parseInt(data);
            int clusternum = number;

            Cluster[] wei = new Cluster[number * 2];
            WeightedQuickUnionUF yuru = new WeightedQuickUnionUF(number);
            String stillhave;
            String[] temp = new String[2];
            int id = 0;
            MinPQ<Pair> pq = new MinPQ<Pair>(number);

            while ((stillhave = br.readLine()) != null) {
                temp = stillhave.split("" "");
                wei[id] = new Cluster(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), 1, id, id);
                id++;
            }
            Pair[] apple = new Pair[number * number / 2];
            int c = 0;
            for (int i = 0; i < number; i++) {
                for (int j = i + 1; j < number; j++) {
                    apple[c] = new Pair(wei[i], wei[j], i, j);
                    pq.insert(apple[c]);
                    c = c + 1;
                }
            }

            Pair a;
            while (clusternum > 3) {
                a = pq.delMin();
                if (a.a.valid + a.b.valid == 2) {
                    a.a.valid = 0;
                    a.b.valid = 0;
                    yuru.union(a.a.getHead(), a.b.getHead());
                    wei[id] = new Cluster(a.a.centriodx(), a.a.centriody(), a.a.getSize(), id, a.a.getHead());
                    wei[id].merge(a.b);
                    for (int i = 0; i < id; i++) {
                        if (wei[i].valid == 1) {
                            Pair test = new Pair(wei[i], wei[id], i, id);
                            pq.insert(test);
                        }
                    }
                    id++;
                    clusternum--;
                    {

                    }
                }
            }
            MinPQ<Pair> pq2 = new MinPQ<Pair>(number);
            Pair[] mdts = new Pair[number * number / 2];
            int d = 0;
            for (int i = 0; i < number; i++) {
                for (int j = i + 1; j < number; j++) {
                    if (!yuru.connected(i, j)) {
                        mdts[d] = new Pair(wei[i], wei[j], i, j);
                        pq2.insert(mdts[d]);
                        d++;
                    }
                }
            }

            Cluster b;
            MaxPQ<Cluster> mpq = new MaxPQ<Cluster>(3);
            for (int i = 0; i < id; i++) {
                if (wei[i].valid == 1) {
                    mpq.insert(wei[i]);
                }
            }
            for (int i = 0; i < 3; i++) {

                System.out.println(mpq.max().size+ "" "" + String.format(""%.2f"",mpq.max().x) + "" "" + String.format(""%.2f"",mpq.max().y));
            }
            String y7 = String.format(""%.2f"",pq2.min().getDistance());
            System.out.println(y7);

        }

    }

}
