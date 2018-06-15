
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
            if(this.valid > that.valid){
                return 1;
            }
            if(this.valid < that.valid){
                return -1;
            }
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

        public Pair(Cluster a, Cluster b) {
            this.a = a;
            this.b = b;
        }

        public double getDistance() {
            double dx = this.a.centriodx() - this.b.centriodx();
            double dy = this.a.centriody() - this.b.centriody();
            return Math.sqrt(dx * dx + dy * dy);
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
            Cluster[] wei = new Cluster[number * 2-3];
            WeightedQuickUnionUF yuru = new WeightedQuickUnionUF(number);
            String stillhave;
            String[] temp = new String[2];
            int id = 0;
            MinPQ<Pair> pq = new MinPQ<Pair>(number);
            MinPQ<Pair> pq2 = new MinPQ<Pair>(number);
            while ((stillhave = br.readLine()) != null) {
                temp = stillhave.split("" "");
                wei[id] = new Cluster(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), 1, id, id);
                id++;
            }
            Pair a;
            for (int i = 0; i < number; i++) {
                for (int j = i + 1; j < number; j++) {
                    a = new Pair(wei[i], wei[j]);
                    pq.insert(a);
                    pq2.insert(a);
                }
            }
            while (id < number*2-3) {
                a = pq.delMin();
                if (a.a.valid == 1  && a.b.valid == 1) {
                    a.a.valid = 0;
                    a.b.valid = 0;
                    yuru.union(a.a.getHead(), a.b.getHead());
                    wei[id] = new Cluster(a.a.centriodx(), a.a.centriody(), a.a.getSize(), id, a.a.getHead());
                    wei[id].merge(a.b);
                    for (int i = 0; i < id; i++) {
                        if (wei[i].valid == 1)  pq.insert(new Pair(wei[i], wei[id]));
                    }
                    id++;
                }
            }
            Pair close=null;
            int flag = 0;
            while(flag == 0){
                close = pq2.delMin();
                if(!yuru.connected(close.a.getIndex(), close.b.getIndex()))  flag=1;
            }
            Merge.sort(wei);
            System.out.println(wei[number*2-4].size + "" "" + String.format(""%.2f"", wei[number*2-4].centriodx()) + "" "" + String.format(""%.2f"", wei[number*2-4].centriody()));
            System.out.println(wei[number*2-5].size + "" "" + String.format(""%.2f"", wei[number*2-5].centriodx()) + "" "" + String.format(""%.2f"", wei[number*2-5].centriody()));
            System.out.println(wei[number*2-6].size + "" "" + String.format(""%.2f"", wei[number*2-6].centriodx()) + "" "" + String.format(""%.2f"", wei[number*2-6].centriody()));
            System.out.println(String.format(""%.2f"",close.getDistance()) );
        }
    }
}
