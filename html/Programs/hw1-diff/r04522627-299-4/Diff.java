import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {

    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String datastream = br.readLine();
            String[] data = datastream.split("","");
          
            int n = Integer.parseInt(data[0]);
            WeightedQuickUnionUF WQUF = new  WeightedQuickUnionUF(n*n + 2);
            for(int i = 1;i < n + 1;i++)
            {
                WQUF.union(0, i);
                WQUF.union(n*n+1, i + (n - 1) * n);
            }
            //reset objects
            int[] objects = new int[n*n];
            for(int i = 0;i< objects.length;i++)
            {
                objects[i] = 0;   // 0 means close
            }
            
            
            do
            {
                 datastream = br.readLine();
                 if(datastream == null) 
                 {
                     System.out.printf(""%d"",-1);
                     br.close();
                     break;
                 }
                 else
                 {
                     data = datastream.split("","");
                 }
                 int num1 = Integer.parseInt(data[0]);  // x coordinate
                 int num2 = Integer.parseInt(data[1]);  // y coordinate
                 int m = num1 + (num2-1) * n -1;
                 objects[m] = 1; //1 means open
                 if(num1 % n == 0)    //最右
                 {
                     if(num2 % n == 0) //最下
                     {
                        if(objects[m - n] == 1) // 上
                        {
                            WQUF.union(m+1, m - n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - 1] == 1) //左
                        {
                            WQUF.union(m+1, m);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                     }
                     else if(num2 % n == 1) //最上
                     {
                        if(objects[m + n] == 1) //下
                        {
                            WQUF.union(m+1, m + n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - 1] == 1)  //左
                        {
                            WQUF.union(m+1, m);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                     }
                     else
                     {
                        if(objects[m + n] == 1) //下
                        {
                            WQUF.union(m+1, m + n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - 1] == 1) //左
                        {
                            WQUF.union(m+1, m);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - n] == 1) // 上
                        {
                            WQUF.union(m+1, m - n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                     }
                 }
                 else if(num1 % n == 1) //最左
                 {
                      if(num2 % n == 0) //最下
                     {
                        if(objects[m - n] == 1) // 上
                        {
                            WQUF.union(m+1, m - n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m + 1] == 1) // 右
                        {
                            WQUF.union(m+1, m + 1 + 1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                     }
                     else if(num2 % n == 1) //最上
                     {
                        if(objects[m + 1] == 1) // 右
                        {
                            WQUF.union(m+1, m + 1 + 1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m + n] == 1) //下
                        {
                            WQUF.union(m+1, m + n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                     }
                    else
                    {
                       if(objects[m + 1] == 1) // 右
                        {
                            WQUF.union(m+1, m + 1 + 1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m + n] == 1) //下
                        {
                            WQUF.union(m+1, m + n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - n] == 1) // 上
                        {
                            WQUF.union(m+1, m - n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                    }
                 }
                 else
                 {
                    if(num2 % n == 0) //最下
                    {
                         if(objects[m + 1] == 1) // 右
                        {
                            WQUF.union(m+1, m + 1 + 1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - n] == 1) // 上
                        {
                            WQUF.union(m+1, m - n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - 1] == 1) //左
                        {
                            WQUF.union(m+1, m);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                    }
                    else if(num2 % n == 1) //最上
                    {
                      if(objects[m + 1] == 1) // 右
                        {
                            WQUF.union(m+1, m + 1 + 1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m + n] == 1) //下
                        {
                            WQUF.union(m+1, m + n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - 1] == 1) //左
                        {
                            WQUF.union(m+1, m);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                    }
                    else
                    {
                        if(objects[m + 1] == 1) // 右
                        {
                            WQUF.union(m+1, m + 1 + 1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m + n] == 1) //下
                        {
                            WQUF.union(m+1, m + n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - n] == 1) // 上
                        {
                            WQUF.union(m+1, m - n +1);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                        if(objects[m - 1] == 1) //左
                        {
                            WQUF.union(m+1, m);
                            if(WQUF.connected(0, n*n+1))
                            {
                                System.out.printf(""%d,%d"",num1,num2);
                                break;
                            }
                        }
                    }
                 }
                 
            }while(datastream != null);
        }
    }
    
}

