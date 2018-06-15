
import java.io.BufferedReader;
import java.io.FileReader;
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
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String counts = br.readLine();
            int N = Integer.parseInt(counts);
            String str = null;
            Point2D[] points = new Point2D[2 * N];
            MinPQ<Double> pq = new MinPQ<Double>();
            QuickFindUF uf = new QuickFindUF(2 * N);
            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split("" "");
                double tempx = Double.parseDouble(temp[0]);
                double tempy = Double.parseDouble(temp[1]);
                points[i] = new Point2D(tempx, tempy);
            }
//            StdDraw.setCanvasSize(800, 800);
//            StdDraw.setXscale(0, 1);
//            StdDraw.setYscale(0, 1);
//            StdDraw.setPenColor(StdDraw.BLUE);
//            StdDraw.setPenRadius(0.5);
//            for (int i = 0; i < N; i++) {
//                points[i].draw();
//            }
            HashMap<Double, String> map = new HashMap<Double, String>();

            //最一開始的distance tree
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    double d = points[i].distanceTo(points[j]);
                    pq.insert(d);
                    String temp = """";
                    temp = String.valueOf(j);
                    temp += ("","");
                    temp += String.valueOf(i);
                    map.put(d, temp);
                }
            }
            if (N == 2) {
                System.out.printf(""1 "" + String.format(""%.2f"", points[0].x()) + "" "" + String.format(""%.2f"", points[0].y()) + ""\n"");
                System.out.printf(""1 "" + String.format(""%.2f"", points[1].x()) + "" "" + String.format(""%.2f"", points[1].y()) + ""\n"");
                System.out.printf(String.format(""%.2f"", points[0].distanceTo(points[1]))+ ""\n"");
            }

            if (N > 3) {
                Stack<Integer> rep = new Stack<Integer>();//points that are already be deleted
                Stack<Integer> rep2 = new Stack<Integer>();
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
                    int pra = 0;
                    int prb = 0;
                    for (int j = 0; j < N; j++) {
                        if (uf.connected(a, j)) {
                            pra++;
                        }
                    }
                    for (int j = 0; j < N; j++) {
                        if (uf.connected(b, j)) {
                            prb++;
                        }
                    }
                    uf.union(brr, ar);
//                StdDraw.setPenRadius();
//                StdDraw.setPenColor(StdDraw.RED);
//                points[a].drawTo(points[b]);

                    double tempx = ((points[a].x() * pra) + (points[b].x() * prb)) / (pra + prb);
                    double tempy = ((points[a].y() * pra) + (points[b].y() * prb)) / (pra + prb);
                    points[N + i] = new Point2D(tempx, tempy);
                    uf.union(N + i, a);
                    rep.push(a);
                    rep.push(b);
                    for (int j = 0; j < N + i; j++) {
                        double d = points[N + i].distanceTo(points[j]);
                        pq.insert(d);
                        String temp2 = """";
                        temp2 = String.valueOf(j);
                        temp2 += ("","");
                        temp2 += String.valueOf(N + i);
                        map.put(d, temp2);
                    }

                }
                //main
                Stack<Integer> a = new Stack<Integer>();
                Stack<Integer> b = new Stack<Integer>();
                Stack<Integer> c = new Stack<Integer>();
                int countcc = 0;
                for (int i = 0; i < N; i++) {
                    int f = 0;
                    for (int j = 0; j < N; j++) {
                        if (uf.find(j) == i) {
                            f = 1;
                            if (countcc == 0) {
                                a.push(j);
                            } else if (countcc == 1) {
                                b.push(j);
                            } else {
                                c.push(j);
                            }
                        }
                        if (j == N - 1 && f == 1) {
                            countcc++;
                        }
                    }
                }

                while (a.size() <= b.size() || b.size() <= c.size()) {
                    if (b.size() > a.size()) {
                        Stack<Integer> t = new Stack<Integer>();
                        t = a;
                        a = b;
                        b = t;
                    } else if (c.size() > b.size()) {
                        Stack<Integer> t = new Stack<Integer>();
                        t = b;
                        b = c;
                        c = t;
                    } else if (c.size() > a.size()) {
                        Stack<Integer> t = new Stack<Integer>();
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
                    ansax += points[aa[i]].x();
                    ansay += points[aa[i]].y();
                }
                for (int i = 0; i < bs; i++) {
                    ba[i] = b.pop();
                    ansbx += points[ba[i]].x();
                    ansby += points[ba[i]].y();
                }
                for (int i = 0; i < cs; i++) {
                    ca[i] = c.pop();
                    anscx += points[ca[i]].x();
                    anscy += points[ca[i]].y();
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
//            if (as > bs && as > cs) {
//                System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                if (bs > cs) {
//                    System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                    System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                } else {
//                    System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                    System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                }
//            }
//            if (bs > as && bs > cs) {
//                System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                if (as > cs) {
//                    System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                } else {
//                    System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                    System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                }
//            }
//            if (cs > as && cs > bs) {
//                System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                if (as > bs) {
//                    System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                } else {
//                    System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                    System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                }
//            }
//            if (as == bs) {
//                if (as > cs) {
//                    if (ansax > ansbx) {
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                    } else {
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    }
//                    System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                } else {
//                    System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                    if (ansax > ansbx) {
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                    } else {
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    }
//                }
//            } else if (bs == cs) {
//                if (bs > as) {
//                    if (ansbx > anscx) {
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                        System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                    } else {
//                        System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                    }
//                    System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                } else {
//                    System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    if (ansax > ansbx) {
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                    } else {
//                        System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    }
//                }
//
//            } else if (as == cs) {
//                if (as > bs) {
//                    if (ansax > anscx) {
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                        System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                    } else {
//                        System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    }
//                    System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                } else {
//                    System.out.printf(bs + "" "" + String.format(""%.2f"", ansbx) + "" "" + String.format(""%.2f"", ansby) + ""\n"");
//                    if (ansax > anscx) {
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                        System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                    } else {
//                        System.out.printf(cs + "" "" + String.format(""%.2f"", anscx) + "" "" + String.format(""%.2f"", anscy) + ""\n"");
//                        System.out.printf(as + "" "" + String.format(""%.2f"", ansax) + "" "" + String.format(""%.2f"", ansay) + ""\n"");
//                    }
//                }
//            }
                double ansd = 100;
                for (int i = 0; i < as; i++) {
                    for (int j = 0; j < bs; j++) {
                        if ((points[aa[i]].distanceTo(points[ba[j]])) < ansd) {
                            ansd = points[aa[i]].distanceTo(points[ba[j]]);
                        }
                        for (int k = 0; k < cs; k++) {
                            if ((points[aa[i]].distanceTo(points[ca[k]])) < ansd) {
                                ansd = points[aa[i]].distanceTo(points[ba[k]]);
                            }
                            if ((points[ba[j]].distanceTo(points[ca[k]])) < ansd) {
                                ansd = points[ba[j]].distanceTo(points[ca[k]]);
                            }
                        }
                    }
                }
                System.out.printf(String.format(""%.2f"", ansd));
            }
        }
    }
}

