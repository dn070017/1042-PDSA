import java.io.FileReader;
import java.io.BufferedReader;

class Percolation {

    private final int num;
    private UF uf;
    private boolean[] flag;

    public Percolation (int num){
        this.num = num;
        uf = new UF(num * num + 2);
        flag = new boolean[num * num + 2];
        flag[num * num] = true;
        flag[num * num + 1] = true;
    }

    public boolean getPos(int index){
        return flag[index];
    }

    public void setPos(int x, int y) {
        x--;
        y--;
        int up = (x - 1) * num + y;
        int index = x * num + y;
        int down = (x + 1) * num + y;
        int left = x * num + y - 1;
        int right = x * num + y + 1;

        flag[index] = true;

        union(index, up);
        union(index, down);
        union(index, left);
        union(index, right);

        if(index < num) {
            uf.union(index, num * num);
            // System.out.printf(""union (%d, %d) and top pseudo node...\n"", index / num + 1, index % num + 1, index);
        }
        if(index >= num * (num - 1)) {
            uf.union(index, num * num + 1);
            // System.out.printf(""union (%d, %d) and bottom pseudo node...\n"", index / num + 1, index % num + 1, index);
        }
    }

    public boolean checkPercolation(){
        return uf.connected(num * num, num * num + 1);
    }

    private void union(int index, int neighbor){
        if(neighbor < 0 || neighbor > num * num - 1) return;
        if(index < 0 || index > num * num - 1) return;
        if(flag[neighbor] == true){
            // System.out.printf(""union (%d, %d) and (%d, %d)...\n"", index / num + 1, index % num + 1, neighbor / num + 1, neighbor % num + 1);
            uf.union(index, neighbor);
        }
    }

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int num = Integer.parseInt(br.readLine());

            Percolation p = new Percolation(num);

            while(br.readLine() != null) {
                String[] data = br.readLine().split("","");
                int x = Integer.parseInt(data[0]);
                int y = Integer.parseInt(data[1]);

                p.setPos(x, y);
                if(p.checkPercolation()){
                    System.out.printf(""%d,%d\n"", x, y);
                    break;
                }
            }


            if(!p.checkPercolation()) System.out.printf(""-1\n"");
            /*
            for(int i = 0; i < num * num; i++){
                System.out.printf(""%d\t"", p.getPos(i)? 1: 0);
                if(i % num == num - 1) System.out.printf(""\n"");
            }
            System.out.printf(""%d\t"", p.getPos(num * num)? 1: 0);
            System.out.printf(""%d\t"", p.getPos(num * num + 1)? 1: 0);
            */
        }
    }
}
