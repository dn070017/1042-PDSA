
public class LabelCC {
    private int[] parent;
    public LabelCC(int N)   //每個仁自己是自己的根
    { parent = new int[N*N];
     for (int i = 0 ; i<N*N ; i++)
         parent[i] = i; }
    
    public int find(int p) //找根
    { while (p != parent[p])
        p = parent[p];
        return p;}
    public boolean connected(int p,int q)  //看是否根相同
    {return find(p)==find(q);}
    
    public void union(int p,int q){  //連結在一起
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)return;
        parent[rootP] = rootQ;}//p接到q上
     
            
    public static void main(String[] args) {
       In in = new In(args[0]);
       String[] firstrow = new String[3];
       String[] secondrow = new String[2];
       firstrow = in.readString().split("","");
       int N = Integer.parseInt(firstrow[0]);
       int a = Integer.parseInt(firstrow[1]);
       int b = Integer.parseInt(firstrow[2]);
       int target = (a-1)*N+b;
       //System.out.println(""N為""+N+"" 座標為""+a+"",""+b);
       //System.out.println(""target為""+target);
       
       int[] blockstring = new int[N*N];
       int i = 0;
       while(!in.isEmpty()){
           secondrow = in.readString().split("","");
           a = Integer.parseInt(secondrow[0]);
           b = Integer.parseInt(secondrow[1]);
           blockstring[i] = (a-1)*N + b;
           //System.out.println(""座標為""+a+"",""+b+""轉成""+blockstring[i]);
           if(target == blockstring[i])  {System.out.println(""0"");  return;}
           i++;   
       } 
       if(blockstring[0]==0){System.out.println(""1"");  return;}//給沒有BLOCK的例外
       //System.out.println(""blockstring.length""+blockstring.length);
       LabelCC labelcc= new LabelCC(N); 
       int[] labelnumber = new int[N*N+1];
       int count = 1;
       //firstpass
       for(i = 1;i<=N*N;i++){
          
          int flag = 0;
          for(int j = 0;blockstring[j]!=0&&j<blockstring.length;j++){
              flag = 1;
              if(i==blockstring[j]){flag = 0; break;}}
          if(flag ==0)continue;
          if(i ==1){labelnumber[i] = count; count++;}
          else if(i <= N){
              if(labelnumber[i-1]!=0 )
                  labelnumber[i] = labelnumber[i-1];
              else {labelnumber[i] = count; count++;}}
          else if (i % N == 1 ){ //最左邊那排
              if(labelnumber[i-N]!=0 )
                  labelnumber[i] = labelnumber[i-N];
              else {labelnumber[i] = count; count++;}}
          
          else{
              if(labelnumber[i-N]!=0 && labelnumber[i-1]==0 )
                  labelnumber[i] = labelnumber[i-N];
              else if(labelnumber[i-N]==0 && labelnumber[i-1]!=0)
                  labelnumber[i] = labelnumber[i-1];
              else if(labelnumber[i-N]==0 && labelnumber[i-1]==0)
                  {labelnumber[i] = count;count++;}
              else//labelnumber[i-N]!=0 && labelnumber[i-1]!=0
                  if(labelnumber[i-N]<labelnumber[i-1]){
                      labelnumber[i] = labelnumber[i-N];
                      labelcc.union(labelnumber[i-1], labelnumber[i-N]);  }//i-1接到i-N上
                  else if(labelnumber[i-N]>labelnumber[i-1]){
                      labelnumber[i] = labelnumber[i-1];
                      labelcc.union(labelnumber[i-N], labelnumber[i-1]);}//i-N接到i-1上
                  else labelnumber[i]= labelnumber[i-1];    
          }
          //System.out.println(""i=""+i+""  labelnumber[i]""+labelnumber[i]);
       }
       
        
     //secondpass  全部改成root的label
       for(i = 1;i<=N*N;i++){
           if (labelnumber[i]==0)  continue;//是block則跳過
           else if(labelcc.find(labelnumber[i])==labelnumber[i]) continue;//自己就是根則跳過
           else //把自己的標籤改成根的
               labelnumber[i] = labelcc.find(labelnumber[i]);
           //System.out.println(""i=""+i+""  labelnumber[i]""+labelnumber[i]);
       }
       System.out.println(labelnumber[target]);
        
        
        
        
        
    }
}

