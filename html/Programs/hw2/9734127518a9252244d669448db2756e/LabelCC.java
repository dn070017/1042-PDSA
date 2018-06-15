//import edu.princeton.cs.algs4.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class LabelCC {
	QuickUnionUF quickUnionUF;
	boolean[][] booleanMap;
	int[][] labelMap;
	int size;
	
	public LabelCC(int size){
		this.size = size;
		quickUnionUF = new QuickUnionUF (size * size);
		booleanMap = new boolean [size][size];
		labelMap = new int[size][size];
		for(int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				booleanMap[i][j] = true;
			}
		}
	}

	public void inputSite(int row, int col){
		booleanMap[row][col] = false;
	}

	public void labelling(){
		int tempLeft;
		int tempTop;
		int labelCount = 1;

		// for(int i = 0; i < size; i++){
		// 	for (int j = 0; j < size; j++){
		// 		//System.out.print(booleanMap[i][j] + "" "");
		// 	}
		// 	System.out.println();
		// }

		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(booleanMap[i][j]){
					tempLeft = 0;
					tempTop = 0;
					int[] left = {i, j-1};
					int[] top = {i-1, j};
					if(left[1] >= 0 && booleanMap[left[0]][left[1]]){
						tempLeft = labelMap[left[0]][left[1]];
					}
				
					if(top[0] >= 0 && booleanMap[top[0]][top[1]]){
						tempTop = labelMap[top[0]][top[1]];
					}

					if(tempLeft > 0 && tempTop > 0){
						int rootLeft = quickUnionUF.find(toInt(left[0], left[1]));
						int rootTop = quickUnionUF.find(toInt(top[0], top[1]));
						if(tempLeft > tempTop){
							labelMap[i][j] = tempTop;
							if(rootLeft != rootTop){
								quickUnionUF.union(toInt(left[0], left[1]),toInt(top[0], top[1]));
							}
							else{
								quickUnionUF.union(toInt(i,j), toInt(top[0],top[1]));
							}
						}
						else{
							labelMap[i][j] = tempLeft;
							if(rootLeft != rootTop){
								quickUnionUF.union(toInt(top[0], top[1]), toInt(left[0],left[1]));
							}
							else{
								quickUnionUF.union(toInt(i,j), toInt(left[0],left[1]));
							}
						}
					}
					else if(tempLeft > 0){
						labelMap[i][j] = tempLeft;
						quickUnionUF.union(toInt(i,j), toInt(left[0],left[1]));
					}
					else if(tempTop > 0){
						labelMap[i][j] = tempTop;
						quickUnionUF.union(toInt(i,j), toInt(top[0],top[1]));
					}
					else{
						labelMap[i][j] = labelCount;
						++labelCount;
					}
				}
			}
		}

		// for(int i = 0; i < size; i++){
		// 	for (int j = 0; j < size; j++){
		// 		System.out.print(labelMap[i][j] + "" "");
		// 	}
		// 	System.out.println();
		// }
		// System.out.println(""----------------------------"");

		merge();
	}

	private void merge(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size ; j++){
				int [] coordinate;
				coordinate = toCoordinate(quickUnionUF.find(toInt(i,j)));
				if(labelMap[coordinate[0]][ coordinate[1]] != labelMap[i][j]){
					labelMap[i][j] = labelMap[coordinate[0]][coordinate[1]];
				}
			}
		}

		// for(int i = 0; i < size; i++){
		// 	for (int j = 0; j < size; j++){
		// 		System.out.print(labelMap[i][j] + "" "");
		// 	}
		// 	System.out.println();
		// }
	}

	public int check(int row, int col){
		return labelMap[row][col];
	}

	private int toInt(int row, int col){
			return row * size + col;
	}

	private int[] toCoordinate(int length){
		int[] coordinate = new int [2];

		coordinate[0] = length/size;
		coordinate[1] = length%size;

		return coordinate;
	}

	public static void main(String[] args) throws Exception {
		String[] firstLine;
		int size;
		int checkRow, checkClo;
		String buffer;
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		int test;

		firstLine = br.readLine().split("","");
		size = Integer.valueOf(firstLine[0]);
		checkRow = Integer.valueOf(firstLine[1]);
		checkClo = Integer.valueOf(firstLine[2]);
		LabelCC labelCC = new LabelCC(size);

		while((buffer = br.readLine()) != null && buffer.length() != 0){
			String[] coordinate = buffer.split("","");
			labelCC.inputSite(Integer.valueOf(coordinate[0]) - 1, Integer.valueOf(coordinate[1]) - 1);
			//System.out.println(coordinate[0] + coordinate[1]);
		}

		labelCC.labelling();
		System.out.println(labelCC.check(checkRow - 1, checkClo - 1));
	}
}
