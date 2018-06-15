//import edu.princeton.cs.algs4.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class Percolation{
	int size;
	int length;
	boolean[][] markMap;
	WeightedQuickUnionUF uf;

	public Percolation(int size){		
		// TODO size = 1
		this.size = size;
		this.length = size * size;
		this.uf = new WeightedQuickUnionUF(length + 2);		// length + 1 start point; length + 2 end point
		this.markMap = new boolean [size][size];

		// TODO Constructor
	}

	public void inputSite (int row, int col){
		markMap[row][col] = true;
		this.union(row, col);
		// TODO input the site to Percolation
	}

	public boolean checkMap(){
		return uf.connected(length, length+1);
	}

	private void union(int row, int col){
		// TODO connect all the site which have already inputed
		// TODO must solve the size 1 problem
		int index = toInd(row, col);

		int[] top = {row-1, col};
		int[] end = {row+1, col};
		int[] left = {row, col-1};
		int[] right = {row, col+1};

		if (top[0]<0) {
			uf.union(index, length);
		}
		else if (markMap[top[0]][top[1]]){
			uf.union(index, toInd(top[0], top[1]));
		}

		if (end[0] >= size) {
			uf.union(index, length + 1);
		}
		else if (markMap[end[0]][end[1]]) {
			uf.union(index, toInd(end[0], end[1]));
		}

		if(left[1] >= 0 && markMap[left[0]][left[1]]){
			uf.union(index, toInd(left[0],left[1]));
		}

		if(right[1] < size && markMap[right[0]][right[1]]){
			uf.union(index, toInd(right[0], right[1]));
		}


		
	}

	private int toInd(int row, int col){
			return row * size + col;
		}

	public static void main(String[] args) throws Exception{
		int size;
		int row;
		int col;
		String buffer;
		BufferedReader br = new BufferedReader(new FileReader(args[0]));

		size = Integer.valueOf(br.readLine());
		Percolation percolation = new Percolation(size);
		while((buffer = br.readLine()) != null && buffer.length() != 0){
			String[] siteBuffer = buffer.split("","");
			row = Integer.valueOf(siteBuffer[0]);
			col = Integer.valueOf(siteBuffer[1]);
			percolation.inputSite(row-1, col-1);	// TODO size check
			if(percolation.checkMap()){
				System.out.println("""" + row + "","" + col);
				break;
			}
		}
		if(!percolation.checkMap()){
			System.out.println(""-1"");
		}
	}
}
