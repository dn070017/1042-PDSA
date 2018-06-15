import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    private final int num;
    private QuickUnionUF uf;
    private int[] flag;

    public LabelCC(int num){
        this.num = num;
        flag = new int[num * num];
    }

    public int getPos(int x, int y){
        x--;
        y--;
        if(x < 0 || y < 0) return -1;
        if(x > num || y > num) return -1;
        return flag[x * num + y];
    }

    public void setPos(int x, int y, int val){
        x--;
        y--;
        if(x < 0 || y < 0) return;
        if(x > num || y > num) return;
        flag[x * num + y] = val;
    }

    public void initUF(int group){
        uf = new QuickUnionUF(group);
    }

    public void union(int a, int b){
        a--;
        b--;
        if(a < b) uf.union(b, a);
        if(a >= b) uf.union(a, b);
    }

    public int find(int a){
        a--;
        return uf.find(a) + 1;
    }

    public static int lower(int a, int b){
        if(a < b) return a;
        return b;
    }

    public static void main(String[] args) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] data = br.readLine().split("","");
            int num = Integer.parseInt(data[0]);
            int targetX = Integer.parseInt(data[1]);
            int targetY = Integer.parseInt(data[2]);

            LabelCC lcc = new LabelCC(num);


            for(String in = br.readLine(); in != null; in = br.readLine()){
                data = in.split("","");
                int x = Integer.parseInt(data[0]);
                int y = Integer.parseInt(data[1]);
                lcc.setPos(x, y, -1);
            }

            int group = 1;
            for(int i = 1; i <= num; i++){
                for(int j = 1; j <= num; j++){
                    if(lcc.getPos(i, j) == -1) continue;

                    int left = lcc.getPos(i - 1, j);
                    int top = lcc.getPos(i, j - 1);

                    if(left == -1 && top == -1){
                        lcc.setPos(i, j, group);
                        group++;
                    }
                    else if(left != -1 && top == -1){
                        lcc.setPos(i, j, left);
                    }
                    else if(left == -1 && top != -1){
                        lcc.setPos(i, j, top);
                    }
                    else{
                        lcc.setPos(i, j, LabelCC.lower(top, left));
                    }
                }
            }

            lcc.initUF(--group);


            for(int i = 1; i <= num; i++){
                for(int j = 1; j <= num; j++){
                    int left = lcc.getPos(i - 1, j);
                    int top = lcc.getPos(i, j - 1);
                    int current = lcc.getPos(i, j);

                    if(current == -1) continue;
                    if(left != -1 && current != left) lcc.union(left, current);
                    if(top != -1 && current != top) lcc.union(top, current);
                }
            }

            /*for(int i = 1; i <= num; i++){
                for(int j =1; j <= num; j++){
                    System.out.printf(""%s\t"", lcc.getPos(i, j) == -1? ""-"": lcc.find(lcc.getPos(i, j)));
                }
                System.out.printf(""\n"");
            }

            System.out.printf(""\n(%d, %d): \n"", targetX, targetY);*/

            System.out.printf(""%d\n"", lcc.find(lcc.getPos(targetX, targetY)));
        }

    }
}

