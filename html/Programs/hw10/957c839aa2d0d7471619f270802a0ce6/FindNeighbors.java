import java.util.ArrayList;
import java.util.Comparator;

public class FindNeighbors {
    private static class Node {
        private Point2D point;
        private int layer;
        private Node left_node;
        private Node right_node;
        private Node(Point2D point, int layer) {
            this.point = point;
            this.layer = layer;
        }
        private int comp_x(Point2D that) {
            if (this.point.x() < that.x()) {
                return -1;
            }
            if (this.point.x() > that.x()) {
                return +1;
            }
            return 0;
        }

        private int comp_y(Point2D that) {
            if (this.point.y() < that.y()) {
                return -1;
            }
            if (this.point.y() > that.y()) {
                return +1;
            }
            return 0;
        }
    }
    
    private static class BSTree {
        private Node root;
        boolean expend_leaf = true;
        ArrayList<Node> tree_all;
        ArrayList<Node> tree_sub;

        public void add(Point2D point) {
            root = add(root, point);
        }
        private Node add(Node x, Point2D point) {
            if (x == null) {
                if (expend_leaf) {
                    return new Node(point, 1);
                } else {
                    return new Node(point, 0);
                }
            }
            int shed = 0;
            if ((x.layer % 2) == 1) {
                shed = x.comp_x(point);
                expend_leaf = false;
            } else {
                shed = x.comp_y(point);
                expend_leaf = true;
            }

            if (shed > 0) {
                x.left_node = add(x.left_node, point);
            } else if (shed < 0) {
                x.right_node = add(x.right_node, point);
            } else if (shed == 0) {
                x.point = point;
            }
            return x;
        }

        private ArrayList<Node> return_node() {
            return tree_all;
        }

        private ArrayList<Node> find(Node x) {
            tree_sub = null;
            tree_sub = new ArrayList<Node>();
            tree_sub.add(x);
            find_res(x);
            return tree_sub;
        }

        private void find_res(Node x) {
            if (x.left_node != null) {
                tree_sub.add(x.left_node);
                find_res(x.left_node);
            }
            if (x.right_node != null) {
                tree_sub.add(x.right_node);
                find_res(x.right_node);
            }
        }

        private void search(Node x) {
            tree_all = null;
            tree_all = new ArrayList<Node>();
            Node a = search_res(x, root);
        }

        private Node search_res(Node x, Node r) {
            if (r != null) {
                int comp = 0;
                if ((r.layer % 2) == 1) {
                    comp = r.comp_x(x.point);
                } else {
                    comp = r.comp_y(x.point);
                }
                if (comp > 0) {
                    tree_all.add(r);
                    r.left_node = search_res(x, r.left_node);
                } else if (comp < 0) {
                    tree_all.add(r);
                    r.right_node = search_res(x, r.right_node);
                } else if (comp == 0) {
                    tree_all.add(r);
                    exist = true;
                    return r;
                }
            }
            return r;
        }
    }

    private static BSTree KDtree = new BSTree();
    private static boolean exist;

    private static MaxPQ<distance> calc_dist(MaxPQ<distance> dist_res, ArrayList<Node> node_res, Node r, Node p, int k) {
        double dif, c;
        c = p.point.distanceTo(r.point);
        if (dist_res.size() < k || c < dist_res.max().distance) {
            dist_res.insert(new distance(r, c));
            if (dist_res.size() > k) {
                dist_res.delMax();
            }
        }
        if (r.layer != 0) {
            dif = p.point.x() - r.point.x();
        } else {
            dif = p.point.y() - r.point.y();
        }
        if (dist_res.max().distance > Math.abs(dif) || dist_res.size() < k) {
            if (dif < 0 && r.right_node != null) {
                calc_dist(dist_res, node_res, r.right_node, p, k);
            }
            if (dif > 0 && r.left_node != null) {
                calc_dist(dist_res, node_res, r.left_node, p, k);
            }

        }
        if (!node_res.contains(r)) {
            if (dif < 0 && r.left_node != null) {
                calc_dist(dist_res, node_res, r.left_node, p, k);
            }
            if (dif > 0 && r.right_node != null) {
                calc_dist(dist_res, node_res, r.right_node, p, k);
            }
        }
        return dist_res;
    }

    private static class distance implements Comparable<distance> {

