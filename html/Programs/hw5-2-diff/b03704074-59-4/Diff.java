
import java.awt.Color;
import java.io.FileReader;
import java.io.BufferedReader;

public class MyConvexHull {

    public static int[] ConvexHullVertex(Point2D[] a) {
        
        Point2D[] OLD = new Point2D[a.length];//留住舊的編號
        for(int i = 0;i<a.length;i++){
        OLD[i] = a[i];}
        double min = a[0].y();
        int flag = 0;
        for(int i =0;i<a.length;i++){
            if(a[i].y()<min){
                min = a[i].y();
                flag = i; }}
        Point2D swap;
        swap = a[0];
        a[0] = a[flag];
        a[flag] = swap;
        for(int i = 1;i<a.length;i++){//i是後面的數  //j是前面的數    //x越大越右邊，所以j的x要大於i的x
            for(int j = i;j >1;j--){
                if(a[j].x()>a[j-1].x()){
                 
                    swap = a[j];
                    a[j] = a[j-1];
                    a[j-1] = swap;}
                if(a[j].x()>=a[0].x() && a[j-1].x()>=a[0].x()){ 
                    if(((a[j-1].y()-a[0].y())/(a[j-1].x()-a[0].x()))>((a[j].y()-a[0].y())/(a[j].x()-a[0].x()))){
                        swap = a[j-1];
                        a[j-1] = a[j];
                        a[j] = swap;} }
                else if(a[j].x()<a[0].x() && a[j-1].x()<a[0].x()){
                    if(((a[j].y()-a[0].y())/(a[j].x()-a[0].x()))<((a[j-1].y()-a[0].y())/(a[j-1].x()-a[0].x()))){
                    swap = a[j-1];
                    a[j-1] = a[j];
                    a[j] = swap;}}}}
        int first = 0;
        int middle = 1;
        int last = 2;
        while(last<a.length){
            //System.out.println(""first=""+first+""middle=""+middle+""last=""+last);
            if(((a[middle].x()-a[first].x())*(a[last].y()-a[first].y())-(a[middle].y()-a[first].y())*(a[last].x()-a[first].x()))>0){
                first = middle;
                middle = last;
                while(true){
                    if(last+1==a.length){last++;break;}
                    else if(a[last+1]!=null){last++;break;}
                    else//a[++last]==null
                    {if(last+2==a.length){last = last+2;break;}
                    else{last = last+2;
                         if(a[last]!=null)break;}}}}
            else {
                a[middle] = null;
                middle = first;
                //last remain the same
                while(true){
                    if(a[first-1]!=null){first--;break;}//first往前一個，最多到零因為A[0]不會是空的
                    else//a[--first]==null
                    {first = first-2;
                    if(a[first]!=null)break;}}}}
        int[] indexnew = new int[a.length];
        int j = 0;
        for(int i = 0;i<a.length;i++){
            if(a[i]!=null){
                indexnew[j] = i;//System.out.println(""i=""+i);
                j++; }}
        int jmax = j;
        int[] indexold = new int[jmax];
        int h =0;
        for(j = 0;j < jmax;j++){
            for(int k = 0;k<a.length;k++){
                if(a[indexnew[j]]==OLD[k]){
                    indexold[h] = k;h++;}}}
        for(int i = 1;i<jmax;i++){
            for(j = i;j>0;j--){
                if(indexold[j]<indexold[j-1]){
                    int swap2;
                    swap2 = indexold[j];
                    indexold[j] = indexold[j-1];
                    indexold[j-1] = swap2;}}}
        StdRandom.setSeed(3);
        /*for(h = 0;h<indexold.length;h++){
            System.out.println(indexold[h]);
        }*/
        
        return indexold;
}
    public static void main(String[] args)throws Exception {
        /*FileReader FileStream = new FileReader(""data.in.txt"");
        BufferedReader br = new BufferedReader(FileStream);*/
        In br = new In(args[0]);
        String data = br.readLine();//讀取第一行
        float d = Float.parseFloat(data);//第一行轉成d
        //System.out.println(""d=""+d);
        data = br.readLine();//讀取第二行
        int N = Integer.parseInt(data);//第二行轉成N(個數)
        //System.out.println(""N=""+N);
        Point2D[] A = new Point2D[N];
        for(int i = 0;i < N;i++){
            String[] datas = br.readLine().split("" "");
            
            float a = Float.parseFloat(datas[0]);
            float b = Float.parseFloat(datas[1]);
            //System.out.println(""a = ""+a+""   b = ""+b);
            A[i] = new Point2D(a,b);}
        int count = 0;
        back:
        while(true){
            Point2D[] B = new Point2D[N];//每次重新計算都開一個新的point
            int h = 1;//h是B的位置
            for(int i = 0;i < N;i++){ // 找出新的CC的第一個值
                if(A[i]!=null){
                    B[0] = A[i];//System.out.println(""B[0]=""+B[0]);
                    break;}
                else if (i == N-1 && A[i] == null)break back;}
            for(int i = 0;i < N;i++){
                if(A[i] == null)continue;
                for(int j = 0;j < B.length;j++){
                    if(B[j] == null)break;
                    //System.out.println(""d=""+d*d);
                    //System.out.println(Math.pow((B[j].x()-A[i].x()),2)+Math.pow((B[j].y()-A[i].y()), 2));
                    if(Math.pow((B[j].x()-A[i].x()),2)+Math.pow((B[j].y()-A[i].y()), 2)==0)break;
                    else if(Math.pow((B[j].x()-A[i].x()),2)+Math.pow((B[j].y()-A[i].y()), 2)<=d*d){
                        B[h] = A[i];//System.out.println(""i =""+i+""j =""+j+""h = ""+h);
                        h++;break;}}}
            int lengthmax = B.length;
            for(int i = 0;i < B.length;i++){
                //System.out.println(""B[""+i+""]=""+B[i]);
                if(B[i]==null){
                    lengthmax = i;break;}}
            Point2D[] C = new Point2D[lengthmax];
            for(int i = 0;i < lengthmax;i++){
                C[i] = B[i];
            }
            for(int i = 0;i < C.length;i++){
                for(int j = 0;j < N;j++){
                    if(C[i] == A[j]){
                        A[j] = null;break;}}}
            int[] plus = ConvexHullVertex(C);
            //System.out.println(""pluslength=""+plus.length);
            if(plus.length>=3){
            count = count + plus.length;
            //System.out.println(""count=""+count);
            }}
        System.out.println(count);
}
}
