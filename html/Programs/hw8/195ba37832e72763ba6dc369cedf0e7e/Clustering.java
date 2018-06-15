import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


/**
 *
 * @author CHIN LUNG
 */
public class Clustering {

    protected static double[][]vertices;
    protected static MinPQ PQ = new MinPQ();
    protected static MinPQ mp = new MinPQ();
    protected static int Totalpoints;
    protected static LinkedList<cluster> Cluster = new LinkedList<cluster>() ;
    protected static LinkedList<cluster> record = new LinkedList<cluster>() ;
    //protected static LinkedList<cluster> copy = new LinkedList<cluster>() ;

    
    
    public static class cluster implements Comparable<cluster>
    {       

        private Point2D Centroid ;       
        public  LinkedList<Point2D> allpoint = new LinkedList<Point2D>() ;
        public  cluster cs  ;
        public  boolean IsClusted = false;
        public int ID;
        public cluster(cluster [] points)  //將得到cluster的所有點都merge
        {
            for(int i = 0; i < points.length ; i++)
            {
                for(int j = 0; j < points[i].size() ;j++)
                {
                    allpoint.add(points[i].allpoint.get(j));
                }
            }
        }

        public cluster(Point2D  point)
        {
            allpoint.add(point) ;
        }
        
        public  Point2D GetCentroid()
        {   double x = 0;double y = 0; 
            for(int i = 0; i < allpoint.size() ;i++)
            {
                x+=allpoint.get(i).x();
                y+=allpoint.get(i).y();
            }
            x = x/allpoint.size(); y = y/allpoint.size();
            Centroid = new Point2D(x,y);
            
            return Centroid;
        }
        
         public  cluster GetclusterCentroid()
        {   
            cs = new cluster(GetCentroid());
            return cs;
        }
        
        // compute distance between two cluster 
        public double clusterToCluster(cluster that)
        {
          return  this.GetCentroid().distanceTo(that.GetCentroid());
        }
       
        public int size() 
        {
            return allpoint.size();
        }

        public boolean equals(Object that) {
        if(that instanceof cluster) {
            cluster p = (cluster) that;
            return this.clusterToCluster(p)== 0;
        }
        return false;
    }

        @Override
        public int compareTo(cluster t) {
            if(this.allpoint.size() > t.allpoint.size())
            {
                return 1;
            }
            else if(this.allpoint.size() == t.allpoint.size())
                    {
                        if(this.GetCentroid().x() > t.GetCentroid().x())
                        {
                            return 1;
                        }
                        else if(this.GetCentroid().x() < t.GetCentroid().x())
                        {
                            return -1;
                        }
                        else
                        {
                            if(this.GetCentroid().y() > t.GetCentroid().y())
                            {
                                return 1;
                            }
                            else if(this.GetCentroid().y() < t.GetCentroid().y())
                            {
                                return -1;
                            }
                            else{
                                return 0;
                            }
                        }
                    }
            else{
                return -1;
            }
        }
    }
    
    public static class Pair implements Comparable<Pair> {
        
        private cluster []clusters = new cluster[2];
        public double  distance ;

        
        public Pair(cluster c1, cluster c2)
        {
            clusters[0] = c1;clusters[1] = c2;
            distance = c1.GetCentroid().distanceTo(c2.GetCentroid());
            c1.cs = c1.GetclusterCentroid();
            c2.cs = c2.GetclusterCentroid();
        }
        //怎麼知道有些pair需要忽略??如果他的重心改變過??看他的距離有無重複過
  public boolean IsClusted(LinkedList<cluster> rc )
  {

      if( rc.contains(this.clusters[0].cs) == false && rc.contains(this.clusters[1].cs) == false &&this.clusters[0].cs.equals(this.clusters[1].cs) != true)
      {
          return false;
      }
      else
      {
          return true;
      }
    
  }
               
        
        
        public cluster[] Clusters()
        {
            return clusters;
        }
        public int pairsize()
        {
            return clusters[0].size()+clusters[1].size();
        }

  
    public int compareTo(Pair that) {
        if(this.distance > that.distance)
        {
            return 1;
        }
        else if(this.distance == that.distance)
        {
            return 0;
        }
        else{ return -1;}
        }
    
}


    
        public static void UpdatePair(cluster merged , LinkedList<cluster> clusters)
        {   //distance = new double [merged.size() * clusters.size()];int a =0;
         
            //merged.IsClusted = true; 
            
            for(int j = 0; j < clusters.size() ;j++)
            {
                    Pair target = new Pair(merged,clusters.get(j));
                    PQ.insert(target);
            }

        }
//         public static boolean check(Pair merged , WeightedQuickUnionUF w1,WeightedQuickUnionUF w2,WeightedQuickUnionUF w3)
//         {
//             for(int i = 0; i < merged.clusters[0].size();i++)
//             {
//                w1.find(merged.clusters[0].allpoint.get(0)) 
//             }
//             for(int i = 0; i < merged.clusters[1].size();i++)
//             {
//                 
//             }
//         }
    
    
    
