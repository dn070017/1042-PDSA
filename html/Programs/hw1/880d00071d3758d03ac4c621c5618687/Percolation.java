import java.io.BufferedReader;
import java.io.FileReader;

public class Percolation 
{	
	public static void main(String[] args) throws Exception 
    {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0])))
        {
        	String[] data = br.readLine().split("","");
            //N: matrix size
            int N = Integer.parseInt(data[0]);
            UF uf = new UF(N*N+2);
            //System.out.printf(""%d\n"",N);

            String temp;
	    	int[][] matrix = new int[N+2][N+2]; //error: have to initialize : type""new int[N][N]""
	    	boolean[][] status = new boolean[N+2][N+2];
	    	
	    	int k=0;
	    	int i;
	    	int j;
	    	for(i = 1; i<=N+1; i++)
	    	{
	    		for(j = 1; j<N+1; j++)
	    		{
	    			if(i!=(N+1))
	    			{
	    				k++;
	    				matrix[i][j]=k;
	    			}else
	    			{
	    				matrix[i][j]=N*N+1;
	    			}
	    		}
	    	}
	    	
	    	for(i = 0; i<N+2; i++)
	    	{
	    		for(j = 0; j<N+2; j++)
	    		{
	    			if(i==0 && j>=1 && j<=N)
	    			{
	    				status[i][j] = true; 
	    			}else if(i==N+1 && j>=1 && j<=N)
	    			{
	    				status[i][j] = true;
	    			}else
	    			{
	    				status[i][j] = false;
	    			}
	    		}
        	}

	    	/*just for test*/
//	    	for(i = 0; i<N+2; i++)
//	    	{
//	    		for(j = 0; j<N+2; j++)
//	    		{
//	    			System.out.printf(""%d "",matrix[i][j]);	
//	    		}
//	    		System.out.printf(""%n"");
//        	}
//	    	for(i = 0; i<N+2; i++)
//	    	{
//	    		for(j = 0; j<N+2; j++)
//	    		{
//	    			System.out.printf(""%b "",status[i][j]);	
//	    		}
//	    		System.out.printf(""%n"");
//        	}
	    	
	    	boolean flag = false;
	    	while((temp = br.readLine())!=null)
	    	{
	    		String[] coordinates = temp.split("","");
	    		int row = Integer.parseInt(coordinates[0]);
	            int col = Integer.parseInt(coordinates[1]);
	            status[row][col]=true;
	            System.out.printf(""%d \n"", matrix[row][col]);
	            if(status[row-1][col]==true)
	            {
	            	uf.union(matrix[row][col],matrix[row-1][col]);
	            	//System.out.printf(""row-1 %b \n"", uf.connected(matrix[row][col],matrix[row-1][col]));
	            }
	            if(status[row+1][col]==true)
	            {
	            	uf.union(matrix[row][col],matrix[row+1][col]);
	            	//System.out.printf(""row+1 %b \n"", uf.connected(matrix[row][col],matrix[row+1][col]));
	            }
	            if(status[row][col+1]==true)
	            {
	            	uf.union(matrix[row][col],matrix[row][col+1]);
	            	//System.out.printf(""col+1 %b \n"", uf.connected(matrix[row][col],matrix[row][col+1]));
	            }
	            if(status[row][col-1]==true)
	            {
	            	uf.union(matrix[row][col],matrix[row][col-1]);
	            	//System.out.printf(""col-1 %b \n"", uf.connected(matrix[row][col],matrix[row][col-1]));
	            }
	            if(uf.connected(0,N*N+1))
	            {
	            	System.out.printf(""%d,%d"",row,col);
	            	flag = true;
	            	break;
	            }
	            //System.out.printf(""::: %d %b \n"", uf.find(9) ,uf.connected(0,N*N+1));
	    	}
	    	if(flag==false)
	    	{
	    		System.out.printf(""-1"");
	    	}
        }
    }
}

