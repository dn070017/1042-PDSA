import java.io.FileReader;
import java.io.BufferedReader;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    

    @SuppressWarnings(""empty-statement"")
    public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
         //讀檔，要幾階>order
         String order1=br.readLine();
         int order=Integer.parseInt(order1);
         
         //建立UF物件，名為gg
         WeightedQuickUnionUF gg=new WeightedQuickUnionUF(order*order+2);
         //MARK
         br.mark(870);
         
        //數有幾個座標要開通，一個逗點一個坐標
         int count=0;
         int readd=br.read();
         while(readd!=-1){
            if(readd==44) {
                count++;
            }
            readd=br.read();
         }
        if(count==0)
            System.out.printf(""-1"");
         
         //reset
        br.reset();         
         
         //做字串陣列來存要開通的格子
         String ayaya[][]=new String[count][2];
         for(int i=0;i<count;i++) {
             ayaya[i]=br.readLine().split("","");             
         }
         
         //mark用，UF物件裡存父子關係，opcl裡存是否開通        
         int opcl[]=new int[order*order];
         for(int i=0; i<order*order; i++) opcl[i]=i+1;         
             
         //先將頂排全體相連，底排亦然
         for(int i=0;i<order;i++){
             gg.union(order*order,i);
             gg.union(order*order+1,order*order-1-i);
         }
         
         //開始開通，ayaya[n][0]為y坐標，ayaya[n][1]為x坐標
    
         int y,x,id;
         for(int i=0;i<count;i++){
             
             y=Integer.parseInt(ayaya[i][0]);
             x=Integer.parseInt(ayaya[i][1]);
             //化二維坐標為一維坐標
             id=order*(y-1)+x-1;
             //開通
             opcl[id]=0;
             //檢查四周格子是否為0，有就union
             //先檢查是四周還是三周還是上下排
             //先排除上下排
             if(y!=1&&y!=order){
                 //中心
                 if(x!=1&&x!=order){
                     if(opcl[id+1]==0) gg.union(id, id+1);
                     if(opcl[id-1]==0) gg.union(id, id-1);
                     if(opcl[id+order]==0) gg.union(id, id+order);
                     if(opcl[id-order]==0) gg.union(id, id-order);
                 }
                 //左排
                 else if(x==1){
                     if(opcl[id+1]==0) gg.union(id,id+1);
                     if(opcl[id+order]==0) gg.union(id, id+order);
                     if(opcl[id-order]==0) gg.union(id, id-order);
                 }
                 //右排
                 else if(x==order){
                     if(opcl[id-1]==0) gg.union(id, id-1);
                     if(opcl[id+order]==0) gg.union(id, id+order);
                     if(opcl[id-order]==0) gg.union(id, id-order);
                 }
                 }//再來看下排，再來看上排
             else if (y==order){
                 if(opcl[id-order]==0) gg.union(id, id-order);
             }else if (y==1){
                 if(opcl[id+order]==0) gg.union(id, id+order);
             }            
             
             //上下列恰好連起來了嗎?若有輸出當下座標，若無且i又走到盡頭輸出-1
             if(gg.connected(0,order*order-1)){
                 System.out.printf(""%d,%d"",y,x);
                 break;
             }else if(i==count-1)
                 System.out.printf(""-1"");
         } 
        }
    }
}

