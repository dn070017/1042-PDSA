import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Dennis
 */
public class Percolation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
 
     try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        String[] Num = br.readLine().split("","");
        int N = Integer.parseInt(Num[0]);
        int ans=0,res=0;
        QuickUnionUF uf = new QuickUnionUF(N*N+2);

        int i =0;
        int x =0 , y =0;
        int checkxup =0 , checkxdown =0, checkyleft = 0, checkyright =0 ;
        int checkansup=0,checkansdown=0,checkansleft=0,checkansright=0;
        int sysend=0 ,findx=0,findy=0;
        String d;
        
        while( (d = br.readLine()) != null )
        {
            
            String [] data = d.split("","");
            x =  Integer.parseInt(data[0]);
            y =  Integer.parseInt(data[1]);
            ans = (x-1)*N+y;
            
            if(x==1)
            {
                uf.union(ans,0);
            }
            if(x==N)
            {
                uf.union(ans,N*N+1);
            }
            
            checkxup=x-1;
            checkansup = (checkxup-1)*N+y;
            if( checkxup != 0 )
            {
                uf.union(ans,checkansup);
            }
            
            checkxdown=x+1;
            checkansdown = (checkxdown-1)*N+y;
            if( checkxdown < N )
            {
                uf.union(ans,checkansdown);          
            }
            
            checkyleft=y-1;
            checkansleft = (x-1)*N+checkyleft;
            if( checkyleft != 0)
            {
                  uf.union(ans,checkansleft);         
            }
            
            checkyright=y+1;
            checkansright = (x-1)*N+checkyright;
            if( checkyright < N )
            {
                  uf.union(ans,checkansright);   
            }
            
            if(uf.connected(0, N*N+1) && sysend == 0)
            {
                sysend = 1;
                findx = x;
                findy = y;
            }

        }
        
        if( sysend == 1)
        {                
            System.out.println(findx +"",""+ findy);
        }
        else if( sysend != 1)
        {                
            System.out.println(-1);
        }
        
        }
    }
}
