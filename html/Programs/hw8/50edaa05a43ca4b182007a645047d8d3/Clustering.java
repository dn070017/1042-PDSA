

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author user
 */
public class Clustering {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String counts = br.readLine();
            int N = Integer.parseInt(counts);
            String str = null;
            ArrayList<Point2D> points = new ArrayList<Point2D>();
            MinPQ<Double> pq = new MinPQ<Double>();
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(2 * N);
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split("" "");
                double tempx = Double.parseDouble(temp[0]);
                double tempy = Double.parseDouble(temp[1]);
                Point2D tpoint = new Point2D(tempx, tempy);
                points.add(tpoint);
            }
//            StdDraw.setCanvasSize(800, 800);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenColor(StdDraw.BLUE);
//            StdDraw.setPenRadius(0.5);
//            for (int i = 0; i < N; i++) {
//                points[i].draw();
//            }
            HashMap<Double, String> map = new HashMap<>();
            HashMap<Integer, Integer> weight = new HashMap<>();

            //最一開始的distance tree
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    double d = points.get(i).distanceTo(points.get(j));
                    if (d < 0.3) {
                    pq.insert(d);
                    String temp = String.valueOf(j);
                    temp += ("","");
                    temp += String.valueOf(i);
                    map.put(d, temp);
                    }
                }
            }
            if (N == 2) {
                System.out.printf(""1 "" + String.format(""%.2f"", points.get(0).x()) + "" "" + String.format(""%.2f"", points.get(0).y()) + ""\n"");
                System.out.printf(""1 "" + String.format(""%.2f"", points.get(1).x()) + "" "" + String.format(""%.2f"", points.get(1).y()) + ""\n"");
                System.out.printf(String.format(""%.2f"", points.get(0).distanceTo(points.get(1))) + ""\n"");
            }

            if (N > 3) {
                Stack<Integer> rep = new Stack<>();//points that are already be deleted
                Stack<Integer> rep2 = new Stack<>();
                for (int i = 0; i < N - 3; i++) {
                    int a;
                    int b;
                    //pop the smallest distance and check
                    while (true) {
                        int flag = 1;
                        String temp = map.get(pq.delMin());

                        String[] temps = temp.split("","");
                        a = Integer.parseInt(temps[0]);
                        b = Integer.parseInt(temps[1]);
                        rep2 = (Stack<Integer>) rep.clone();
                        int s = rep2.size();

                        for (int j = 0; j < s; j++) {
                            int x = rep2.pop();
                            if (x == a || x == b) {
                                flag = 0;
                                break;
                            }
                        }
                        if (flag == 1) {
                            break;
                        }
                    }
                    int ar = a;
                    int brr = b;
                    while (ar != uf.find(a)) {
                        ar = uf.find(a);
                    }
                    while (brr != uf.find(b)) {
                        brr = uf.find(b);
                    }
                    int pra = 1;
                    int prb = 1;
//                    for (int j = 0; j < N; j++) {
//                        if (uf.connected(a, j)) {
//                            pra++;
//                        }
//                    }
//                    for (int j = 0; j < N; j++) {
//                        if (uf.connected(b, j)) {
//                            prb++;
//                        }
//                    }
                    if (weight.get(a) != null) {
                        pra = weight.get(a);
                    }
                    if (weight.get(b) != null) {
                        prb = weight.get(b);
                    }
                    uf.union(brr, ar);
//                StdDraw.setPenRadius();
//                StdDraw.setPenColor(StdDraw.RED);
//                points[a].drawTo(points[b]);

                    double tempx = ((points.get(a).x() * pra) + (points.get(b).x() * prb)) / (pra + prb);
                    double tempy = ((points.get(a).y() * pra) + (points.get(b).y() * prb)) / (pra + prb);
                    points.add(new Point2D(tempx, tempy));
                    uf.union(N + i, a);
                    rep.push(a);
                    rep.push(b);
                    if (weight.get(a) != null && weight.get(b) != null) {
                            int ta = weight.get(a);
                            int tb = weight.get(b);
                            weight.put(N + i, ta + tb);
                        } else if (weight.get(a) != null) {
                            int ta = weight.get(a);
                            weight.put(N + i, ta + 1);
                        } else if (weight.get(b) != null) {
                            int tb = weight.get(b);
                            weight.put(N + i, tb + 1);
                        } else {
                            weight.put(N + i, 2);
                        }
                    for (int j = 0; j < N + i; j++) {
                        double d = points.get(N + i).distanceTo(points.get(j));
                        //if (d < 0.3) {
                        pq.insert(d);
                        String temp2 = String.valueOf(j);
                        temp2 += ("","");
                        temp2 += String.valueOf(N + i);
                        map.put(d, temp2);
                        //}
                        

                    }

                }
                //main
                Stack<Integer> a = new Stack<>();
                Stack<Integer> b = new Stack<>();
                Stack<Integer> c = new Stack<>();
                Stack<Integer> d = new Stack<>();
                int countcc = 0;
                for (int i = 0; i < N; i++) {
                    if (i == uf.find(i)) {
                        d.push(i);
                    }
                }
                int x = d.pop();
                int y = d.pop();
                int z = d.pop();
                for (int i = 0; i < N; i++) {
                    if (x == uf.find(i)) {
                        a.push(i);
                    } else if (y == uf.find(i)) {
                        b.push(i);
                    } else {
                        c.push(i);
                    }
                }

                while (a.size() <= b.size() || b.size() <= c.size()) {
                    if (b.size() > a.size()) {
                        Stack<Integer> t = new Stack<>();
                        t = a;
                        a = b;
                        b = t;
                    } else if (c.size() > b.size()) {
                        Stack<Integer> t = new Stack<>();
                        t = b;
                        b = c;
                        c = t;
                    } else if (c.size() > a.size()) {
                        Stack<Integer> t = new Stack<>();
                        t = a;
                        a = c;
                        c = t;
                    }
                }

                int as = a.size();
                int bs = b.size();
                int cs = c.size();

                double ansax = 0;
                double ansay = 0;
                double ansbx = 0;
                double ansby = 0;
                double anscx = 0;
                double anscy = 0;

                int[] aa = new int[as];
                int[] ba = new int[bs];
                int[] ca = new int[cs];
                for (int i = 0; i < as; i++) {
                    aa[i] = a.pop();
                    ansax += points.get(aa[i]).x();
                    ansay += points.get(aa[i]).y();
                }
                for (int i = 0; i < bs; i++) {
                    ba[i] = b.pop();
                    ansbx += points.get(ba[i]).x();
                    ansby += points.get(ba[i]).y();
                }
                for (int i = 0; i < cs; i++) {
                    ca[i] = c.pop();
                    anscx += points.get(ca[i]).x();
                    anscy += points.get(ca[i]).y();
                }
                ansax /= as;
                ansay /= as;
                ansbx /= bs;
                ansby /= bs;
                anscx /= cs;
                anscy /= cs;

                System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
                System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
                System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");

                double ansd = 100;
                for (int i = 0; i < as; i++) {
                    for (int j = 0; j < bs; j++) {
                        if ((points.get(aa[i]).distanceTo(points.get(ba[j]))) < ansd) {
                            ansd = points.get(aa[i]).distanceTo(points.get(ba[j]));
                        }
                    }
                }
                for (int i = 0; i < as; i++) {
                    for (int k = 0; k < cs; k++) {
                        if ((points.get(aa[i]).distanceTo(points.get(ca[k]))) < ansd) {
                            ansd = points.get(aa[i]).distanceTo(points.get(ca[k]));
                        }
                    }
                }
                for (int j = 0; j < bs; j++) {
                    for (int k = 0; k < cs; k++) {
                        if ((points.get(ba[j]).distanceTo(points.get(ca[k]))) < ansd) {
                            ansd = points.get(ba[j]).distanceTo(points.get(ca[k]));
                        }
                    }
                }
                System.out.printf(String.format(""%.2f"", ansd));
            }
        }
    }
}

