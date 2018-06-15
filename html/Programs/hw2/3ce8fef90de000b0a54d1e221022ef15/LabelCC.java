import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author stonebreaker
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        try(BufferedReader br = new BufferedReader (new FileReader (args[0]))){
        
            // Initialization & Read first line
            String [] data = br.readLine().split("","");
            int N = Integer.parseInt(data[0]);         // Dimension
            int x0 = Integer.parseInt(data[1]);
            int y0 = Integer.parseInt(data[2]);
            int [][] check = new int [N][N];
//            int [][] parent = new int [N][N];
            String d;
            
            for (int i = 0; i < N; i++){
                for(int j = 0; j < N; j++)
                    check[i][j] = 1;
            }
//            System.out.println(N);
//            System.out.println(x0);
//            System.out.println(y0);
            
            // Readfile the other lines
            while((d = br.readLine()) != null){
                d = d.replaceAll("" "", """");
                String [] data1 = d.split("","");
                int x = Integer.parseInt(data1[0]);
                int y = Integer.parseInt(data1[1]);
                
                check[x-1][y-1] = 0;
                
            }
            
        int label = 1;
        UF uf = new UF(N*N+2);
//        QuickFindUF uf = new QuickFindUF(N*N+1);
        int p = 0, q = 0;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(0 != check[i][j])
                {
                    if (0 == i)
                    {
                        if (0 == j)
                        {
                            check[i][j] = label;
//                            parent[i][j] = label;
                            label += 1;
                        }
                        else
                        {
                            if (0 == check[i][j-1])
                            {
                                check[i][j] = label;
//                                parent[i][j] = label;
                                label += 1;
                            }
                            else
                            {
                                check[i][j] = check[i][j-1];
//                                parent[i][j] = parent[i][j-1];
                            }
                        }
                    }
                    else
                    {
                        if (0 == j)
                        {
                            if (0 == check[i-1][j])
                            {
                                check[i][j] = label;
//                                parent[i][j] = label;
                                label += 1;
                            }
                            else
                            {
                                check[i][j] = check[i-1][j];
//                                parent[i][j] = parent[i-1][j];
                            }
                        }
                        else
                        {
                            if (0 == check[i-1][j])
                            {
                                if (0 == check[i][j-1])
                                {
                                    check[i][j] = label;
//                                    parent[i][j] = label;
                                    label += 1;
                                }
                                else
                                {
                                    check[i][j] = check[i][j-1];
//                                    parent[i][j] = parent[i][j-1];
                                }
                            }
                            else
                            {
                                if (0 == check[i][j-1])
                                {
                                    check[i][j] = check[i-1][j];
//                                    parent[i][j] = parent[i-1][j];
                                }
                                else
                                {
                                    if (check[i][j-1] < check[i-1][j])
                                    {
                                        check[i][j] = check[i][j-1];
//                                        p = check[i][j-1];
//                                        q = check[i-1][j];
                                        uf.union(check[i][j-1],check[i-1][j]);
                                    }
                                    else if (check[i][j-1] > check[i-1][j])
                                    {
                                        check[i][j] = check[i-1][j];
                                        uf.union(check[i-1][j],check[i][j-1]);
                                    }
                                }
                            }
                        }
                            
                    }
                    
                }
                
            }
        }
        
//        for (int i = 0; i < N; i++)
//        {
//            for (int j = 0; j <N; j++)
//                System.out.println(check[i][j]);
//        }
//            System.out.println(label);
//        
        
            for (int i =0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    if(0 != check[i][j])
                        check[i][j] = uf.find(check[i][j]);
                }
            }
            
            System.out.println(check[x0-1][y0-1]);
//            for (int i =0; i < N; i++)
//            {
//                for (int j = 0; j < N; j++)
//                {
//                    System.out.println(check[i][j]);
//                }
//            }
        }
    }
    
}

