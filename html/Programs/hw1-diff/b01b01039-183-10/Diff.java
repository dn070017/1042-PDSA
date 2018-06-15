/**********************************************************************************
PDSA Homework1
date 2016/03/07
Author : Chia Yu Chang
Percolation problem
The model. 
	We model a percolation system using an N-by-N grid of sites. 
	Each site is either open or blocked. 
	A full site is an open site that can be connected to an open site 
.
.
	In other words, a system percolates if we fill all open sites connected to the 
	top row and that process fills some open site on the bottom row. 
Input
	num : the size of the block
	row,col : position
Output
	percolate : last position
	not percolate : -1
***********************************************************************************/

import java.io.FileReader;
import java.io.BufferedReader;


public class Percolation {
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
			sudo_node_connect(uf, num);
			
			// read file line by line
			boolean fin = true; // if system did not percolate fin will stay true
			while ((data = br.readLine()) != null) {
				String[] pos = data.split("","");
				// checking whether input error
				if(pos.length != 2){
					System.out.println(""Error input"" + data);
					break;
				}
				int row = Integer.parseInt(pos[0])-1;
				int col = Integer.parseInt(pos[1])-1;
				/* For input checking
				System.out.println(""row : "" + row + "" col : "" + col);
				*/
				node[twod2oned(row, col, num)] = true;
				edge_formation(uf, row, col, num, node);
				// if system percolate stop the loop
				if(uf.connected(num*num, num*num-1)){
					row ++; col++;
					System.out.printf(""%d,%d"",row,col);
					fin = false;
					break;
				}
				/*
				boolean_array_print(node, num);
				uf_array_check(uf, num);
				*/
			}
			if(fin) System.out.println(-1); // if system did not percolate print out -1
        }
    }
	
	// transform 2D array to 1D array
	public static int twod2oned(int row, int col, int num){
		int pos;
		pos = row * num + col;
		//System.out.printf(""%d "", pos);
		return pos;
	}
	
	// transform 1D array to 2D array
	public static void oned2twod(int pos, int num){
		int row;
		int col;
		row = pos / 5;
		col = pos % 5;
		//System.out.printf(""row : %2d col : %2d "", row, col);
	}

	// for making connection to sudo_node
	public static void sudo_node_connect(QuickUnionUF uf, int num){
		for(int idx = 0; idx < num; idx++){
			uf.union(twod2oned(0, idx, num), num*num);
		}
		for(int idx = 0; idx < num; idx++){
			uf.union(twod2oned((num-1), idx, num), num*num+1);
		}
	}
	
	// for making edge
	public static void edge_formation(QuickUnionUF uf, int row, int col, int num, boolean node[]){
		if(row-1 >= 0){
				if(node[twod2oned(row-1, col, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row-1, col, num));
			}
		}
		if(row+1 < num){
			if(node[twod2oned(row+1, col, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row+1, col, num));
			}
		}
		if(col-1 >= 0){
			if(node[twod2oned(row, col-1, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row, col-1, num));
			}
		}
		if(col+1 < num){
			if(node[twod2oned(row, col+1, num)]){
				uf.union(twod2oned(row, col, num), twod2oned(row, col+1, num));
			}
		}
	}
	
	// for print our uf parent array
	public static void uf_array_check(QuickUnionUF uf, int num){
		for(int row = 0; row < num; row++){
			for(int col = 0; col < num; col++){
				System.out.printf(""%2d "",uf.find(twod2oned(row, col, num)));
			}
			System.out.printf(""\n"");
		}
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
}


