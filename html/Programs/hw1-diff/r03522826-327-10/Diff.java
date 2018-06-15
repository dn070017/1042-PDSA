import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

     private static int convert(int x, int y, int N){
       int p = (x-1)*N + y;
       return p;
    }
     
     
    
    
    public static void main(String[] args) throws Exception{
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
           
           String [] data = br.readLine().split("","");
           int N = Integer.parseInt(data[0]);
           int [][] check = new int [N][N];
           QuickUnionUF uf = new QuickUnionUF(N*N+2);
           int x = 0, y = 0, x1 = 0, y1 = 0, j = 1, end = 0;
           String d;

          while((d = br.readLine())!= null){
           
            d = d.replaceAll("" "", """");
            String [] data1 = d.split("","");
           
            x =  Integer.parseInt(data1[0]);
            y =  Integer.parseInt(data1[1]);
           
            
           int inx = x -1, iny = y -1 ;
             check[inx][iny] = 1;
             
             int p = convert(x,y,N);
             
             if(1 == x){
                 uf.union(p,0);
             }
             
             if(N == x){
                 uf.union(p,N*N+1);
             }
             
             if((x-1)>0 && (x-1)<=N){
                int pu = convert(x-1,y,N);
                if( 1 == check[x-2][y-1]){
                uf.union(p,pu);
                }
             }
             
             if((x+1)<=N && (x+1)>0){
                int pl = convert(x+1,y,N);
                if( 1 == check[x][y-1]){
                uf.union(p,pl);
                }
             }
            
              if((y-1)>0 && (y-1)<=N){
                int pl = convert(x,y-1,N);
                if(1 == check[x-1][y-2]){
                uf.union(p,pl);
                }
             }
              
             if((y+1)<=N && (y+1)>0){
                int pr = convert(x,y+1,N);
                if(1 == check[x-1][y]){
                uf.union(p,pr);
                }
             }
             
           if((uf.connected(0, N*N+1))&&(0 == end)){
               x1 = x;
               y1 = y;
               end = 1;
               j = 2;
           }
             
                 
          }
         switch(j){
                 case 1:
                     System.out.println(-1);
                     break;
                 case 2:
                     System.out.println(x1+"",""+y1);
                     break;
                 default:
             }
//            System.out.println(j);
//            System.out.println(end);
//          System.out.println(check[0][0]);
//          System.out.println(check[0][1]);
//          System.out.println(check[0][2]);
//          System.out.println(check[1][0]);
//          System.out.println(check[1][1]);
//          System.out.println(check[1][2]);
//          System.out.println(check[2][0]);
//          System.out.println(check[2][1]);
//          System.out.println(check[2][2]);
        }
    }
    
}
