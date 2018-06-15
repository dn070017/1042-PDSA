/*
.
 * To change this template file, choose Tools | Templates
.
 */
//import edu.princeton.cs.algs4.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.*;
/**
 *
 * @author user
 */
public class MyConvexHull {

    /**
     * @param args the command line arguments
     */
        public static int[] ConvexHullVertex(Point2D[] a,double distance) {

           //StdRandom.uniform();
       Point2D[] d=a;
       QuickUnionUF uf=new QuickUnionUF(a.length);
    /*   HashMap hm=new HashMap(); 
       for(int i=0;i<a.length;i++)
       {hm.put(a[i],i);
  
       }*/
         //   System.out.println(hm);
       Insertion.sort(d);
      Arrays.sort(d,d[0].POLAR_ORDER );
      
     /*      for(int i=0;i<a.length;i++){   System.out.println(d[i]);
               if(i==0){StdDraw.setPenColor(StdDraw.RED);
             StdDraw.filledCircle(d[i].x(), d[i].y(), 0.01);
               StdDraw.text(d[i].x(), d[i].y()+0.03,Integer.toString(i) );
               }
         else{
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(d[i].x(), d[i].y(), 0.01);}
              StdDraw.text(d[i].x(), d[i].y()+0.03,Integer.toString(i) );
               }*/
     int[] label=new int[a.length];
     int count=0;
         for(int i=0;i<a.length;i++) 
         {for(int j=i+1;j<a.length;j++)
         {if(d[i].distanceTo(d[j])<=distance)
             {if(!uf.connected(i,j)){uf.union(j,i); 
             label[uf.find(i)]=label[uf.find(i)]+1; }
                 
             }
         }
             
         }
         //uf.union(1,0);
        // for(int i=0;i<a.length;i++){System.out.println(label[i]);}
      int k=0;
     for(int z=0;z<a.length;z++){
         if(label[z]==2){k=k+3;}
         else if(label[z]>2){
             Point2D[] b=new Point2D[label[z]+1];
             int g=0;
            // k=k+1;
           for(int u=z;u<a.length;u++)
           {if(uf.connected(z,u)){b[g]=d[u];g=g+1;}}
           //int c=z;
           Insertion.sort(b);
           Arrays.sort(b,b[0].POLAR_ORDER );
           /* for(int j=0;j<b.length;j++){   System.out.println(b[j]);}
                       for(int i=0;i<b.length;i++){   System.out.println(b[i]);
               if(i==0){StdDraw.setPenColor(StdDraw.RED);
             StdDraw.filledCircle(b[i].x(), b[i].y(), 0.01);
               StdDraw.text(b[i].x(), b[i].y()+0.03,Integer.toString(i) );
               }
         else{
             StdDraw.setPenColor(StdDraw.BLACK);
             StdDraw.filledCircle(b[i].x(), b[i].y(), 0.01);}
              StdDraw.text(b[i].x(), b[i].y()+0.03,Integer.toString(i) );
               }*/
            for(int i=0;i<b.length;i++)  {
                int c=i+1;
                
              for(int j=c+1;j<b.length;j++){
                  
              if(b[i].POLAR_ORDER.compare(b[c],b[j])==1)
              {c=j;}
            //  k=k+1;
                  
            // k=k+1;
                  }
              i=c-1;
              k=k+1;
              if(c==b.length){break;
              }
             // if(i==a.length){k=k+1;}
              
              
              
             // b[q]=c;
      //       System.out.println(k);
    //         System.out.println(c);
           //    k=k+1;
          
          //  k=k+1;
       }
        
      
           }
            System.out.println(k);
         
     }
     
     
     int[]e=new int[1];
     e[0]=k;
     /*         int q=0;
            int[] b=new int[k];
            b[q]=0;
          
                 for(int i=0;i<a.length;i++)  {
               int c=i+1;
               if(c!=a.length){
              for(int j=c+1;j<a.length;j++){
              if(d[i].POLAR_ORDER.compare(d[c],d[j])==1)
              {c=j;}
              }
              
              q=q+1;
              b[q]=c;
 //            System.out.println(c);
             //System.out.println(q);
               i=c-1;}
               if(c==a.length){break;
              }
              //Point2D temp=new Point2D(0,0);
        }*/
          // hm.get(b[0]);
        
  /*         for(int t=0;t<q+1;t++)
           {   e[t]= (int)hm.get(d[b[t]]);
                   
       //        System.out.println(e[t]);
           }*/
 
        return  e;
    }

    
    
public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("" "");
            
            // store the first integer in variable stringCount (number of announced strings)
            double distance =Double.parseDouble(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
               String[] data1 = br.readLine().split("" "");
              int num = Integer.parseInt(data1[0]);
              Point2D[] a=new Point2D[num];
              
         for(int i=0;i<num;i++){
            String[] data2 = br.readLine().split("" "");
            a[i]=new Point2D(Double.parseDouble(data2[0]),Double.parseDouble(data2[1]));
          //  System.out.println(a[i]);
         }
         int[] b=ConvexHullVertex(a,distance);
        System.out.println(b[0]);
        }
}
}
