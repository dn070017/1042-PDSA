
import java.io.FileReader;
import java.io.BufferedReader;

public class Percolation {

    public int size;
    public int id;
    public static int[] state;
    public WeightedQuickUnionUF qf;

    Percolation(int sizee, WeightedQuickUnionUF qff) {
        size = sizee;
        qf = qff;
        state = new int[size*size+2];
    }

    public void open(int i, int j) {
        checkrange(i, j);
        if (checkopen(i, j)) {
            return;
        }
        id = getindex(i, j);
        state[id] = 1;
        //if not top row
        if (i != 1 && checkopen(i - 1, j)) {
            union(getindex(i - 1, j), id);
        } else if (i == 1) {
            //connect to virtual top cell
            union(id, size * size);
        }
        //if not bottom row
        if (i != size && checkopen(i + 1, j)) {
            union(getindex(i + 1, j), id);
        } else if (i == size) {
            //connect to virtual bottom cell
            union(id, size * size + 1);
        }
        //if not left border
        if (j != 1 && checkopen(i, j - 1)) {
            union(getindex(i, j - 1), id);
        }
        //if not right border
        if (j != size && checkopen(i, j + 1)) {
            union(getindex(i, j + 1), id);
        }
    }

    public int getindex(int i, int j) {
        return size * (i - 1) + j - 1;
    }

    public void checkrange(int i, int j) {
        if (i <= 0 || j <= 0 || i > size || j > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean checkopen(int i, int j) {
        checkrange(i, j);
        return state[getindex(i, j)] == 1;
    }

    public boolean isfull(int i, int j) {
        checkrange(i, j);
        return qf.connected(size * size, getindex(i, j));
    }

    public void union(int i, int j) {
        if (!qf.connected(i, j)) {
            qf.union(i, j);
        }
    }

    public boolean percolate(int i, int j) {
        return qf.connected(size * size, size * size + 1);
    }
    
    public void setstate(int i,int value)
    {
        state[i] = value;
    }
    
    public int getstate(int i)
    {
        return state[i];
    }

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int top = 0;
            int bottom;
            boolean ok = false;
            //WeightedQuickUnionUF qf;
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            int size = Integer.parseInt(data[0]);
            //System.out.printf(""%d"", size);

            bottom = size;
            //state = new int[size * size + 2];
            WeightedQuickUnionUF qf = new WeightedQuickUnionUF(size * size + 2);
            
            Percolation per = new Percolation(size, qf);
            for (int i = 0; i < size * size; i++) {
                per.setstate(i, 0);
                //state[i] = 0;
            }
            per.setstate(size*size, 1);
            per.setstate(size*size+1,1);
            //state[size * size] = 1;
            //state[size * size + 1] = 1;

            boolean doo;
            int count = 0;
            int i =0;
                int j =0;
            
            
            while (!ok) {
                String[] location = br.readLine().split("","");
                count++;
                //System.out.printf(""\n%s %s\n"", location[0], location[1]);
                i = Integer.parseInt(location[0]);
                j = Integer.parseInt(location[1]);
                //System.out.printf(""i=%d j=%d\n"", i, j);
                per.open(i, j);
                doo = per.percolate(i, j);
                //System.out.printf(""i=%b\n"", doo);
                ok = doo;
                 //System.out.printf(""___________________ \n"");
                //System.out.printf(""%d , %d is open \n"",i,j );
                for(int k=0;k<size*size+2;k++)
                {                   
                    //System.out.println
                    //System.out.printf(""%d == %d  \n"",k,per.getstate(k));
                }
                if(ok==true){
                    System.out.printf(""%d,%d"",i,j);
                    return;
                }
//            }
            }
        }
        catch(Exception e){
    System.err.println(""-1"");
}
    }
}

