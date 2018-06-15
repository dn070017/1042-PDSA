import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

/**
 *
 * @author Po-Lin
 */
public class Percolation {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            BufferedReader temp = new BufferedReader(new FileReader(args[0]));
            // read a line and split by ','
            String[] data;
            if(temp.readLine()!=null){
              
            data=br.readLine().split("","");
            
            
            
            // store the integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[0]);
            int i,j,k;            
            int[][] connectarray=new int[num][num];
            int connectnum=1;
            for(i=0;i<num;i++)  //將座標與數字相結合
                for(j=0;j<num;j++)
                {
                    connectarray[i][j]=connectnum;
                    connectnum++;
                }

            int[][] checkarray = new int[num][num];
         
            
            WeightedQuickUnionUF ufstruct= new WeightedQuickUnionUF(num*num+2); 
          


            int tempcoord[]=new int[2];
            int xa,xd,ya,yd;
            int checkpoint=0;
            while(temp.readLine()!=null) 
            {
                data = br.readLine().split("","");
                tempcoord[0]=Integer.parseInt(data[0]);
                tempcoord[1]=Integer.parseInt(data[1]);
                checkarray[tempcoord[0]-1][tempcoord[1]-1]=1;
                xa=tempcoord[0]-1+1;
                xd=tempcoord[0]-1-1;
                ya=tempcoord[1]-1+1;    
                yd=tempcoord[1]-1-1;
                if(xa>=0 && xa<num)
                    if(checkarray[xa][tempcoord[1]-1]==1)
                        ufstruct.union(connectarray[xa][tempcoord[1]-1], connectarray[tempcoord[0]-1][tempcoord[1]-1]);
                if(xd>=0 && xd<num)
                    if(checkarray[xd][tempcoord[1]-1]==1)
                        ufstruct.union(connectarray[xd][tempcoord[1]-1], connectarray[tempcoord[0]-1][tempcoord[1]-1]);
                if(ya>=0 && ya<num)
                    if(checkarray[tempcoord[0]-1][ya]==1)
                        ufstruct.union(connectarray[tempcoord[0]-1][ya], connectarray[tempcoord[0]-1][tempcoord[1]-1]);
                if(yd>=0 && yd<num)
                    if(checkarray[tempcoord[0]-1][yd]==1)
                        ufstruct.union(connectarray[tempcoord[0]-1][yd], connectarray[tempcoord[0]-1][tempcoord[1]-1]);
                if(connectarray[tempcoord[0]-1][tempcoord[1]-1]<=num)
                    ufstruct.union(connectarray[tempcoord[0]-1][tempcoord[1]-1],0);
                else if(connectarray[tempcoord[0]-1][tempcoord[1]-1]>num*num-num)
                    ufstruct.union(connectarray[tempcoord[0]-1][tempcoord[1]-1],num*num+1);
                
                if(ufstruct.connected(0, num*num+1)==true)
                {
                    System.out.println(tempcoord[0]+"",""+tempcoord[1]);
                    checkpoint=1;
                    break;
                }
            }
            if(checkpoint==0)
                System.out.println(-1);
                            
          //  data = br.readLine().split("",""); //read second line{
        // TODO code application logic here
     }
        }
    }
    
}

