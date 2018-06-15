import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.*;

/**
.
 */
public class MyConvexHull {
    public static int[] ConvexHullVertex(Point2D[] a) {

        int N = a.length;
        int yMin = 0;
        Point2D[] a2 = new Point2D[N];

        for (int i = 0; i < N; i++)
            a2[i] = a[i];

        for (int i = 0; i < N; i++) {
            if (i > 0) {
                int yOrder = Point2D.Y_ORDER.compare(a[yMin], a[i]);
                if (yOrder == 1) yMin = i;
            }
        }
        Point2D p = new Point2D(a[yMin].x(), a[yMin].y());
        Arrays.sort(a2, p.ATAN2_ORDER);

        int[] sortIndex = new int[N];
        int[] isConvex = new int[N-1];
        for (int i = 1; i < N; i++)
            isConvex[i - 1] = i;

        for (int i = 0; i < N; i++) {
            boolean e = false;
            int j = 0;
            while (!e) {
                e = a2[i].equals(a[j]);
                if (e)
                    sortIndex[i] = j;
                else
                    j++;
            }
        }

        int index = 0;
        int[] ccwArray = new int[3];
        int ansCount = N;

        while (index < isConvex.length - 1) {
            if (ccwArray[2] == N-1)
                break;
            int ccwCount = 0;
            int j = index;
            while (ccwCount < 3) {
                if (isConvex[j] != -1) {
                    ccwArray[ccwCount] = isConvex[j];
                    ccwCount++;
                }
                j++;
            }

            int temp = Point2D.ccw(a2[ccwArray[0]],a2[ccwArray[1]],a2[ccwArray[2]]);
            if (temp == 1) {
                index++;
            } else {
                isConvex[ccwArray[1] - 1] = -1;
                ansCount--;
                if (index != 0) {
                    index--;
                }

            }
        }

        int[] ans = new int[ansCount];
        int[] ansNew = new int[ansCount];
        ansNew[0] = sortIndex[0];
        ans[0] = sortIndex[0];
        for (int i = 0; i < N-1; i++) {
            if (isConvex[i] != -1) {
                ans[ansCount - 1] = sortIndex[isConvex[i]];
                ansCount--;
            }
        }
        for (int i = 0; i < ans.length; i++) {
            int tempMin = N;
            if (i > 0) {
                for (int j = 0; j < ans.length; j++) {
                    if ((ans[j] < tempMin) && (ans[j] > ansNew[i - 1])) {
                        tempMin = ans[j];
                    }
                }
            } else {
                for (int j = 0; j < ans.length; j++) {
                    if (ans[j] < tempMin) {
                        tempMin = ans[j];
                    }
                }
            }
            ansNew[i] = tempMin;
        }
        return ansNew;
    }
    public static void main(String[] args) throws Exception {
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                double distance = Double.parseDouble(br.readLine());
                int num = Integer.parseInt(br.readLine());

//                distance = 0.3;
//                num = 12;
                Point2D[] points = new Point2D[num];

                for (int i = 0; i < num; i++) {
                String[] coordinate = br.readLine().split(""\\s"");
                double x = Double.parseDouble(coordinate[0]);
                double y = Double.parseDouble(coordinate[1]);
                points[i] = new Point2D(x,y);
//                    points[i] = new Point2D(Math.random(), Math.random());
//                    System.out.println(String.format(""%d:(%.3f,%.3f)"", i, points[i].x(), points[i].y()));
                }


                int[] parents = new int[num];
                for (int i = 0; i < parents.length; i++)
                    parents[i] = i;
//            printArray(parents);

                for (int i = 0; i < num; i++) {
                    Point2D p1 = points[i];
                    for (int j = 0; j < num; j++) {
                        Point2D p2 = points[j];
                        if (p1.distanceTo(p2) < distance) {
                            int root1 = getRoot(parents, i);
                            int root2 = getRoot(parents, j);
                            if (root1 <= root2)
                                parents[root2] = root1;
                            else
                                parents[root1] = root2;
                        }
                    }
                }

                for (int i = 0; i < parents.length; i++) {
                    parents[i] = getRoot(parents, i);
                }

                printArray(parents);

                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int i : parents) {
                    if (map.containsKey(i)) {
                        map.put(i, map.get(i) + 1);
                    } else {
                        map.put(i, 1);
                    }
                }

//                StdDraw.setCanvasSize(800, 800);
//                StdDraw.setXscale(-0.1, 1.1);
//                StdDraw.setYscale(-0.1, 1.1);
//                StdDraw.setPenRadius(0.02);
//                for (int i = 0; i < num; i++) {
//                    Point2D p1 = points[i];
//                    p1.draw();
//                    StdDraw.setPenRadius(0.002);
//                    StdDraw.circle(p1.x(), p1.y(), distance / 2.0);
//                    StdDraw.setPenRadius(0.02);
//                    StdDraw.text(p1.x() + 0.015, p1.y() + 0.015, """" + parents[i] + """");
//                }
//                StdDraw.setPenColor(StdDraw.MAGENTA);

                int totalConvexHull = 0;
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//                    System.out.println(""label="" + entry.getKey() + "" : "" + entry.getValue());

                    Point2D[] pointGroup = new Point2D[entry.getValue()];
                    int count = 0;
                    for (int i = 0; i < parents.length; i++) {
                        if (parents[i] == entry.getKey())
                            pointGroup[count++] = points[i];
                    }
                    if (pointGroup.length > 2) {
                        Point2D[] copyArray = Arrays.copyOf(pointGroup, pointGroup.length);
                        int[] convex = ConvexHullVertex(pointGroup);
                        totalConvexHull += convex.length;
//                        for (int i : convex) {
//                            copyArray[i].draw();
//                        }
                    }
                }

            System.out.println(totalConvexHull);
            }
    }



    public static int getRoot(int[] parents , int index){
        while(parents[index]!=index)
            index = parents[index];
        return index;
    }

    public static void printArray(int[] array){
        String sp = """";
        System.out.print(""["");
        for(int i:array){
            System.out.print(sp + i);
            sp = "","";
        }
        System.out.println(""]"");
    }
//        double rand1,rand2;
//        Point2D[] a2 = new Point2D[10];
//        for (int i = 0; i < a2.length; i++) {
//            rand1 = StdRandom.uniform();
//            rand2 = StdRandom.uniform();
//            a2[i] = new Point2D(rand1,rand2);
//        }
//        int[] ans =  ConvexHullVertex(a2);
//        for (int i = 0; i < ans.length; i++) {
//            System.out.println(ans[i]);
//        }
    }