    public static void main(String[] args)throws Exception {
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {  //to amend when upload
             Totalpoints = Integer.parseInt(br.readLine().trim());
            vertices = new double[Totalpoints][2]; int a = 0;
            // 1. read in the file containing N 2-dimentional points
            for(int i = 0 ; i < Totalpoints;i++)
            {
                String[] data= br.readLine().split("" "");
                vertices[i][0] = Double.parseDouble(data[0]) ;
                vertices[i][1] = Double.parseDouble(data[1]) ;
                Point2D p2 = new Point2D(vertices[i][0],vertices[i][1]) ;
                cluster c = new cluster(p2);
                c.ID =a ;a++;
                Cluster.add(c) ;//let point become clust individually 
            }    
        }
            if(Totalpoints == 2)
            {
                  System.out.println(Cluster.get(0).size() +"" "" + String.format(""%.2f"",Cluster.get(0).GetCentroid().x() )+"" "" + String.format(""%.2f"",Cluster.get(0).GetCentroid().y()));
                  System.out.println(Cluster.get(1).size() +"" "" + String.format(""%.2f"",Cluster.get(1).GetCentroid().x() )+"" "" + String.format(""%.2f"",Cluster.get(1).GetCentroid().y()));
                  System.out.println(String.format(""%.2f"", Cluster.get(0).clusterToCluster(Cluster.get(1)) ));
            }
            // get every points generated pair
    else{        
            for(int i = 0 ; i < Cluster.size();i++)
            {
                for(int j = i+1; j < Cluster.size();j++)
                {
                    Cluster.get(i).GetclusterCentroid();
                    Pair target = new Pair(Cluster.get(i),Cluster.get(j));
                    PQ.insert(target);
                    
                }
            } 
            
            
            
            Pair merge; cluster merged  ; Pair[] outcome = new Pair[3]; cluster newmerge; 
             
            while( Cluster.size() > 3 )
            {                               
               merge = (Pair)PQ.delMin();               
   
               while(merge.IsClusted( record  ) && PQ.isEmpty()!=true) //弄出""沒有做記號""要merge的那一組
               {                      
                   merge = (Pair)PQ.delMin();
                   mp.insert(merge);
               }
               
               
               merge.clusters[0].cs.IsClusted = true;record.add(merge.clusters[0].cs);
               merge.clusters[1].cs.IsClusted = true;record.add(merge.clusters[1].cs);
               // delete merged point in clusters
               for(int i = 0; i < merge.clusters.length;i++)
               {
                   for(int j = 0; j < Cluster.size() ;j++)
                   {    //for single point
                       if(merge.clusters[i].cs.equals(Cluster.get(j).cs))
                       {    
                           //record.get(j).cs.IsClusted = true;
                           
                           Cluster.remove(j);                      
                       } 
                   }
               }       
               

               
               merged = new cluster(merge.Clusters()); //將那一組真的merge 產生新的cluster

               //record.add(merged);

                
                newmerge = new cluster(merged.GetCentroid()); //新的merge point 誕生!!!
                
          
                Cluster.add(merged);
                //copy.add(merged);
                UpdatePair( merged , Cluster) ;  // update clusters 新的cluster和舊式cluster的關係( 加入new pair
                //有些pair沒加進去??!!!因為你寫的update船進去的是 cluster!!!所以根本不會有3對3組合乾
                          
            }
             //抓漏  為了找出只有一個點ㄉ

              
            cluster [] temp = new cluster[3];
            temp[0] = Cluster.get(0);temp[1] = Cluster.get(1);temp[2] = Cluster.get(2);
            WeightedQuickUnionUF w1 = new WeightedQuickUnionUF(temp[0].size());
            WeightedQuickUnionUF w2 = new WeightedQuickUnionUF(temp[1].size());
            WeightedQuickUnionUF w3 = new WeightedQuickUnionUF(temp[2].size());

                         
            Arrays.sort(temp);
            

            
            System.out.println(temp[2 ].size() +"" "" +String.format(""%.2f"", temp[2 ].GetCentroid().x())+"" "" + String.format(""%.2f"", temp[2].GetCentroid().y()));
            System.out.println(temp[1 ].size() +"" "" + String.format(""%.2f"",temp[1 ].GetCentroid().x() )+"" "" + String.format(""%.2f"",temp[1].GetCentroid().y()));
            System.out.println(temp[0].size() +"" "" + String.format(""%.2f"",temp[0].GetCentroid().x() )+"" "" + String.format(""%.2f"",temp[0].GetCentroid().y()));
            
//            for(int i = 0; i< temp[0].size() ;i++)
//            {
//                for(int j = i+1; j < temp[0].size() ;j++)
//                {w1.union(temp[i].ID, temp[j].ID);}
//            }
//            for(int i = 0; i< temp[1].size() ;i++)
//            {
//                for(int j = i+1; j < temp[1].size() ;j++)
//                {w1.union(temp[i].ID, temp[j].ID);}
//            }
//             for(int i = 0; i< temp[2].size() ;i++)
//            {
//                for(int j = i+1; j < temp[2].size() ;j++)
//                {w1.union(temp[i].ID, temp[j].ID);}
//            }
//             
//             Pair out;
//             out = (Pair)mp.delMin();
//            while(check)
//            {
//                
//            }
            

                System.out.println(String.format(""%.2f"", mp.delMin() ));
        }
    }
}
