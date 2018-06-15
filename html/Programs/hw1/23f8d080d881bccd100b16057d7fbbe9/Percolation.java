
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Percolation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        StringBuffer sb = new StringBuffer();
        String strNum = new String();
        
    while ((strNum = br.readLine())!=null){
        sb.append(strNum);
        sb.append("";"");
    }
        strNum = sb.toString();
        String data[] = strNum.split("";"");
        
        //for(int i = 0; i<data.length; i++){
        //    System.out.print(data[i]+"" "");
        //}
        int n = Integer.valueOf(data[0]);
        QuickUnionUF uf = new QuickUnionUF(n*n+2);
        int top = 0;
        int botten = n*n+1;
        for(int i = 1; i<=n; i++){
             uf.union(top,i);
        }
        for(int i = n*n; i> n*(n-1); i--){
             uf.union(botten,i);
        }
        
        int flag[][] = new int [n][n];
        for(int i = 0 ; i<flag.length; i++){
            for(int j = 0; j < flag[i].length; j++){
                flag[i][j] = 0;            
            }
        }
        
        int point[] = {0,0};
        for(int i = 1 ; i < data.length ; i++ ){
            point = taker(data[i]);
            flag[point[0]][point[1]] = 1;
            //top
            if(point[1] > 0){
                if(flag[point[0]][point[1]-1] == 1){
                    int p = point[0]*n + point[1] +1;
                    int q = point[0]*n + (point[1]-1) +1;
                    uf.union(p, q);
                
                }
                
                
            }
            //down
            if(point[1] < (n-1)){
            if(flag[point[0]][point[1]+1] == 1){
                    int p = point[0]*n + point[1] +1;
                    int q = point[0]*n + (point[1]+1) +1;
                    uf.union(p, q);
                
                }
            }
            //left
            if(point[0] > 0){
                if(flag[point[0]-1][point[1]] == 1){
                    int p = point[0]*n + point[1] +1;
                    int q = (point[0]-1)*n +point[1] +1;
                    uf.union(p, q);

                    }
            
            }
            
            if(point[0] < (n-1)){
                 if(flag[point[0]+1][point[1]] == 1){
                    int p = point[0]*n + point[1] +1;
                    int q = (point[0]+1)*n +point[1] +1;
                    uf.union(p, q);

                    }
            
            }
            if(uf.connected(top, botten)){
                System.out.println((point[0]+1) + "","" + ((point[1])+1));
                break;
            }            
            
            
        }

        
        
        
        
        
        
    }
    static int[] taker(String s){
        String temp [] = s.split("","");
        int ans[] = new int[2];
        ans[0] = Integer.valueOf(temp[0])-1;
        ans[1] = Integer.valueOf(temp[1])-1;
        return ans;
    }
    
    
}

