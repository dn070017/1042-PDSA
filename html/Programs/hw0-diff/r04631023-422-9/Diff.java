import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
            
            String[] hit = br.readLine().split("","") ;
            for (int i = 0; i < num; i++)
            {
                String[] row = br.readLine().split("","") ;
                matrix[i] = row ;
            }
            
            int rightslash = 0 ;
            int leftslash = 0 ;
            int horizental = 0 ;
            int vertical = 0 ;
            int end = num - 1 ;
            int count = 0 ;
            int[][] bingo = new int[num][num] ;
            int[] HorLoc = new int[num] ;
            int[] VerLoc = new int[num] ;
            
            for(int i = 0; i < num; i++)
            {
                for(int j = 0; j < num; j++)
                {
                    for(int k = 0; k < stringCount; k++)
                    {
                        int check = hit[k].compareTo(matrix[i][j]) ;
                        if(i == 0 && j == 0 && check==0 )
                        {
                            rightslash++ ;
                            HorLoc[horizental] = i ;
                            horizental++ ;
                            VerLoc[vertical] = j ;
                            vertical++ ;
                            bingo[i][j] = 1 ;
                        }
                        else if(i == 0 && check==0)
                        {
                            VerLoc[vertical] = j ;
                            vertical++ ;
                            bingo[i][j] = 1 ;
                            //System.out.printf(""%d\n"", bingo[i][j]) ;
                        }
                        else if(j == 0 && check==0) 
                        {
                            HorLoc[horizental] = i ;
                            horizental++ ;
                            bingo[i][j] = 1 ;
                        }
                        else if(i == end && check==0)
                        {
                            leftslash++ ;
                            bingo[i][j] = 1 ;
                        }
                        else if(check==0)
                        {
                            bingo[i][j] = 1 ;
                        }
                    }
                }
            }
            
            if(rightslash > 0)
            {
                int Cal = 0 ;
                for(int i = 0 ; i < num; i++ )
                {
                    Cal += bingo[i][i] ;
                }
                if(Cal == num)
                {
                    count++ ;
                }
            }
            if(horizental > 0)
            {
                for(int i = 0 ; i < horizental ; i++ )
                {
                    int Cal = 0 ;
                    for(int j = 0 ; j < num ; j++)
                    {
                        Cal += bingo[HorLoc[i]][j] ;
                    }
                    if(Cal == num)
                    {
                        count++ ;
                    }
                }
            }
            if(vertical > 0)
            {
                for(int i = 0 ; i < vertical ; i++ )
                {
                    int Cal = 0 ;
                    for(int j = 0 ; j < num ; j++)
                    {
                        Cal += bingo[j][VerLoc[i]] ;
                    }
                    if(Cal == num)
                    {
                        count++ ;
                    }
                }
            }
            if(leftslash > 0)
            {
                int Cal = 0 ;
                for(int i = 0 ; i < num ; i++)
                {
                    Cal += bingo[end-i][i] ;
                }
                if(Cal == num)
                    {
                        count++ ;
                    }
            }
            System.out.printf(""%d\n"", count) ;
        }
    }
}
