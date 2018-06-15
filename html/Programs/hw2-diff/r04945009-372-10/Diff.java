
import java.io.BufferedReader;
import java.io.FileReader;


/**
 *
 * @author hung-wei
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws Exception {

        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0])))
        {
            String[] Line1 = br.readLine().split("","");
            int N = Integer.parseInt(Line1[0]);
            int[] target = new int[2];
            target[0] = Integer.parseInt(Line1[1]);
            target[1] = Integer.parseInt(Line1[2]);
            boolean[][] matrix = new boolean[N+1][N+1];
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    matrix[i][j] = true;
                }
            }
            
            while (br.ready()){
                String[] data = br.readLine().split("","");
                int x = Integer.parseInt(data[0]);
                int y = Integer.parseInt(data[1]);
                matrix[x][y] = false;
            }
            int[][] label = new int[N][N];
            int Count = 1;
            int Count2 = 0;
            int Special[] = new int[N*N+1];
            Special[0] = 0;
            
            for(int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    if(matrix[i][j]){
                        if(!matrix[i][j-1] && !matrix[i-1][j]){
                            label[i-1][j-1] = Count++;
                        }    
                        else if(!matrix[i][j-1])
                            label[i-1][j-1] = label[i-2][j-1];
                        else if(!matrix[i-1][j])
                            label[i-1][j-1] = label[i-1][j-2];
                        else{
                            if(label[i-2][j-1] < label[i-1][j-2]){
                                label[i-1][j-1] = label[i-2][j-1];
                                Special[2*Count2] = label[i-1][j-2]; 
                                Special[2*Count2+1] = label[i-2][j-1]; Count2++;
                            }
                            else if(label[i-1][j-2] < label[i-2][j-1]){
                                label[i-1][j-1] = label[i-1][j-2];
                                Special[2*Count2] = label[i-2][j-1]; 
                                Special[2*Count2+1] = label[i-1][j-2]; Count2++;
                            }
                            else
                                label[i-1][j-1] = label[i-1][j-2];
                        } 
                    }
                }
            }
            QuickFindUF GO = new QuickFindUF(Count);
            for(int i=0; i<Count2; i++){
                GO.union(Special[2*i],Special[2*i+1]);
            }
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    label[i][j] = GO.find(label[i][j]);                    
                }
            }
            System.out.println(label[target[0]-1][target[1]-1]);
        }                 
    }
}

