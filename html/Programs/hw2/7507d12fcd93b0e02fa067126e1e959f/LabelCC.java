
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class LabelCC {
public static int n;
public static int count=1;
public static int[] label;
public static int[] root;
public static boolean[][] matrix;

    public static int xyTo1D(int i, int j) {
        return i*n+j;
    }

    public static int findroot( int i) {
        while(i!=root[i]){    
            root[i]=root[root[i]];
            i=root[i];
        }
        return i;
    }     

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data = br.readLine().split("","");

            n = Integer.parseInt(data[0]);
            int x = Integer.parseInt(data[1]);
            int y = Integer.parseInt(data[2]);
            label = new int[n*n];
            root = new int[n*n];
            for (int i = 0; i < n*n; i++){
                root[i]=i;
            }
            
            matrix = new boolean[n][n];
            for (int i = 0; i < n*n; i++){                
                String da = br.readLine();
                if(da==null){break;}
                String[] d = da.split("","");
                int p = Integer.parseInt(d[0]);
                int q = Integer.parseInt(d[1]);
                matrix[p-1][q-1]=true;}

            //srart
            for (int r = 0;  r < 1; r++){
                for (int c = 0;  c < n; c++){
                    if(matrix[r][c]==false){
                        
                    if(c>0 && matrix[r][c-1]==false)    
                    root[xyTo1D(r,c)]=findroot(xyTo1D(r,c-1)); 
                else {
                    label[xyTo1D(r,c)]=count;
                    count++;
                     }                
                } }  }            
            
            for (int r = 1;  r < n; r++){
                for (int c = 0;  c < n; c++){
                    if(matrix[r][c]==false){
                        //
                        if(c==0){
                            if(matrix[r-1][c]==false)
                                root[xyTo1D(r,c)]=findroot(xyTo1D(r-1,c));                           
                            else {
                                label[xyTo1D(r,c)]=count;
                                count++;
                            }
                        }                                
                        //
                        
                        else if( matrix[r][c-1]==false ){
                            if( matrix[r-1][c]==false){
                            int rootL = findroot(xyTo1D(r,c-1));                            
                            int rootT = findroot(xyTo1D(r-1,c));
                            int labelL = label[rootL];
                            int labelT = label[rootT];                           
                            if(labelL<labelT){
                                root[rootT]=rootL;
                                root[xyTo1D(r,c)]=rootL;                        
                            }
                            else if(labelL>labelT){
                                root[rootL]=rootT;
                                root[xyTo1D(r,c)]=rootT;                          
                            }
                            else{
                                root[xyTo1D(r,c)]=rootT;                            
                            }
                            }
                            else{root[xyTo1D(r,c)]=findroot(xyTo1D(r,c-1));}
                        }
                        //
                        else if(matrix[r-1][c]==false){
                                root[xyTo1D(r,c)]=findroot(xyTo1D(r-1,c));  }
                        else{                      
                            label[xyTo1D(r,c)]=count;
                            count++;
                            }
                        }
                    }
                }                  
            System.out.println(label[findroot(xyTo1D(x-1,y-1))]); 
        }            
    }
}

