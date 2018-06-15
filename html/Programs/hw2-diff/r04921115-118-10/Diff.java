import java.io.BufferedReader;
import java.io.FileReader;

public class LabelCC {
    private int[] id;     // id[i] = parent of i

    public LabelCC(int N) {
        if (N < 0) throw new IllegalArgumentException();
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    public int find(int p) {
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        id[i]=j;
    }
    
	public static void main(String[] args) throws Exception{
        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        	String[] data = br.readLine().split("","");
        	int N = Integer.parseInt(data[0]);
        	int target_x = Integer.parseInt(data[1]);
        	int target_y = Integer.parseInt(data[2]);
        	//System.out.printf(""N: %d x: %d y: %d"", N, target_x, target_y);
        	int i, j, k;
        	int [][] matrix = new int[N+2][N+2];
        	boolean [][] status = new boolean[N+2][N+2];
        	int [][] label = new int[N+2][N+2]; 
        	k=1;
        	for(i = 0 ; i<N+2 ;i++){
            	for(j = 0 ; j<N+2 ;j++){
            		if(i>=1 && i<=N && j>=1 && j<=N){
            			matrix[i][j] = k++ ;
            			status[i][j] = true;
            			label[i][j] = 0;
            		}else{
            			label[i][j] = 0;
            			matrix[i][j] = -1;
            			status[i][j] = false;
            		}
            		// 2 as default virtual block in the beginning
            		// in order to let the matrix calculation more convenient
            	}
        	}
            
        	LabelCC labelcc = new LabelCC(N*N+1);

            String temp;
        	while((temp = br.readLine())!=null){
	    		String[] coordinates = temp.split("","");
	    		int block_row = Integer.parseInt(coordinates[0]);
	            int block_col = Integer.parseInt(coordinates[1]);
	            matrix[block_row][block_col]=0;// 0 as real block
	            status[block_row][block_col]=false;
	            label[block_row][block_col]=0;
	        }
        	
//        	for(i = 0 ; i<N+2 ;i++){
//            	for(j = 0 ; j<N+2 ;j++){
//            		System.out.printf(""%d "", matrix[i][j]);
//            		if(j==N+1){
//            			System.out.printf(""\n"");
//            		}
//            	}
//        	}
//			System.out.printf(""-------\n"");

        	int currentlabelcount=0;
        	
        	for(i = 1 ; i<N+1 ;i++){
            	for(j = 1 ; j<N+1 ;j++){
            		if(status[i][j]!=false){
            			if(label[i][j-1]==0 && label[i-1][j]==0){
            				currentlabelcount++;
            				label[i][j] = currentlabelcount;
            			}
            			if(label[i][j-1]!=0 && label[i-1][j]==0){
            				label[i][j] = label[i][j-1];
            			}
            			if(label[i][j-1]==0 && label[i-1][j]!=0){
            				label[i][j] = label[i-1][j];
            			}
            			if(label[i][j-1]!=0 && label[i-1][j]!=0){
            				if(label[i][j-1] > label[i-1][j]){
            					label[i][j] = label[i-1][j];
            					labelcc.union(label[i][j-1], label[i-1][j]);
            				}else{
            					label[i][j] = label[i][j-1];
            					labelcc.union(label[i-1][j], label[i][j-1]);
            				}
            			}
            		}
            	}
        	}
        	
    		System.out.printf(""%d"", labelcc.find(label[target_x][target_y]));
//        	for(i = 0 ; i<N+2 ;i++){
//            	for(j = 0 ; j<N+2 ;j++){
//            		System.out.printf(""%d "", label[i][j]);
//            		if(j==N+1){
//            			System.out.printf(""\n"");
//            		}
//            	}
//        	}
//			System.out.printf(""...........\n"");
//
//        	for(i = 0 ; i<N+2 ;i++){
//            	for(j = 0 ; j<N+2 ;j++){
//            		System.out.printf(""%d "", labelcc.find(label[i][j]));
//            		if(j==N+1){
//            			System.out.printf(""\n"");
//            		}
//            	}
//        	}
        }
    }
}

