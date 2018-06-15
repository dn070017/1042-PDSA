import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author jerry
 */
public class Percolation {
    public static void main(String[] args)throws Exception {

        try{
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String data = br.readLine();//read in first integer
        int N = Integer.parseInt(data);
        int[][] Statearray = new int[N][N]; // using 0 and 1 to indicate location, 0 = close, 1 = open
        UF UF = new UF(N*N+2);//create new UF, 2 digits for top and bottum cluster
        //imagine both connected state in top and bottom row
        int x,x2,y,y2;
        for(String line = br.readLine(); line != null;line = br.readLine()){
        String[] data2 = line.split("","");
        x = Integer.parseInt(data2[0]);
        y = Integer.parseInt(data2[1]);
        x2 = x-1;
        y2 = y-1;//easy for array use
        Statearray[x2][y2] = 1; // open this site
        if(x2 == 0){
            UF.union((x2*N+y2), N*N);// for the top row
            if(y2 == 0){
                if(Statearray[x2][y2]*Statearray[(x2+1)][y2] == 1) UF.union((x2*N+y2), ((x2+1)*N+y2));
                if(Statearray[x2][y2]*Statearray[x2][(y2+1)] == 1) UF.union((x2*N+y2), (x2*N+y2+1));                
            }
            else if(y2 == (N-1)){
                if(Statearray[x2][y2]*Statearray[(x2+1)][y2] == 1) UF.union((x2*N+y2), ((x2+1)*N+y2));              
                if(Statearray[x2][y2]*Statearray[x2][(y2-1)] == 1) UF.union((x2*N+y2), (x2*N+y2-1));                     
            }
            else{
                if(Statearray[x2][y2]*Statearray[(x2+1)][y2] == 1) UF.union((x2*N+y2), ((x2+1)*N+y2));
                if(Statearray[x2][y2]*Statearray[x2][(y2+1)] == 1) UF.union((x2*N+y2), (x2*N+y2+1));                
                if(Statearray[x2][y2]*Statearray[x2][(y2-1)] == 1) UF.union((x2*N+y2), (x2*N+y2-1));               
            }
        }
         else if(x2 == (N-1)){
            UF.union((x2*N+y2), N*N+1);// for the buttom row
            if(y2 == 0){
                if(Statearray[x2][y2]*Statearray[(x2-1)][y2] == 1) UF.union((x2*N+y2), ((x2-1)*N+y2));
                if(Statearray[x2][y2]*Statearray[x2][(y2+1)] == 1) UF.union((x2*N+y2), (x2*N+y2+1));                
            }
            else if(y2 == (N-1)){
                if(Statearray[x2][y2]*Statearray[(x2-1)][y2] == 1) UF.union((x2*N+y2), ((x2-1)*N+y2));              
                if(Statearray[x2][y2]*Statearray[x2][(y2-1)] == 1) UF.union((x2*N+y2), (x2*N+y2-1));                     
            }
            else{
                if(Statearray[x2][y2]*Statearray[(x2-1)][y2] == 1) UF.union((x2*N+y2), ((x2-1)*N+y2));
                if(Statearray[x2][y2]*Statearray[x2][(y2+1)] == 1) UF.union((x2*N+y2), (x2*N+y2+1));                
                if(Statearray[x2][y2]*Statearray[x2][(y2-1)] == 1) UF.union((x2*N+y2), (x2*N+y2-1));               
            }
        }       
         else{
            if(y2 == 0){
                if(Statearray[x2][y2]*Statearray[(x2+1)][y2] == 1) UF.union((x2*N+y2), ((x2+1)*N+y2));
                if(Statearray[x2][y2]*Statearray[(x2-1)][y2] == 1) UF.union((x2*N+y2), ((x2-1)*N+y2));
                if(Statearray[x2][y2]*Statearray[x2][(y2+1)] == 1) UF.union((x2*N+y2), (x2*N+y2+1));                
            }
            else if(y2 == (N-1)){
                if(Statearray[x2][y2]*Statearray[(x2+1)][y2] == 1) UF.union((x2*N+y2), ((x2+1)*N+y2));
                if(Statearray[x2][y2]*Statearray[(x2-1)][y2] == 1) UF.union((x2*N+y2), ((x2-1)*N+y2));
                if(Statearray[x2][y2]*Statearray[x2][(y2-1)] == 1) UF.union((x2*N+y2), (x2*N+y2-1));                
           }
            else{
                if(Statearray[x2][y2]*Statearray[(x2+1)][y2] == 1) UF.union((x2*N+y2), ((x2+1)*N+y2));
                if(Statearray[x2][y2]*Statearray[(x2-1)][y2] == 1) UF.union((x2*N+y2), ((x2-1)*N+y2));
                if(Statearray[x2][y2]*Statearray[x2][(y2+1)] == 1) UF.union((x2*N+y2), (x2*N+y2+1));                
                if(Statearray[x2][y2]*Statearray[x2][(y2-1)] == 1) UF.union((x2*N+y2), (x2*N+y2-1));               
            }         
         }
        if(UF.connected(N*N, (N*N+1))) {
        System.out.print(x+"",""+y);
        break;
        }
        }
        br.close();
        if(UF.connected(N*N, (N*N+1))){}
        else{System.out.print(-1);}
        }
        catch(Exception e){
        System.out.print(-1);
        }
    }
}

