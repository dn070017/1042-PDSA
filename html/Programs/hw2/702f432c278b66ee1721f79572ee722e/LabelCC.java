
import java.io.FileReader;
import java.io.BufferedReader;
        

public class LabelCC {
    
  /*  public void union(int q, int p){
        
        
    }*/
    
    public static void main(String[] args) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0])) ){
            
            String[] N = br.readLine().split("","");
            
            int n = Integer.parseInt(N[0]);
           // System.out.print(n);
            int a = Integer.parseInt(N[1]);
            int b = Integer.parseInt(N[2]);
            //System.out.print(a);
            //System.out.print(b);
            int[][] label = new int[n+1][n+1];
            int[][] open = new int[n+1][n+1];
            
            int x;
            int y;
            
            for(x = 1;x<n+1;x++){
                for(y = 1;y<n+1;y++){
                    open[x][y] = 1;
                }
            }
            
            UF uf = new UF(n*n);
            
            String buffer;
            String gg = """";
            int i = 0;
            
            buffer = br.readLine();
            while(buffer != null){
                gg = gg.concat(buffer);
                gg = gg.concat("","");
                i++;
                buffer = br.readLine();
            }
            String[] lgg = gg.split("","");
            int[] data = new int[2];
            for(int j = 0;j<2*i;j+=2){
                data[0] = Integer.parseInt(lgg[j]);
                data[1] = Integer.parseInt(lgg[j+1]);
                //System.out.print(lgg[j]);
                //System.out.print(lgg[j+1]);
                open[data[0]][data[1]] = 0;                
            }
            int[] root = new int[n*n+1];
            for(x = 0; x<n*n+1; x++){
                root[x] = x;
            }
            int count = 1;
            for(x = 1;x<n+1 ;x++){
                for(y = 1;y<n+1;y++){
                if(open[x][y] == 1){
                    if(open[x-1][y] == 1 && open[x][y-1] == 1 && label[x][y-1] != label[x-1][y] ){
                        if(label[x-1][y] < label[x][y-1]){
                        label[x][y] = label[x-1][y];
                        //System.out.print(label[x][y]);
                        root[label[x][y-1]] = label[x-1][y];
                        
                        }
                        else{
                             label[x][y] = label[x][y-1];
                             //System.out.print(label[x][y]);
                             root[label[x-1][y]] = label[x][y-1];
                            
                        }
                    }
                    else if(open[x-1][y] == 1){
                        label[x][y] = label[x-1][y];
                        //System.out.print(label[x][y]);
                       
                    }
                    else if(open[x][y-1] == 1){
                        label[x][y] = label[x][y-1];
                       // System.out.print(label[x][y]);                       
                    }
                    else {
                        label[x][y] = count;
                        count++;
                       // System.out.print(label[x][y]);                        
                    }
                }    
                }
            }            
        for(x=1;x<n+1;x++){
            for(y = 1;y<n+1;y++){
                while(root[label[x][y]] != label[x][y]){
                    label[x][y] = root[label[x][y]];
                }
                //System.out.print(label[x][y]);
            }
        }
        System.out.print(label[a][b]);
            
        }
    }
    
    
    
    
    
    
    
}

