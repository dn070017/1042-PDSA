
import java.io.BufferedReader;
import java.io.FileReader;


public class LabelCC {
    public static int xyTo1D(int i, int j, int n) {
        return i*n+j;
    }
    public static int findroot(int[] label, int[] root, int i) {
        int l=label[i];
        int z=0;
        int t=3;
        int[] s = new int[t];
        while(l==0){
            if(z==t){
                t=t*2;
                int[] s1=new int[t];
                for(int j = 0; j < z ; j++){
                    s1[j]=s[j];
                }      
                s=s1;
            }
            s[z]=i;            
            i=root[i];
            l=label[i];
            z++;
        }
        for(int j = 0; j < z ; j++){    root[s[j]]=i; 
        }
        
        return i;
    }     
    public static int findlab(int[] label, int[] root, int i) {
        int l=label[i];
        while(l==0){
            i=root[i];
            l=label[i];
        }
        return l;
    } 
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data = br.readLine().split("","");

            int n = Integer.parseInt(data[0]);
            int x = Integer.parseInt(data[1]);
            int y = Integer.parseInt(data[2]);
            boolean[][] matrix = new boolean[n][n];
            for (int i = 0; i < n*n; i++){                
                String da = br.readLine();
                if(da==null){break;}
                String[] d = da.split("","");
                int p = Integer.parseInt(d[0]);
                int q = Integer.parseInt(d[1]);
                if (p <= 0 || q <= 0 || p > n || q > n) {
                }
                else {
                matrix[p-1][q-1]=true;}
                }

            int count=1;
            int[] label = new int[n*n];
            int[] root = new int[n*n];
            for (int i = 0; i < n*n; i++) root[i]=i;
            
            //srart
            for (int r = 0;  r < n; r++){
                for (int c = 0;  c < n; c++){
                    if(matrix[r][c]==false){
                        //
                        if(r==0){
                            if(c>0 && matrix[r][c-1]==false)
                                root[xyTo1D(r,c,n)]=findroot(label,root,xyTo1D(r,c-1,n));                           
                            else {
                                label[xyTo1D(r,c,n)]=count;
                                count++;
                            }
                        }
                        //
                        else if(c==0){
                            if(matrix[r-1][c]==false)
                                root[xyTo1D(r,c,n)]=findroot(label,root,xyTo1D(r-1,c,n));                           
                            else {
                                label[xyTo1D(r,c,n)]=count;
                                count++;
                            }
                        }                                
                        //
                        else if( matrix[r][c-1]==false && matrix[r-1][c]==false){

                            int rootL = findroot(label,root,xyTo1D(r,c-1,n));                            
                            int rootT = findroot(label,root,xyTo1D(r-1,c,n));
                            int labelL = label[rootL];
                            int labelT = label[rootT];                           
                            if(labelL<labelT){
                                label[rootT]=0;
                                root[rootT]=rootL;
                                root[xyTo1D(r,c,n)]=rootL;                        
                            }
                            else if(labelL>labelT){
                                label[rootL]=0;
                                root[rootL]=rootT;
                                root[xyTo1D(r,c,n)]=rootT;                          
                            }
                            else{
                                root[xyTo1D(r,c,n)]=findroot(label,root,xyTo1D(r,c-1,n));                            
                            }
                        }
                        //
                        else{
                            if(matrix[r][c-1]==false){
                                root[xyTo1D(r,c,n)]=findroot(label,root,xyTo1D(r,c-1,n));} 
                            else if(matrix[r-1][c]==false){
                                root[xyTo1D(r,c,n)]=findroot(label,root,xyTo1D(r-1,c,n));  }
                            else{                      
                            label[xyTo1D(r,c,n)]=count;
                            count++;
                            }
                        }
                    }

                }
            }                  
            StdOut.println(findlab(label,root,xyTo1D(x-1,y-1,n))); 
        }            
    }
}

