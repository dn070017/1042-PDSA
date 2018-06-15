import java.io.FileReader;
import java.io.BufferedReader;


public class LabelCC {
    public static void main(String[] args) throws Exception {
           // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            //IC for initial condition which is given by the first row
            String IC[]=new String[3];
            IC=br.readLine().split("","");
            int order=Integer.parseInt(IC[0]);
            //matrix called clo for blocked sites
            String clo[][]=new String[order*order][2];            
            
            //N-N Grid called opcl，多創一圈待會好處理
            int opcl[][]=new int[order+2][order+2];            
            for(int i=1;i<=order;i++){
                for(int j=1;j<=order;j++){
                    opcl[i][j]=-1;
                }
            }
            
            //New UF object
            UF gg=new UF(order*order);
            
            //read the assigned blocked sites
            String[] readd;
            int a=0;
            while(br.ready()){
                readd=br.readLine().split("","");
                clo[a][0]=readd[0];
                clo[a][1]=readd[1];
                a++;
            }
            //共有a組座標<我剛剛發現的，不設count就是爽
            
            //block the assigned blocked sites
           for(int i=0;i<a;i++){
               int row=Integer.parseInt(clo[i][0]);
               int column=Integer.parseInt(clo[i][1]);
               opcl[row][column]=0;
           }
                              
.
           int label=1;
               //Step 1
           for(int row=1;row<=order;row++){
               for(int column=1;column<=order;column++){
                   //if blocked, don't do anything
                   if(opcl[row][column]==0)
                       ;
                   //if only up is labeled
                   else if((opcl[row-1][column]!=0)&&(opcl[row][column-1]==0)){
                       opcl[row][column]=opcl[row-1][column];
                       gg.union((row-2)*order+column-1,(row-1)*order+column-1);
                   }
                   //if only left is labeled
                   else if((opcl[row][column-1]!=0)&&(opcl[row-1][column]==0)){
                       opcl[row][column]=opcl[row][column-1];
                       gg.union((row-1)*order+column-2,(row-1)*order+column-1);                      
                   }
                   //if both up and left are labeled
                   else if((opcl[row][column-1]!=0)&&(opcl[row-1][column]!=0)){
                       opcl[row][column]=opcl[row][column-1];
                       //檢查左跟上的root，取小的自己連過去，把另一個也連過去<<其實可以不用，反正下面還要再喬一次
                       //左小
                            if(gg.find((row-1)*order+column-2)<=(gg.find((row-2)*order+column-1))){
                                 gg.union((row-1)*order+column-2,(row-1)*order+column-1);  
                                 gg.union((row-1)*order+column-1,(row-2)*order+column-1);
                            }
                       //上小
                            else if(gg.find((row-1)*order+column-2)>(gg.find((row-2)*order+column-1))){
                                 gg.union((row-2)*order+column-1,(row-1)*order+column-1);  
                                 gg.union((row-1)*order+column-1,(row-1)*order+column-2);
                            }
                       }     
                   //if neither up nor left is labeled
                   else if((opcl[row][column-1]==0)&&(opcl[row-1][column]==0)){         
                       opcl[row][column]=label;                       
                       label++;                       
                   }
               }
           }
                //Step 2; 自己非黑塊，與其他open block union時，若label不同，取小的
                for(int row=1;row<=order;row++){
                    for(int column=1;column<=order;column++){
                        
                        if(opcl[row][column]!=0){
                            
                            for(int i=row;i>=1;i--){
                                for(int j=order;j>=1;j--){
                                    int id=(i-1)*order+j-1;
                                    if(gg.connected((row-1)*order+column-1,id)){                                    
                                        if(opcl[row][column]<opcl[i][j]){
                                        int x=opcl[row][column];
                                        opcl[i][j]=x;                                        
                                        }
                                        else if(opcl[row][column]>opcl[i][j]){
                                        int x=opcl[i][j];
                                        opcl[row][column]=x;                                        
                                        }
                                }
                            }
                            }
                        }
                   }
                 }
        System.out.println(opcl[Integer.parseInt(IC[1])][Integer.parseInt(IC[2])]);
        }
    }

    private static class UF {//UF.union(X,Y)會把Y連給X

        private int[] parent;   // parent[i] = parent of i
        private int[] size;     // size[i] = number of sites in subtree rooted at i
        private int count;      // number of components
        
        public UF(int x) {//constructor
            count=x;
            parent=new int[x];
            size=new int[x];
            for(int i=0;i<count;i++){
                parent[i]=i;
                size[i]=1;
            }           
        }
        
        public int count(){
            return count;
        }
        
        public int find(int p) {
            while (p != parent[p])
                p = parent[p];
            return p;
        }
        
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            
        // make smaller root point to larger one
        if (rootP < rootQ) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
        }
    }
    }
    
