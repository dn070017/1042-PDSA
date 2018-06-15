 public static int[] ConvexHullVertex(Point2D[] a) {
                    HashMap<Double, Integer> map = new HashMap<Double, Integer>();
                    for (int i = 0; i < a.length; i++) {
                              map.put((a[i].x() * 100 + a[i].y()), i);
                    }
                    MergeX.sort(a, Point2D.Y_ORDER);    //sort
                    Point2D p = new Point2D(a[0].x(), a[0].y());
                    MergeX.sort(a, p.POLAR_ORDER);
                    ArrayList<Integer> storge = new ArrayList<Integer>();
                    storge.add(0);
                    storge.add(1);
                    int count_storge = 0;
                    int count_point = 2;
                    while (count_point < a.length) {
                              if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) != -1) {
                                        storge.add(count_point);
                                        count_storge++;
                                        count_point++;
                              } else if (Point2D.ccw(a[storge.get(count_storge)], a[storge.get(count_storge + 1)], a[count_point]) == -1) {
                                        storge.remove(storge.size() - 1);
                                        count_storge--;
                              }
                    }
                    int[] output = new int[storge.size()];
                    for (int count = 0; count < storge.size(); count++) {
                              output[count] = map.get((a[storge.get(count)].x() * 100) + a[storge.get(count)].y());
                    }
                    return output;
          }
