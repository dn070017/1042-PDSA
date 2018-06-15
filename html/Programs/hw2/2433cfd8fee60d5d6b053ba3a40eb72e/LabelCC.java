import java.io.FileReader;
import java.io.BufferedReader;
//import java.util.*;

public class LabelCC {
	private int now_label;
	private boolean[][] node;
	int[][] label;
	int[] target;
	
	public LabelCC(int N){
		now_label = 1;
		target = new int[2];
		node  = new boolean[N][N];
		for(int row = 0; row < N; row++){
			for(int col = 0; col < N; col++){
				node[row][col] = true;
			}
		}
		label = new int[N][N];
	}

	public void target_assign(int row, int col){
		target[0] = row;
		target[1] = col;	
	}
	/*
	public void target_print(){
		System.out.printf(""Target site row: %2d col: %2d\n"",target[0],target[1]);
	}
	*/
	public int get_target_row(){
		return(target[0]);
	}
	
	public int get_target_col(){
		return(target[1]);
	}
	
	public boolean node(int row, int col){
		return node[row][col];
	}
	
	public void node_assign(int row, int col, boolean b){
		node[row][col] = node[row][col] & b;
	}
	/*
	public void node_print(){
		for(int row = 0; row < node.length; row++){
			for(int col = 0; col < node.length; col++){
				System.out.printf(""%5b "", node[row][col]);
			}
			System.out.printf(""\n"");
		}
	}
	*/
	public void label_assign(int row, int col, int lab){
		label[row][col] = lab;
	}
	
	public void label_print(){
		for(int row = 0; row < label.length; row++){
			for(int col = 0; col < label.length; col++){
				System.out.printf(""%d "", label[row][col]);
			}
			System.out.printf(""\n"");
		}
	}
	
	public int get_label(int row, int col){
		return(label[row][col]);
	}
	
	public int now_label(){
		return now_label++;
	}
	
	public void both_print(){
		for(int row = 0; row < node.length; row++){
			for(int col = 0; col < node.length; col++){
				System.out.printf(""%5b %2d "", node[row][col], label[row][col]);
			}
			System.out.printf(""\n"");
		}
	}
	
	private boolean up_open(int row, int col){
		if(row > 0){
			return node[row-1][col];
		}else{
			return false;
		}
	}
	
	private boolean left_open(int row, int col){
		if(col > 0){
			return node[row][col-1];
		}else{
			return false;
		}
	}
	
	private int up_label(int row, int col){
		return label[row-1][col];
	}
	
	private int left_label(int row, int col){
		return label[row][col-1];
	}

	
	public static void main(String[] args) throws Exception {
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            long time1, time2, time3, time4;
			//time1 = System.nanoTime();
			
            // read a line and split by ','
			String line = br.readLine();
            String[] data = line.split("","");
			
			Stack<Integer> st = new Stack<Integer>();
			//Stack<Integer> up_st = new Stack<Integer>();
			//Stack<Integer> left_st = new Stack<Integer>();
			
			// Creat a object            
            int num = Integer.parseInt(data[0]);
			LabelCC lcc = new LabelCC(num);
			// Asign target site
			lcc.target_assign(Integer.parseInt(data[1])-1, Integer.parseInt(data[2])-1);
			//lcc.target_print();
			// read file line by line
			while ((line = br.readLine()) != null) {
				String[] pos = line.split("","");
				// checking whether input error
				if(pos.length != 2){
					System.out.println(""Error input"" + data);
					break;
				}
				int row = Integer.parseInt(pos[0])-1;
				int col = Integer.parseInt(pos[1])-1;
				//System.out.println(""row :""+ row + "" col :""+ col);
				lcc.node_assign(row, col, false);
				//lcc.node_print();
			}
			//lcc.both_print();
			//System.out.println();
			//time2 = System.nanoTime();
			//int CC_N = 0;
			for(int row = 0; row < num; row++){
				for(int col = 0; col < num; col++){
					if(lcc.node(row,col))
						if(lcc.up_open(row,col) & lcc.left_open(row,col)){
							if(lcc.up_label(row,col) < lcc.left_label(row,col)){
								lcc.label_assign(row,col,lcc.up_label(row,col));
							}else{
								lcc.label_assign(row,col,lcc.left_label(row,col));
							}
							if(lcc.up_label(row,col) != lcc.left_label(row,col)){
								st.push(lcc.up_label(row,col));
								st.push(lcc.left_label(row,col));
							}
						}else if(lcc.up_open(row,col)){
							lcc.label_assign(row,col,lcc.up_label(row,col));
						}else if(lcc.left_open(row,col)){
							lcc.label_assign(row,col,lcc.left_label(row,col));
						}else{
							lcc.label_assign(row,col,lcc.now_label());
						}
				}
			}
			//lcc.label_print();
			//time3 = System.nanoTime();
			uf_label uf_l = new uf_label(lcc.now_label());
			while(st.size() != 0){
				int up = st.pop();
				int left = st.pop();
				//System.out.println(up + "" "" + left);
				if(!uf_l.connected(up, left)){
					uf_l.label_change(up, left);
					uf_l.union(up, left);
					//uf_l.print_lable();
					//uf_l.print_root();
				}
			}
			//System.out.println();
			//uf_l.print_lable();
			//uf_l.print_root();
			
			System.out.println(uf_l.get_label(lcc.get_label(lcc.get_target_row(), lcc.get_target_col())));
			/*time4 = System.nanoTime();
			System.out.println(""=========== Time Cost ==========="");
			System.out.println(time2 - time1);
			System.out.println(time3 - time2);
			System.out.println(time4 - time3);*/
		}
	}	
}

class uf_label extends UF{
	private int[] label;
	
	public uf_label(int N){
		super(N);
		label = new int[N];
		for(int idx = 0; idx < N; idx++){
			label[idx] = idx;
		}
	}
	
	public void label_change(int p, int q){
		int p_l = label[find(p)];
		int q_l = label[find(q)];
		//System.out.println(p_l + "" "" + q_l);
		if(p_l > q_l){
			label[find(p)] = q_l;
			label[p] = q_l;
			label[q] = q_l;
		}else{
			label[find(q)] = p_l;
			label[p] = p_l;
			label[q] = p_l;
		}
	}
	
	public void change_label(int N, int lab){
		label[N] = lab;
	}
	
	public int get_label(int N){
		return label[find(N)];
	}
	
	public void print_lable(){
		for(int i : label){
			System.out.printf(""%d "", i);
		}
		System.out.printf(""\n"");
	}
	
	public void print_root(){
		for(int id = 0; id < label.length; id++){
			System.out.printf(""%d "", find(id));
		}
		System.out.printf(""\n"");
	}
}
