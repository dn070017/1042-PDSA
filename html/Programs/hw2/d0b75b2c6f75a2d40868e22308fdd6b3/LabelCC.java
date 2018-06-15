import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {
    public int[][] root;
    public int parent[];
    public LabelCC(int n){
        root=new int[n][n];
        parent=new int[n*n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                root[i][j]=1;
                parent[i*n+j]=i*n+j;
            }
        }
    }
    
    public void blocked(int x,int y){
        root[x][y]=0;
    }
    public void firstrun(int n){
        int rootput=1;
        boolean stop=false;
        for(int j=0;j<n;j++){
            if(root[0][j]!=0){
                root[0][j]=rootput;
                stop=true;
            }        
            else{
                if(stop){
                    rootput++;
                    stop=false;
                }
            }   
        }    
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                if(root[i][j]!=0){
                    if(j==0 || root[i][j-1]==0){
                        if(root[i-1][j]!=0){
                            root[i][j]=root[i-1][j];
                        }
                        else{
                            rootput++;
                            root[i][j]=rootput;
                        }
                    }
                    else{
                        root[i][j]=root[i][j-1];
                        if(root[i-1][j]!=0 & root[i-1][j]!=root[i][j-1]){
                            if(root[i-1][j]<root[i][j-1])
                                parent[root[i][j-1]]=root[i-1][j];
                            else
                                parent[root[i-1][j]]=root[i][j-1];
                        }
                    }
                     
                }
            }
        }
    }
    public int find(int x){
        while(x!=parent[x]){
            x=parent[parent[x]];
        }
        return x;
    }
    public void show(int n,int p,int q){
        if(n==1){
            StdOut.println(root[0][0]);
            return;
        }
       StdOut.println(find(root[p][q]));
    }
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String data = br.readLine();
            String datacut[];
            datacut = data.split("","");
            int N = Integer.parseInt(datacut[0]);  
            int fp = Integer.parseInt(datacut[1]);
            int fq = Integer.parseInt(datacut[2]);
            int p;
            int q;
            LabelCC labelCC = new LabelCC(N);
            
            while((data = br.readLine()) != null){
                datacut=data.split("","");
                p=Integer.parseInt(datacut[0]);
                q=Integer.parseInt(datacut[1]);
                labelCC.blocked(p-1,q-1);
            }
            labelCC.firstrun(N);
            labelCC.show(N,fp-1,fq-1);
        }        
    }        
}                


