import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author DANNY
 */



public class Percolation {
    
    public static void main(String[] args) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String buf = br.readLine();
//          System.out.println(buf);
            int n = Integer.valueOf(buf);
            UF uf = new UF(n*n+2); //0~(n*n)+1
            int [][] M;
            M = new int[n][n];
            int [] Mx;
            Mx = new int[n*n];
            int [] My;
            My = new int[n*n];
            int count1 = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    count1++;
                    M[i][j] = count1;
                    //System.out.println(M[i][j]+""\n"");
                }
            }
            for(int i=0;i<n;i++){
                uf.union(M[0][i],0);
                uf.union(M[n-1][i],(n*n)+1);
            }
            
//===============================================================================

            String buf1[] = br.readLine().split("","");
            if(!buf1[0].equals("""")){
            Mx[0] = Integer.valueOf(buf1[0]);
            My[0] = Integer.valueOf(buf1[1]);
            }
            int count2 = 1;
            while (br.ready())
            {
            String buf2[] = br.readLine().split("","");
            if(buf2[0].equals("""")){break;}
            
            Mx[count2] = Integer.valueOf(buf2[0]);
            My[count2] = Integer.valueOf(buf2[1]);
            
//          System.out.println(Mx[count2]+"",""+My[count2]);
            
            for(int i=0;i<count2;i++){
                if (!uf.connected(M[Mx[count2-1-i]-1][My[count2-1-i]-1],M[Mx[count2]-1][My[count2]-1]))
                {
                    if(((Math.abs(Mx[count2]-Mx[count2-1-i]))==1)&&((Math.abs(My[count2]-My[count2-1-i]))==0))
                    {
                        uf.union(M[Mx[count2-1-i]-1][My[count2-1-i]-1], M[Mx[count2]-1][My[count2]-1]);
//                        System.out.println(Mx[count2-1-i]+"",""+My[count2-1-i]+""~""+Mx[count2]+"",""+My[count2]);
//                        System.out.println(""cc"");
                    }
                    if(((Math.abs(Mx[count2]-Mx[count2-1-i]))==0)&&((Math.abs(My[count2]-My[count2-1-i]))==1))
                    {
                        uf.union(M[Mx[count2-1-i]-1][My[count2-1-i]-1], M[Mx[count2]-1][My[count2]-1]);
//                        System.out.println(Mx[count2-1-i]+"",""+My[count2-1-i]+""~""+Mx[count2]+"",""+My[count2]);
//                        System.out.println(""cc"");
                    }
                }
                if (uf.connected(0,(n*n)+1))
                {
                StdOut.println(Mx[count2] + "","" + My[count2]);
                 break;
                }
            }
            
            if (uf.connected(0,(n*n)+1))
            {
//            StdOut.println(Mx[count2] + "","" + My[count2]);
             break;
            }
            
            count2++;
            }
            if(!uf.connected(0,(n*n)+1))
            {    
            StdOut.println(""-1"");
            }   
            if(n==1)
            {
               if(buf1[0].equals("""")){StdOut.println(""-1"");}
               else
                StdOut.println(""1,1"");
            }
        } 
    }
}
