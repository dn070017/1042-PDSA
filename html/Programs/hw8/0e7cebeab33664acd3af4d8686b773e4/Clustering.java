
import java.io.BufferedReader;
import java.io.FileReader;

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
        public boolean isValid(){
            return valid==1;
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
            if (this.x < that.x) {
                return -1;
            }
            if (this.x > that.x) {
                return 1;
            }
            if (this.y < that.y) {
                return -1;
            }
            if (this.y > that.y) {
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
            double dx = this.a.x - this.b.x;
            double dy = this.a.y - this.b.y;
            return Math.sqrt(dx * dx + dy * dy);
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
            long time1, time2;
            time1 = System.currentTimeMillis();
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
                wei[id] = new Cluster(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), 1,0,0,0, id, id);
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
                if (a.a.valid+a.b.valid==2) {
                    a.a.valid = 0;
                    a.b.valid = 0;
                    yuru.union(a.a.head, a.b.head);
                    wei[id] = new Cluster(a.a.x, a.a.y, a.a.size,a.b.x  , a.b.y , a.b.size ,id, a.a.head);
                    for (int i = id/2; i < id; i++) {
                        if (wei[i].valid==1)  pq.insert(new Pair(wei[i], wei[id]));
                    }
                    id++;
                }
            }
            Pair close=null;
            int flag = 0;
            while(flag == 0){
                close = pq2.delMin();
                if(!yuru.connected(close.a.index, close.b.index))  flag=1;
            }
            Quick.sort(wei);
            System.out.println(wei[number*2-4].size + "" "" + String.format(""%.2f"", wei[number*2-4].x) + "" "" + String.format(""%.2f"", wei[number*2-4].y));
            System.out.println(wei[number*2-5].size + "" "" + String.format(""%.2f"", wei[number*2-5].x) + "" "" + String.format(""%.2f"", wei[number*2-5].y));
            System.out.println(wei[number*2-6].size + "" "" + String.format(""%.2f"", wei[number*2-6].x) + "" "" + String.format(""%.2f"", wei[number*2-6].y));
            System.out.println(String.format(""%.2f"",close.getDistance()) );
            time2 = System.currentTimeMillis();
            System.out.println(""doSomething()花了："" + (time2-time1) + ""毫秒"");
        }

    }

}
