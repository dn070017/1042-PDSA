import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {
	//
	public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String data = br.readLine();
            
			// store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data);
			
            // initilization of a parent array
			/*
			First row and last row will be connect to sudo nodes
			ID of sudo nodes will be num*num and num*num-1
			node array for checking whether the node has been assign
			*/
			//System.out.println(num*num);
			QuickUnionUF uf = new QuickUnionUF(num * num + 2);
			boolean[] node = new boolean[num * num];
			
			for(int idx = 0; idx < num; idx++){
				uf.union(twod2oned(0, idx, num), num*num);
			}
			for(int idx = 0; idx < num; idx++){
				uf.union(twod2oned((num-1), idx, num), num*num+1);
			}			
			for(int idx = 0; idx < num*num+2; idx++){
				oned2twod(idx, num);
				//System.out.println(uf.find(idx));
			}
			
			
			// read file line by line
			String[] pos;
			int row, col;
			boolean fin = true;
			while ((data = br.readLine()) != null) {
				pos = data.split("","");
				if(pos.length != 2){
					System.out.println(""Error input"" + data);
					break;
				}
				row = Integer.parseInt(pos[0])-1;
				col = Integer.parseInt(pos[1])-1;
				//System.out.printf(""\n"");
				//System.out.println(""row : "" + row + "" col : "" + col);
				//System.out.println(twod2oned(row, col, num));
				node[twod2oned(row, col, num)] = true;
				edge_formation(uf, row, col, num, node);
				if(uf.connected(num*num, num*num-1)){
					row ++; col++;
					System.out.printf(""%d,%d"",row,col);
					fin = false;
					break;
				}
				//boolean_array_print(node, num);
				//uf_array_check(uf, num);
			}
			if(fin) System.out.println(-1);
			//uf_array_check(uf, num);
			//boolean_array_print(node, num);
        }
    }
	
	public static int twod2oned(int row, int col, int num){
		int pos;
		pos = row * num + col;
		//System.out.printf(""%d "", pos);
		return pos;
	}
	
	public static void oned2twod(int pos, int num){
		int row;
		int col;
		row = pos / 5;
		col = pos % 5;
		//System.out.printf(""row : %2d col : %2d "", row, col);
	}
	
	public static void edge_formation(QuickUnionUF uf, int row, int col, int num, boolean node[]){
		//System.out.println(""row : "" + row + "" col : "" + col);
		if(row-1 >= 0){
			//System.out.printf(""%b "", node[twod2oned(row-1, col, num)]);
			//System.out.printf(""UP"");
			if(node[twod2oned(row-1, col, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row-1, col, num));
			}
			//System.out.printf(""\n"");
		}
		if(row+1 < num){
			//System.out.printf(""%b "", node[twod2oned(row+1, col, num)]);
			//System.out.printf(""DOWN"");
			if(node[twod2oned(row+1, col, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row+1, col, num));
			}
			//System.out.printf(""\n"");
		}
		if(col-1 >= 0){
			//System.out.printf(""%b "", node[twod2oned(row, col-1, num)]);
			//System.out.printf(""LEFT"");
			if(node[twod2oned(row, col-1, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row, col-1, num));
			}
			//System.out.printf(""\n"");
		}
		if(col+1 < num){
			//System.out.printf(""%b "", node[twod2oned(row, col+1, num)]);
			//System.out.printf(""RIGHT"");
			if(node[twod2oned(row, col+1, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row, col+1, num));
			}
			//System.out.printf(""\n"");
		}
		//System.out.println(""\n"");
	}
	
	public static void uf_array_check(QuickUnionUF uf, int num){
		for(int row = 0; row < num; row++){
			for(int col = 0; col < num; col++){
				System.out.printf(""%2d "",uf.find(twod2oned(row, col, num)));
			}
			System.out.printf(""\n"");
		}
	}
	
	
	public static void int_array_print(int[] array, int col){
		int i = 0;
			for(int idx : array){
				if(i > 0 && i%col == 0)System.out.printf(""\n"");
				System.out.printf("" %3d"", idx);
				++i;
			}
			System.out.printf(""\n"");
	}
	// for printing out a boolean array
	public static void boolean_array_print(boolean[] array, int col){
		int i = 0;
			for(boolean idx : array){
				if(i > 0 && i%col == 0)System.out.printf(""\n"");
				if(idx) System.out.printf(""%2d "",1);
				else System.out.printf(""%2d "",0);
				++i;
			}
			System.out.printf(""\n\n"");
	}
	//public static viod array_union(){
		
	//}
}


