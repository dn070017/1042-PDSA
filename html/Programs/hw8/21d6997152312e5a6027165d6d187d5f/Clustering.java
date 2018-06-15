
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

public class Clustering {

    private static class Cluster implements Comparable<Cluster> {

        private int size;
        private double x, y;
        private int index;
        private int head;
        public int valid = 1;

        public Cluster(double x, double y, int size,double x2, double y2, int size2, int index, int head) {
            this.x = (x*size+x2*size2)/(size+size2);
            this.y = (y*size+y2*size2)/(size+size2);
            this.size = size+size2;
            this.index = index;
            this.head = head;
        }

        public double centriodx() {
            return this.x;
        }

        public double centriody() {
            return this.y;
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


        public int compareTo(Cluster that) {
            if (this.size < that.size) {
                return -1;
            }
            if (this.size > that.size) {
                return 1;
            }
            if (this.centriodx() > that.centriodx()) {
                return -1;
            }
            if (this.centriodx() < that.centriodx()) {
                return 1;
            }
            if (this.centriody() > that.centriody()) {
                return -1;
            }
            if (this.centriody() < that.centriody()) {
                return 1;
            }
            return 0;
        }

    }

    private static class Pair implements Comparable<Pair> {

        private Cluster a, b;
        private int[] index = new int[2];

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
            int clusternum = number;

            Cluster[] wei = new Cluster[number * 2];
            WeightedQuickUnionUF yuru = new WeightedQuickUnionUF(number);
            String stillhave;
            String[] temp = new String[2];
            int id = 0;
            MinPQ<Pair> pq = new MinPQ<Pair>(number);

            while ((stillhave = br.readLine()) != null) {
                temp = stillhave.split("" "");
                wei[id] = new Cluster(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), 1,0,0,0, id, id);
                id++;
            }
            Pair[] apple = new Pair[number * number / 2];
            int c = 0;
            for (int i = 0; i < number; i++) {
                for (int j = i + 1; j < number; j++) {
                    apple[c] = new Pair(wei[i], wei[j]);
                    pq.insert(apple[c]);
                    c = c + 1;
                }
            }

            Pair a;
            while (clusternum > 3) {
                a = pq.delMin();
                if (a.a.valid ==1 && a.b.valid == 1) {
                    a.a.valid = 0;
                    a.b.valid = 0;
                    yuru.union(a.a.getHead(), a.b.getHead());
                    wei[id] = new Cluster(a.a.centriodx(), a.a.centriody(), a.a.getSize(),a.b.centriodx(),a.b.centriody(),a.b.getSize(),id, a.a.getHead());
                    for (int i = 0; i < id; i++) {
                        if (wei[i].valid == 1) {
                            Pair test = new Pair(wei[i], wei[id]);
                            pq.insert(test);
                        }
                    }
                    id++;
                    clusternum--;

                }
            }
            MinPQ<Pair> pq2 = new MinPQ<Pair>(number);
            Pair[] ts = new Pair[number * number / 2];
            int d = 0;
            for (int i = 0; i < number; i++) {
                for (int j = i + 1; j < number; j++) {
                    if (!yuru.connected(i, j)) {
                        ts[d] = new Pair(wei[i], wei[j]);
                        pq2.insert(ts[d]);
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
            Iterator<Cluster> wei001 = mpq.iterator();
            Cluster temp2;
           while(wei001.hasNext()){
               temp2 = wei001.next();
                System.out.println(temp2.size+"" ""+ String.format(""%.2f"",temp2.x  )+"" ""+ String.format(""%.2f"", temp2.y ));
            }
            String y7 = String.format(""%.2f"",pq2.min().getDistance());
            System.out.println(y7);

        }

    }

}
