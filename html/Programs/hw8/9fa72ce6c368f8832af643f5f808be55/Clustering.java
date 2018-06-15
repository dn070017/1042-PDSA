import java.io.BufferedReader;
import java.io.FileReader;

public class Clustering {

    public static int n, minus,g;
    public static int[] array;
    public static int[] mess;

    public static class Cc implements Comparable<Cc> {

        public int place;

        public Cc(int place) {
            this.place = place;
        }

        Cc() {
.
        }

        // TODO
        @Override
        public int compareTo(Cc that) {
            double a = 1, b = 1;
            int outa=0 ,outb=0;
            for (int i = 0; i < n+minus/2; i++) {for (int k = 0; k < minus; k++) {
                    if (i == array[k]) {i++;k=-1;
                    }
                }
                    if (points[this.place].distanceTo(points[i]) < a && points[this.place].distanceTo(points[i]) != 0) {
                        a = points[this.place].distanceTo(points[i]);
                        outa=i;
                    }
                    if (points[that.place].distanceTo(points[i]) < b && points[that.place].distanceTo(points[i]) != 0) {
                        b = points[that.place].distanceTo(points[i]);
                        outb=i;
                    }
            }        
            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            } else {
                return 0;
            }
        }

        public int getplace() {
            return this.place;
        }

    }

    public static Point2D[] points;
    public static MinPQ<Cc> pq = new MinPQ<>();
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data;
            int count;
            int p = Integer.parseInt(br.readLine());
            n = p;
            int[] delete=new int [2*n];
            int c=0;
            minus = 0;
            Point2D use;
            count = 0;
            points = new Point2D[p * 2 - 2];
            array=new int[2*n];
            mess=new int[2*n-1];
           UF uf = new UF(n*2-3);
            while ((data = br.readLine()) != null) {
                String fund[] = data.split("" "");
                double x = Double.parseDouble(fund[0]);
                double y = Double.parseDouble(fund[1]);
                points[count] = new Point2D(x, y);
                String word = Integer.toString(count);
//                StdDraw.setPenColor(StdDraw.BLUE);
//                points[count].draw();
//                StdDraw.text(x, y - 0.05, word);
                mess[count]=1;
                count++;
            }
            if(n==2){
             String forx = String.format(""%.2f"", points[0].x());
             String fory = String.format(""%.2f"", points[0].y());
            System.out.println(""1 ""+forx+"" ""+fory);
             forx = String.format(""%.2f"", points[1].x());
             fory = String.format(""%.2f"", points[1].y());
            System.out.println(""1 ""+forx+"" ""+fory);
            fory = String.format(""%.2f"", points[1].distanceTo(points[0]));
            System.out.println(fory);
            return;
            }
            int mem1, mem2;
            double inx, iny;
            int b=0;
            int tem =0;