        private Node node;
        private double distance;

        private distance(Node node, double d) {
            this.node = node;
            this.distance = d;
        }

        public int compareTo(distance that) {
            if (this.distance < that.distance) {
                return -1;
            }
            if (this.distance > that.distance) {
                return +1;
            }
            return 0;
        }
    }

    public static void init(Point2D[] points) {
        int i;
        KDtree = new BSTree();
        for (i = 0; i < points.length; i++) {
            KDtree.add(points[i]);
        }
    }
    
    public static Point2D[] query(Point2D point, int k) {
        Point2D[] result = new Point2D[k];
        MaxPQ<distance> dist_res = new MaxPQ<distance>(k);
        Node p = new Node(point, 2);
        distance d, min;
        exist = false;
        KDtree.search(p);
        ArrayList<Node> node_res = KDtree.return_node();
        ArrayList<Node> node_res_sub;
        int i, j = 0, l, N = node_res.size();
        double dif, c;
        if (node_res.get(0).left_node == null && node_res.get(0).right_node == null) {
            result[0] = node_res.get(0).point;
        } else {
            if (k == 1 && exist) {
                result[0] = p.point;
            } else {
                if (exist) {
                    dist_res.insert(new distance(p, 0));
                    j++;
                    boolean exist_left = (node_res.get(N - 1).left_node != null);
                    boolean exist_right = (node_res.get(N - 1).right_node != null);
                    if (!exist_left && !exist_right) {
                        l = N - 2;
                        min = new distance(node_res.get(l), p.point.distanceTo(node_res.get(l).point));
                        dist_res.insert(min);
                        j++;
                        if (j > k) {
                            dist_res.delMax();
                            j--;
                        }
                        if (node_res.get(l).layer != 0) {
                            dif = p.point.x() - node_res.get(l).point.x();
                        } else {
                            dif = p.point.y() - node_res.get(l).point.y();
                        }
                        if (min.distance > Math.abs(dif)) {
                            if (dif < 0 && node_res.get(l).right_node != null) {
                                node_res_sub = KDtree.find(node_res.get(l).right_node);
                            } else if (dif > 0 && node_res.get(l).left_node != null) {
                                node_res_sub = KDtree.find(node_res.get(l).left_node);
                            } else {
                                node_res_sub = new ArrayList<Node>();
                            }
                            while (!node_res_sub.isEmpty()) {
                                c = p.point.distanceTo(node_res_sub.get(0).point);
                                if (c < dist_res.max().distance || j < k) {
                                    d = new distance(node_res_sub.get(0), c);
                                    dist_res.insert(d);
                                    if (min.distance > d.distance) {
                                        min = d;
                                    }
                                    j++;
                                    if (j > k) {
                                        dist_res.delMax();
                                        j--;
                                    }
                                }
                                node_res_sub.remove(0);
                            }
                        }
                        for (i = l - 1; i >= 0; i--) {
                            if (node_res.get(i).layer != 0) {
                                c = p.point.x() - node_res.get(i).point.x();
                            } else {
                                c = p.point.y() - node_res.get(i).point.y();
                            }
                            if (Math.abs(c) < dist_res.max().distance || j < k) {
                                d = new distance(node_res.get(i), p.point.distanceTo(node_res.get(i).point));
                                if (min.distance > d.distance) {
                                    min = d;
                                }
                                dist_res.insert(d);
                                j++;
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                                if (node_res.get(i).layer != 0) {
                                    dif = p.point.x() - node_res.get(i).point.x();
                                } else {
                                    dif = p.point.y() - node_res.get(i).point.y();
                                }
                                if (dist_res.max().distance > Math.abs(dif)) {
                                    if (dif < 0 && node_res.get(i).right_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).right_node);
                                    } else if (dif > 0 && node_res.get(i).left_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).left_node);
                                    } else if (dif == 0) {
                                        node_res_sub = KDtree.find(node_res.get(i));
                                        node_res_sub.remove(0);
                                    } else {
                                        node_res_sub = new ArrayList<Node>();
                                    }
                                    while (!node_res_sub.isEmpty()) {
                                        c = p.point.distanceTo(node_res_sub.get(0).point);
                                        if (c < dist_res.max().distance || j < k) {
                                            d = new distance(node_res_sub.get(0), c);
                                            dist_res.insert(d);
                                            if (min.distance > d.distance) {
                                                min = d;
                                            }
                                            j++;
                                            if (j > k) {
                                                dist_res.delMax();
                                                j--;
                                            }
                                        }
                                        node_res_sub.remove(0);
                                    }
                                }
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                            }
                        }
                    } else if (exist_left && !exist_right) {
                        node_res_sub = KDtree.find(node_res.get(N - 1).left_node);
                        min = new distance(node_res_sub.get(0), p.point.distanceTo(node_res_sub.get(0).point));
                        dist_res.insert(min);
                        j++;
                        if (j > k) {
                            dist_res.delMax();
                            j--;
                        }
                        node_res_sub.remove(0);
                        while (!node_res_sub.isEmpty()) {
                            c = p.point.distanceTo(node_res_sub.get(0).point);
                            if (c < dist_res.max().distance || j < k) {
                                d = new distance(node_res_sub.get(0), c);
                                dist_res.insert(d);
                                if (min.distance > d.distance) {
                                    min = d;
                                }
                                j++;
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                            }
                            node_res_sub.remove(0);
                        }
                        l = N - 1;
                        for (i = l - 1; i >= 0; i--) {
                            if (node_res.get(i).layer != 0) {
                                c = p.point.x() - node_res.get(i).point.x();
                            } else {
                                c = p.point.y() - node_res.get(i).point.y();
                            }
                            if (Math.abs(c) < dist_res.max().distance || j < k) {
                                d = new distance(node_res.get(i), p.point.distanceTo(node_res.get(i).point));
                                if (min.distance > d.distance) {
                                    min = d;
                                }
                                dist_res.insert(d);
                                j++;
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                                if (node_res.get(i).layer != 0) {
                                    dif = p.point.x() - node_res.get(i).point.x();
                                } else {
                                    dif = p.point.y() - node_res.get(i).point.y();
                                }
                                if (dist_res.max().distance > Math.abs(dif)) {
                                    if (dif < 0 && node_res.get(i).right_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).right_node);
                                    } else if (dif > 0 && node_res.get(i).left_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).left_node);
                                    } else if (dif == 0) {
                                        node_res_sub = KDtree.find(node_res.get(i));
                                        node_res_sub.remove(0);
                                    } else {
                                        node_res_sub = new ArrayList<Node>();
                                    }
                                    while (!node_res_sub.isEmpty()) {
                                        c = p.point.distanceTo(node_res_sub.get(0).point);
                                        if (c < dist_res.max().distance || j < k) {
                                            d = new distance(node_res_sub.get(0), c);
                                            dist_res.insert(d);
                                            if (min.distance > d.distance) {
                                                min = d;
                                            }
                                            j++;
                                            if (j > k) {
                                                dist_res.delMax();
                                                j--;
                                            }
                                        }
                                        node_res_sub.remove(0);
                                    }
                                }
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                            }
                        }

                    } else if (!exist_left && exist_right) {
                        node_res_sub = KDtree.find(node_res.get(N - 1).right_node);
                        min = new distance(node_res_sub.get(0), p.point.distanceTo(node_res_sub.get(0).point));
                        dist_res.insert(min);
                        j++;
                        if (j > k) {
                            dist_res.delMax();
                            j--;
                        }
                        node_res_sub.remove(0);
                        while (!node_res_sub.isEmpty()) {
                            c = p.point.distanceTo(node_res_sub.get(0).point);
                            if (c < dist_res.max().distance || j < k) {
                                d = new distance(node_res_sub.get(0), c);
                                dist_res.insert(d);
                                if (min.distance > d.distance) {
                                    min = d;
                                }
                                j++;
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                            }
                            node_res_sub.remove(0);
                        }
                        l = N - 1;
                        for (i = l - 1; i >= 0; i--) {
                            if (node_res.get(i).layer != 0) {
                                c = p.point.x() - node_res.get(i).point.x();
                            } else {
                                c = p.point.y() - node_res.get(i).point.y();
                            }
                            if (Math.abs(c) < dist_res.max().distance || j < k) {
                                d = new distance(node_res.get(i), p.point.distanceTo(node_res.get(i).point));
                                if (min.distance > d.distance) {
                                    min = d;
                                }
                                dist_res.insert(d);
                                j++;
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                                if (node_res.get(i).layer != 0) {
                                    dif = p.point.x() - node_res.get(i).point.x();
                                } else {
                                    dif = p.point.y() - node_res.get(i).point.y();
                                }
                                if (dist_res.max().distance > Math.abs(dif)) {
                                    if (dif < 0 && node_res.get(i).right_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).right_node);
                                    } else if (dif > 0 && node_res.get(i).left_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).left_node);
                                    } else if (dif == 0) {
                                        node_res_sub = KDtree.find(node_res.get(i));
                                        node_res_sub.remove(0);
                                    } else {
                                        node_res_sub = new ArrayList<Node>();
                                    }
                                    while (!node_res_sub.isEmpty()) {
                                        c = p.point.distanceTo(node_res_sub.get(0).point);
                                        if (c < dist_res.max().distance || j < k) {
                                            d = new distance(node_res_sub.get(0), c);
                                            dist_res.insert(d);
                                            if (min.distance > d.distance) {
                                                min = d;
                                            }
                                            j++;
                                            if (j > k) {
                                                dist_res.delMax();
                                                j--;
                                            }
                                        }
                                        node_res_sub.remove(0);
                                    }
                                }
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                            }
                        }
                    } else {
                        node_res_sub = KDtree.find(node_res.get(N - 1));
                        node_res_sub.remove(0);
                        min = new distance(node_res_sub.get(0), p.point.distanceTo(node_res_sub.get(0).point));
                        dist_res.insert(min);
                        j++;
                        if (j > k) {
                            dist_res.delMax();
                            j--;
                        }
                        node_res_sub.remove(0);
                        while (!node_res_sub.isEmpty()) {
                            c = p.point.distanceTo(node_res_sub.get(0).point);
                            if (c < dist_res.max().distance || j < k) {
                                d = new distance(node_res_sub.get(0), c);
                                dist_res.insert(d);
                                if (min.distance > d.distance) {
                                    min = d;
                                }
                                j++;
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                            }
                            node_res_sub.remove(0);
                        }
                        l = N - 1;
                        for (i = l - 1; i >= 0; i--) {
                            if (node_res.get(i).layer != 0) {
                                c = p.point.x() - node_res.get(i).point.x();
                            } else {
                                c = p.point.y() - node_res.get(i).point.y();
                            }
                            if (Math.abs(c) < dist_res.max().distance || j < k) {
                                d = new distance(node_res.get(i), p.point.distanceTo(node_res.get(i).point));
                                if (min.distance > d.distance) {
                                    min = d;
                                }
                                dist_res.insert(d);
                                j++;
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                                if (node_res.get(i).layer != 0) {
                                    dif = p.point.x() - node_res.get(i).point.x();
                                } else {
                                    dif = p.point.y() - node_res.get(i).point.y();
                                }
                                if (dist_res.max().distance > Math.abs(dif)) {
                                    if (dif < 0 && node_res.get(i).right_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).right_node);
                                    } else if (dif > 0 && node_res.get(i).left_node != null) {
                                        node_res_sub = KDtree.find(node_res.get(i).left_node);
                                    } else if (dif == 0) {
                                        node_res_sub = KDtree.find(node_res.get(i));
                                        node_res_sub.remove(0);
                                    } else {
                                        node_res_sub = new ArrayList<Node>();
                                    }
                                    while (!node_res_sub.isEmpty()) {
                                        c = p.point.distanceTo(node_res_sub.get(0).point);
                                        if (c < dist_res.max().distance || j < k) {
                                            d = new distance(node_res_sub.get(0), c);
                                            dist_res.insert(d);
                                            if (min.distance > d.distance) {
                                                min = d;
                                            }
                                            j++;
                                            if (j > k) {
                                                dist_res.delMax();
                                                j--;
                                            }
                                        }
                                        node_res_sub.remove(0);
                                    }
                                }
                                if (j > k) {
                                    dist_res.delMax();
                                    j--;
                                }
                            }
                        }
                    }
                } else {
                    for (i = N - 1; i >= 0; i--) {
                        dist_res = calc_dist(dist_res, node_res, node_res.get(i), p, k);
                    }
                }
                for (i = k - 1; i >= 0; i--) {
                    result[i] = dist_res.max().node.point;
                    dist_res.delMax();
                }
            }
        }
        return result;
    }
}

