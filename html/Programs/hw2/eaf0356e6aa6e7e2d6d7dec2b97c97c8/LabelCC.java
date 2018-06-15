/*
.
 * To change this template file, choose Tools | Templates
.
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
/**
 *
 * @author Phoenix
 */
public class LabelCC {
    public static void main(String[] args)  throws Exception{
        
        try(BufferedReader br =  new BufferedReader(new FileReader(args[0]))){
                       
            String[] N = br.readLine().split("","");
            String buffer ;
            String data = """";
            int n = Integer.parseInt(N[0]);
            int tx = Integer.parseInt(N[1]);
            int ty = Integer.parseInt(N[2]);
            
            int[][] matrix = new int[n+1][n+1];
            int[][] matrix2 = new int[n+1][n+1];
            
            for(int x=1;x<=n;x++)
            {
                Arrays.fill(matrix[x],1);
            }    
            for(int x=0;x<=n;x++)
            {
                matrix[0][x]=0;
                matrix[x][0]=0;
            }
            
               UF uf = new UF(n*n);
            int i = 0;
        
           buffer = br.readLine();
           while(buffer!=null){
           data = data.concat(buffer);
           data = data.concat("","");
           i++;
           buffer = br.readLine();
           }
            int q = 0;
           String[] newdata =  data.split("","");    
           
            int[] Data = new int[2*i];
            for(q = 0;q<2*i;q++){
             Data[q] = Integer.parseInt(newdata[q]);
            }
            for(q = 0; q<2*i ; q+=2){
                int a = Data[q];
                int b =Data[q+1];
                matrix[a][b] = 0;
            }
            int[] label = new int[n*n];
            for(int x=0;x<n*n;x++)
            {
                label[x]=x;
            }
            int labelcount=0;
            
            for(int z=1;z<=n;z++)
            {
                if(matrix[1][z]==1)
                {
                    if(matrix[1][z-1]==1)
                    {
                       matrix2[1][z]=labelcount; 
                    }
                    else
                    {
                        labelcount++;
                        matrix2[1][z]=labelcount;
                    }
                }
            }
            
            for(int  x = 2; x <= n ; x++)
            {
                for(int y =1;y<=n;y++)
                {
                    if(matrix[x][y]==1)
                    {
                        if(matrix[x-1][y]==1)
                        {
                             if(matrix[x][y-1]==1)
                            {
                                 if(matrix2[x-1][y]<matrix2[x][y-1])
                                 {
                                     matrix2[x][y]=matrix2[x-1][y];
                                     for(int c=0;c<n*n;c++)
                                     {
                                         if(label[c]==label[matrix2[x][y-1]])
                                         {label[c]=label[matrix2[x-1][y]];}
                                     }
                                 }
                                 else
                                 {
                                     matrix2[x][y]=matrix2[x][y-1];
                                     for(int c=0;c<n*n;c++)
                                     {
                                         if(label[c]==label[matrix2[x-1][y]])
                                         {label[c]=label[matrix2[x][y-1]];}
                                     }
                                     //label[matrix2[x-1][y]]=label[matrix2[x][y-1]];
                                 }
                            }
                            else
                            {
                                matrix2[x][y]=matrix2[x-1][y];
                            }
                        }
                        else
                        {
                             if(matrix[x][y-1]==1)
                            {
                                matrix2[x][y]=matrix2[x][y-1];
                            }
                            else
                            {
                                labelcount++;
                                matrix2[x][y]=labelcount;
                            }
                        }
                       
                    }
                    
                }
            }
            if(matrix[tx][ty]==1)
            {
                System.out.print(label[matrix2[tx][ty]]);
            }
            else
            {
                System.out.print(0);
            }
                
            }
            
                      
    }   
}

