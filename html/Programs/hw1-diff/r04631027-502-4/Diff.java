

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author YuChing
 */
public class Percolation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine();
            int dimention = Integer.parseInt(line);
            int[][] metrix;
            metrix = new int[dimention][dimention];
            int[][] ver;
            ver = new int[dimention - 1][dimention];
            int[][] hor;
            hor = new int[dimention][dimention - 1];
            int[] union;
            union = new int[dimention-1];
            int[] oh;
            oh = new int[dimention-1];
            int[] connect;
            connect = new int[dimention-1];
            int sum =0;
            int finalc=0;
            int ok=0;
            
            while (/*(line = br.readLine())!=null*/ok!=100) {
                finalc=0;
                line = br.readLine();
                String[] buffer = line.split("","");
                int x = Integer.parseInt(buffer[0]);
                int y = Integer.parseInt(buffer[1]);
                metrix[x - 1][y - 1] = 1;
                
                
                //垂直向上
                if (x != 1 && metrix[x - 2][y - 1] > 0) {
                    ver[x - 2][y - 1] = 1;
                    if(union[x-2]==0)
                    {union[x-2]=1;
                    sum++;
                    }
                    if(oh[x-2]==0)
                        oh[x-2]=y-1;
                }
                //垂直向下
                if (x != dimention && metrix[x][y - 1] > 0) {
                    ver[x - 1][y - 1] = 1;
                    if(union[x-1]==0)
                    {union[x-1]=1;
                    sum++;
                    }
                    if(oh[x-1]==0)
                        oh[x-1]=y-1;
                }
                //left
                if (y != 1 && metrix[x - 1][y - 2] > 0) {
                    hor[x - 1][y - 2] = 1;
                }
                //right
                if (y != dimention && metrix[x - 1][y] > 0) {
                    hor[x - 1][y - 1] = 1;
                }
                
                for (int i = 0; i < (union.length) - 1; i++) {
                    if (union[i] == union[i + 1]) {
                        int cross = oh[i] - oh[i + 1];
                        
                        if (cross > 0) {
                            int count = 0;
                            for (int j = cross; j > 0; j--) {
                                if (hor[i + 1][oh[i]-j] == 1) {
                                    count++;
                                }
                            }
                            if(count==cross)
                            { connect[i]=1;
                            if(i + 1==(union.length)-1)
                                connect[i+1]=1;
                            }
                        }
                         if (cross <0) {
                             int count = 0;
                            for (int j = 0; j < -cross; j++) {
                                if (hor[i + 1][oh[i]+j] == 1) {
                                    count++;
                                }
                            }
                            if(count==(-cross))
                            {connect[i]=1;
                            if(i + 1==(union.length)-1)
                                connect[i+1]=1;
                            }
                        }
                    }
                }
               
                for(int i=0;i<connect.length;i++)
                {finalc+=connect[i];}
                if(finalc==dimention-1)
                    ok=100;
                //System.out.print(finalc);
            }
            
            //print 
//            for (int i = 0; i < dimention; i++) {
//                for (int j = 0; j < dimention - 1; j++) {
//                    System.out.print(hor[i][j] + "" "");
//                }
//                System.out.println("" "");
//            }
//            for (int i = 0; i < dimention - 1; i++) {
//                for (int j = 0; j < dimention; j++) {
//                    System.out.print(ver[i][j] + "" "");
//                }
//                System.out.println("" "");
//            }
//            for (int j = 0; j < dimention-1; j++) {
//                    System.out.println(""oh""+connect[j]);
//                }
            System.out.println(line);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

