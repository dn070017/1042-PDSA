
import java.io.FileReader;
import java.util.Arrays;

public class MyConvexHull {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        //StdDraw.setPenColor(Color.red);
        //StdDraw.setPenRadius(0.00187);
        
        double d=Double.parseDouble(br.readLine());
        int N=Integer.parseInt(br.readLine());
        
        Point2D[] a=new Point2D[N];
        for(int i=0;i<N;i++){
            String readd[]=br.readLine().split("" "");
            a[i]=new Point2D(Double.parseDouble(readd[0]),Double.parseDouble(readd[1]));
        }
        
        //先做connected component，編號和讀檔進來的順序相同
        QuickUnionUF gg=new QuickUnionUF(N);
        //暴力法，N^2感覺很爛>>現在N^2/2應該好一點??
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(a[i].distanceTo(a[j])<=d){
                    gg.union(i,j);
                }
            }
        }
        
        //有幾個label
        int count=gg.count();
        
        //做成Point2D Stack
        Stack group[]=new Stack[count];
        for(int i=0;i<count;i++){
            group[i]=new <Point2D>Stack();            
        }
        
        //labelG[]為每個label的值
        int label[]=new int[a.length];
        //直接弄a個labelG
        int labelG[]=new int[a.length];
        for(int i=0;i<a.length;i++){
                labelG[i]=0;
        }
        
        //找到每個a[i]的label 
        for(int i=0;i<a.length;i++){
            label[i]=gg.find(i);
            //a[i] label多少，給該labelG++ //我猜label=0會有bug
            for(int j=0;j<a.length;j++){
                if(gg.find(i)==j) labelG[j]++;
            }
        }
            
        //  System.out.println(""GG3be0"");  
        
        //把label一樣的存到同一個Stack，同一個labelG一次會掃完
        int k=0;
        for(int i=0;i<a.length;i++){           
            if(labelG[i]!=0){
                for(int j=0; j<a.length; j++){
                    if(label[j]==i)
                    group[k].push(a[j]);
                }
                k++;
            }            
        }
        
        //逐一檢視每個group，若N=1,2就不管了，N=3直接+3，N>3.5則傳到CVH，answer是答案
        int answer=0;
        for(int i=0; i<group.length; i++){
            if((group[i].size()==1)||(group[i].size()==2))
                ;
            else if(group[i].size()==3)
                answer+=3;
            else if(group[i].size()>3){//將該group一一pop出來
                Point2D[] send=new Point2D[group[i].size()];
                k=0;
                while(!group[i].isEmpty()){
                    send[k]=(Point2D) group[i].pop();
                    k++;
                }
                answer+=ConvexHullVertex(send);       
            }
        }
        
        System.out.println(answer);
        }
    }
    //第一題是int[]
     public static int ConvexHullVertex(Point2D[] a) {

        Point2D[] b=new Point2D[a.length];
        Point2D ymin=a[0];
        for(int i=0;i<a.length;i++){//弄一個b，不然a會變成錯誤的形狀
            b[i]=new Point2D(a[i].x(),a[i].y());
            if(b[i].compareTo(ymin)==-1) ymin=b[i];//找到y最小的點
        }
        Arrays.sort(b,ymin.ATAN2_ORDER);//把b變成atan的形狀
        for (int i = 0; i < a.length-1; i++) {
           //b[i].drawTo(b[i+1]);
          // StdDraw.show(187);
        }
        
        Stack <Point2D>vertex=new Stack();//vertex stack
        vertex.push(b[0]);
        vertex.push(b[1]);
        int count=2;//前兩點一定是vertex
      
        for(int i=2;i<b.length;i++){        
            Point2D x=vertex.pop();
            Point2D y=vertex.pop();
            while(Point2D.ccw(y,x,b[i])!=1){
                x=y;
                y=vertex.pop();
                count--;
            }
            vertex.push(y);
            vertex.push(x);
            vertex.push(b[i]);
            count++;
            }        
            return count;
/*下面是第一題才要用到的
        int index[]=new int [count];
        for(int i=0;i<count;i++){
            Point2D temp=vertex.pop();
            for(int j=0;j<a.length;j++){
                if(temp.equals(a[j])){
                    index[i]=j;
                    //System.out.println(index[i]);
                }
            }
        }
        Arrays.sort(index);
        
        // 回傳ConvexHullVertex的index set，index 請依該點在a陣列中的順序編號：0, 1, 2, 3, 4, ....a.length-1
        
        return index;
*/
    }

}
