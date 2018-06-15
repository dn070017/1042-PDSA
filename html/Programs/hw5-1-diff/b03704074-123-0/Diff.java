
import java.awt.Color;


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
        int[] indexold = new int[a.length];
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
        for(h = 0;h<jmax;h++){
            System.out.println(""排序後的k=""+indexold[h]);
        }
        return indexold;
}
    public static void main(String[] args) {
        int N = 10;
        Point2D[] A = new Point2D[N];
        for(int i = 0;i < N ;i++){
            double a = StdRandom.uniform();
            double b = StdRandom.uniform();
            A[i] = new Point2D(a,b);
            
            //StdDraw.filledCircle(A[i].x(),A[i].y(),0.01);
        }
        /*
        double min = A[0].y();
        int flag = 0;
        for(int i = 0;i < N ; i++){
            if(A[i].y()<min){
                min = A[i].y();
                flag = i;
        } }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(A[flag].x(),A[flag].y(),0.01);
        Point2D swap;    //把最小的值存在A[0]
        swap = A[0];
        A[0] = A[flag];
        A[flag] = swap;
        for(int i = 1;i < N;i++){   //i是後面的數  //j是前面的數    //x越大越右邊，所以j的x要大於i的x
            for(int j = i;j >1;j--){
                if(A[j].x()>A[j-1].x()){
                 
                    swap = A[j];
                    A[j] = A[j-1];
                    A[j-1] = swap;}
                if(A[j].x()>=A[0].x() && A[j-1].x()>=A[0].x()){ 
                    if(((A[j-1].y()-A[0].y())/(A[j-1].x()-A[0].x()))>((A[j].y()-A[0].y())/(A[j].x()-A[0].x()))){
                        swap = A[j-1];
                        A[j-1] = A[j];
                        A[j] = swap;} }
                else if(A[j].x()<A[0].x() && A[j-1].x()<A[0].x()){
                    if(((A[j].y()-A[0].y())/(A[j].x()-A[0].x()))<((A[j-1].y()-A[0].y())/(A[j-1].x()-A[0].x()))){
                    swap = A[j-1];
                    A[j-1] = A[j];
                    A[j] = swap;}}}}
        for(int i = 0;i < N;i++){
            String str = String.valueOf(i);
            StdDraw.text(A[i].x(), A[i].y()+0.03,str);}*/
        ConvexHullVertex(A);
        
    
}
}
