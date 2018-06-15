/*
.
 * To change this template file, choose Tools | Templates
.
 */
//import edu.princeton.cs.algs4.Point2D;
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
        public static String ConvexHullVertex(Point2D[] a) {

           //StdRandom.uniform();
       Point2D[] d=a;
       HashMap hm=new HashMap(); 
       for(int i=0;i<a.length;i++)
       {hm.put(a[i],i);
  
       }
    //        System.out.println(hm);
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
         
           int k=0;
           
            for(int i=0;i<a.length;i++)  {
               int c=i+1;
              for(int j=c+1;j<a.length;j++){
              if(d[i].POLAR_ORDER.compare(d[c],d[j])==1)
              {c=j;}
              }
              
              k=k+1;
             // b[q]=c;
           //  System.out.println(k);
    //         System.out.println(c);
               i=c-1;
        }
              int q=0;
            int[] b=new int[k+1];
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
        //     System.out.println(c);
             //System.out.println(q);
               i=c-1;}
               if(c==a.length){q=q+1;
              b[q]=c;}
              //Point2D temp=new Point2D(0,0);
        }
          // hm.get(b[0]);
          int[] e=new int[k+1];
          String f=null;
          int temp=0;
           for(int t=0;t<q;t++)
           {   e[t]= (int)hm.get(d[b[t]]);
                   
//               System.out.println(e[t]);
           }
            for(int t=0;t<q;t++)
           {  for(int y=t+1;y<q;y++) 
           {if(e[t]>e[y])
           {temp=e[t]; e[t]=e[y];e[y]=temp;}
               } 
           if(f==null){f=String.valueOf(e[t]);}
             else  f=f+"",""+String.valueOf(e[t]);    
         //      System.out.println(f);
           }
             // System.out.println(f);
        return  f;
    }

    
    
    public static void main(String[] args) {
   /*     Point2D[] a=new Point2D[100];
             for(int i=0;i<100;i++){
       a[i]=new Point2D(StdRandom.uniform(),StdRandom.uniform());
       //StdDraw.filledCircle(a[i].x(), a[i].y(), 0.01);
       }
  //   int[]b=ConvexHullVertex(a);
          String b=ConvexHullVertex(a);*/
   /*  int h=b.length-1;
     for(int i=1;i<h;i++)
     {System.out.println(b[i]);}*/
      //       System.out.println(b);
}
   
   
    
}