//          初始化pq
            for (int i = 0; i < p - minus; i++) {
                Cc item = new Cc(i);
                pq.insert(item);
            }
            
            
            for(int j=0;j<n-3;j++){
            int lala=0;
            Cc item;
            mem1 = pq.delMin().place;
            array[j*2]=mem1;
            double a=1;
                        //找距離最小
                for (int i = 0; i < n+minus/2; i++) {for (int k = 0; k < minus; k++) {if (i == array[k]) { i++;k = -1;}}if (points[mem1].distanceTo(points[i]) < a && points[mem1].distanceTo(points[i]) != 0) {a = points[mem1].distanceTo(points[i]);b=i;}}
                minus++;
                mem2 = b;
                array[j * 2 + 1] = mem2;
                inx = (points[mem1].x() * mess[mem1] + points[mem2].x() * mess[mem2]) / (mess[mem1] + mess[mem2]);
                iny = (points[mem1].y() * mess[mem1] + points[mem2].y() * mess[mem2]) / (mess[mem1] + mess[mem2]);

//                points[mem1].drawTo(points[mem2]);
                uf.union(mem1,mem2);
                uf.union(mem1,n + minus / 2);
                uf.union(mem2,n + minus / 2);
                use = new Point2D(inx, iny);
                points[n + minus / 2] = new Point2D(inx, iny);
                mess[n + minus / 2] = mess[mem1] + mess[mem2];
                String word = Integer.toString(n + minus / 2);
//                StdDraw.setPenColor(StdDraw.RED);
//                points[n + minus / 2].draw();
//                StdDraw.text(points[n + minus / 2].x(), points[n + minus / 2].y(), word);
//                points[n + minus / 2].draw();
                item = new Cc(n + (minus) / 2);
                minus++;
                array[j * 2 + 1] = mem2;
                //確認是否還沒被使用
                    for (int k = 0; k < minus; k++) {
                    if (pq.min().place == array[k]) {
                        pq.delMin();
                        
                        k = 0;
                    }
                }
                pq.insert(item);
            }
            
            //輸出端
            int out[]=new int[3];
            int change;
            if(pq.size()==3){
            for (int j = 0; j < 3; j++) {
                out[j] = pq.delMin().place;  
            }}else{
                        for (int j = 0; j < 3; j++) {
                            for(int i=2*n-3;i>0;i--){
                            if(pq.min().place==array[i]){
                                pq.delMin();
                            }
                            }
                out[j] = pq.delMin().place;  
            }  
            }
            double outx, outy, outd = 1;
            String lala;
            outx=points[out[1]].x();
            outy=points[out[1]].y();
            String format = String.format(""%.2f"", outx);
            String format1 = String.format(""%.2f"", outy);
            for (int i = 0; i < 3; i++) {
                for (int j = i+1; j < 3; j++) {
                    if (mess[out[i]] < mess[out[j]]) {
                        change = out[i];
                        out[i] = out[j];
                        out[j] = change;
                    }
                }
                outx = points[out[i]].x();
                outy = points[out[i]].y();
                format = String.format(""%.2f"", outx);
                format1 = String.format(""%.2f"", outy);
                outx = Double.parseDouble(format1);
                outy = Double.parseDouble(format);
                lala = Integer.toString(mess[out[i]]);

                System.out.println(lala + "" "" + format + "" "" + format1);
            }
            int cc1=0;
            int cc2=0;
            int cc3=0;
            int[] ss1=new int[mess[out[0]]];
            int[] ss2=new int[mess[out[1]]];
            int[] ss3=new int[mess[out[2]]];
            for(int i=0;i<n;i++){
                if (uf.connected(i, out[0])) {
                    ss1[cc1] = i;
                    cc1++;
                }
                if (uf.connected(i, out[1])) {
                    ss2[cc2] = i;
                    cc2++;
                }
                if (uf.connected(i, out[2])) {
                    ss3[cc3] = i;
                    cc3++;
                }
            }
                    for(int i =0;i<cc1;i++){
                    for(int j =0;j<cc2;j++){
                        if (points[ss1[i]].distanceTo(points[ss2[j]])>outd){}else {
                                outd = points[ss1[i]].distanceTo(points[ss2[j]]);
                    }
                    }
                    }
                    for(int i =0;i<cc2;i++){
                    for(int j =0;j<cc3;j++){
                        if (points[ss2[i]].distanceTo(points[ss3[j]])>outd){}else {
                                outd = points[ss2[i]].distanceTo(points[ss3[j]]);
                    }
                    }
                    }
                    for(int i =0;i<cc1;i++){
                    for(int j =0;j<cc3;j++){
                        if (points[ss1[i]].distanceTo(points[ss3[j]])>outd){}else {
                                outd = points[ss1[i]].distanceTo(points[ss3[j]]);
                    }
                    }
                    }
//                for (int j = 0; j < 2 * n - 3; j++) {
//                    if (uf.connected(j, out[0])) {j++;} else {
//                        for (int i = 0; i < 2 * n - 3; i++) {
//                            if (uf.connected(i, j)||points[i].distanceTo(points[j])>outd){i++;}else {
//                                outd = points[i].distanceTo(points[j]);
//                            }
//                        }
//                    }
//                    if (uf.connected(j, out[1])){} else {
//                        for (int i = 0; i < 2 * n - 3; i++) {
//                            if (uf.connected(i, j)||points[i].distanceTo(points[j])>outd){i++;}else {
//                                outd = points[i].distanceTo(points[j]);
//                            }
//                        }
//                    }
//                    if (uf.connected(j, out[2])){} else {
//                        for (int i = 0; i < 2 * n - 3; i++) {
//                            if (uf.connected(i, j)||points[i].distanceTo(points[j])>outd){i++;}else {
//                                outd = points[i].distanceTo(points[j]);
//                            }
//                        }
//                    }
//                    
//                }
                format1 = String.format(""%.2f"", outd);
            System.out.println(format1);
        }
    }

}

