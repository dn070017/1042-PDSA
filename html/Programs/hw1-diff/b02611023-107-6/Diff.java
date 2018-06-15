import java.io.*;

public class Percolation {

	public static int[] check;

	public static int num;

	public static void main(String[] args) throws Exception{
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

			num = Integer.parseInt(br.readLine());

			String line;

			String[] lines = new String[2];

			int count;

			check = new int[num * num + 2];
			check[num*num] = num * num;
			check[num*num+1] = num * num + 1;

			int root_1 = 0;

			int[][] matrix = new int[num+2][num+2];

			for(int i = 0; i < num * num; i ++){
				line = br.readLine();
				if(num == 1){
					System.out.println(line);
				}
				if(line == null){
					break;
				}

				lines = line.split("","");
				int row = Integer.parseInt(lines[0]);
				int col = Integer.parseInt(lines[1]);
				matrix[row][col] = 1;
				count = col - 1 + num * (row - 1);
				check[count] = count;

				if(row == 1){
					//check[count] = check[num*num];
					union(check[count],check[num*num]);
				}
				else if(row == num){
					//check[count] = check[num*num+1];
					union(check[count],check[num*num+1]);
				}
				else{
					//�U
					if(matrix[row][col] - matrix[row+1][col] == 0){
						//check[row*num+col-1] = check[count];
						union(check[row*num+col-1],check[count]);
					}
					//�k
					if(matrix[row][col] - matrix[row][col+1] == 0){
						//check[(row-1)*num+col] = check[count];
						union(check[(row-1)*num+col],check[count]);
					}
					//��
					if(matrix[row][col] - matrix[row][col-1] == 0){
						//check[count] = check[(row-1)*num+col-2];
						union(check[count],check[(row-1)*num+col-2]);
					}
					//�W
					if(matrix[row][col] - matrix[row-1][col] == 0){
						//check[count] = check[(row-2)*num+col-1];
						union(check[count],check[(row-2)*num+col-1]);
					}
				}
				root_1 = root(check[num*num+1]);

				if(root_1 == check[num*num]){
					System.out.println(row + "","" + col);
					//System.out.println(root_1);
					break;
				}
				//System.out.println(check[count]);
			}
			/*for(int k = 0;k<num*num+2;k++){
				System.out.println(check[k]);
			}*/
			if(root_1 != check[num*num]){
				//System.out.println(check[num*num]);
				System.out.println(""-1"");
			}
		}
	}

	public static int root(int i){
		while(i != check[i]){
			i = check[i];
		}
		return i;
	}

	public static void union(int p, int q){
		int i = root(p);
		int j = root(q);
		check[i] = j;
	}
}

