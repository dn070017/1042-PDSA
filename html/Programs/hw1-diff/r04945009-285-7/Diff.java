

import java.io.BufferedReader;
import java.io.FileReader;


public class Percolation {

     
     
    public static void main(String[] args) throws Exception {

        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0])))
        {
            int lines = 0;
            br.mark(1000);
            while (br.readLine() != null) lines++;
            br.reset();

            String[] data = br.readLine().split("","");
            int N = Integer.parseInt(data[0]);
            //initialization
            int side = N;
            //+2 are for additional top and bottom cells
            UF cellStorage=new UF(N*N+2);      
            int [] states=new int[N*N+2];
            for(int index=0;index<N*N;index++){
                states[index]=0;
            }
            states[N*N]=1;
            states[N*N+1]=1;      
            
            
            

            for (int s=1; s<lines; s++ ){
                
                String[] datatemp = br.readLine().split("","");
                int i = Integer.parseInt(datatemp[0]);
                int j = Integer.parseInt(datatemp[1]);
                
                
                
                   
            int cell=(side*(i-1))+j-1;
            states[cell]=1;
            //if not top row
            if(i!=1 && states[(side*(i-2))+j-1]==1){
                cellStorage.union((side*(i-2))+j-1,cell);
            }else if(i==1){
            //connect to virtual top cell
            cellStorage.union(cell,side*side);
            }
            //if not bottom row
            if(i!=side && states[(side*(i))+j-1]==1){       
                cellStorage.union((side*(i))+j-1,cell);
            }else if (i==side){
            //connect to virtual bottom cell
            cellStorage.union(cell,side*side+1);
            }
            //if not left border
            if(j!=1 && states[(side*(i-1))+j-2]==1){
                cellStorage.union((side*(i-1))+j-2,cell);
            }
            //if not right border
            if(j!=side && states[(side*(i-1))+j]==1){
                cellStorage.union((side*(i-1))+j,cell);
            }
                
                

                
                if (cellStorage.connected(side*side,side*side+1)){
                    System.out.println(i + "","" + j);
                    break;
                }
            }
            if (!cellStorage.connected(side*side,side*side+1))
                System.out.println(-1);
            

        }
            
    }
}

