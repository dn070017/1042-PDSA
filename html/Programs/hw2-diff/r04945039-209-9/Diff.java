import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class LabelCC {
	
	public boolean[][] matrix;
	public static int[][] idarray;
	public static int size;
	public QuickUnionUF qf;
	public int id=1;

	public LabelCC (int n){
		size=n;
		matrix = new boolean[size][size];
		idarray = new int[size][size];  
		for(boolean row[]: matrix)
			Arrays.fill(row, true);
		qf = new QuickUnionUF(size*size);
	}


	public void Blocked(int x,int y) {
		matrix [x-1][y-1]=false;
	}

	public boolean isOpen(int x,int y){
		return matrix[x-1][y-1];
	}

	private int setID (int i, int j){
		return id;
	}

	public void targetid (int i, int j) {
		if (matrix[i-1][j-1]!=true){
			System.out.println(0);
		}
                else{
                    int ans=idarray[i-1][j-1];
			System.out.println(qf.find(ans));
                }
	}
	

    public void getIndex(int x, int y){
	if(isOpen(x,y)!=false){
        if ( x==1 && isOpen(x,y-1)!=true || y==1 && isOpen(x-1,y)!=true || x!=1 && y!=1 && isOpen(x-1,y)!=true && isOpen(x,y-1)!=true){
	id++;	
            idarray[x-1][y-1]=setID(x,y);
		
	}
	else {
		if (y==1 && isOpen(x-1,y)==true || x!=1 &&y!=1 && isOpen(x-1,y)==true && isOpen(x,y-1)!=true){
			idarray[x-1][y-1]=(idarray[x-2][y-1]);
		}
		else{
			if (x==1 && isOpen(x,y-1)==true || x!=1 && y!=1 && isOpen(x,y-1)==true && isOpen(x-1,y)!=true) {
				idarray[x-1][y-1]=idarray[x-1][y-2];
			}
			else{

				int a =Math.max((idarray[x-2][y-1]),(idarray[x-1][y-2]));
				int b =Math.min((idarray[x-2][y-1]),(idarray[x-1][y-2]));
				idarray[x-1][y-1]=b;
				qf.union(a,b);
			}
		}
	} 
}
        else
            idarray[x-1][y-1]=0;
    }
        

        public boolean initiation(){
            return matrix[0][0];
        }
        
        
        public static void main(String[] args) throws Exception {
            try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] hintline=br.readLine().split("","");
            int input = Integer.parseInt(hintline[0]);
            int i = Integer.parseInt(hintline[1]);
            int j= Integer.parseInt(hintline[2]);
            LabelCC test = new LabelCC(input);
            

         for(String line;(line = br.readLine())!=(null); ){
                    
                    String[] coordinate=line.split("","");
                    int x = Integer.parseInt(coordinate[0]);        
                    int y = Integer.parseInt(coordinate[1]);
                    test.Blocked(x, y);
                    
            }
         
         for (int m=1;m<=size;m++){
                      for (int n=1;n<=size;n++){
                          
                          if (m==1&& n==1 &&test.initiation()){
                              idarray[0][0]=1;
                              continue;
                          }
                          else
                              test.getIndex(m, n); 
                        
                      }
         }
            
           
             
            test.targetid(i, j);
            }
    }
	
}
