import java.util.*; 
import java.io.FileReader;
import java.io.BufferedReader;
public class Percolation {
    private int[]parent;
    private byte[]rank;
    public Percolation(int N){              //初始化
        if(N<0) throw new IllegalArgumentException();
        parent = new int[N*N+3];
        rank = new byte[N*N+3];
        for(int i=1;i<N*N+3;i++)
        {parent[i]=i;
         rank[i]=0;
        }
    }
    public int find(int p){                 //找根
        while(p!=parent[p]){
                parent[p]=parent[parent[p]];//壓縮
        p=parent[p];
        }
    return p;
    }
    public boolean connected (int p,int q){
        return find(p)==find(q);//看兩個的根有沒有相等
    }
    public void union(int p,int q){        //連接
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)return;
        if(rank[rootP]<rank[rootQ])
            parent[rootP] = rootQ ; //若p的樹比較矮，則p接到q上
        else if (rank[rootP]>rank[rootQ])
            parent[rootQ] = rootP;  //若Q的樹比較矮，則Q接到P上
        else {parent[rootQ] = rootP;//若一樣的高度，則q接到p然後p的高度加一
            rank[rootP]++;
        }
    }
    public static void main(String[] args){
        In in = new In(args[0]);
        int N = in.readInt();
        Percolation percolation= new Percolation(N); 
        //System.out.println(N);
        String[] data = new String[2];
        int[] numstring = new int[N*N+3];
        int i = 0;
        while(!in.isEmpty()){
            data=in.readString().split("","");
            //System.out.println(""座標""+data[0]+"",""+data[1]);
            int a = Integer.parseInt(data[0]); 
            int b = Integer.parseInt(data[1]);
            numstring[i] = (a-1)*N+b;
            //System.out.println(""換算值為""+numstring[i]);
            i++;
        }
        back:{
        for(i =0;i<N*N+3;i++){
          if (numstring[i]<=N &&numstring[i]>0) percolation.union(N*N+1, numstring[i]);//第一排
          if(numstring[i]>N*(N-1) && numstring[i]<=N*N) percolation.union(N*N+2,numstring[i]);//最後一排
          
          for(int j=0;j<i;j++){
          if(!percolation.connected(numstring[i],numstring[j])){
          if(numstring[i]%N==0)  //最右邊
          {if(numstring[j]==numstring[i]-1 ||numstring[j]==numstring[i]+N ||numstring[j]==numstring[i]-N)
                percolation.union(numstring[i], numstring[j]);}
          else if(numstring[i]%N==1)//最左邊
          {if(numstring[j]==numstring[i]+1 ||numstring[j]==numstring[i]+N ||numstring[j]==numstring[i]-N)
                percolation.union(numstring[i], numstring[j]);}
          else if(numstring[j]==numstring[i]+1 ||numstring[j]==numstring[i]-1 || numstring[j]==numstring[i]+N ||numstring[j]==numstring[i]-N)//其他的
              percolation.union(numstring[i], numstring[j]);}
          if(percolation.connected(N*N+1,N*N+2)) 
            {//System.out.println(""找到""+numstring[i]); 
            if(numstring[i]%N==0)System.out.println((numstring[i]/N)+"",""+N);
            else System.out.println((numstring[i]/N+1)+"",""+numstring[i]%N);
            break back ;}
        }
        if (i==N*N-1) System.out.println(""-1"");
        }
        }
        
        
}}
        


