
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
                        
            String[] data = br.readLine().split("","");            
            int N = Integer.parseInt(data[0]);

            WeightedQuickUnionUF wQUF=new WeightedQuickUnionUF(N*N +2);
            
            int [] open=new int[N*N+1];
            for(int i=1;i<N*N+1;i++)
            {
                open[i]=0;
            }
            
            do{
                data = br.readLine().split("","");               
                if(data[0]==null)
                {
                    if(!wQUF.connected(0, N*N+1))
                    {
                        System.out.print(""-1"");
                    }
                    break;
                }               
            
                int x = Integer.parseInt(data[0]);//x座標
                int y = Integer.parseInt(data[1]);//y座標
                
                open[N*(x-1)+y]=1;
                
                if(N*(x-1)+y-N<1)//第一列與虛擬開頭連
                {
                    wQUF.union(N*(x-1)+y,0);
                }
                if(N*(x-1)+y+N>N*N)//最後一列與虛擬結尾連
                {
                    wQUF.union(N*(x-1)+y,N*N+1);
                }
                
                if(N*(x-1)+y-N>=1)//上方可連
                {
                    if(open[N*(x-1)+y-N]==1)//上方已開
                    {
                        wQUF.union(N*(x-1)+y,N*(x-1)+y-N);
                        if(wQUF.connected(0, N*N+1))
                        {
                            System.out.print(x+"",""+y);
                            break;
                        }
                    }
                }
                if((N*(x-1)+y)%N!=1)//左方可連
                {
                    if(open[N*(x-1)+y-1]==1)//左方已開
                    {
                        wQUF.union(N*(x-1)+y,N*(x-1)+y-1);
                        if(wQUF.connected(0, N*N+1))
                        {
                            System.out.print(x+"",""+y);
                            break;
                        }
                    }
                }
                if((N*(x-1)+y)%N!=0)//右方可連
                {
                    if(open[N*(x-1)+y+1]==1)//右方已開
                    {
                        wQUF.union(N*(x-1)+y,N*(x-1)+y+1);
                        if(wQUF.connected(0, N*N+1))
                        {
                            System.out.print(x+"",""+y);
                            break;
                        }
                    }
                }
                if(N*(x-1)+y+N<=N*N)//下方可連
                {
                    if(open[N*(x-1)+y+N]==1)//下方已開
                    {
                        wQUF.union(N*(x-1)+y,N*(x-1)+y+N);
                        if(wQUF.connected(0, N*N+1))
                        {
                            System.out.print(x+"",""+y);
                            break;
                        }
                    }
                }
                
            }
            while(data[0]!=null);
            
            
            
        }
    }
}

