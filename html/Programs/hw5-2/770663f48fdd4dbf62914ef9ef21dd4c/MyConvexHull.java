
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
        FileReader FileStream = new FileReader(""data.in"");
        BufferedReader br = new BufferedReader(FileStream);
        String data = br.readLine();//讀取第一行
        double d = Double.parseDouble(data);//第一行轉成d
        data = br.readLine();//讀取第二行
        int N = Integer.parseInt(data);//第二行轉成N(個數)
        Point2D[] A = new Point2D[N];
        for(int i = 0;i < N;i++){
            String datas[] = br.readLine().split("","");
            double a = Double.parseDouble(datas[0]);
            double b = Double.parseDouble(datas[1]);
            A[i] = new Point2D(a,b);}
        int count = 0;
        back:
        while(true){
            Point2D[] B = new Point2D[N];//每次重新計算都開一個新的point
            int h = 1;//h是B的位置
            for(int i = 0;i < N;i++){ // 找出新的CC的第一個值
                if(A[i]!=null){
                    B[0] = A[i];break;}
                else if (i == N-1 && A[i] == null)break back;}
            for(int i = 1;i < N;i++){
                if(A[i] == null)continue;
                for(int j = i;j > 0;j--){
                    if(A[j] == null)continue;
                    int k = j - 1;
                    while(A[k] == null){
                        k--;}
                    if(Math.pow((A[j].x()-A[k].x()),2)+Math.pow((A[j].y()-A[k].y()), 2)<=Math.pow(d, 2)){
                        B[h] = A[j];h++;}}}
            for(int i = 0;i < B.length;i++){
                for(int j = 0;j < N;j++){
                    if(B[i] == A[j]){
                        A[j] = null;break;}}}
            int[] plus = ConvexHullVertex(B);
            count = count + plus.length;System.out.println(count);}
        System.out.println(count);
}
}
