
//import edu.princeton.cs.algs4.QuickUnionUF;
import java.io.FileReader;
import java.io.BufferedReader;


public class LabelCC {

    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String nullData;
            String[] data = br.readLine().split("","");            
            int N = Integer.parseInt(data[0]);
            int ansX=Integer.parseInt(data[1]);
            int ansY=Integer.parseInt(data[2]);            
            
            int [][] mark=new int[N][N];//block  判斷
            int [][] code=new int[N][N];//單位編號
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<N;j++)
                {
                    mark[i][j]=1;
                    code[i][j]=0;
                }                
            }            
            
            QuickUnionUF qUF=new QuickUnionUF(N*N);
            
            do{
                nullData=br.readLine();                          
                if(nullData ==null)
                {                    
                    break;
                }               
                data = nullData.split("","");     
                int x = Integer.parseInt(data[0]);//x座標
                int y = Integer.parseInt(data[1]);//y座標                
                mark[x-1][y-1]=0;//被傳入的座標變成block                        
            }
            while(data[0]!=null);
            
            int count=0;
            for(int i=0;i<N;i++)
            {
                if(i==0)
                {
                    for(int j=0;j<N;j++)
                    {                           
                        if(mark[i][j]==1&&j==0)
                        {
                            count++;
                            code[i][j]=count;
                        }
                        if(j!=0)
                        {
                            if(mark[i][j]==1)
                            {
                                if(mark[i][j-1]==0)
                                {
                                    count++;
                                    code[i][j]=count;
                                }
                                if(mark[i][j-1]==1)
                                {
                                    code[i][j]=code[i][j-1];
                                    qUF.union(code[i][j],code[i][j-1]);//與左方連接
                                }
                            }                            
                        }                        
                    }                
                }
                else
                {                    
                    for(int j=0;j<N;j++)
                    {
                        if(mark[i][j]==1 && mark[i-1][j]==1)
                        {   
                            if(j==0)
                            {
                                code[i][j]=code[i-1][j];
                                qUF.union(code[i][j],code[i-1][j]);//與上方連接
                            }
                            if(j!=0)
                            {
                                if(mark[i][j-1]==1)
                                {
                                    if(code[i-1][j]<code[i][j-1])
                                    {
                                        code[i][j]=code[i-1][j];
                                        qUF.union(code[i][j],code[i-1][j]);//與上方連接
                                        qUF.union(code[i][j-1],code[i][j]);//左方(數字較大)須連在此[i][j]後方
                                    }
                                    else if(code[i-1][j]>code[i][j-1])
                                    {
                                        code[i][j]=code[i][j-1];
                                        qUF.union(code[i][j],code[i][j-1]);//與左方連接
                                        qUF.union(code[i-1][j],code[i][j]);//上方須連在此[][]後方
                                    }
                                    else
                                    {
                                        code[i][j]=code[i-1][j];
                                        qUF.union(code[i][j],code[i-1][j]);//與上方連接
                                        qUF.union(code[i][j],code[i][j-1]);//與左方連接
                                    }
                                }
                                if(mark[i][j-1]==0)
                                {
                                     code[i][j]=code[i-1][j];
                                     qUF.union(code[i][j],code[i-1][j]);//與上方連接
                                }
                            }                                                        
                        }
                        if(mark[i][j]==1 && mark[i-1][j]==0)
                        {
                            if(j==0)
                            {
                                count++;
                                code[i][j]=count;
                            }
                            if(j!=0)
                            {
                                if(mark[i][j-1]==1)
                                {
                                    code[i][j]=code[i][j-1];
                                     qUF.union(code[i][j],code[i][j-1]);//與左方連接
                                }
                                if(mark[i][j-1]==0)
                                {
                                    count++;
                                    code[i][j]=count;
                                }
                            }                            
                        }                        
                    }
                }                
            }            
            if(code[ansX-1][ansY-1]==0)
            {
                System.out.printf(""0"");
            }
            else
            {
                int ans=qUF.find(code[ansX-1][ansY-1]);
                System.out.printf(""%d"",ans);
            }
            
        }
    }
}

