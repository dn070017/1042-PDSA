
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
                return 1;
            }
            if (this.centriodx() > that.centriodx()) {
                return -1;
            }
            if (this.centriody() < that.centriody()) {
                return 1;
            }
            if (this.centriody() > that.centriody()) {
                return -1;
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
            MinPQ<Pair> pq = new MinPQ<Pair>(number*number*2);

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
            while (clusternum > 3) {
                if(pq.min().a.valid == 1){
                if (pq.min().b.valid == 1) {
                    pq.min().a.valid = 0;
                    pq.min().b.valid = 0;
                    yuru.union(pq.min().a.getHead(), pq.min().b.getHead());
                    wei[id] = new Cluster(pq.min().a.centriodx(), pq.min().a.centriody(), pq.min().a.getSize(), id, pq.min().a.getHead());
                    wei[id].merge(pq.delMin().b);
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
            }
            Pair ans=null;
            int d = 0;
            for (int i = 0; i < number; i++) {
                for (int j = i + 1; j < number; j++) {
                    if (!yuru.connected(i, j)) {
                        if(d==0)  ans = new Pair(wei[i], wei[j], i, j);
                        else{
                            Pair chi = new Pair(wei[i], wei[j], i, j);
                            if(ans.getDistance()>chi.getDistance())
                                ans = chi;
                        }
                        
                        d++;
                    }
                }
            }

           
            MaxPQ<Cluster> mpq = new MaxPQ<Cluster>(3);
            for (int i = 0; i < id; i++) {
                if (wei[i].valid == 1) {
                    mpq.insert(wei[i]);
                }
            }
            for (int i = 0; i < 3; i++)           
                System.out.println(mpq.max().size + "" "" + String.format(""%.2f"", mpq.max().centriodx()) + "" "" + String.format(""%.2f"", mpq.delMax().centriody()));
            
            System.out.println(String.format(""%.2f"", ans.getDistance()));

        }

    }

}
